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
import android.widget.TextView;

public class createSAQuestion extends AppCompatActivity implements View.OnClickListener{

    private TextView questionBox;
    private TextView answerBox1;
    private TextView answerBox2;
    private TextView answerBox3;
    private TextView answerBox4;
    private TextView boxDia;
    private Button submitButton;
    private CheckBox mc;
    private CheckBox[] answerBoxes=new CheckBox[4];

    int selectedQ=2;


    //    private String answer;

    // TODO create and add short answer question



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_saquestion);

        questionBox = (TextView)findViewById(R.id.questionBox);
        submitButton = (Button)findViewById(R.id.submitButton);
//        TODO find out if listeners are needed for regular text boxes
        answerBox1= (TextView)findViewById(R.id.McQ1);
        answerBox2= (TextView)findViewById(R.id.McQ2);
        answerBox3= (TextView)findViewById(R.id.McQ3);
        answerBox4= (TextView)findViewById(R.id.McQ4);
        boxDia= (TextView) findViewById(R.id.boxDialogue);
        mc = (CheckBox)findViewById(R.id.McCheck);
        answerBoxes[0] = (CheckBox)findViewById(R.id.McCheck1);
        answerBoxes[1] = (CheckBox)findViewById(R.id.McCheck2);
        answerBoxes[2] = (CheckBox)findViewById(R.id.McCheck3);
        answerBoxes[3] = (CheckBox)findViewById(R.id.McCheck4);
        MCvisiblility(false);


//        FragmentManager fm = getSupportFragmentManager();
//        Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);

        submitButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submitButton:
                Log.i("aramsey", "submit tapped");
                addQuestion();
                finish();
                break;
            case R.id.McCheck:
                Log.i("aramsey", "addmC tapped");
                    this.MCvisiblility(mc.isChecked());
                break;
            case R.id.McCheck1:
                Log.i("aramsey", "check1 tapped");
                this.answerBoxToggle(0);
                break;
            case R.id.McCheck2:
                Log.i("aramsey", "check2 tapped");
                this.answerBoxToggle(1);
                break;
            case R.id.McCheck3:
                Log.i("aramsey", "check3 tapped");
                this.answerBoxToggle(2);
                break;
            case R.id.McCheck4:
                Log.i("aramsey", "check4 tapped");
                this.answerBoxToggle(3);
                break;


        }
    }

    private void addQuestion()
    {
        SAQuestion userQ = new SAQuestion(questionBox.getText().toString());
        //                adds short answer question with no answer because i dont think students should have access to answer
        if(!mc.isChecked())
        {
            CardFragment.questions.add(userQ);
        }
        else
        {
            userQ=new MCQuestion(userQ.getQuestion(),answerBox1.getText().toString(),
                        answerBox2.getText().toString(),answerBox3.getText().toString(),answerBox4.getText().toString(),selectedQ);
            CardFragment.questions.add(userQ);

        }

    }

    /**
     * called upon multiple choice box selection
     * sets visibility of multiple choice dependant upon whether box is selected or not
     * @param checked
     */
    private void MCvisiblility(boolean checked)
    {
        if(checked)
        {
            answerBox1.setVisibility(View.VISIBLE);
            answerBox2.setVisibility(View.VISIBLE);
            answerBox3.setVisibility(View.VISIBLE);
            answerBox4.setVisibility(View.VISIBLE);
            boxDia.setVisibility(View.VISIBLE);
            answerBoxes[0].setVisibility(View.VISIBLE);
            answerBoxes[1].setVisibility(View.VISIBLE);
            answerBoxes[2].setVisibility(View.VISIBLE);
            answerBoxes[3].setVisibility(View.VISIBLE);
        }
        else
        {
            answerBox1.setVisibility(View.INVISIBLE);
            answerBox2.setVisibility(View.INVISIBLE);
            answerBox3.setVisibility(View.INVISIBLE);
            answerBox4.setVisibility(View.INVISIBLE);
            boxDia.setVisibility(View.INVISIBLE);
            answerBoxes[0].setVisibility(View.INVISIBLE);
            answerBoxes[1].setVisibility(View.INVISIBLE);
            answerBoxes[2].setVisibility(View.INVISIBLE);
            answerBoxes[3].setVisibility(View.INVISIBLE);
        }
    }

    /**
     * called upon answer box selection
     * makes sure only one box is selected
     * @param boxIndex
     */
    private void answerBoxToggle(int boxIndex)
    {
        Log.i("aramsey", "answer Box "+ boxIndex+" tapped.");
        selectedQ = boxIndex;
        for(int i=0;i<4;i++)
        {
            if(i==boxIndex)
                answerBoxes[i].setChecked(true);
            else
                answerBoxes[i].setChecked(false);
        }




    }
}
