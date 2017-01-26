package com.haochen.competitionbrain.test;

import com.haochen.xmlbuilder.XmlNode;

/**
 * Created by Haochen on 2017/1/2.
 */
public class Ininner {
    @XmlNode()
    private int testInt = 24;
    @XmlNode(name = "double")
    private double testDouble = 5.0003;
    private float testFloat = 122.7f;
    @XmlNode()
    private char testChar = 'f';
    @XmlNode(name = "intest")
    public Test intest;
}
