package com.example.demo.services.undoServices;

import com.example.demo.factory.ObjectFactoryService;
import com.example.demo.response.ResponseObject;
import com.example.demo.services.ShapeService;
import com.example.demo.shapes.Point;
import com.example.demo.shapes.Shape;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UndoRedoServices {
    @Autowired
    ShapeService shapeServices;

    @Autowired
    ObjectFactoryService objectFactoryService;

    @Autowired
    UndoObjectBuilder undoObjectBuilder;

    public void undo(UndoObject undoObject) {
        if (undoObject.isAdd || undoObject.isCopy) {
            shapeServices.getdrawns().remove(shapeServices.getdrawns().size() - 1);
            shapeServices.getDrawnShapes().getResponses().remove(shapeServices.getdrawns().size() - 1);
        } else if (undoObject.isClear) {
            shapeServices.getDrawnShapes().setResponses(undoObject.prevResponses);
            shapeServices.getDrawnShapes().setDrawnShapes(undoObject.prevDrawns);
        } else {
            Point p1 = new Point(undoObject.prevShape.getX1(), undoObject.prevShape.getY1());
            Point p2 = new Point(undoObject.prevShape.getX2(), undoObject.prevShape.getY2());
            Point p3 = new Point(undoObject.prevShape.getX3(), undoObject.prevShape.getY3());
            if (undoObject.isDelete) {
                shapeServices.getDrawnShapes().addShape(objectFactoryService.getObject(undoObject.prevShape.name, undoObject.prevShape.getColor(), p1, p2, p3));
                shapeServices.getDrawnShapes().addResponse(undoObject.newShape);
            } else if (undoObject.isMove) {
                shapeServices.getdrawns().remove(shapeServices.getdrawns().size() - 1);
                shapeServices.getDrawnShapes().getResponses().remove(shapeServices.getdrawns().size() - 1);
                shapeServices.getDrawnShapes().addShape(objectFactoryService.getObject(undoObject.prevShape.name, undoObject.prevShape.getColor(), p1, p2, p3), undoObject.prevInd);
                shapeServices.getDrawnShapes().addResponse(undoObject.newShape, undoObject.prevInd);
            } else if (undoObject.isResize) {
                shapeServices.getdrawns().remove(undoObject.prevInd);
                shapeServices.getDrawnShapes().getResponses().remove(undoObject.prevInd);
                shapeServices.getDrawnShapes().addShape(objectFactoryService.getObject(undoObject.prevShape.name, undoObject.prevShape.getColor(), p1, p2, p3), undoObject.prevInd);
                shapeServices.getDrawnShapes().addResponse(undoObject.newShape, undoObject.prevInd);
            }
        }
    }

    public void redo(UndoObject undoObject) {
        if (undoObject.isAdd) {
            Point p1 = new Point(undoObject.prevShape.getX1(), undoObject.prevShape.getY1());
            Point p2 = new Point(undoObject.prevShape.getX2(), undoObject.prevShape.getY2());
            Point p3 = new Point(undoObject.prevShape.getX3(), undoObject.prevShape.getY3());
            Shape shape = objectFactoryService.getObject(undoObject.prevShape.name, undoObject.prevShape.getColor(), p1, p2, p3);
            shapeServices.getdrawns().add(shape);
            shapeServices.getDrawnShapes().getResponses().add(undoObject.newShape);
        } else if (undoObject.isClear) {
            shapeServices.clear();
        } else {
            Point p1 = new Point(undoObject.newShape.getX1(), undoObject.newShape.getY1());
            Point p2 = new Point(undoObject.newShape.getX2(), undoObject.newShape.getY2());
            Point p3 = new Point(undoObject.newShape.getX3(), undoObject.newShape.getY3());
            Shape shape = objectFactoryService.getObject(undoObject.newShape.name, undoObject.newShape.getColor(), p1, p2, p3);
            if (undoObject.isResize) {
                shapeServices.getdrawns().remove(undoObject.prevInd);
                shapeServices.getDrawnShapes().getResponses().remove(undoObject.prevInd);
                shapeServices.getdrawns().add(undoObject.prevInd, shape);
                shapeServices.getDrawnShapes().getResponses().add(undoObject.prevInd, undoObject.newShape);
            } else if (undoObject.isMove) {
                shapeServices.getdrawns().remove(undoObject.prevInd);
                shapeServices.getDrawnShapes().getResponses().remove(undoObject.prevInd);
                shapeServices.getdrawns().add(shape);
                shapeServices.getDrawnShapes().getResponses().add(undoObject.newShape);
            } else if (undoObject.isDelete) {
                shapeServices.getdrawns().add(undoObject.prevInd, shape);
                shapeServices.getDrawnShapes().getResponses().add(undoObject.prevInd, undoObject.newShape);
            } else if (undoObject.isCopy) {
                shapeServices.getdrawns().add(shape);
                shapeServices.getDrawnShapes().getResponses().add(undoObject.newShape);
            }
        }
    }

}