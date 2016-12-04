package edu.ilstu;

import android.widget.CheckBox;

/**
 * Created by anonymous on 1/15/16.
 */
public class MCQuestion extends SAQuestion {

    private String a, b, c, d;
    private int ans;

    public MCQuestion(String question, String a, String b, String c, String d, int ans) {
        super(question);
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.ans = ans;
    }

    public void setAns(int ans) {
        this.ans = ans;
    }

    public int getAns() { return ans; }

    public void setA(String a) {
        this.a = a;
    }

    public void setB(String b) {
        this.b = b;
    }

    public void setC(String c) {
        this.c = c;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getA() {
        return a;
    }

    public String getB() {
        return b;
    }

    public String getC() {
        return c;
    }

    public String getD() {
        return d;
    }

    @Override
    public String getQuestion() {
        return super.getQuestion();
    }

    @Override
    public void setQuestion(String question) {
        super.setQuestion(question);
    }

    @Override
    public String toString() {
        super.formatQuestion();
        return "" + super.getQuestion() + "?\n" + "\tA: " + a + "\n\tB: " + b + "\n\tC: " + c + "\n\tD: " + d + "\n";
    }
}




