package com.hry.core.domain;

import lombok.Data;
import lombok.experimental.Builder;

@Builder
@Data
public class EvaluacionRequest {
	String fechaDesde;
	String fechaHasta;
}
