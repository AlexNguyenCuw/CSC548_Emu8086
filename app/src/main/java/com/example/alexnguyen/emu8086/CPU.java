package com.example.alexnguyen.emu8086;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by AlexNguyen on 10/24/16.
 */

public class CPU
{
    static GeneralPurposeRegister ax = new GeneralPurposeRegister("ax", 16, "ah", "al");
    static GeneralPurposeRegister bx = new GeneralPurposeRegister("bx", 16, "bh", "bl");
    static GeneralPurposeRegister cx = new GeneralPurposeRegister("cx", 16, "ch", "cl");
    static GeneralPurposeRegister dx = new GeneralPurposeRegister("dx", 16, "dh", "dl");

    static HashMap<String, Storage> registers = new HashMap<String, Storage>(){{put("ax", ax);
        put("bx", bx); put("cx", cx); put("dx", dx);}};

    static void processInstruction(LinkedList<String> parts)
    {
        String command = parts.get(0).toLowerCase();
        if(command.equals("mov"))
        {
            Storage dest = registers.get(parts.get(1).toLowerCase());
            if(dest == null)
            {
                // dest must be a variable location instread of a register
                boolean placed = false;
                for(Storage s : registers.values())
                {
                    if(s instanceof GeneralPurposeRegister)
                    {
                        if (((GeneralPurposeRegister)s).hasHighSubRegister(parts.get(1).toLowerCase()))
                        {
                            ((GeneralPurposeRegister)s).loadHigh(parts.get(2).toLowerCase());
                            placed = true;
                            break;
                        }
                        else if (((GeneralPurposeRegister)s).hasLowSubRegister(parts.get(1).toLowerCase()))
                        {
                            ((GeneralPurposeRegister)s).loadLow(parts.get(2).toLowerCase());
                            placed = true;
                            break;
                        }
                    }
                }
                if (!placed)
                {

                }
            }
            else
            {
                //we have our destination register
                ((GeneralPurposeRegister)dest).load(parts.get(2));
            }
        }
    }
}
