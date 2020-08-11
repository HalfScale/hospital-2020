package hospital.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.filter.DelegatingFilterProxy;

public class WebAppInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		servletContext.addFilter("myWebFilter", new DelegatingFilterProxy("myWebFilter"))
        .addMappingForUrlPatterns(null, true, "/*");
	}

}
