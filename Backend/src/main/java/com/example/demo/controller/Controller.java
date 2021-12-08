package com.example.demo.controller;

import com.example.demo.factory.ObjectFactoryService;
import com.example.demo.services.ShapeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;


@CrossOrigin
@RestController
public class Controller {

    @Autowired
    ShapeService serve;
    @Autowired
    ObjectFactoryService factory;

    @PostMapping
    public void integration(@RequestBody String name,@RequestBody String color, @RequestBody Point first, @RequestBody Point second, @RequestBody Point third) {
        serve.addToDP(factory.getObject(name,color,first,second,third));

    }

}
