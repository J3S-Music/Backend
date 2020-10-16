package com.j3s.helper;

public class JSONTextHelper {
    public static String currentOffset(int depth){
        StringBuilder s = new StringBuilder();
        for(int i = 0; i<depth;i++){
            s.append(" ");
        }
        return s.toString();
    }
}
