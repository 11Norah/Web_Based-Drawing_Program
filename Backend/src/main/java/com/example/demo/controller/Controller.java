package com.example.demo.controller;

import com.example.demo.factory.ObjectFactoryService;
import com.example.demo.services.ShapeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.shapes.*;

@CrossOrigin
@RestController
public class Controller {

    @Autowired
    ShapeService serve;
    @Autowired
    ObjectFactoryService factory;
    @Autowired


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
        else{
            //do Nothing
        }
    }

}
