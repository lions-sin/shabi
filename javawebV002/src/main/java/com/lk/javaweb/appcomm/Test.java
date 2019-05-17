package com.lk.javaweb.appcomm;

public class Test {

    public static void main(String[] args) {

        BaseDaoMysql jdbc = new BaseDaoMysql();

        System.out.println(jdbc.getConn());
    }
}
