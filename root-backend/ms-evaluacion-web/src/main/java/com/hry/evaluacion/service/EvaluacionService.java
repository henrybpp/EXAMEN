package com.hry.evaluacion.service;

import java.text.ParseException;
import java.util.List;

import com.hry.core.common.wrapper.Response;
import com.hry.core.domain.EvaluacionDTO;
import com.hry.persistence.mongo.document.EvaluacionModel;

public interface EvaluacionService {
	Response<Void> crearEvaluacion(EvaluacionDTO evaluacion) throws ParseException;
	Response<Void> modificarEvaluacion(EvaluacionDTO evaluacion);
	Response<List<EvaluacionModel>> obtenerEvaluaciones();
	Response<List<EvaluacionModel>> obtenerEvaluacionesPorFecha(String desde, String hasta);
}
