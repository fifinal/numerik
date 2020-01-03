package com.example.numerik;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.TextView;
import android.widget.Toast;

import net.objecthunter.exp4j.Expression;

public class MainActivity extends AppCompatActivity {
    TextView numberDisplay, operationsDisplay;
    HorizontalScrollView scrollerDisplayNumber, scrollerDisplayOperations;
    Button button1, button2, button3, button4, button5, button6, button7, button8, button9, button0, buttonCE, buttonRoot, buttonSin, buttonCos, buttonBracketsOpen, buttonBracketsClose, buttonTan, buttonX, buttonExponentation, buttonSum, buttonSubtraction, buttonMultiplication, buttonDivision, buttonEqual;
    EditText edtA,edtB,edtIterasi;
    String stringNumber, stringSpecial;
    Expression expression;
    double value=0;
    boolean numberClicked=false;
    ClipboardManager clipboard;
    ClipData clip;
    int charBracketOpenCount=0, charBracketCloseCount=0, charInExceed=0, dotCount=0;
    char bracketOpen='(';
    char bracketClose=')';
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        numberDisplay = (TextView) findViewById(R.id.displayNumber);
        operationsDisplay = (TextView) findViewById(R.id.displayOperationNumber);
        scrollerDisplayNumber = (HorizontalScrollView) findViewById(R.id.displayNumberScroller);
        scrollerDisplayOperations = (HorizontalScrollView) findViewById(R.id.displayOperationNumberScroller);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);
        button9 = (Button) findViewById(R.id.button9);
        button0 = (Button) findViewById(R.id.button0);
        buttonCE = (Button) findViewById(R.id.buttonCE);
        buttonRoot = (Button) findViewById(R.id.buttonRoot);
//        edtA = findViewById(R.id.edt_a);
//        edtB = findViewById(R.id.edt_b);
//        edtIterasi =findViewById(R.id.edt_iterasi);
        buttonBracketsOpen = (Button) findViewById(R.id.buttonBracketsOpen);
        buttonBracketsClose = (Button) findViewById(R.id.buttonBracketsClose);
        buttonX = (Button) findViewById(R.id.buttonX);
        buttonExponentation = (Button) findViewById(R.id.buttonExponentation);
        buttonSum = (Button) findViewById(R.id.buttonSum);
        buttonSubtraction = (Button) findViewById(R.id.buttonSubtraction);
        buttonMultiplication = (Button) findViewById(R.id.buttonMultiplication);
        buttonDivision = (Button) findViewById(R.id.buttonDivision);
        buttonEqual = (Button) findViewById(R.id.buttonEqaul);

        getSupportActionBar().setElevation(0);

        clickButtonCE();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        stringSpecial = numberDisplay.getText().toString();
        outState.putString("numberDisplay", stringSpecial);

        stringSpecial = operationsDisplay.getText().toString();
        outState.putString("operationsDisplay", stringSpecial);

        outState.putDouble("resultValue", value);

        outState.putBoolean("numberClicked", numberClicked);

        outState.putInt("bracketOpenCount", charBracketOpenCount);
        outState.putInt("bracketCloseCOunt", charBracketCloseCount);
        outState.putInt("charInExceed", charInExceed);
        outState.putInt("dotCount", dotCount);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        numberDisplay.setText(savedInstanceState.getString("numberDisplay"));
        operationsDisplay.setText(savedInstanceState.getString("operationsDisplay"));

        value = savedInstanceState.getDouble("resultValue");

        numberClicked = savedInstanceState.getBoolean("numberClicked");

        charBracketOpenCount = savedInstanceState.getInt("bracketOpenCount");
        charBracketCloseCount = savedInstanceState.getInt("bracketCloseCount");
        charInExceed = savedInstanceState.getInt("charInExceed");
        dotCount = savedInstanceState.getInt("dotCount");
    }

    public void clickButton1(View v) {
        numberDisplay.setText(numberDisplay.getText()+"1");
        operationsDisplay.setText(operationsDisplay.getText()+"1");
        numberClicked=true;
    }

    public void clickButton2(View v) {
        numberDisplay.setText(numberDisplay.getText()+"2");
        operationsDisplay.setText(operationsDisplay.getText()+"2");
        numberClicked=true;
    }

    public void clickButton3(View v) {

        numberDisplay.setText(numberDisplay.getText()+"3");
        operationsDisplay.setText(operationsDisplay.getText()+"3");
        numberClicked=true;
    }

    public void clickButton4(View v) {

        numberDisplay.setText(numberDisplay.getText()+"4");
        operationsDisplay.setText(operationsDisplay.getText()+"4");
        numberClicked=true;
    }

    public void clickButton5(View v) {

        numberDisplay.setText(numberDisplay.getText()+"5");
        operationsDisplay.setText(operationsDisplay.getText()+"5");
        numberClicked=true;
    }

    public void clickButton6(View v) {

        numberDisplay.setText(numberDisplay.getText()+"6");
        operationsDisplay.setText(operationsDisplay.getText()+"6");
        numberClicked=true;
    }

    public void clickButton7(View v) {

        numberDisplay.setText(numberDisplay.getText()+"7");
        operationsDisplay.setText(operationsDisplay.getText()+"7");
        numberClicked=true;
    }

    public void clickButton8(View v) {

        numberDisplay.setText(numberDisplay.getText()+"8");
        operationsDisplay.setText(operationsDisplay.getText()+"8");
        numberClicked=true;
    }

    public void clickButton9(View v) {

        numberDisplay.setText(numberDisplay.getText()+"9");
        operationsDisplay.setText(operationsDisplay.getText()+"9");
        numberClicked=true;
    }

    public void clickButton0(View v) {

        numberDisplay.setText(numberDisplay.getText()+"0");
        operationsDisplay.setText(operationsDisplay.getText()+"0");
        numberClicked=true;
    }

    public void clickButtonDot(View v) {

        stringSpecial = operationsDisplay.getText().toString();

        if (numberClicked == false) {

        }

        else if(stringSpecial.endsWith("(") || stringSpecial.endsWith("+") || stringSpecial.endsWith("-") || stringSpecial.endsWith("*") || stringSpecial.endsWith("/")) {

        }

        else {
            if (dotCount == 1) {

            } else {
                dotCount++;
                operationsDisplay.setText(operationsDisplay.getText() + ".");
                stringSpecial = numberDisplay.getText().toString();
                if(!stringSpecial.contains(".")) {
                    numberDisplay.setText(numberDisplay.getText() + "."); }
            }
        }
    }

    public void clickButtonBracketsOpen(View v) {

        numberDisplay.setText(numberDisplay.getText()+"(");
        operationsDisplay.setText(operationsDisplay.getText()+"(");
        dotCount=0;
        numberClicked=false;
    }

    public void clickButtonBracketsClose(View v) {

        stringSpecial = operationsDisplay.getText().toString();

        if(stringSpecial.isEmpty()) {

        }

        else if(stringSpecial.substring(stringSpecial.length()-1).equals("(")) {

        }

        else if(stringSpecial.substring(stringSpecial.length()-1).equals("+") ||
                stringSpecial.substring(stringSpecial.length()-1).equals("-") ||
                stringSpecial.substring(stringSpecial.length()-1).equals("*") ||
                stringSpecial.substring(stringSpecial.length()-1).equals("/")) {

        }

        else if(!stringSpecial.contains("(")) {

        }

        else {
            operationsDisplay.setText(operationsDisplay.getText() + ")");
            numberDisplay.setText(numberDisplay.getText()+")");
            dotCount=0;
        }
    }

    public void clickButtonExponentation(View view) {

        stringSpecial = numberDisplay.getText().toString();
        if(numberClicked == false) {

        }

        else if(stringSpecial.endsWith("^")) {

        }

        else {
            numberDisplay.setText(numberDisplay.getText() + "^");
            operationsDisplay.setText(operationsDisplay.getText() + "^");

        }

    }

    public void clickButtonSquareRoot(View v) {

        numberDisplay.setText(numberDisplay.getText()+"√(");
        operationsDisplay.setText(operationsDisplay.getText()+"sqrt(");
        numberClicked=false;
        charBracketOpenCount=0;
        charBracketCloseCount=0;
        charInExceed=0;
        dotCount=0;

    }

    public void clickButtonSin(View v) {
        numberDisplay.setText(numberDisplay.getText()+"sin(");
        operationsDisplay.setText(operationsDisplay.getText()+"sin(");
        numberClicked=false;
        charBracketOpenCount=0;
        charBracketCloseCount=0;
        charInExceed=0;
        dotCount=0;
    }

    public void clickButtonCos(View v) {

        numberDisplay.setText(numberDisplay.getText()+"cos(");
        operationsDisplay.setText(operationsDisplay.getText()+"cos(");
        numberClicked=false;
        charBracketOpenCount=0;
        charBracketCloseCount=0;
        charInExceed=0;
        dotCount=0;
    }

    public void clickButtonTan(View v) {

        numberDisplay.setText(numberDisplay.getText()+"tan(");
        operationsDisplay.setText(operationsDisplay.getText()+"tan(");
        numberClicked=false;
        charBracketOpenCount=0;
        charBracketCloseCount=0;
        charInExceed=0;
        dotCount=0;
    }

    public void clickButtonX(View v) {

        numberDisplay.setText(numberDisplay.getText() + "x");
        operationsDisplay.setText(operationsDisplay.getText() + "x");
        numberClicked = true;

    }

    public void clickButtonAddition(View v) {

        checkNumberDisplay();

        stringSpecial = operationsDisplay.getText().toString();

        if(stringSpecial.isEmpty()) {

        }

        else if(stringSpecial.charAt(stringSpecial.length()-1)=='+' || stringSpecial.charAt(stringSpecial.length()-1)=='-' || stringSpecial.charAt(stringSpecial.length()-1)=='*' || stringSpecial.charAt(stringSpecial.length()-1)=='/') {

        }

        else {
            buttonCE.setText("DEL");
            operationsDisplay.setText(operationsDisplay.getText() + "+");
            numberDisplay.setText(null);
            numberClicked=false;
            charBracketCloseCount=0;
            charBracketCloseCount=0;
            charBracketOpenCount=0;
            dotCount=0;

        }


    }

    public void clickButtonSubtraction(View v) {

        checkNumberDisplay();

        stringSpecial = operationsDisplay.getText().toString();

        if(stringSpecial.endsWith("sqrt(")) {

        }

        else {
            buttonCE.setText("DEL");
            operationsDisplay.setText(operationsDisplay.getText() + "-");
            numberDisplay.setText("-");
            numberClicked = false;
            charBracketCloseCount = 0;
            charBracketCloseCount = 0;
            charBracketOpenCount = 0;
            dotCount = 0;
        }

    }

    public void clickButtonMultiplication(View v) {

        checkNumberDisplay();


        stringSpecial = operationsDisplay.getText().toString();

        if(stringSpecial.isEmpty()) {

        }

        else if(stringSpecial.charAt(stringSpecial.length()-1)=='(') {

        }

        else if(stringSpecial.charAt(stringSpecial.length()-1)=='+' || stringSpecial.charAt(stringSpecial.length()-1)=='-' || stringSpecial.charAt(stringSpecial.length()-1)=='*' || stringSpecial.charAt(stringSpecial.length()-1)=='/') {

        }

        else {
            buttonCE.setText("DEL");
            operationsDisplay.setText(operationsDisplay.getText() + "*");
            numberDisplay.setText(null);
            numberClicked=false;
            charBracketCloseCount=0;
            charBracketCloseCount=0;
            charBracketOpenCount=0;
            dotCount=0;
        }
    }

    public void clickButtonDivision(View v) {

        checkNumberDisplay();

        stringSpecial = operationsDisplay.getText().toString();

        if(stringSpecial.isEmpty()) {

        }

        else if(stringSpecial.charAt(stringSpecial.length()-1)=='(') {

        }

        else if(stringSpecial.charAt(stringSpecial.length()-1)=='+' || stringSpecial.charAt(stringSpecial.length()-1)=='-' || stringSpecial.charAt(stringSpecial.length()-1)=='*' || stringSpecial.charAt(stringSpecial.length()-1)=='/') {

        }

        else {
            numberClicked=false;
            buttonCE.setText("DEL");
            operationsDisplay.setText(operationsDisplay.getText() + "/");
            numberDisplay.setText(null);
            numberClicked=false;
            charBracketCloseCount=0;
            charBracketCloseCount=0;
            charBracketOpenCount=0;
            dotCount=0;
        }
    }

    public void checkNumberDisplay() {
        stringSpecial = numberDisplay.getText().toString();
        if(Double.toString(value).equals(stringSpecial)) {
            operationsDisplay.setText(stringSpecial);
        }
    }

    public void checkBracketsNumber() {

        charBracketCloseCount=0;
        charBracketOpenCount=0;
        charInExceed=0;

        for (int i = 0; i < stringNumber.length(); i++) {
            if (stringNumber.charAt(i) == bracketOpen)
                charBracketOpenCount++;

            else if (stringNumber.charAt(i) == bracketClose)
                charBracketCloseCount++;
        }

        if (charBracketOpenCount == charBracketCloseCount) {

        }

        else if (charBracketOpenCount > charBracketCloseCount) {
            charInExceed = charBracketOpenCount - charBracketCloseCount;

            for (int j = 0; j < charInExceed; j++) {
                stringNumber = stringNumber + ")";
            }
        }
    }

//    public void clickButtonEqual(View v) {
//
//        if(numberClicked==false) {
//
//        }
//
//        else {
//
//            stringNumber = operationsDisplay.getText().toString();
//
//            checkBracketsNumber();
//
//            if(charBracketCloseCount > charBracketOpenCount) {
//                numberDisplay.setText("Invalid expression");
//            }
//
//            else if(stringNumber.contains("Infinity")) {
//                numberDisplay.setText("Infinity");
//            }
//
//            else {
////
//
//                try {
//                    double a=Double.valueOf(edtA.getText().toString());
//                    double b=Double.valueOf(edtB.getText().toString());
//
//                    RegulaFalsi regulaFalsi=new RegulaFalsi(a,b,1,1,stringNumber);
//                    regulaFalsi.run();
//                    Toast.makeText(this, String.valueOf(regulaFalsi.x), Toast.LENGTH_SHORT).show();
//
//                    numberDisplay.setText(Double.toString(regulaFalsi.x));
//                }
//
//                catch (Exception e) {
//                    numberDisplay.setText("Can't divide by 0");
//                    Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
//                }
//
//
//                buttonCE.setText("CLR");
//            }
//        }
//
//    }

    public void clickNumberDisplay(View v) {

        clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        clip = ClipData.newPlainText("number", numberDisplay.getText().toString());
        clipboard.setPrimaryClip(clip);
        Toast.makeText(MainActivity.this, "Copied result to clipboard", Toast.LENGTH_SHORT).show();
    }

    public void clickOperationsDisplay(View v) {

        clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        clip = ClipData.newPlainText("operations", operationsDisplay.getText().toString());
        clipboard.setPrimaryClip(clip);
        Toast.makeText(MainActivity.this, "Copied operations to clipboard", Toast.LENGTH_SHORT).show();
    }

    public void clickButtonCE() {

        buttonCE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                stringSpecial = operationsDisplay.getText().toString();

                if(!stringSpecial.isEmpty()) {

                    if (Double.toString(value).equals(numberDisplay.getText().toString())) {

                    }

                    else if (stringSpecial.endsWith("sin(") || stringSpecial.endsWith("cos(") || stringSpecial.endsWith("tan(")) {
                        stringSpecial = stringSpecial.substring(0, stringSpecial.length() - 4);
                        operationsDisplay.setText(stringSpecial);
                    }

                    else if(stringSpecial.endsWith(".")) {
                        dotCount=0;
                        stringSpecial = stringSpecial.substring(0, stringSpecial.length()-1);
                        operationsDisplay.setText(stringSpecial);

                    }

                    else {
                        stringSpecial = stringSpecial.substring(0, stringSpecial.length() - 1);
                        operationsDisplay.setText(stringSpecial);
                        numberClicked=false;
                        if(stringSpecial.endsWith("1") || stringSpecial.endsWith("2") || stringSpecial.endsWith("3") || stringSpecial.endsWith("4") || stringSpecial.endsWith("5") || stringSpecial.endsWith("6") || stringSpecial.endsWith("7") || stringSpecial.endsWith("8") || stringSpecial.endsWith("9") || stringSpecial.endsWith("0") || stringSpecial.endsWith(")")) {
                            numberClicked=true;
                        }
                    }
                }

                stringSpecial = numberDisplay.getText().toString();

                if(!stringSpecial.isEmpty()) {

                    if (Double.toString(value).equals(numberDisplay.getText().toString())) {

                    }

                    else if(stringSpecial.equals("Invalid expression") || stringSpecial.equals("Can't divide by 0")) {

                    }

                    else if (stringSpecial.endsWith("sin(") || stringSpecial.endsWith("cos(") || stringSpecial.endsWith("tan(")) {
                        stringSpecial = stringSpecial.substring(0, stringSpecial.length() - 4);
                        numberDisplay.setText(stringSpecial);
                    }

                    else if(stringSpecial.endsWith("√(")) {
                        stringSpecial = stringSpecial.substring(0, stringSpecial.length()-2);
                        numberDisplay.setText(stringSpecial);
                    }

                    else {

                        stringSpecial = stringSpecial.substring(0, stringSpecial.length() - 1);
                        numberDisplay.setText(stringSpecial);
                    }
                }

            }
        });


        buttonCE.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                numberDisplay.setText(null);
                operationsDisplay.setText(null);
                stringNumber="";
                stringSpecial="";
                value=0;
                numberClicked=false;
                charBracketCloseCount=0;
                charBracketOpenCount=0;
                charInExceed=0;
                dotCount=0;
                buttonCE.setText("DEL");
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.metode, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        stringNumber = operationsDisplay.getText().toString();
        Intent intent;
        switch(item.getItemId()){
            case R.id.action_regula_falsi:
                intent=new Intent(this, RegulaFalsiActivity.class);
                intent.putExtra("FX",stringNumber);
                startActivity(intent);
                return true;

            case R.id.action_newton_raphson:
                intent=new Intent(this, NewtonActivity.class);
                intent.putExtra("FX",stringNumber);
                startActivity(intent);
                return true;

            default:{
                return super.onOptionsItemSelected(item);
            }
        }
    }
}