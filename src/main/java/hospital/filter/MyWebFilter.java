package hospital.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

public class MyWebFilter extends GenericFilterBean {

	private String rootPath;
	
	public MyWebFilter(String path) {
		this.rootPath = path;
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
//		System.out.println("WebFilter rootPath ->" + );
		request.setAttribute("file_path", rootPath);
		chain.doFilter(request, response);
	}

}
