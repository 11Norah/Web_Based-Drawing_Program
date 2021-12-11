package com.example.demo.services.undoServices;

import com.example.demo.Model.ResponsesList;
import com.example.demo.Model.ShapesList;
import com.example.demo.response.ResponseObject;
import com.example.demo.shapes.Shape;
import com.example.demo.shapes.ShapeI;



public class UndoObject {
    protected ResponseObject prevShape,newShape;
    protected int prevInd,newInd;
    protected boolean isAdd,isMove,isCopy,isDelete,isResize,isClear;
    protected ResponsesList prevResponses;
    protected ShapesList prevDrawns;
}
