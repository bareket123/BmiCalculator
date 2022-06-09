package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.DecimalFormat;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView instructions;
    private TextView firstNameTitle;
    private EditText firstNameText;
    private TextView lastNameTitle;
    private EditText lastNameText;
    private TextView ageTitle;
    private EditText ageText;
    private TextView genderTitle;
    private RadioButton maleButton;
    private RadioButton femaleButton;
    private RadioGroup  radioGroupGender;
    private TextView bodyStructureTitle;
    private RadioButton smallButton;
    private RadioButton mediumButton;
    private RadioButton largeButton;
    private RadioGroup radioGroupBody;
    private TextView heightTitle;
    private SeekBar heightSlider;
    private TextView weightTitle;
    private EditText weightText;
    private TextView bmiTitle;
    private TextView bmiResult;
    private TextView idealWeightTitle;
    private TextView idealWeightResult;
    private Button submitButton;
    private Button clearButton;

    private double slimness;
    private String userWeightInput;
    private double userHeightInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        instructions=(TextView) findViewById(R.id.Instructions);
        firstNameTitle=(TextView) findViewById(R.id.firstNameTitle);
        firstNameText=(EditText) findViewById(R.id.firstNameText);
        lastNameTitle=(TextView) findViewById(R.id.lastNameTitle);
        lastNameText=(EditText) findViewById(R.id.lastNameText);
        ageTitle=(TextView) findViewById(R.id.ageTitle);
        ageText=(EditText) findViewById(R.id.ageText);
        genderTitle=(TextView) findViewById(R.id.genderTitle);
        maleButton=(RadioButton) findViewById(R.id.maleButton);
        maleButton.setOnClickListener(this);
        femaleButton=(RadioButton) findViewById(R.id.femaleButton);
        femaleButton.setOnClickListener(this);
        radioGroupGender=(RadioGroup)findViewById(R.id.buttonGroupGender);
        bodyStructureTitle=(TextView) findViewById(R.id.bodyStructureTitle);
        smallButton=(RadioButton) findViewById(R.id.smallButton);
        smallButton.setOnClickListener(this);
        mediumButton=(RadioButton) findViewById(R.id.mediumButton);
        mediumButton.setOnClickListener(this);
        largeButton=(RadioButton) findViewById(R.id.largeButton);
        radioGroupBody =(RadioGroup) findViewById(R.id.buttonGropBody);
        largeButton.setOnClickListener(this);
        heightTitle=(TextView)findViewById(R.id.heightTitle);
        heightSlider=(SeekBar) findViewById(R.id.heightSlider);
        heightSlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
              heightTitle.setText("Height: "+ String.valueOf(progress));
              userHeightInput=progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        weightTitle=(TextView)findViewById(R.id.weightTitle);
        weightText=(EditText) findViewById(R.id.weightText);
        bmiTitle=(TextView)findViewById(R.id.bmiTitle);
        bmiResult=(TextView)findViewById(R.id.bmiResult);
        idealWeightTitle=(TextView)findViewById(R.id.idealWeightTitle);
        idealWeightResult=(TextView)findViewById(R.id.idealWeightResult);
        submitButton=(Button) findViewById(R.id.sumbitButton);
        submitButton.setOnClickListener(this);
        clearButton=(Button) findViewById(R.id.clearButton);
        clearButton.setOnClickListener(this);


}


    public void onClick(View view) {
        try{
            switch (view.getId()){

                case R.id.smallButton:
                    if(smallButton.isChecked()){
                        slimness=0.9;
                    }
                    break;
                case R.id.mediumButton:
                    if (mediumButton.isChecked()){
                        slimness=1;
                    }
                    break;
                case R.id.largeButton:
                    if (largeButton.isChecked()){
                        slimness=1.1;
                    }
                    break;
                case R.id.sumbitButton:
                    bmiResult.setText(calculateBmiResult());
                    idealWeightResult.setText((calculateIdealWeight(userHeightInput,slimness)) + " (" + setBmiStatusLabel(Double.parseDouble(calculateBmiResult())) + ")");


                    break;
                case R.id.clearButton:
                    firstNameText.setText(null);
                    lastNameText.setText(null);
                    ageText.setText(null);
                    heightSlider.setProgress(0);
                    weightText.setText(null);
                    bmiResult.setText(null);
                    idealWeightResult.setText(null);
                    radioGroupBody.clearCheck();
                    radioGroupGender.clearCheck();

                    break;
            }

        }catch (Exception e){
            e.printStackTrace();
        }



    }
    public String calculateBmiResult(){
        double bmi;
           userWeightInput=String.valueOf(weightText.getText());
            //note: due to the fact that the user insert height in cmd we multiplied by 100 to get right bmi
            bmi= 100*100*Double.parseDouble(userWeightInput) / (userHeightInput * userHeightInput);
           DecimalFormat df = new DecimalFormat();
           df.setMaximumFractionDigits(3);
        return String.valueOf(df.format(bmi));
    }
    //set status label accordance to bmi result
    public String setBmiStatusLabel(double userBmi){
        String userWeightStatus;

        if (userBmi<15){
            userWeightStatus="Anorexic";
        }
        else if (userBmi>= 15 && userBmi<18.5){
            userWeightStatus="Underweight ";
        }
        else if(userBmi>= 18.5 && userBmi< 24.9){
            userWeightStatus="Normal";
        }
        else if (userBmi>= 24.9 && userBmi< 29.9){
            userWeightStatus="Overweight";
        }
        else if(userBmi>= 30 && userBmi< 35){
            userWeightStatus="Obese";
        }
        else {
            userWeightStatus="Extreme Obese";
        }


        return userWeightStatus;
    }

    //return ideal weight result calculation
    public String calculateIdealWeight(double userHeight,double slimness){
        double idealWeight;
        String userAge=String.valueOf(ageText.getText());
        //= (height - 100 + (age / 10)) * 0.9 * slimness
        idealWeight= (userHeightInput-100+(  Double.parseDouble(userAge)/10)) *0.9*slimness;
        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setMaximumFractionDigits(3);
        return String.valueOf(decimalFormat.format(idealWeight));
    }





}