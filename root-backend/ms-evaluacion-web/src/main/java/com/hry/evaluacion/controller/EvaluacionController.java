package com.hry.evaluacion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hry.core.common.wrapper.Response;
import com.hry.core.domain.EvaluacionDTO;
import com.hry.core.domain.EvaluacionRequest;
import com.hry.evaluacion.service.EvaluacionService;
import com.hry.persistence.mongo.document.EvaluacionModel;

@RestController
@RequestMapping("/evaluacion")
public class EvaluacionController {
	
	@Autowired
	EvaluacionService evaluacionService;
	
	@GetMapping(value = "/",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String ping(){
		return "web controller is running..";
	}
	
	@PostMapping(value = "/creacion",consumes=MediaType.APPLICATION_JSON_UTF8_VALUE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Response<Void> crearEvaluacion(@RequestBody EvaluacionDTO evaluacion) throws Exception{
		return evaluacionService.crearEvaluacion(evaluacion);
	}
	
	@GetMapping(value = "/lista", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Response<List<EvaluacionModel>> obtenerEvaluaciones(){
		return evaluacionService.obtenerEvaluaciones();
	}
	
	@PutMapping(value = "/modificacion",consumes=MediaType.APPLICATION_JSON_UTF8_VALUE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Response<Void> modificarEvaluacion(@RequestBody EvaluacionDTO evaluacion){
		return evaluacionService.modificarEvaluacion(evaluacion);
	}
	
	@PostMapping(value = "/lista/fecha", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Response<List<EvaluacionModel>> obtenerEvaluacionesPorFecha(@RequestBody EvaluacionRequest request){
		return evaluacionService.obtenerEvaluacionesPorFecha(request.getFechaDesde(),request.getFechaHasta());
	}
}
