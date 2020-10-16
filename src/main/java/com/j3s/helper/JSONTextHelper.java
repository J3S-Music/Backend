package com.j3s.helper;

public class JSONTextHelper {
    public static String currentOffset(int depth){
        return " ".repeat(Math.max(0, depth));
    }
}
