package com.example.demo.controller;


import com.example.demo.factory.ObjectFactoryService;
import com.example.demo.response.ResponseObject;
import com.example.demo.services.ShapeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.example.demo.shapes.*;

import java.util.List;

@CrossOrigin
@RestController
public class Controller {

    @Autowired
    ShapeService serve;

    @Autowired
    ObjectFactoryService factory;

    private int index;

    @RequestMapping("/add")
    public void integration(@RequestParam String name, @RequestParam String color, @RequestParam double x1, @RequestParam double y1, @RequestParam double x2, @RequestParam double y2, @RequestParam double x3, @RequestParam double y3) {
        System.out.println("Ana fe add"+x1 +" "+ y1 +x2 +" "+ y2+x3 +" "+ y3 );
        Point first = new Point(x1, y1);
        Point second = new Point(x2, y2);
        Point third = new Point(x3, y3);
        serve.add(factory.getObject(name, color, first, second, third));
        serve.addResponse(name, color, x1, y1, x2, y2, x3, y3);
    }

    @GetMapping("/select")
    public String select(@RequestParam double x, @RequestParam double y) {
        Point click = new Point(x, y);
        this.index = serve.checkCoordinate(click);
        System.out.println(index);
        System.out.println(x+" "+y);
        for (int i =0;i<serve.getdrawns().size();i++){
            System.out.println(serve.getdrawns().get(i).getName());
        }
        if (this.index != -1) {
            System.out.println("YEEEEES");
            return serve.getdrawns().get(this.index).getName();
        }
        System.out.println("NOOOOOOOO");
        return null;
    }

    @GetMapping("/move")
    public List<ResponseObject> move(@RequestParam double x, @RequestParam double y) {
        System.out.println(x+" "+y);
        Point moveTo = new Point(x, y);
        serve.move(this.index, moveTo);
        System.out.println(serve.getdrawns().get(serve.getdrawns().size()-1).getPoints()[0]);
        return serve.getDrawnShapes().getResponses();
    }

    @GetMapping("/copy")
    public List<ResponseObject> copy(@RequestParam double x, @RequestParam double y) {
        Point copyTo = new Point(x, y);
        serve.copy(this.index, copyTo);
        return serve.getDrawnShapes().getResponses();
    }

    @GetMapping("/delete")
    public List<ResponseObject> delete() {
        serve.delete(this.index);
        return serve.getDrawnShapes().getResponses();
    }

    @GetMapping("/resize")
    public List<ResponseObject> resize(@RequestParam double x1, @RequestParam double y1, @RequestParam double x2, @RequestParam double y2) {
        Point p1 = new Point(x1, y1);
        Point p2 = new Point(x2, y2);
        serve.resize(this.index, p1, p2);
        return serve.getDrawnShapes().getResponses();
    }

    @GetMapping("/clear")
    public void clear() {
        serve.clear();
    }

    @GetMapping("/undo")
    public List<ResponseObject> undo() {
        return serve.undo();
    }

    @GetMapping("/redo")
    public List<ResponseObject> redo() {
        return serve.redo();
    }

    @GetMapping("/load")
    public List<ResponseObject> load(@RequestParam String filePath, @RequestParam String fileType) {
        return serve.load(filePath, fileType);
    }

    @GetMapping("/save")
    public HttpStatus save(@RequestParam String filePath, @RequestParam String fileType) {
        if(serve.save(filePath, fileType)) {
            return HttpStatus.OK;
        }
        else {
            return HttpStatus.NOT_ACCEPTABLE;
        }
    }

}
