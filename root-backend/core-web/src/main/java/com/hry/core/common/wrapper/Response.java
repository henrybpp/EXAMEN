package com.hry.core.common.wrapper;

import javax.annotation.PostConstruct;

import com.hry.core.common.util.Constantes;

import lombok.Data;

@Data
public class Response<T> {
	Integer status;
	String statusText;
	String statusCode;
	String message;
	String path;
	
	@PostConstruct
	public void initIt(){
		this.statusText=Constantes.STATUS_TEXT_OK;
		this.status=200;
	}
	
	private T objetoRespuesta;	
}
