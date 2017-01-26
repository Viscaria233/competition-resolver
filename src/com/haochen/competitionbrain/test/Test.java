package com.haochen.competitionbrain.test;

import com.haochen.competitionbrain.test.Inner;
import com.haochen.xmlbuilder.XmlNode;

/**
 * Created by Haochen on 2017/1/2.
 */
public class Test {
    @XmlNode()
    private int testInt = 12;
    @XmlNode(name = "double")
    private double testDouble = 5.3;
    private float testFloat = 1.7f;
    @XmlNode()
    private char testChar = 'H';
    @XmlNode(name = "Byte")
    private byte testByte = 'B';
    @XmlNode(name = "bool")
    public boolean testBoolean = true;
    @XmlNode(name = "hw")
    public String testString = "Hello World";
    @XmlNode
    public Inner inner;
    @XmlNode
    private Inner nullInner;
}
