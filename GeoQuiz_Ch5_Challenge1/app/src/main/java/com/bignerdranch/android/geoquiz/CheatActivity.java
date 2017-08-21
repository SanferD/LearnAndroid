package com.bignerdranch.android.geoquiz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    private static final String EXTRA_ANSWER_IS_TRUE =
            "com.bignerdranch.android.geoquiz.answer_is_true";

    private static final String EXTRA_ANSWER_SHOWN =
            "com.bignerdranch.android.geoquiz.answer_shown";

    private static final String KEY_HAS_CHEATED = "has_cheated";

    private static final String EXTRA_HAS_CHEATED =
            "com.bignerdranch.android.geoquiz.has_cheated";

    private boolean mAnswerIsTrue;
    private TextView mAnswerTextView;
    private Button mShowAnswerButton;
    private boolean mAnswerIsShown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
        boolean hasCheated = getIntent().getBooleanExtra(EXTRA_HAS_CHEATED, false);
        Log.d("hello123456", hasCheated? "cheated": "not cheated");
        Log.d("TAG", "this is a string");

        if (savedInstanceState != null) {
            mAnswerIsShown = savedInstanceState.getBoolean(KEY_HAS_CHEATED, false);
        }

        mAnswerTextView = (TextView) findViewById(R.id.answer_text_view);
        if (mAnswerIsShown) {
            int msg = mAnswerIsTrue? R.string.true_button: R.string.false_button;
            mAnswerTextView.setText(msg);
            setAnswerShownResult(mAnswerIsShown);
        }

        mShowAnswerButton = (Button) findViewById(R.id.show_answer_button);
        mShowAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int msg = mAnswerIsTrue? R.string.true_button: R.string.false_button;
                mAnswerTextView.setText(msg);
                mAnswerIsShown = true;
                setAnswerShownResult(mAnswerIsShown);
            }
        });
        if (hasCheated)
            mShowAnswerButton.performClick();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(KEY_HAS_CHEATED, mAnswerIsShown);
    }

    public static Intent newIntent(Context packageContext, boolean answerIsTrue, boolean cheated) {
        Intent intent = new Intent(packageContext, CheatActivity.class);
        intent.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
        intent.putExtra(EXTRA_HAS_CHEATED, cheated);
        return intent;
    }

    public static boolean wasAnswerShown(Intent result) {
        return result.getBooleanExtra(EXTRA_ANSWER_SHOWN, false);
    }

    private void setAnswerShownResult(boolean isAnswerShown) {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    }
}
