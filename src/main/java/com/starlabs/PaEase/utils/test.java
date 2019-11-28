/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.starlabs.PaEase.utils;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author chulu
 */
public class test {

    public static void main(String[] args) {
        test t = new test();
        String id = "12";
        String url = "5,9,12,10,7,8,11=http://localhost/PaEaseServiceWrappers/index.php|20=http://localhost/PaEaseService|";
        System.out.print(t.getURL(id, url));
    }

    public String getURL(String serviceID, String URLStr) {
        String url = "";
        String[] serviceStr = URLStr.split("\\|");
        for (int i = 0; i < serviceStr.length; i++) {
            String[] str = serviceStr[i].split("=");
            List<String> list = Arrays.asList(str[0].split(","));
            if (list.contains(serviceID)) {
                url = str[1];
                break;
            }
        }
        return url;

    }
}
