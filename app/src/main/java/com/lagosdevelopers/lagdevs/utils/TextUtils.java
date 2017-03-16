package com.lagosdevelopers.lagdevs.utils;

/**
 * Created by blessochampion on 3/16/17.
 */

public class TextUtils
{
    public static String capitalizeFirsCharacterIn(String s){
        String firstChar = s.substring(0,1).toUpperCase();
        return firstChar+s.substring(1);
    }
}
