package com.jularic.dominik.prvadomacazadaca;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import org.apache.commons.math3.complex.Complex;

public class MainActivity extends AppCompatActivity {

    Button mBtnCalculate;
    EditText mEtXNumberReal;
    EditText mEtXNumberImaginary;
    EditText mEtYNumberReal;
    EditText mEtYNumberImaginary;
    String mRadioButtonOperation = "";

   InputMethodManager mInputMethodManager;

    private View.OnClickListener btnCalculateAddListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {

            if(checkFormFilled()){
                Complex answer = new Complex(0,0);

                //create complex number from the input variables from edit text x real and x imaginary
                //lhs - left hand side, rhs - right hand side
                Complex lhs = new Complex(Integer.valueOf((mEtXNumberReal.getText().toString())),
                        Integer.valueOf(mEtXNumberImaginary.getText().toString()));
                //create complex number from the input variables from edit text y real and y imaginary
                Complex rhs = new Complex(Integer.valueOf((mEtYNumberReal.getText().toString())),
                        Integer.valueOf(mEtYNumberImaginary.getText().toString()));

                //depending on the operation calulate the result
                switch (mRadioButtonOperation){
                    case "+":
                        answer = lhs.add(rhs); // add two complex numbers
                        break;
                    case "-":
                        answer = lhs.subtract(rhs); // subtract two complex numbers
                        break;
                    case "*":
                        answer = lhs.multiply(rhs); // multiply two complex numbers
                        break;
                    case "/":
                        answer = lhs.divide(rhs); //divide two complex numbers
                        break;
                }

                Intent intent = new Intent(MainActivity.this,GraphActivity.class);
                intent.putExtra(GraphActivity.INTENT_PARAMETER_FIRST_NUMBER, lhs);
                intent.putExtra(GraphActivity.INTENT_PARAMETER_SECOND_NUMBER, rhs);
                intent.putExtra(GraphActivity.INTENT_PARAMETER_RESULT, answer);

                mInputMethodManager = (InputMethodManager) getApplicationContext()
                        .getSystemService(Context.INPUT_METHOD_SERVICE);
                startActivity(intent);

            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mBtnCalculate = (Button) findViewById(R.id.btn_main_calculate);
        mEtXNumberReal = (EditText) findViewById(R.id.et_main_x_real);
        mEtXNumberImaginary = (EditText) findViewById(R.id.et_main_x_imaginary);
        mEtYNumberReal = (EditText) findViewById(R.id.et_main_y_real);
        mEtYNumberImaginary = (EditText) findViewById(R.id.et_main_y_imaginary);

        mInputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mBtnCalculate.setOnClickListener(btnCalculateAddListener);



    }

    private boolean checkFormFilled(){

        //we expect that user will fill in the form, if he does not fill in he gets appropriate message
        boolean isEmptyXReal = false;
        boolean isEmptyXImaginary = false;
        boolean isEmptyYReal = false;
        boolean isEmptyYImaginary = false;
        boolean isEmptyRadioBtn = false;


        if(mEtXNumberReal.getText().toString().trim().length() == 0){
            Toast.makeText(getApplicationContext(), "Postavite realni dio prvog operanda",
                  Toast.LENGTH_LONG).show();
            isEmptyXReal = true;
            mEtXNumberReal.setFocusableInTouchMode(true);
            mEtXNumberReal.requestFocus();
           mInputMethodManager.showSoftInput(mEtXNumberReal, InputMethodManager.SHOW_IMPLICIT);
        }
        else if(mEtXNumberImaginary.getText().toString().trim().length() == 0){
            Toast.makeText(getApplicationContext(), "Postavite imaginarni dio prvog operanda",
                    Toast.LENGTH_LONG).show();
            isEmptyXImaginary = true;
            mEtXNumberImaginary.setFocusableInTouchMode(true);
            mEtXNumberImaginary.requestFocus();
            mInputMethodManager.showSoftInput(mEtXNumberImaginary, InputMethodManager.SHOW_IMPLICIT);
        }
        else if(mRadioButtonOperation == ""){
            Toast.makeText(getApplicationContext(), "Postavite operaciju računanja",
                    Toast.LENGTH_LONG).show();
            isEmptyRadioBtn = true;
        }
        else if(mEtYNumberReal.getText().toString().trim().length() == 0){
            Toast.makeText(getApplicationContext(), "Postavite realni dio drugog operanda",
                    Toast.LENGTH_LONG).show();
            isEmptyYReal = true;
            mEtYNumberReal.setFocusableInTouchMode(true);
            mEtYNumberReal.requestFocus();
            mInputMethodManager.showSoftInput(mEtYNumberReal, InputMethodManager.SHOW_IMPLICIT);

        }
        else if(mEtYNumberImaginary.getText().toString().trim().length() == 0){
            Toast.makeText(getApplicationContext(), "Postavite imaginarni dio drugog operanda",
                    Toast.LENGTH_LONG).show();
            isEmptyYImaginary = true;
            mEtYNumberImaginary.setFocusableInTouchMode(true);
            mEtYNumberImaginary.requestFocus();
            mInputMethodManager.showSoftInput(mEtYNumberImaginary, InputMethodManager.SHOW_IMPLICIT);
        }


        if(!(isEmptyXReal == false && isEmptyXImaginary == false && isEmptyYReal == false && isEmptyYImaginary == false && isEmptyRadioBtn == false)) {
            return false;
        }
        else{
            return true;
        }



    }


    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_operation_add:
                if (checked)
                    mRadioButtonOperation = "+";
                    break;
            case R.id.radio_operation_substract:
                if (checked)
                    mRadioButtonOperation = "-";
                    break;
            case R.id.radio_operation_multiply:
                if (checked)
                    mRadioButtonOperation = "*";
                    break;
            case R.id.radio_operation_divide:
                if (checked)
                    mRadioButtonOperation = "/";
                    break;
        }
    }
}
