package com.example.calculadora;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import org.mariuszgromada.math.mxparser.*;
import android.content.ComponentName;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.input);
        display.setShowSoftInputOnFocus(false);

        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getString(R.string.mostrar).equals(display.getText().toString())){
                    display.setText("");
                }
            }
        });
    }
    private void updateText(String strToAdd){
        String oldStr = display.getText().toString();
        int cursorPos = display.getSelectionStart();
        String leftStr = oldStr.substring(0, cursorPos);
        String rightStr = oldStr.substring(cursorPos);
        if (getString(R.string.mostrar).equals(display.getText().toString())){
            display.setText(strToAdd);
            display.setSelection(cursorPos + 1);
        }
        else{
            display.setText(String.format("%s%s%s", leftStr, strToAdd, rightStr));
            display.setSelection(cursorPos + 1);

        }

    }
    public void ceroBTN(View view){

        updateText("0");

    }
    public void unoBTN(View view){
        updateText("1");
    }
    public void dosBTN(View view ){
        updateText("2");
    }
    public void tresBTN(View view){
        updateText("3");
    }
    public void cuatroBTN(View view){
        updateText("4");
    }
    public void cincoBTN(View view){
        updateText("5");
    }
    public void seisBTN(View view){
        updateText("6");
    }
    public void sieteBTN(View view){
        updateText("7");
    }
    public void ochoBTN(View view){
        updateText("8");
    }

    public void nueveBTN(View view){
        updateText("9");
    }

    public void clearBTN(View view){
        display.setText("");
    }
    public void sumaBTN(View view){
        updateText("+");
    }
    public void restaBTN(View view){
        updateText("-");
    }
    public void exponenteBTN(View view){
        updateText("^");
    }
    public void multiplicacionBTN(View view){
        updateText("×");
    }
    public void divisionBTN(View view){
        updateText("÷");
    }
    public void puntoBTN(View view){
        updateText(".");
    }
    public void igualBTN(View view){
        String userExp = display.getText().toString();
        userExp = userExp.replaceAll("÷", "/");
        userExp = userExp.replaceAll("×", "*");

        Expression exp = new Expression(userExp);

        String result = String.valueOf((exp.calculate()));
        display.setText(result);
        display.setSelection(result.length());

    }
    public void parentesisBTN(View view){
        int cursorPos = display.getSelectionStart();
        int openPar = 0;
        int closedPar = 0;
        int textLen = display.getText().length();
        for (int i=0; i<cursorPos; i++){
            if (display.getText().toString().substring(i,i+1).equals("(")){
                openPar += 1;
            }
            if (display.getText().toString().substring(i,i+1).equals(")")){
                closedPar += 1;
            }
        }
        if(openPar == closedPar || display.getText().toString().substring(textLen -1, textLen).equals("(")){
            updateText("(");
            display.setSelection(cursorPos + 1);
        }
        else if(closedPar < openPar && !display.getText().toString().substring(textLen -1, textLen).equals("(")){
            updateText(")");

        }
        display.setSelection((cursorPos + 1));

    }
    public void masmenosBTN(View view){
        updateText("-");

    }
    public void espacioBTN(View view){
        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();
        if (cursorPos != 0 && textLen !=0){
            SpannableStringBuilder selection  = (SpannableStringBuilder) display.getText();
            selection.replace(cursorPos-1, cursorPos, "");
            display.setText(selection);
            display.setSelection(cursorPos-1);



        }
    }


}