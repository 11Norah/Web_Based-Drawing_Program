package com.example.demo.controller;

import com.example.demo.factory.ObjectFactoryService;
import com.example.demo.services.ShapeService;
import com.example.demo.Model.DrawnShapesI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.*;


@CrossOrigin
@RestController
public class Controller {

    @Autowired
    ShapeService service;
    @Autowired
    ObjectFactoryService factory;

    @PostMapping("/newShape")
    public void add(@RequestBody String name,@RequestBody String color, @RequestBody Point first, @RequestBody Point second, @RequestBody Point third) {
        service.add(factory.getObject(name,color,first,second,third));
    }
    @PostMapping("/save")
    public void save(@RequestBody String name) {
        service.save(name);
    }

//    @GetMapping("/load/{fileName}")
//    public Object[] load(@PathVariable("fileName") String fileName) {
//
//    }
}
