package com.demo01;



public class demo01 {
    public static void main(String[] args) {
        Derived d = new Derived();

        System.out.println(((Derived)d).tag);

        System.out.println(((Parent)d).tag);
    }
}


class Parent{
    public String tag = "1111";
}

class Derived extends Parent {
    public String tag = "111222";
}