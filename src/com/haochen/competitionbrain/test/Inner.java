package com.haochen.competitionbrain.test;

import com.haochen.xmlbuilder.XmlNode;

/**
 * Created by Haochen on 2017/1/2.
 */
public class Inner {
    private int testInt = 12;
    @XmlNode()
    private double testDouble = 5.3;
    @XmlNode(name = "innerFloat")
    private float testFloat = 1.7f;
    private char testChar = 'H';
    @XmlNode(name = "innerByte")
    private byte testByte = 'B';
    public boolean testBoolean = true;
    @XmlNode(name = "innerStr")
    public String testString = "苟利国家生死以";
    @XmlNode(name = "inin")
    public Ininner ininner;
}
