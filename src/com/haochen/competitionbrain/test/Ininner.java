package com.haochen.xmlbuilder;

import com.haochen.xmlbuilder.annotation.XMLNode;

/**
 * Created by Haochen on 2017/1/2.
 */
public class Ininner {
    @XMLNode()
    private int testInt = 24;
    @XMLNode(name = "double")
    private double testDouble = 5.0003;
    private float testFloat = 122.7f;
    @XMLNode()
    private char testChar = 'f';
    @XMLNode(name = "intest")
    public Test intest;
}
