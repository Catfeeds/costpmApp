package com.fudan.cosmosapp.utils;

import android.animation.IntEvaluator;
import android.animation.ValueAnimator;
import android.view.View;

/**
 * Created by Administrator on 2017/8/22 0022.
 */

public class AnimationUtils {

    public static ValueAnimator getScaleAnimator(final View target, final int startWidth, final int endWidth
            , final int startHeight, final int endHeight) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(1, 100);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            //持有一个IntEvaluator对象，方便下面估值的时候使用
            private IntEvaluator mEvaluator = new IntEvaluator();

            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                //获得当前动画的进度值，整型，1-100之间
                int currentValue = (Integer) animator.getAnimatedValue();
                //计算当前进度占整个动画过程的比例，浮点型，0-1之间
                float fraction = currentValue / 100f;
                //这里我偷懒了，不过有现成的干吗不用呢
                //直接调用整型估值器通过比例计算出宽度，然后再设给Button
                target.getLayoutParams().width = mEvaluator.evaluate(fraction, startWidth, endWidth);
                target.getLayoutParams().height = mEvaluator.evaluate(fraction, startHeight, endHeight);
                target.requestLayout();
            }
        });
        return valueAnimator;
    }

}
