package com.zhr.test;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * 生成测试方法后 里面的逻辑要自己去写
 * 并且需要重写 setup方法 用于实例化我们的需要测试的类
 */
public class CalculatorTest {

    private Calculator mCalculator;

    @Before
    public void setUp() throws Exception {
        mCalculator = new Calculator();
    }

    @Test
    public void sum() throws Exception {
        assertEquals(6 , mCalculator.sum(1 , 5), 0);
        assertEquals(7 , mCalculator.sum(1 , 5), 0);
    }

    @Test
    public void substract() throws Exception {
        assertEquals(2 , mCalculator.substract(5 , 4), 0);
    }

    @Test
    public void divide() throws Exception {
        assertEquals(3 , mCalculator.divide(20 , 5), 1);
    }

    @Test
    public void multiply() throws Exception {
        assertEquals(12 , mCalculator.multiply(2 , 5), 1);
    }
}