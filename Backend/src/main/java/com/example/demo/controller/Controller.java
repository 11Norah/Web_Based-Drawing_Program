package com.example.demo.controller;

import com.example.demo.factory.ObjectFactoryService;
import com.example.demo.services.ShapeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.shapes.*;

@CrossOrigin
@RestController
public class Controller {

    @Autowired
    ShapeService serve;

    @Autowired
    ObjectFactoryService factory;

    @PostMapping("/add")
    public void integration(@RequestBody String name,@RequestBody String color, @RequestBody Point first, @RequestBody Point second, @RequestBody Point third) {
        serve.add(factory.getObject(name,color,first,second,third));
        serve.addResponse(name,color,first,second,third);
    }
    @PostMapping("/move")
    public void move(@RequestBody Point click) {
        int index = serve.checkCoordinate(click);
        if( index != -1){
            serve.move(index,click);
            //move is done, return the new response array
        }
        //do Nothing

    }
    @PostMapping("/copy")
    public void copy(@RequestBody Point click) {
        int index = serve.checkCoordinate(click);
        if( index != -1){
            serve.copy(index,click);
            //copy is done, return the new response array
        }
        //do Nothing

    }
    @PostMapping("/delete")
    public void delete(@RequestBody Point click) {
        int index = serve.checkCoordinate(click);
        if( index != -1){
            serve.delete(index);
            //delete is done, return the new response array
        }
        //do Nothing

    }
    @PostMapping("/resize")
    public void resize(@RequestBody Point click,@RequestBody Point p1,@RequestBody Point p2) {
        int index = serve.checkCoordinate(click);
        if( index != -1){
            serve.resize(index,p1,p2);
            //delete is done, return the new response array
        }
        //do Nothing

    }

}
