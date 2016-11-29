package com.mobila.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by ckibuchi on 10/31/2016.
 */
public class Data {
    public static StringBuilder Readinput(InputStream inputStream) {
        StringBuilder crunchifyBuilder=new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            while ((line = in.readLine()) != null) {
                crunchifyBuilder.append(line);
            }
        } catch (Exception e) {
            System.out.println("Error Parsing: - ");
        }
        return crunchifyBuilder;
    }
}
