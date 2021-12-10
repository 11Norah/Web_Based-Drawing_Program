package com.example.demo.controller;

import com.example.demo.Model.DrawnShapes;
import com.example.demo.Model.DrawnShapesI;
import com.example.demo.factory.ObjectFactoryService;
import com.example.demo.response.ResponseObject;
import com.example.demo.services.ShapeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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


    @GetMapping("/add")
    public void integration(@RequestParam String name, @RequestParam String color, @RequestParam double x1, @RequestParam double y1, @RequestParam double x2, @RequestParam double y2, @RequestParam double x3, @RequestParam double y3) {
        Point first = new Point(x1, y1);
        Point second = new Point(x2, y2);
        Point third = new Point(x3, y3);
        serve.add(factory.getObject(name, color, first, second, third));
        serve.addResponse(name, color, x1, y1, x2, y2, x3, y3);
    }


    @GetMapping("/move")
    public List<ResponseObject> move(@RequestParam double x1, @RequestParam double y1, @RequestParam double x2, @RequestParam double y2) {
        Point click = new Point(x1, y1);
        Point moveTo = new Point(x2, y2);
        int index = serve.checkCoordinate(click);
        if (index != -1) {
            serve.move(index, moveTo);
            return serve.getDrawnShapes().getResponses();
        }
        return null;
    }

    @GetMapping("/copy")
    public List<ResponseObject> copy(@RequestParam double x1, @RequestParam double y1, @RequestParam double x2, @RequestParam double y2) {
        Point click = new Point(x1, y1);
        Point copyTo = new Point(x2, y2);
        int index = serve.checkCoordinate(click);
        if (index != -1) {
            serve.copy(index, copyTo);
            return serve.getDrawnShapes().getResponses();
        }
        return null;
    }

    @GetMapping("/delete")
    public List<ResponseObject> delete(@RequestParam double x1, @RequestParam double y1) {
        Point click = new Point(x1, y1);
        int index = serve.checkCoordinate(click);
        if (index != -1) {
            serve.delete(index);
            return serve.getDrawnShapes().getResponses();
        }
        return null;
    }

    @GetMapping("/resize")
    public List<ResponseObject> resize(@RequestParam double x1, @RequestParam double y1, @RequestParam double x2, @RequestParam double y2, @RequestParam double x3, @RequestParam double y3) {
        Point click = new Point(x1, y1);
        Point p1 = new Point(x2, y2);
        Point p2 = new Point(x3, y3);
        int index = serve.checkCoordinate(click);
        if (index != -1) {
            serve.resize(index, p1, p2);
            return serve.getDrawnShapes().getResponses();
        }
        return null;
    }

    @GetMapping("/clear")
    public void clear() {
        serve.clear();
    }

}
