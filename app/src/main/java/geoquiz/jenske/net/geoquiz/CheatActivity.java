package geoquiz.jenske.net.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Artic on 20/08/14.
 */
public class CheatActivity extends Activity {

    public static final String EXTRA_ANSWER_IS_TRUE = "geoquiz.jenske.net.geoquiz.answer_is_true";
    public static final String EXTRA_ANSWER_SHOWN = "geoquiz.jenske.net.geoquiz.answer_shown";

    private boolean mAnswerIsTrue;

    private TextView mAnswerTextView;
    private TextView mAPIversion;
    private Button mShowAnswer;

    private static final String KEY_PLAYER_CHEATED= "playerCheated";
    private boolean hasShownAnswer;

    private void setAnswerShownResult(boolean isAnswerShown){
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
        if(isAnswerShown){
            hasShownAnswer = isAnswerShown;
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        //not showing answer until user presses the button
        setAnswerShownResult(false);

        if (savedInstanceState != null){
            hasShownAnswer = savedInstanceState.getBoolean(KEY_PLAYER_CHEATED);
            setAnswerShownResult(hasShownAnswer);
        }

        mAPIversion = (TextView)findViewById(R.id.showAPIversion);

        mAPIversion.setText("API level: " + Build.VERSION.SDK_INT);

        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

        mAnswerTextView = (TextView)findViewById(R.id.answerTextView);

        mShowAnswer = (Button)findViewById(R.id.showAnswerButton);
        mShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mAnswerIsTrue) {
                    mAnswerTextView.setText(R.string.true_button);
                } else {
                    mAnswerTextView.setText(R.string.false_button);
                }
                hasShownAnswer = true;
                setAnswerShownResult(true);
            }
        });

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putBoolean(KEY_PLAYER_CHEATED, hasShownAnswer);
    }
}
