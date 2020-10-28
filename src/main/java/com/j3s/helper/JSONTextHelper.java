package com.j3s.helper;

/**
 * useless helper class
 */
public class JSONTextHelper {
    /**
     * @param depth number of spaces
     * @return a string containing depth number of spaces
     */
    public static String currentOffset(int depth){
        return " ".repeat(Math.max(0, depth));
    }
}
