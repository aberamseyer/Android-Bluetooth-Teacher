package edu.ilstu;

/**
 * Created by Abe on 12/3/2016.
 */

public class SAQuestion {
    private String question;
    private boolean selected;

    public SAQuestion(String question) {
        this.question = question;
        selected = true;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void formatQuestion() {
        if (question.lastIndexOf("?") == question.length()-1) { // remove last question mark if there is one
            question = question.substring(0, question.length()-1);
        }
        question = question.substring(0, 1).toUpperCase() + question.substring(1); // capitalize the first letter
    }

    public void toggleSelected() {
        selected = !selected;
    }

    public boolean getSelected() {
        return selected;
    }

    @Override
    public String toString() {
        return "" + question + "?\n";
    }
}
