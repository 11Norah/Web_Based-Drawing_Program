package com.example.demo.controller;

import com.example.demo.Model.*;
import com.example.demo.factory.ObjectFactoryService;
import com.example.demo.response.ResponseObject;
import com.example.demo.services.ShapeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.example.demo.shapes.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
public class Controller {

    @Autowired
    ShapeService serve;

    @Autowired
    ObjectFactoryService factory;

    private int index;

    @PostMapping("/add")
    public HttpStatus integration(@RequestBody ResponseObject req) {
        serve.add(req);
        return HttpStatus.OK;
    }

    @GetMapping("/select/{x}/{y}")
    public String select(@PathVariable("x") double x, @PathVariable("y") double y) {
        Point click = new Point(x, y);
        this.index = serve.checkCoordinate(click);
        if (this.index != -1) {
            return serve.getdrawns().get(this.index).getName();
        }
        System.out.print("NO");
        return "null";
    }

    @GetMapping("/move")
    public List<ResponseObject> move(@RequestParam double x, @RequestParam double y) {
        System.out.println("MOVE");
        Point moveTo = new Point(x, y);
        serve.move(this.index, moveTo);
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
    public ResponsesList load(@RequestParam String filePath, @RequestParam String fileType) {
        return serve.load(filePath, fileType);
    }

    @PostMapping("/save")
    public HttpStatus save(@RequestBody FileInfo file) {
        if(serve.save(file.getFilePath(), file.getFileType())) {
            return HttpStatus.OK;
        }
        else {
            return HttpStatus.NOT_ACCEPTABLE;
        }
    }

}
