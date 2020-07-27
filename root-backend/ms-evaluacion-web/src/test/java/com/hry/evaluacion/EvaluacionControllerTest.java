package com.hry.evaluacion;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.hry.core.common.util.Util;
import com.hry.core.common.wrapper.Response;
import com.hry.core.domain.EvaluacionDTO;
import com.hry.core.domain.EvaluacionRequest;
import com.hry.evaluacion.controller.EvaluacionController;
import com.hry.evaluacion.service.EvaluacionService;
import com.hry.persistence.mongo.document.EvaluacionModel;
import com.hry.persistence.mongo.repository.EvaluacionRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = EvaluacionController.class)
public class EvaluacionControllerTest {

	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
    private EvaluacionService evaluacionService;

    @MockBean
    private EvaluacionRepository evaluacionRepository;

	@Before
	public void setUp() {
		
	}
	
	@Test
    public void testPing() throws Exception {
        mockMvc.perform(get("/evaluacion/"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }
	
	
    @Test
	public void testCrearEvaluacion() throws Exception {
    	EvaluacionDTO evaluacion=EvaluacionDTO.builder()
    			.comentario("comentario 1")
    			.correo("test1@gmail.com")
    			.fecha(Util.obtenerFechaString())
    			.nombres("hpalacios")
    			.puntuacion(10)
    			.build();
    	
	   given(evaluacionService.crearEvaluacion(evaluacion))
	   	.willReturn(new Response<Void>());

	   String json = Util.objectToJson(evaluacion);
       mockMvc.perform(
               post("/evaluacion/creacion")
               .contentType(MediaType.APPLICATION_JSON_VALUE)
               .content(json))
               .andDo(print())
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
       ;
	}
    
    @Test
   	public void testModificarEvaluacion() throws Exception {
       	EvaluacionDTO evaluacion=EvaluacionDTO.builder()
       			.comentario("comentario 1")
       			.correo("test1@gmail.com")
       			.fecha(Util.obtenerFechaString())
       			.id(1L)
       			.nombres("hpalacios")
       			.puntuacion(10)
       			.build();
       	
   	   given(evaluacionService.modificarEvaluacion(evaluacion))
   	   	.willReturn(new Response<Void>());

   	   String json = Util.objectToJson(evaluacion);
          mockMvc.perform(
                  put("/evaluacion/modificacion")
                  .contentType(MediaType.APPLICATION_JSON_VALUE)
                  .content(json))
                  .andDo(print())
                  .andExpect(status().isOk())
                  .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
          ;
   	}
    
    @Test
   	public void testObtenerEvaluaciones() throws Exception {       	
   	   given(evaluacionService.obtenerEvaluaciones())
   	   	.willReturn(new Response<List<EvaluacionModel>>());

          mockMvc.perform(
                  get("/evaluacion/lista")
                  .contentType(MediaType.APPLICATION_JSON_VALUE))
                  .andDo(print())
                  .andExpect(status().isOk())
                  .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
          ;
   	}
    
    @Test
   	public void testObtenerEvaluacionesPorFecha() throws Exception {     	
       	EvaluacionRequest request=EvaluacionRequest.builder()
       		.fechaDesde("21/07/2020 02:29:53")
       		.fechaHasta("24/07/2020 02:29:53")
       		.build();
       	      	
   	   given(evaluacionService.obtenerEvaluacionesPorFecha(request.getFechaDesde(), request.getFechaHasta()))
   	   	.willReturn(new Response<List<EvaluacionModel>>());

   	   String json = Util.objectToJson(request);
          mockMvc.perform(
                  post("/evaluacion/lista/fecha")
                  .contentType(MediaType.APPLICATION_JSON_VALUE)
                  .content(json))
                  .andDo(print())
                  .andExpect(status().isOk())
                  .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
          ;
   	}
}
