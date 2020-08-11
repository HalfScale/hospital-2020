package hospital.misc;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:file-upload-config.properties")
public class MyFileUploader {
	
	@Autowired
	private Environment env;
	
	private String rootPath;
	
	@PostConstruct
	public void init() {
		rootPath = env.getProperty("file.root.dir");
	}

	public String getRootPath() {
		return rootPath;
	}

	public void setRootPath(String rootPath) {
		this.rootPath = rootPath;
	}
	
}
