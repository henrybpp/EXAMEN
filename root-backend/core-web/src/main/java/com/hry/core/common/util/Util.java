package com.hry.core.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class Util {
	private Util(){} 
	
	public static LocalDate generaFecha(String fecha){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constantes.FORMATO_FECHA_LARGA);
		return LocalDate.parse(fecha, formatter);
	}
	
	public static String obtenerFechaString() {
		Locale local = new Locale("es-PE");
		// DateFormat df = new SimpleDateFormat(Constantes.FORMATO_FECHA_LARGA);
		DateFormat df = new SimpleDateFormat(Constantes.FORMATO_FECHA_LARGA, local);
		Date timeDate = Calendar.getInstance().getTime();
		return df.format(timeDate);
	}
	
	public static Date obtenerFechaDate() throws ParseException {
		Locale local = new Locale("es-PE");
		DateFormat df = new SimpleDateFormat(Constantes.FORMATO_FECHA_LARGA, local);
		return df.parse(Calendar.getInstance().getTime().toString());	
	}
	
	public static boolean fechaOk(LocalDate fechaDesde,LocalDate fechaHasta,LocalDate fechaData){
		boolean ok=false;
		if(fechaData.equals(fechaDesde) || fechaData.equals(fechaHasta)){ 
			ok=true;
		}
		if(fechaData.isBefore(fechaHasta) && fechaData.isAfter(fechaDesde)){ 
			ok=true;
		}
		return ok;
	}
	
	public static String objectToJson(Object o) {
		String jsonInString =null;
		try {
			ObjectMapper mapperObj = new ObjectMapper();
			mapperObj.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);			
			jsonInString = mapperObj.writeValueAsString(o);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return jsonInString;
	}
}
