package com.haochen.competitionbrain.test;

import com.haochen.xmlbuilder.annotation.XMLNode;

/**
 * Created by Haochen on 2017/1/2.
 */
public class Inner {
    private int testInt = 12;
    @XMLNode()
    private double testDouble = 5.3;
    @XMLNode(name = "innerFloat")
    private float testFloat = 1.7f;
    private char testChar = 'H';
    @XMLNode(name = "innerByte")
    private byte testByte = 'B';
    public boolean testBoolean = true;
    @XMLNode(name = "innerStr")
    public String testString = "苟利国家生死以";
    @XMLNode(name = "inin")
    public Ininner ininner;
}
