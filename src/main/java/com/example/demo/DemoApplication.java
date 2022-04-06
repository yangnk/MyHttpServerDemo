package com.example.demo;

import com.example.demo.httpServerV2.server.MyServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		new Thread(new MyServer()).start();
		SpringApplication.run(DemoApplication.class, args);
	}

}
