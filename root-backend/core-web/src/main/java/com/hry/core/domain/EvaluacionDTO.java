package com.hry.core.domain;

import java.util.List;

import lombok.Data;
import lombok.experimental.Builder;

@Builder
@Data
public class EvaluacionDTO {
	Long id;
	String nombres;
	String correo;
	String telefono;
	String comentario;
	String fecha;
	Integer puntuacion;
	private List<ComentarioDTO> comentarios;
}
