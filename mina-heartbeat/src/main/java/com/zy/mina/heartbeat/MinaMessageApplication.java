package com.zy.mina.heartbeat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(scanBasePackages = {"com.zy.mina.message"})
public class MinaMessageApplication {
	
	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(MinaMessageApplication.class, args);
		System.out.println(ctx.getId() + " started!");
	}
	
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(MinaMessageApplication.class);
	}

}
