package com.example.demo.factory;

import com.example.demo.response.*;
import com.example.demo.shapes.*;

public class ObjectFactoryService implements ObjectFactoryI{

    @Override
    public ResponseObjectI getObject(ResponseObjectI response) {
        String name = response.getName();
        switch (name){
            case "rectangle":
                Rectangle rect = (Rectangle) response;
                rect.evalWidth();
                rect.evalLength();
                return rect;
            case "square":
                Square sq = (Square) response;
                sq.evalLength();
                return sq;
            case "triangle":
                Triangle tri = (Triangle) response;
                return tri;
            case "ellipse":
                Ellipse ell = (Ellipse) response;
                return ell;
            case "line":
                Line l = (Line) response;
                return l;
            case "circle":
                Circle cricle = (Circle) response;
                return cricle;
            default:
                throw new NullPointerException();
        }
    }
}
