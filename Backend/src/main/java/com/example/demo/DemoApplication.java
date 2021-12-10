package com.example.demo;

import com.example.demo.Model.DrawnShapes;
import com.example.demo.Model.DrawnShapesI;
import com.example.demo.shapes.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		System.out.println("HELLO THERE!");
		ObjectMapper mapper = new ObjectMapper();

		Rectangle rectangle = new Rectangle(new Point(10, 20), new Point(40, 70), "blue");
		Circle circle = new Circle(new Point(30, 30), new Point(20, 40), "yellow");

		DrawnShapes drawnShapes = new DrawnShapes();

		drawnShapes.loadDrawnShapes("E://Paint/Backend/TEST");
		drawnShapes.saveDrawnShapes("E://Paint/Backend/load");
		drawnShapes.loadDrawnShapes("E://Paint/Backend/load");
	}

}
