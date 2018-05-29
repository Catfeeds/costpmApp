package com.fudan.cosmosapp.VoiceRecognition;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.fudan.cosmosapp.R;
import com.fudan.cosmosapp.base.BaseActivity;
import com.fudan.cosmosapp.ui.classify.QuestionDetailActivity;
import com.fudan.cosmosapp.utils.ToastUtils;

/**
 * Created by yinxiaofei on 2017/7/6.
 */

public class VoiceRecgnitionActivity extends BaseActivity implements View.OnClickListener, TextWatcher {

    public EditText mEditText;
    public Button   controlButton;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.voicerecognition_layout);

        mEditText=(EditText)findViewById(R.id.editText);
        controlButton=(Button)findViewById(R.id.buttonStartVoiceRecognition) ;
        VoiceParser.getInstance().initialize(this,mEditText);

        mEditText.addTextChangedListener(VoiceRecgnitionActivity.this);
        mEditText.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
//文本显示的位置在EditText的最上方
        mEditText.setGravity(Gravity.TOP);
//改变默认的单行模式
        mEditText.setSingleLine(false);
//水平滚动设置为False
        mEditText.setHorizontallyScrolling(false);



    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.buttonStartVoiceRecognition:
                int  edittextisnull  =   mEditText.getText().length();
                if (edittextisnull==0) {
                    ToastUtils.showToast(getApplicationContext(),"请开始语音输入");
                    VoiceParser.getInstance().startVoiceRecognition();
                }
                else if(edittextisnull<=1){
                    ToastUtils.showToast(getApplicationContext(),"输入题目字数必须大于5");
                    controlButton.setText("修改题目");
                    VoiceParser.getInstance().startVoiceRecognition();
                }else {
                    controlButton.setText("正在上传。。。");
                    //WebsocketClient.titleStr=tempStr;
                    //WebsocketClient.startRequest();
                    //WebsocketClient.sendMessage();
                    //String  solution=WebsocketClient.solution;
                    //WebsocketClient.closeWebSocket();
                    //WebsocketClient.destroy();

//                    Intent  start_show_solution =   new Intent(this, WebView_show_problem.class);
//                    //start_show_solution.putExtra("solution",mEditText.getText());
//                    Bundle bundle=new Bundle();
//                    //传递name参数为tinyphp
//                    bundle.putString("solution", mEditText.getText().toString());
//                    start_show_solution.putExtras(bundle);
//                    startActivity(start_show_solution);

                    Intent intent = new Intent(this, QuestionDetailActivity.class);
                    intent.putExtra("searchKey", mEditText.getText().toString());
                    this.startActivity(intent);

                    this.finish();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        controlButton.setText("语音输题");
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        controlButton.setText("上传题目");
    }
}
