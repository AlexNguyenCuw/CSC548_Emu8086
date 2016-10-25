package com.example.alexnguyen.emu8086;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity
{
    private EditText instructionET;
    private TextView outputTV;
    private  TextView axRegTV;
    private  TextView bxRegTV;
    private  TextView cxRegTV;
    private  TextView dxRegTV;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.instructionET = (EditText)this.findViewById((R.id.instuctionET));
        this.outputTV = (TextView)this.findViewById(R.id.outputTV);
        this.axRegTV = (TextView)this.findViewById(R.id.axRegTV);
        this.bxRegTV = (TextView)this.findViewById(R.id.bxRegTV);
        this.cxRegTV = (TextView)this.findViewById(R.id.cxRegTV);
        this.dxRegTV = (TextView)this.findViewById(R.id.dxRegTV);
    }



    public void emulateButtonPressed(View v)
    {

        String entry = this.instructionET.getText().toString();
        LinkedList<String> parts = this.getParts(entry);
        CPU.processInstruction(parts);


    }
    private void showRegisterValue()
    {
        this.axRegTV.setText(((GeneralPurposeRegister)CPU.registers.get("ax")).getValue());
        this.bxRegTV.setText(((GeneralPurposeRegister)CPU.registers.get("bx")).getValue());
        this.cxRegTV.setText(((GeneralPurposeRegister)CPU.registers.get("cx")).getValue());
        this.dxRegTV.setText(((GeneralPurposeRegister)CPU.registers.get("dx")).getValue());
    }

    private LinkedList<String> getParts(String entry)
    {
        LinkedList<String>  answer = new LinkedList<String>();
        entry = entry.trim();
        String command = "";
        int pos = 0;
        while (entry.charAt(pos) != ' ')
        {
            command += entry.charAt(pos);
            pos++;
        }
        answer.addLast(command);

        if (pos == entry.length())
        {
            return answer;
        }
        while (entry.charAt(pos) == ' ')
        {
            pos++;
        }
        //read dest
        String dest = "";
        while (pos != entry.length() && entry.charAt(pos) != ',' && entry.charAt(pos) != ' ')
        {
            dest += entry.charAt(pos);
            pos++;
        }
        answer.addLast(dest);

        if (pos == entry.length())
        {
            return answer;
        }
       while (pos != entry.length())
       {
           while (entry.charAt(pos) == ' ')
           {
               pos++;
           }
           pos++;

           while (entry.charAt(pos) == ' ')
           {
               pos++;
           }
           //read param
           String param = "";
           while (pos != entry.length() && entry.charAt(pos) != ',' && entry.charAt(pos) != ' ')
           {
               param += entry.charAt(pos);
               pos++;
           }
           answer.addLast(param);

       }
        return answer;

    }
}
