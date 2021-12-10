package com.example.demo.response;

import com.example.demo.shapes.Point;
import org.springframework.stereotype.Component;

@Component
public class ResponseObject {
    public String name;
    private String color;
    private double x1,y1,x2,y2,x3,y3;

    public ResponseObject() {}

    public ResponseObject(String name, String color, double x1, double y1, double x2, double y2, double x3, double y3) {
        this.name = name;
        this.color = color;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }
}
