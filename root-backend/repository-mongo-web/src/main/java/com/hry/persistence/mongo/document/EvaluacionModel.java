package com.hry.persistence.mongo.document;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.experimental.Builder;

@Builder
@Data
@Document(collection = "evaluaciones")
public class EvaluacionModel {
	public static final String SEQUENCE_NAME = "evaluaciones_sequencia"; 
	
	@Id
	private Long id;
	private String nombres;
	private String correo;
	private String telefono;
	private String comentario;
	private String fecha;
	private Integer puntuacion;
	private List<ComentarioModel> comentarios;
}
