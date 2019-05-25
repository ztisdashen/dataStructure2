package com.hash;

import java.util.Random;

/**
 * @ClassName StringHashFamily
 * @Description TODO
 * @Author zt648
 * @Date 2019/5/22 15:28
 * @Version 1.0
 */

public class StringHashFamily implements HashFamily<String > {
    private int[] MULTIPLIERS;
    private final Random r = new Random();


    public StringHashFamily(int d){
        MULTIPLIERS = new int[d];
        generateFunction();
    }
    @Override
    public int hash(String s, int which) {
        final int multipliter = MULTIPLIERS[which];
        int hashVal = 0;
        for(int i=0;i<s.length();i++){
            hashVal = multipliter * hashVal + s.charAt(i);
        }
        return hashVal;
    }

    @Override
    public int getNumberOfFunctions() {
        return MULTIPLIERS.length;
    }

    @Override
    public void generateFunction() {
        for(int i=0;i<MULTIPLIERS.length;i++){
            MULTIPLIERS[i] = r.nextInt();
        }
    }
}
