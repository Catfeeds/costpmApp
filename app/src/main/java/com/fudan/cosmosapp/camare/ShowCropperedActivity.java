package com.fudan.cosmosapp.camare;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Base64;
import android.util.Log;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.fudan.cosmosapp.R;
import com.fudan.cosmosapp.base.BaseActivity;
import com.fudan.cosmosapp.bean.CropperImage;
import com.fudan.cosmosapp.utils.AnimationUtils;
import com.fudan.cosmosapp.utils.Utils;

import java.io.ByteArrayOutputStream;


/**
 * @Class: ——
 * @Description: 显示截图结果界面
 * @author: lling(www.cnblogs.com/liuling)
 * @Date: 2015/10/25
 */
public class ShowCropperedActivity extends BaseActivity {
    private static final String TAG = "CropperedActivity";
    ImageView imageView;
    TextView textView_showresult;
    int beginHeight, endHeight, beginWidht, endWidth;
    CropperImage cropperImage;


    String temp = null;
    Handler handle = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //mTextView.setText(来自网络的文字);
            switch (msg.what){
                case 1:
                    Bundle  bundle=msg.getData();
                    textView_showresult.setText(bundle.getString("0"));
            }

        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_croppered);
        imageView = (ImageView) findViewById(R.id.image);
        textView_showresult = (TextView) findViewById(R.id.idCard_textView);


        String path = getIntent().getStringExtra("path");
        cropperImage = (CropperImage) getIntent().getSerializableExtra("cropperImage");
        int width = getIntent().getIntExtra("width", 0);
        int height = getIntent().getIntExtra("height", 0);
        beginWidht = (int) cropperImage.getHeight();
        beginHeight = (int) cropperImage.getWidth();
        if (width != 0 && height != 0) {
            int screenWidth = Utils.getWidthInPx(this);
            float scale = (float) screenWidth / (float) width;
            final ViewGroup.LayoutParams lp = imageView.getLayoutParams();
            int imgHeight = (int) (scale * height);
            endWidth = screenWidth;
            endHeight = imgHeight;
            lp.height = imgHeight;
            imageView.setLayoutParams(lp);
            Log.e(TAG, "imageView.getLayoutParams().width:" + imageView.getLayoutParams().width);
        }
        imageView.setImageURI(getIntent().getData());
        doAnimation();

        //编码图片


        //请求一下


//        new Thread(){
//            public void run(){
//                Bitmap bitmap = BitmapUtils.getBitmapFromUri(getIntent().getData());
//                bitmap  = BitmapUtils.compressImage(bitmap);
//
//                String result = "题目";
//                Message msg = new Message();
//                msg.what=1;
//                Bundle bundle = new Bundle();
//                bundle.putString("0",result);
//                msg.setData(bundle);
//                handle.sendMessage(msg);
//
//            }
//        }.start();




    }

    /** 
      * 通过Base32将Bitmap转换成Base64字符串 
      * @param bit 
      * @return 
      */
    public String Bitmap2StrByBase64(Bitmap bit){
        ByteArrayOutputStream bos= new ByteArrayOutputStream();
        bit.compress(Bitmap.CompressFormat.JPEG,100,bos);//参数100表示不压缩  
        byte[] bytes=bos.toByteArray();
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }

    private void doAnimation() {

        AnimatorSet animSet = new AnimatorSet();
        ValueAnimator scaleAnimator = AnimationUtils.getScaleAnimator(imageView, beginWidht, endWidth, beginHeight, endHeight);
        ObjectAnimator translationX = ObjectAnimator.ofFloat(imageView, "translationX", cropperImage.getX(), 0);
        ObjectAnimator translationY = ObjectAnimator.ofFloat(imageView, "translationY", cropperImage.getY(), 0);
        /*ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(imageView, "scaleX", 1f, scaleX);
        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(imageView, "scaleY", 1f, scaleX);*/
//        ObjectAnimator rotateAnimator = ObjectAnimator.ofFloat(imageView, "rotation", 90f, 0f);
        animSet.play(translationX).with(translationY)/*.with(rotateAnimator)*/.with(scaleAnimator);
        animSet.setInterpolator(new LinearInterpolator());
        animSet.setDuration(1000L);
        animSet.start();
        RotateAnimation rotateAnimation = new RotateAnimation(90, 0,
                Animation.ABSOLUTE, cropperImage.getX() + cropperImage.getWidth() / 2, Animation.ABSOLUTE, cropperImage.getY() + cropperImage.getWidth() / 2);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setDuration(1000L);
        imageView.startAnimation(rotateAnimation);
    }

}