package com.haochen.xmlbuilder;

import com.haochen.xmlbuilder.annotation.XMLBean;
import com.haochen.xmlbuilder.annotation.XMLNode;

/**
 * Created by Haochen on 2017/1/2.
 */
@XMLBean(name = "test")
public class Test {
    @XMLNode()
    private int testInt = 12;
    @XMLNode(name = "double")
    private double testDouble = 5.3;
    private float testFloat = 1.7f;
    @XMLNode()
    private char testChar = 'H';
    @XMLNode(name = "Byte")
    private byte testByte = 'B';
    @XMLNode(name = "bool")
    public boolean testBoolean = true;
    @XMLNode(name = "hw")
    public String testString = "Hello World";
    @XMLNode
    public Inner inner;
    @XMLNode
    private Inner nullInner;
}
