package com.hry.evaluacion.service;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.hry.core.common.base.BaseService;
import com.hry.core.common.util.Constantes;
import com.hry.core.common.util.Util;
import com.hry.core.common.wrapper.Response;
import com.hry.core.domain.EvaluacionDTO;
import com.hry.persistence.mongo.document.ComentarioModel;
import com.hry.persistence.mongo.document.EvaluacionModel;
import com.hry.persistence.mongo.repository.EvaluacionRepository;

@Service
public class EvaluacionServiceImpl extends BaseService implements EvaluacionService{
	private static final Logger logger = LogManager.getLogger(EvaluacionServiceImpl.class);

	@Autowired
	EvaluacionRepository evaluacionRepository;
	
	@Autowired
	SequenceGeneratorService seqGeneratorService;
	
	@Autowired
    MongoOperations mongoOpt;

	@Override
	public Response<Void> crearEvaluacion(EvaluacionDTO evaluacionDTO) throws ParseException {
		logger.info("");	
		Response<Void> response=getResponse();		
		try {
			if(!StringUtils.isEmpty(buscarPorCorreo(evaluacionDTO.getCorreo()))) {
				response.setStatusText(Constantes.STATUS_TEXT_ERROR);
				response.setStatusCode(Constantes.VAL_YA_EXISTE_EVALUACION);
				response.setMessage("validación en la creación...");
				return response;
			}
			
			List<ComentarioModel> comentarios =new ArrayList<>();
			evaluacionDTO.getComentarios().forEach(x->{
				ComentarioModel comentarioModel=new ComentarioModel();
				comentarioModel.setComentario(x.getComentario());
				comentarios.add(comentarioModel);
			});	

			EvaluacionModel evaluacion=EvaluacionModel.builder()
				.comentario(evaluacionDTO.getComentario())
				.comentarios(comentarios)
				.correo(evaluacionDTO.getCorreo())
				.fecha(Util.obtenerFechaString())
				.id(seqGeneratorService.generateSequence(EvaluacionModel.SEQUENCE_NAME))
				.nombres(evaluacionDTO.getNombres())
				.puntuacion(evaluacionDTO.getPuntuacion())
				.telefono(evaluacionDTO.getTelefono())
				.build();			
			evaluacionRepository.save(evaluacion);
		}catch(Exception e) {
			response.setStatusText(Constantes.STATUS_TEXT_ERROR);
			response.setStatusCode(Constantes.ERR_GENERICO);
			response.setMessage(e.getMessage());
		}	
		return response;
	}

	@Override
	public Response<List<EvaluacionModel>> obtenerEvaluaciones() {
		logger.info("inicio obtenerEvaluaciones... ");
		Response<List<EvaluacionModel>> response=getResponse();
		try {
			response.setObjetoRespuesta(evaluacionRepository.findAll());
		}catch(Exception e) {
			response.setStatusText(Constantes.STATUS_TEXT_ERROR);
			response.setStatusCode(Constantes.ERR_GENERICO);
			response.setMessage(e.getMessage());
		}
		return response;
	}

	@Override
	public Response<Void> modificarEvaluacion(EvaluacionDTO evaluacionDTO) {
		logger.info("inicio actualizarEvaluacion...");
		Response<Void> response=getResponse();
		try {
			//EvaluacionModel evaluacionModel=buscarPorCorreo(evaluacionDTO.getCorreo());
			if(StringUtils.isEmpty(buscarPorCorreo(evaluacionDTO.getCorreo()))) {	
				response.setStatusText(Constantes.STATUS_TEXT_ERROR);
				response.setStatusCode(Constantes.ERR_NO_EXISTE_EVALUACION);
				return response;
			}
			
			List<ComentarioModel> comentarios =new ArrayList<>();
			evaluacionDTO.getComentarios().forEach(x->{
				ComentarioModel comentarioModel=new ComentarioModel();
				comentarioModel.setComentario(x.getComentario());
				comentarios.add(comentarioModel);
			});	
			
			EvaluacionModel evaluacionModel=EvaluacionModel.builder()
				.comentario(evaluacionDTO.getComentario())
				.comentarios(comentarios)
				.correo(evaluacionDTO.getCorreo())
				.fecha(evaluacionDTO.getFecha())
				.id(evaluacionDTO.getId())
				.nombres(evaluacionDTO.getNombres())
				.puntuacion(evaluacionDTO.getPuntuacion())
				.telefono(evaluacionDTO.getTelefono())
				.build();
			evaluacionRepository.save(evaluacionModel);
		}catch(Exception e) {
			response.setStatusText(Constantes.STATUS_TEXT_ERROR);
			response.setStatusCode(Constantes.ERR_GENERICO);
			response.setMessage(e.getMessage());
		}
		return response;
	}

	@Override
	public Response<List<EvaluacionModel>> obtenerEvaluacionesPorFecha(String fechaDesde, String fechaHasta) {
		logger.info("init get evaluation by date");
		Response<List<EvaluacionModel>> response=getResponse();
		
		try {
			LocalDate dtFechadesde=Util.generaFecha(fechaDesde);
			LocalDate dtFechaHasta=Util.generaFecha(fechaHasta);		

			List<EvaluacionModel> lista=new ArrayList<>();
			evaluacionRepository.findAll().forEach(x->{
				if(Util.fechaOk(dtFechadesde,dtFechaHasta,Util.generaFecha(x.getFecha()))) {
					lista.add(x);
				}  
			});		
			response.setObjetoRespuesta(lista);
		}catch(Exception e) {
			response.setStatusText(Constantes.STATUS_TEXT_ERROR);
			response.setStatusCode(Constantes.ERR_GENERICO);
			response.setMessage(e.getMessage());
		}
		return response;
	}
	
	private EvaluacionModel buscarPorCorreo(String correo) {
		EvaluacionModel evaluacionModel=null;
		try {
			evaluacionModel=evaluacionRepository.buscarPorCorreo(correo);
		}catch(Exception e) {
			throw new RuntimeException("error al obtener evaluacion");
		}
		return evaluacionModel;
	}
}
