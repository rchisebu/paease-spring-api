/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.starlabs.PaEase.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author chulu
 */
public class test {

    public static void main(String[] args) {
      ArrayList<String> arr = new ArrayList<>();
        arr.add("cat");
        arr.add("dog");
        arr.add("bird");

        // Convert the ArrayList into a String.
        String res = String.join(",", arr);
        System.out.println(res);
    }
}
