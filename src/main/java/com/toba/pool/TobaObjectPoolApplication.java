package com.toba.pool;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;

@Slf4j
@SpringBootApplication
public class TobaObjectPoolApplication implements CommandLineRunner {


	public static void main(String[] args) {
		//SpringApplication.run(TobaObjectPoolApplication.class, args);
		SpringApplication app = new SpringApplication(TobaObjectPoolApplication.class);
		//app.run(TobaObjectPoolApplication.class, args);
		app.addListeners(new ApplicationPidFileWriter()); // ApplicationPidFileWriter 설정
		app.run(args);
	}

	@Override
	public void run(String... args) throws Exception {
		while(true) {
			log.info("i'm living....");
			Thread.sleep(14000);
		}
	}
}
