package edu.ilstu;

import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;

public class createSAQuestion extends AppCompatActivity {

    private CheckBox[] answerBoxes=new CheckBox[4];

    private EditText questionBox, answerText1, answerText2, answerText3, answerText4;
    private Button submitButton;
    private CheckBox mc, answerBox1, answerBox2, answerBox3, answerBox4;
    private GridLayout gridLayout;

    int selectedQ=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_saquestion);
        gridLayout = (GridLayout)findViewById(R.id.gridLayout);
        questionBox = (EditText)findViewById(R.id.questionBox);
        answerText1 = (EditText)findViewById(R.id.mcAnswer1);
        answerText2 = (EditText)findViewById(R.id.mcAnswer2);
        answerText3 = (EditText)findViewById(R.id.mcAnswer3);
        answerText4 = (EditText)findViewById(R.id.mcAnswer4);
        submitButton = (Button)findViewById(R.id.submitButton);
        mc = (CheckBox)findViewById(R.id.McCheck);
        answerBox1 = (CheckBox)findViewById(R.id.cbAnswer1);
        answerBox2 = (CheckBox)findViewById(R.id.cbAnswer2);
        answerBox3 = (CheckBox)findViewById(R.id.cbAnswer3);
        answerBox4 = (CheckBox)findViewById(R.id.cbAnswer4);
        answerBoxes[0] = answerBox1;
        answerBoxes[1] = answerBox2;
        answerBoxes[2] = answerBox3;
        answerBoxes[3] = answerBox4;

        mc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mc.isChecked()) {
                    gridLayout.setVisibility(View.VISIBLE);
                    Log.i("aramsey", "made gridlayout visible");
                } else {
                    gridLayout.setVisibility(View.INVISIBLE);
                    Log.i("aramsey", "made gridlayout visible");
                }
            }
        });
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mc.isChecked()) {
                    for(int i = 0; i < 4; i++) {
                        if(answerBoxes[i].isChecked())
                            selectedQ = i+1;
                    }
                    CardFragment.questions.add(new MCQuestion(questionBox.getText().toString(),
                                                              answerText1.getText().toString(),
                                                              answerText2.getText().toString(),
                                                              answerText3.getText().toString(),
                                                              answerText4.getText().toString(),
                                                              selectedQ));
                }
                else {
                    CardFragment.questions.add(new SAQuestion(questionBox.getText().toString()));
                }
                CardFragment.myAdapter.notifyDataSetChanged();
                finish();
            }
        });
    }

//    /**
//     * called upon answer box selection
//     * makes sure only one box is selected
//     * @param boxIndex
//     */
//    private void answerBoxToggle(int boxIndex)
//    {
//        Log.i("aramsey", "answer Box "+ boxIndex+" tapped.");
//        selectedQ = boxIndex;
//        for(int i=0;i<4;i++)
//        {
//            if(i==boxIndex)
//                answerBoxes[i].setChecked(true);
//            else
//                answerBoxes[i].setChecked(false);
//        }
//
//
//
//
//    }
}
