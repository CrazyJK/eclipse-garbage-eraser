package jk.kamoru.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EclipseGarbageEraserApplication {

	protected final Log logger = LogFactory.getLog(getClass());

	public static void main(String[] args) {
		SpringApplication.run(EclipseGarbageEraserApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			logger.info(" ");
			if (args == null || args.length < 1) {
				logger.warn("  missing parameter. folder path is required");
			}
			else {
				EclipseGarbageEraser eraser = new EclipseGarbageEraser(args);
				eraser.start();
			}
			logger.info(" ");

		};
	}

}
