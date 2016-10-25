package com.example.alexnguyen.emu8086;

import java.util.Arrays;

/**
 * Created by AlexNguyen on 10/22/16.
 */

public class GeneralPurposeRegister extends Storage
{
    private  String highName;
    private  String lowName;
    private  char [] value;

    public  GeneralPurposeRegister (String name, int size, String highName, String lowName)
    {
        this .name = name;
        this.size =  size;
        this.highName = highName;
        this.lowName = lowName;
        this.value = new char[this.size/4];
        Arrays.fill(this.value,'0');
    }
    public  String getValue()
    {
        return new String(this.value);
    }
    public void load(String val)
    {
        String hexVal = this.toHex(val);
        int endPos = this.value.length-1;
        for(int i = hexVal.length()-1; i >= 0; i--)
        {
            this.value[endPos] = hexVal.charAt(i);
            endPos --;
        }
    }
}
