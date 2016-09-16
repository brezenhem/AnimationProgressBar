package lindenvalley.de.example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

import lindenvalley.de.customprogress.AnimationProgressBar;

public class ExampleActivity extends AppCompatActivity {
    private Timer mTimer = new Timer();
    private AnimationProgressBar mAnimationProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.example_activity);
        mAnimationProgressBar = (AnimationProgressBar) findViewById(R.id.progress);

        mTimer.schedule(new TimerTask() {
            int i = 0;
            @Override
            public void run() {
                if(i>=100){
                    mTimer.cancel();
                    return;
                }
                i ++;
                updateProgress();
            }
        }, 0, 100);
    }

    private void updateProgress() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mAnimationProgressBar.updateProgressIndicator();
            }
        });
    }

    @Override
    protected void onDestroy() {
        if (mAnimationProgressBar != null)
            mAnimationProgressBar.freeAnimationDrawable();
        super.onDestroy();
    }
}
