package org.sindifisco.portal.api.config.cors;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {
	
	@Autowired
	private SindifiscoApiProperty sindifiscoApiProperty;

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		response.setHeader("Access-Control-Allow-Origin", sindifiscoApiProperty.getOriginPermitida());
        response.setHeader("Access-Control-Allow-Credentials", "true");
		
		if ("OPTIONS".equals(request.getMethod()) && sindifiscoApiProperty.getOriginPermitida().equals(request.getHeader("Origin"))) {
			response.setHeader("Access-Control-Allow-Methods", "POST, GET, DELETE, PUT, OPTIONS");
        	response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, Accept");
        	response.setHeader("Access-Control-Max-Age", "3600");
		} else {
			chain.doFilter(req, res);
		}
		
	}

}
