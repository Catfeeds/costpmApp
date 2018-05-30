//package com.fudan.cosmosapp.NetWorkConnection;
//
//import android.util.Log;
//
//import com.orhanobut.logger.Logger;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
///**
// * @ explain:
// * @ author：xujun on 2016-8-25 15:17
// * @ email：gdutxiaoxu@163.com
// */
//public class NewsModel {
//
//    public static void getNews(String number, String page) {
//        APi api = Network.getInstance().getApi();
//        Call<Grade> news = api.getNews();
//        news.enqueue(new Callback<Grade>() {
//            @Override
//            public void onResponse(Call<Grade> call, Response<Grade> response) {
//                Grade body = response.body();
//                Logger.i("onResponse:1="+body.toString());
//                Log.i("获得年级成功", String.valueOf(body.getGradeList().get(2).getGradeName()));
//            }
//
//            @Override
//            public void onFailure(Call<Grade> call, Throwable t) {
//                Logger.i("获得年级失败"+t.getMessage());
//
//            }
//        });
//
//    }
//
//    public static void getSubject(int   gradeId){
//        APi api=Network.getInstance().getApi();
//        Call<Subject>   subjectCall=api.getSubject(gradeId);
//        subjectCall.enqueue(new Callback<Subject>() {
//            @Override
//            public void onResponse(Call<Subject> call, Response<Subject> response) {
//                Subject body    =response.body();
//                Logger.i("onResponse:1="+body.toString());
//                Log.i("获得科目成功",body.getSubjectList().get(0).getSubjectName());
//            }
//            @Override
//            public void onFailure(Call<Subject> call, Throwable t) {
//                Log.i("获得科目失败","");
//            }
//        });
//    }
//
////    public static void getquestionListByESJson(String   searchkey){
////        APi api=Network.getInstance().getApi();
////        Call<questionListByESJson>  questionListByESJsonCall=api.getquestionListByESJson(searchkey);
////        questionListByESJsonCall.enqueue(new Callback<questionListByESJson>() {
////            @Override
////            public void onResponse(Call<questionListByESJson> call, Response<questionListByESJson> response) {
////  //              questionListByESJson    body=response.body();
//////                Log.i("语音找题目",body.getQuestionList().get(0).getQuestionContext());
////            }
////            @Override
////            public void onFailure(Call<questionListByESJson> call, Throwable t) {
////                Log.i("语音：",t.getMessage());
////            }
////        });
////    }
//
//
//
//}
