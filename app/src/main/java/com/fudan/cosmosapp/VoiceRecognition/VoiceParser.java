package com.fudan.cosmosapp.VoiceRecognition;

import android.content.Context;
import android.text.TextUtils;
import android.widget.EditText;

import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Created by PC on 2017/6/29.
 */

public class VoiceParser
{
    private static VoiceParser instance;
    private InitListener mInitListener;
    private RecognizerDialogListener mRecognizerDialogListener;
    private RecognizerDialog mRecognizerDialog;
    private EditText mShowEditText;
    private VoiceParser()
    {
    }
    public void initialize(Context context)
    {
        SpeechUtility.createUtility(context, SpeechConstant.APPID+"=5953c0ff");//Test CosmosApplication id.
        mRecognizerDialogListener=new RecognizerDialogListener()
        {
            @Override
            public void onResult(RecognizerResult recognizerResult, boolean b)
            {
                String result=parseIatResult(recognizerResult.getResultString());
                if(mShowEditText!=null)
                {
                    mShowEditText.setText(mShowEditText.getText()+result);
                }
            }

            @Override
            public void onError(SpeechError speechError)
            {
                // Tips：
                // 错误码：10118(您没有说话)，可能是录音机权限被禁，需要提示用户打开应用的录音权限。
                // 如果使用本地功能（语记）需要提示用户开启语记的录音权限。
            }
        };
        mInitListener=new InitListener()
        {
            @Override
            public void onInit(int i)
            {

            }
        };
        mRecognizerDialog=new RecognizerDialog(context,mInitListener);

    }
    public void initialize(Context context, EditText showEditText)
    {
        initialize(context);
        mShowEditText=showEditText;
    }
    public void startVoiceRecognition()
    {
        //清空Grammar_ID，防止识别后进行听写时Grammar_ID的干扰
        mRecognizerDialog.setParameter(SpeechConstant.CLOUD_GRAMMAR, null);
        //设置听写dialog的引擎
        mRecognizerDialog.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD);
        //设置采样率参数  支持8k 和 16k
        mRecognizerDialog.setParameter(SpeechConstant.SAMPLE_RATE, "8000");
        //显示听写对话框
        mRecognizerDialog.setListener(mRecognizerDialogListener);
        //清空文本框
        //if(mShowEditText!=null)
        //{
        //    mShowEditText.setText("");
        //}
        mRecognizerDialog.show();

    }
    public static VoiceParser getInstance()
    {
        if(instance==null)
        {
            instance=new VoiceParser();
        }
        return instance;
    }

    /**
     * 听写结果的Json格式解析
     * @param voiceResultJson
     * @return string
     */
    private String parseIatResult(String voiceResultJson)
    {
        if(TextUtils.isEmpty(voiceResultJson))
            return "";

        StringBuffer ret = new StringBuffer();
        try
        {
            JSONTokener tokener = new JSONTokener(voiceResultJson);
            JSONObject joResult = new JSONObject(tokener);

            JSONArray words = joResult.getJSONArray("ws");
            for (int i = 0; i < words.length(); i++)
            {
                JSONArray items = words.getJSONObject(i).getJSONArray("cw");// 听写结果词，默认使用第一个结果
                JSONObject obj = items.getJSONObject(0);
                ret.append(obj.getString("w"));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return ret.toString();
    }
}
