package com.digicorp.listeners;

import org.testng.annotations.*;

public class Runner {
    @BeforeSuite
    public void setUpSuite() { System.out.println("before suite in runner"); }

    @AfterSuite
    public void tearDownSuite() { System.out.println("after suite in runner"); }

    @BeforeMethod
    public void setUp() { System.out.println("before method in runner"); }

    @AfterMethod
    public void tearDown() { System.out.println("after method in runner"); }

    @Test
    public void test1() { System.out.println("this is test1"); }

    @Test
    public void test2() { System.out.println("this is test2"); }

    @Test
    public void test6() { System.out.println("this is test6"); }
}
