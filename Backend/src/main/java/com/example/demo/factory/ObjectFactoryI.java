package com.example.demo.factory;

import com.example.demo.shapes.*;


public interface ObjectFactoryI {
    Shape getObject(String name,String color, Point first, Point second, Point third);


}
