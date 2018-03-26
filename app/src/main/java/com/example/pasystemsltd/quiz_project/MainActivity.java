package com.example.pasystemsltd.quiz_project;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private final int numberOfQuestions=9;
    private int score;
    private boolean missingAwnser=false;
    Intent shareIntent;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    //when submit button called//

        public void submit(View view) {

            int score = checkAnswers();
            if (score < 0) {      //a negative score value indicates the first question that is not answered yet (e.g. -2 means the 2.question)
                Toast toastMessage = Toast.makeText(this, "Answer for question " + (-score) + " is missing", Toast.LENGTH_SHORT);
                toastMessage.setGravity(Gravity.BOTTOM, 0, 200);
                toastMessage.show();
            } else {            //if all questions are answered
                showResult();
            }
        }

        //check answers and gives the final score//

    private int checkAnswers () {

        missingAwnser=false;
        score=0;
        String textInput;
        RadioGroup radioGroup;
        EditText editText;
        CheckBox[] checkBoxes=new CheckBox[3];
        boolean[] correctAnswers=new boolean[3];
        RadioButton correctRadioButton;

        //Q1: What is the meaning of Makhaarij?
        radioGroup=findViewById(R.id.question1_radio_group);
        correctRadioButton=findViewById(R.id.question1_radio_button2);
        evaluateRadioButtonAnswer(radioGroup,correctRadioButton);
        if (missingAwnser) return -1;       //return -1 as score value if answer is missing

        //Question 2 : What does huroof mean?//
        radioGroup=findViewById(R.id.question2_radio_group);
        correctRadioButton=findViewById(R.id.question2_radio_button2);
        evaluateRadioButtonAnswer(radioGroup,correctRadioButton);
        if (missingAwnser) return -2;

        //Question 3 : What does Al-halq mean?//
        radioGroup=findViewById(R.id.question3_radio_group);
        correctRadioButton=findViewById(R.id.question2_radio_button1);
        evaluateRadioButtonAnswer(radioGroup,correctRadioButton);
        if (missingAwnser) return -3;

        //Question 4 : What does Al-shafataan mean?//
        radioGroup=findViewById(R.id.question4_radio_group);
        correctRadioButton=findViewById(R.id.question4_radio_button3);
        evaluateRadioButtonAnswer(radioGroup,correctRadioButton);
        if (missingAwnser) return -4;

        //Question 5 : What does Al-Khayshoom mean?//
        checkBoxes[0]=findViewById(R.id.question5_checkbox1);
        checkBoxes[1]=findViewById(R.id.question5_checkbox2);
        checkBoxes[2]=findViewById(R.id.question5_checkbox3);
        correctAnswers[0]=false;
        correctAnswers[1]=true;
        correctAnswers[2]=true;
        evaluateCheckBoxAnswer(checkBoxes, correctAnswers);
        if (missingAwnser) return -5;

        //Question 6 : What does Al-jawf mean?//
        radioGroup=findViewById(R.id.question6_radio_group);
        correctRadioButton=findViewById(R.id.question6_radio_button2);
        evaluateRadioButtonAnswer(radioGroup,correctRadioButton);
        if (missingAwnser) return -6;

        //Question 7 : What does lisaan mean?//
        radioGroup=findViewById(R.id.question7_radio_group);
        correctRadioButton=findViewById(R.id.question7_radio_button3);
        evaluateRadioButtonAnswer(radioGroup,correctRadioButton);
        if (missingAwnser) return -7;

        //Question 8 : What does kharaja mean?//
        radioGroup=findViewById(R.id.question8_radio_group);
        correctRadioButton=findViewById(R.id.question8_radio_button1);
        evaluateRadioButtonAnswer(radioGroup,correctRadioButton);
        if (missingAwnser) return -8;

        //Question 9 : hom many types of makhaarij are they?//
        editText=findViewById(R.id.question9_edit_text);
        textInput=editText.getText().toString();
        if (textInput.equals(5)){
            score++;
        } else if (textInput.length()==0) {  //if text length is zero then nothing was typed in
            missingAwnser=true;
            return -9;
        }
        return score;

    }

    //check answers in radio group questions and missing answers returned as -1

    private void evaluateRadioButtonAnswer (RadioGroup radioGroup, RadioButton rightRadioButton){
        int chosenAwnserID;

        chosenAwnserID=radioGroup.getCheckedRadioButtonId();
        if (rightRadioButton.getId()==chosenAwnserID){
            score++;
        } else if (chosenAwnserID==-1){
            missingAwnser=true;
        }
    }



    //checks answers in checkbox group question

    private void evaluateCheckBoxAnswer (CheckBox[] checkBoxes, boolean correctAwnser[]){
        boolean [] chosenAnswer=new boolean[3];
        chosenAnswer[0]=checkBoxes[0].isChecked();
        chosenAnswer[1]=checkBoxes[0].isChecked();
        chosenAnswer[2]=checkBoxes[0].isChecked();
        boolean[] noAnswer={false,false,false};
        if (Arrays.equals(chosenAnswer, noAnswer)){
            missingAwnser=true;
        }else if (Arrays.equals(chosenAnswer,correctAwnser)){
            score++;
        }
    }

    //show result of quiz with score and option to reset or share//

    private void showResult() {

        Toast.makeText(this,"you got " + score + " out of 9",Toast.LENGTH_SHORT).show();
    }


}
