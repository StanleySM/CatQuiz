package com.example.android.catquiz;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    int finalScore = 0;
    String totalScoreDisplay;
    // Controlled values
    RadioButton questionNumber1b;
    RadioButton questionNumber1c;
    RadioButton questionNumber2;
    RadioButton questionNumber3;
    RadioButton questionNumber4;
    RadioButton questionNumber5;
    TextView questionNumber6;
    EditText questionNumber6EditText;
    RadioButton questionNumber7a;
    RadioButton questionNumber7b;
    CheckBox questionNumber8a;
    CheckBox questionNumber8b;
    CheckBox questionNumber8c;
    CheckBox questionNumber8d;
    CheckBox questionNumber8e;
    ImageView catFace;
    TextView userName;
    TextView correctAnswersMessage;
    TextView resultsMessage;
    ScrollView scrollView;

    public static void hideKeyboard(Activity activity) {
        if (activity != null && activity.getWindow() != null && activity.getWindow().getDecorView() != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View v = getCurrentFocus();

        if (v != null &&
                (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) &&
                v instanceof EditText &&
                !v.getClass().getName().startsWith("android.webkit.")) {
            int scrcoords[] = new int[2];
            v.getLocationOnScreen(scrcoords);
            float x = ev.getRawX() + v.getLeft() - scrcoords[0];
            float y = ev.getRawY() + v.getTop() - scrcoords[1];

            if (x < v.getLeft() || x > v.getRight() || y < v.getTop() || y > v.getBottom())
                hideKeyboard(this);
        }
        return super.dispatchTouchEvent(ev);
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionNumber1b = (RadioButton) findViewById(R.id.radio_button_1b);
        questionNumber1c = (RadioButton) findViewById(R.id.radio_button_1c);
        questionNumber2 = (RadioButton) findViewById(R.id.radio_button_2a);
        questionNumber3 = (RadioButton) findViewById(R.id.radio_button_3b);
        questionNumber4 = (RadioButton) findViewById(R.id.radio_button_4b);
        questionNumber5 = (RadioButton) findViewById(R.id.radio_button_5a);
        questionNumber6 = (TextView) findViewById(R.id.question6);
        questionNumber6.setText("");
        // Get a reference to the AutoCompleteTextView in the layout
        AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.question6);
        // Get the string array
        String[] countries = getResources().getStringArray(R.array.choose_your);
        // Create the adapter and set it to the AutoCompleteTextView
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, countries);
        textView.setAdapter(adapter);
        questionNumber7a = (RadioButton) findViewById(R.id.radio_button_7a);
        questionNumber7b = (RadioButton) findViewById(R.id.radio_button_7b);
        questionNumber8a = (CheckBox) findViewById(R.id.checkbox_8a);
        questionNumber8b = (CheckBox) findViewById(R.id.checkbox_8b);
        questionNumber8c = (CheckBox) findViewById(R.id.checkbox_8c);
        questionNumber8d = (CheckBox) findViewById(R.id.checkbox_8d);
        questionNumber8e = (CheckBox) findViewById(R.id.checkbox_8e);
        catFace = (ImageView) findViewById(R.id.cat_face);
        userName = (TextView) findViewById(R.id.player_EditText);
        correctAnswersMessage = (TextView) findViewById(R.id.correctAnswers_TextView);
        resultsMessage = (TextView) findViewById(R.id.result_TextView);
        scrollView = (ScrollView) findViewById(R.id.top_of_app);
    }

    // Method for calculating result
    private int scoreCalculation() {
        finalScore = 0;


        boolean answer1b = questionNumber1b.isChecked();
        if (answer1b) {
            finalScore = finalScore + 2;
        }
        boolean answer1c = questionNumber1b.isChecked();
        if (answer1c) {
            finalScore = finalScore + 1;
        }
        boolean answer2 = questionNumber2.isChecked();
        if (answer2) {
            finalScore = finalScore + 2;
        }
        boolean answer3 = questionNumber3.isChecked();
        if (answer3) {
            finalScore = finalScore + 1;
        }
        boolean answer4 = questionNumber4.isChecked();
        if (answer4) {
            finalScore = finalScore + 1;
        }
        boolean answer5 = questionNumber5.isChecked();
        if (answer5) {
            finalScore = finalScore + 2;
        }
        EditText answer6 = (EditText) findViewById(R.id.question6);
        String choiceInputText = answer6.getText().toString();
        if (choiceInputText.equals("Wild")) {
            finalScore = finalScore + 2;
        }
        boolean answer7a = questionNumber7a.isChecked();
        if (answer7a) {
            finalScore = finalScore + 1;
        }
        boolean answer7b = questionNumber7b.isChecked();
        if (answer7b) {
            finalScore = finalScore + 2;
        }
        boolean answer8a = questionNumber8a.isChecked();
        if (answer8a) {
            finalScore = finalScore + 3;
        }
        boolean answer8c = questionNumber8c.isChecked();
        if (answer8c) {
            finalScore = finalScore + 1;
        }
        return finalScore;
    }

    private void displayResults(int finalScore) {

        //Getting User name
        String nameOfUser = userName.getText().toString();

        if (finalScore > 9) {
            totalScoreDisplay = "Congratulations, " + nameOfUser + "! You are a cat person!\n" + "You scored " + finalScore + " out of 16";
        } else {
            totalScoreDisplay = "You are probably a dog person!\n" + "You scored " + finalScore + " out of 16";
        }

        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, totalScoreDisplay, duration);
        LinearLayout layout = (LinearLayout) toast.getView();
        if (layout.getChildCount() > 0) {
            TextView tv = (TextView) layout.getChildAt(0);
            tv.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        }
        toast.show();
    }

    // Method for calculating + displaying the Results after pressing Submit button
    public void submitResults(View view) {
        scoreCalculation();
        displayResults(finalScore);
    }

    // Method for reseting
    public void resetQuiz(View view) {
        RadioGroup question1RadioGroup = (RadioGroup) findViewById(R.id.Question1_radio_group);
        question1RadioGroup.clearCheck();

        RadioGroup question2RadioGroup = (RadioGroup) findViewById(R.id.Question2_radio_group);
        question2RadioGroup.clearCheck();

        RadioGroup question3RadioGroup = (RadioGroup) findViewById(R.id.Question3_radio_group);
        question3RadioGroup.clearCheck();

        RadioGroup question4RadioGroup = (RadioGroup) findViewById(R.id.Question4_radio_group);
        question4RadioGroup.clearCheck();

        RadioGroup question5RadioGroup = (RadioGroup) findViewById(R.id.Question5_radio_group);
        question5RadioGroup.clearCheck();

        questionNumber6EditText.setText("");

        RadioGroup question7RadioGroup = (RadioGroup) findViewById(R.id.Question7_radio_group);
        question7RadioGroup.clearCheck();

        questionNumber8a.setChecked(false);
        questionNumber8b.setChecked(false);
        questionNumber8c.setChecked(false);
        questionNumber8d.setChecked(false);
        questionNumber8e.setChecked(false);


        correctAnswersMessage.setText("");

        userName.setText("");
        // Move up
        catFace.requestFocus();
        Toast.makeText(this, "Reset DONE", Toast.LENGTH_SHORT).show();

    }

}

