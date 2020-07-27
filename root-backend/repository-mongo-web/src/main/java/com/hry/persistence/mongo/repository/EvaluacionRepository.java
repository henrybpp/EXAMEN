package com.hry.persistence.mongo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.hry.persistence.mongo.document.EvaluacionModel;

public interface EvaluacionRepository extends MongoRepository<EvaluacionModel,Long>{
	
	@Query("{'fecha' : {$gt : ?0, $lt : ?1}}")
    List<EvaluacionModel> obtenerEvaluacionesXfecha(Date startTime, Date endTime);
	
	@Query("{'correo' : ?0}")
	EvaluacionModel buscarPorCorreo(String correo);
}
