package com.example.demo.factory;

import com.example.demo.response.ResponseObjectI;

public interface ObjectFactoryI {
    public ResponseObjectI getObject(ResponseObjectI response);
}
