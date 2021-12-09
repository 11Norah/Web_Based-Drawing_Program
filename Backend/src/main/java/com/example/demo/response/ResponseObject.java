package com.example.demo.response;

import com.example.demo.shapes.Point;
import org.springframework.stereotype.Component;

@Component
public class ResponseObject {
    private String name;
    private String color;
    private Point p1,p2,p3;

    public ResponseObject() {}

    public ResponseObject(String name, String color, Point p1, Point p2, Point p3) {
        this.name = name;
        this.color = color;
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }
}
