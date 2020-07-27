package com.hry.evaluacion.config;

import javax.servlet.Filter;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.hry.core.common.base.BaseCORSfilter;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CORSFilter extends BaseCORSfilter implements Filter {

}