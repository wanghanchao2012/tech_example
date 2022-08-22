package com.example.sign.controller;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(7);
        list.add(2);
        list.add(3);
        list.add(8);
        list.add(5);
        list.stream().forEachOrdered(e -> {
            System.out.println(e);
        });
    }
}
