package com.example.demo.controller;

import com.example.demo.Model.DrawnShapes;
import com.example.demo.Model.DrawnShapesI;
import com.example.demo.factory.ObjectFactoryService;
import com.example.demo.response.ResponseObject;
import com.example.demo.services.ShapeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.shapes.*;

import java.util.List;

@CrossOrigin
@RestController
public class Controller {

    @Autowired
    ShapeService serve;

    @Autowired
    ObjectFactoryService factory;


    @PostMapping("/add")
    public void integration(@RequestBody String name, @RequestBody String color, @RequestBody double x1, @RequestBody double y1,@RequestBody double x2, @RequestBody double y2,@RequestBody double x3, @RequestBody double y3) {
        Point first = new Point(x1,y1);
        Point second = new Point(x2,y2);
        Point third = new Point(x3,y3);
        serve.add(factory.getObject(name, color, first, second, third));
        serve.addResponse(name, color, first, second, third);
    }


    @PostMapping("/move")
    public List<ResponseObject> move(@RequestBody double x1, @RequestBody double y1,@RequestBody double x2, @RequestBody double y2) {
        Point click = new Point(x1,y1);
        Point moveTo = new Point(x2,y2);
        int index = serve.checkCoordinate(click);
        if (index != -1) {
            serve.move(index, moveTo);
            return serve.getDrawnShapes().getResponses();
        }
        return null;
    }

    @PostMapping("/copy")
    public List<ResponseObject> copy(@RequestBody double x1, @RequestBody double y1,@RequestBody double x2, @RequestBody double y2) {
        Point click = new Point(x1,y1);
        Point copyTo = new Point(x2,y2);
        int index = serve.checkCoordinate(click);
        if (index != -1) {
            serve.copy(index, copyTo);
            return serve.getDrawnShapes().getResponses();
        }
        return null;
    }

    @PostMapping("/delete")
    public List<ResponseObject> delete(@RequestBody double x1, @RequestBody double y1) {
        Point click = new Point(x1,y1);
        int index = serve.checkCoordinate(click);
        if (index != -1) {
            serve.delete(index);
            return serve.getDrawnShapes().getResponses();
        }
        return null;
    }

    @PostMapping("/resize")
    public List<ResponseObject> resize(@RequestBody double x1, @RequestBody double y1,@RequestBody double x2, @RequestBody double y2,@RequestBody double x3, @RequestBody double y3) {
        Point click = new Point(x1,y1);
        Point p1 = new Point(x2,y2);
        Point p2 = new Point(x3,y3);
        int index = serve.checkCoordinate(click);
        if (index != -1) {
            serve.resize(index, p1, p2);
            return serve.getDrawnShapes().getResponses();
        }
        return null;
    }
    @PostMapping("/clear")
    public void clear() {
        serve.clear();
    }

}
