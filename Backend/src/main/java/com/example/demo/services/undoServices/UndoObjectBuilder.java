package com.example.demo.services.undoServices;

import com.example.demo.Model.ResponsesList;
import com.example.demo.Model.ShapesList;
import com.example.demo.response.ResponseObject;
import org.springframework.stereotype.Component;

@Component
public class UndoObjectBuilder {

    public UndoObject afterAdd(ResponseObject shape) {
        UndoObject undoObject = new UndoObject();
        undoObject.prevShape = shape;
        undoObject.isAdd = true;
        return undoObject;
    }

    public UndoObject afterMove(ResponseObject prevShape, ResponseObject newShape, int prevInd, int newInd) {
        UndoObject undoObject = new UndoObject();
        undoObject.prevShape = prevShape;
        undoObject.newShape = newShape;
        undoObject.prevInd = prevInd;
        undoObject.newInd = newInd;
        undoObject.isMove = true;
        return undoObject;
    }

    public UndoObject afterCopy(ResponseObject prevShape, ResponseObject newShape, int prevInd, int newInd) {
        UndoObject undoObject = new UndoObject();
        undoObject.prevShape = prevShape;
        undoObject.newShape = newShape;
        undoObject.prevInd = prevInd;
        undoObject.newInd = newInd;
        undoObject.isCopy = true;
        return undoObject;
    }

    public UndoObject afterDelete(ResponseObject prevShape, int prevInd) {
        UndoObject undoObject = new UndoObject();
        undoObject.prevShape = prevShape;
        undoObject.prevInd = prevInd;
        undoObject.isDelete = true;
        return undoObject;
    }

    public UndoObject afterResize(ResponseObject prevShape, ResponseObject newShape, int prevInd) {
        UndoObject undoObject = new UndoObject();
        undoObject.prevShape = prevShape;
        undoObject.newShape = newShape;
        undoObject.prevInd = prevInd;
        undoObject.isResize = true;
        return undoObject;
    }

    public UndoObject afterClear(ResponsesList prevResponses, ShapesList drawnShapes) {
        UndoObject undoObject = new UndoObject();
        undoObject.prevResponses = prevResponses;
        undoObject.prevDrawns = drawnShapes;
        undoObject.isClear = true;
        return undoObject;
    }


}
