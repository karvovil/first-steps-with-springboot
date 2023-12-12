package com.example.demo.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {
  
  @Bean
  CommandLineRunner commandLineRunner (StudentRepository repository) {
    return args -> {
			Student make = new Student (
				"Make",
				"lol@lol",
				LocalDate.of(2006, Month.APRIL, 5)
			);
      Student kake = new Student (
				"Kake",
				"lol23232323@lol",
				LocalDate.of(2002, Month.APRIL, 5)
      );
      repository.saveAll(List.of(make,kake));
    };
  }
}
