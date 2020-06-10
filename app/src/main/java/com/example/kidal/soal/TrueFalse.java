package com.example.kidal.soal;

public class TrueFalse {

    private int mAnswer;
    private int mQid, ans1, ans2, ans3;

    TrueFalse(int q, int a, int ans1) {
        this.mQid = q;
        this.mAnswer = a;
        this.ans1 = ans1;
    }
    int getQid() {
        return this.mQid;
    }

    int getAns1() {
        return ans1;
    }

    int isAnswer() {
        return this.mAnswer;
    }

}
