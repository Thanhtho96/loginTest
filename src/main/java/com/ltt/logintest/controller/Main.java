package com.ltt.logintest.controller;

public class Main {
    public static void main(String[] args) {
        int  a = (int) Math.floor(Math.random() * 10000);
        String t = "dog_" + String.valueOf(a) +".jpg";
        int  b = (int) Math.floor(Math.random() * 2);
        int  c = (int) Math.floor(Math.random() * 2);
        int  d = (int) Math.floor(Math.random() * 2);
        int  e = (int) Math.floor(Math.random() * 2);
        int  f = (int) Math.floor(Math.random() * 2);
        System.out.println(t);
    }
}
