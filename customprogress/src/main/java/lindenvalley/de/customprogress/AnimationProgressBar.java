package lindenvalley.de.customprogress;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class AnimationProgressBar extends LinearLayout {
    private RelativeLayout mIndicatorView;
    private TextView mNumberIndicator;
    private ImageView mInvertProgressScale;
    private Progress mProgress;
    private AnimationDrawable mFrameAnimation;

    public AnimationProgressBar(Context context) {
        super(context);
        initComponent();
    }

    public AnimationProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initComponent();
    }

    public AnimationProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initComponent();
    }

    private void initComponent() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.custom_progress_layout, this);

        mProgress = (Progress) findViewById(R.id.progressBar);
        mIndicatorView = (RelativeLayout) findViewById(R.id.indicator);
        mNumberIndicator = (TextView) findViewById(R.id.number_value);
        mInvertProgressScale = (ImageView) findViewById(R.id.invert_scale);

        ImageView progressAnimator = ((ImageView) findViewById(R.id.anim_progress));
        progressAnimator.setBackgroundResource(R.drawable.spin_animation);
        mFrameAnimation = (AnimationDrawable) progressAnimator.getBackground();
        mFrameAnimation.start();

        mProgress.setMax(100);
        mProgress.setProgress(0);
        mProgress.setInterpolator(new LinearInterpolator());
    }

    public void freeAnimationDrawable() {
        mFrameAnimation.stop();
    }

    public Progress getProgressView() {
        return mProgress;
    }

    public void updateProgressIndicator() {
        mProgress.setProgress(mProgress.getProgress() + 1);
        mNumberIndicator.setText(String.valueOf(mProgress.getProgress() + "%"));
        animateIndicatorView();
    }

    public void updateProgressIndicator(int deltaProgress) {
        int progress = mProgress.getProgress() + deltaProgress;
        mProgress.setProgress(progress);
        mNumberIndicator.setText(String.valueOf(progress + "%"));
        animateIndicatorView();
    }

    private void animateIndicatorView() {
        LinearLayout.LayoutParams progressParams = (LinearLayout.LayoutParams)mIndicatorView.getLayoutParams();
        progressParams.setMargins(mProgress.getProgressX(), 0, 0, 0);
        mIndicatorView.setLayoutParams(progressParams);

        RelativeLayout.LayoutParams scaleParams = (RelativeLayout.LayoutParams)mInvertProgressScale.getLayoutParams();
        scaleParams.setMargins(mProgress.getProgressX(), 0, 0, 0);
        mInvertProgressScale.setLayoutParams(scaleParams);
    }

    public void clearProgress() {
        mProgress.setProgress(0);
        mNumberIndicator.setText(String.valueOf(mProgress.getProgress() + "%"));

        LinearLayout.LayoutParams progressParams = (LinearLayout.LayoutParams)mIndicatorView.getLayoutParams();
        progressParams.setMargins(0, 0, 0, 0);
        mIndicatorView.setLayoutParams(progressParams);

        RelativeLayout.LayoutParams scaleParams = (RelativeLayout.LayoutParams)mInvertProgressScale.getLayoutParams();
        scaleParams.setMargins(0, 0, 0, 0);
        mInvertProgressScale.setLayoutParams(scaleParams);
    }
}
