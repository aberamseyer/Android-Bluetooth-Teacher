package edu.ilstu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class createSAQuestion extends AppCompatActivity {

    private EditText questionBox, answerText1, answerText2, answerText3, answerText4;
    private Button submitButton;
    private CheckBox mc;
    private RadioButton answerBox1, answerBox2, answerBox3, answerBox4;
    private RadioGroup rg;
    private LinearLayout linearLayout;
    private TextView tv;

    String selectedQ = "2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_saquestion);
        linearLayout = (LinearLayout)findViewById(R.id.linearLayout);
        questionBox = (EditText)findViewById(R.id.questionBox);
        answerText1 = (EditText)findViewById(R.id.mcAnswer1);
        answerText2 = (EditText)findViewById(R.id.mcAnswer2);
        answerText3 = (EditText)findViewById(R.id.mcAnswer3);
        answerText4 = (EditText)findViewById(R.id.mcAnswer4);
        submitButton = (Button)findViewById(R.id.submitButton);
        mc = (CheckBox) findViewById(R.id.McCheck);
        answerBox1 = (RadioButton) findViewById(R.id.cbAnswer1);
        answerBox2 = (RadioButton) findViewById(R.id.cbAnswer2);
        answerBox3 = (RadioButton) findViewById(R.id.cbAnswer3);
        answerBox4 = (RadioButton) findViewById(R.id.cbAnswer4);
        tv = (TextView)findViewById(R.id.boxDialogue);
        rg = (RadioGroup)findViewById(R.id.radioGroup);

        mc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mc.isChecked()) {
                    linearLayout.setVisibility(View.VISIBLE);
                    tv.setVisibility(View.VISIBLE);
                    Log.i("aramsey", "made gridlayout visible");
                } else {
                    linearLayout.setVisibility(View.INVISIBLE);
                    tv.setVisibility(View.INVISIBLE);
                    Log.i("aramsey", "made gridlayout visible");
                }
            }
        });
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!questionBox.getText().toString().equals("")) {
                    if (mc.isChecked()) {
                        if(!(answerText1.getText().toString().equals("") || answerText2.getText().toString().equals("") ||
                                answerText3.getText().toString().equals("") || answerText4.getText().toString().equals(""))) {
                            switch(rg.getCheckedRadioButtonId()) {
                                case R.id.cbAnswer1:
                                    selectedQ = "a";
                                    break;
                                case R.id.cbAnswer2:
                                    selectedQ = "b";
                                    break;
                                case R.id.cbAnswer3:
                                    selectedQ = "c";
                                    break;
                                case R.id.cbAnswer4:
                                    selectedQ = "d";
                                    break;
                            }
                            CardFragment.questions.add(new MCQuestion(questionBox.getText().toString(),
                                    answerText1.getText().toString(),
                                    answerText2.getText().toString(),
                                    answerText3.getText().toString(),
                                    answerText4.getText().toString(),
                                    selectedQ));
                            CardFragment.myAdapter.notifyDataSetChanged();
                            finish();
                        }
                        else {
                            CharSequence cs = "Specify all answers";
                            Toast.makeText(Project3Bluetooth.getAppContext(), cs, Toast.LENGTH_SHORT).show();

                        }
                    } else {
                        CardFragment.questions.add(new SAQuestion(questionBox.getText().toString()));
                        CardFragment.myAdapter.notifyDataSetChanged();
                        finish();
                    }
                }
                else {
                    CharSequence cs = "Empty question not created";
                    Toast.makeText(Project3Bluetooth.getAppContext(), cs, Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

}
