package com.example.demo;

import com.example.demo.Model.DrawnShapes;
import com.example.demo.Model.DrawnShapesI;
import com.example.demo.shapes.Rectangle;
import com.example.demo.shapes.Line;
import com.example.demo.shapes.ShapeI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.*;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
