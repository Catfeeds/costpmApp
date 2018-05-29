package com.fudan.cosmosapp.httpClient;

import com.fudan.cosmosapp.bean.BookVersion;
import com.fudan.cosmosapp.bean.ChCompositionTitleArray;
import com.fudan.cosmosapp.bean.CnCompositionClassifyBean;
import com.fudan.cosmosapp.bean.CnComsitionDetailbean;
import com.fudan.cosmosapp.bean.EnCompositionClassifyBean;
import com.fudan.cosmosapp.bean.EnCompositionTitleArray;
import com.fudan.cosmosapp.bean.EnComsitionDetailbean;
import com.fudan.cosmosapp.bean.EnWordDetailBean;
import com.fudan.cosmosapp.bean.EnWordListArray;
import com.fudan.cosmosapp.bean.Grade;
import com.fudan.cosmosapp.bean.KeyArray;
import com.fudan.cosmosapp.bean.KeyDetailArray;
import com.fudan.cosmosapp.bean.KnowledgePoint;
import com.fudan.cosmosapp.bean.PinYinArray;
import com.fudan.cosmosapp.bean.Question;
import com.fudan.cosmosapp.bean.QuestionDetail;
import com.fudan.cosmosapp.bean.RadicalArray;
import com.fudan.cosmosapp.bean.SearchQuestion;
import com.fudan.cosmosapp.bean.Subject;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Ang on 2017/8/9.
 */

public interface Api {

    @GET("nrms-web/searchQuestions/gradeListJson")
    Observable<Grade> getGrades();//@Query("num") String num,@Query("page")String page);

    @GET("nrms-web/searchQuestions/subjectListJson")
    Observable<Subject> getSubjects(@Query("gradeId") String gradeId);

    @GET("nrms-web/searchQuestions/textbookversionListJson")
    Observable<BookVersion> getVersions(@Query("subjectId") String subjectId, @Query("gradeId") String gradeId);

    @GET("nrms-web/searchQuestions/knowledgepointListJson")
    Observable<KnowledgePoint> getKnowledgePoint(@Query("textbookVersionId") String textbookVersionId, @Query("subjectId") String subjectId, @Query("gradeId") String gradeId);

    @GET("nrms-web/searchQuestions/questionListJson")
    Observable<Question> getQuestions(@Query("knowledgePointId") String knowledgePointId);

    @GET("nrms-web/searchQuestions/questionDetailJson")
    Observable<QuestionDetail> getQuestionDetail(@Query("quesId") String quesId);

    @GET("nrms-web/searchQuestions/questionListByESJson")
    Observable<SearchQuestion> getSearchQuestion(@Query("searchKey") String searchKey);

//    @GET("nrms-web/searchQuestions/questionDetailJson")
//    Call<String> getQuestionDetailStr(@Query("quesId") String quesId);

    @GET("nrms-web/searchQuestions/keyDetailByKeyNameJson")
    Observable<KeyDetailArray> getSearchKeyDetail(@Query("keyName") String keyName);

    @GET("nrms-web/searchQuestions/radicalListJson")
    Observable<RadicalArray> getRadicalList();

    @GET("nrms-web/searchQuestions/pinyinListJson")
    Observable<PinYinArray> getPinYinList();

    @GET("nrms-web/searchQuestions/keyListByRadicalJson")
    Observable<KeyArray> getKeyIdByRadicalId(@Query("radicalId") String radicalId);

    @GET("nrms-web/searchQuestions/keyListByPinyinJson")
    Observable<KeyArray> getKeyIdByPinYin(@Query("pinyinId") String pinyinId);

    @GET("nrms-web/searchQuestions/keyDetailByKeyIdJson")
    Observable<KeyDetailArray> getKeyDetailByKeyId(@Query("keyId") String keyId);

    @GET("nrms-web/searchQuestions/chCompositionListByTitle")
    Observable<ChCompositionTitleArray> getChCompositionTitleListByTitle(@Query("title") String title);

    @GET("nrms-web/searchQuestions/chCompositionById")
    Observable<CnComsitionDetailbean> getChCompositionDetailById(@Query("chCompositionId") String chCompositionId);

    @GET("nrms-web/searchQuestions/chCClassificationListJson")
    Observable<CnCompositionClassifyBean> getChCompositionClassify();

    @GET("nrms-web/searchQuestions/chCompositionListByClassification")
    Observable<ChCompositionTitleArray> getChCompositionTitleListByClassifyId(@Query("classificationId") String classificationId);

    //
    @GET("nrms-web/searchQuestions/enCompositionListByTitle")
    Observable<EnCompositionTitleArray> getEnCompositionTitleListByTitle(@Query("title") String title);

    @GET("nrms-web/searchQuestions/enCompositionById")
    Observable<EnComsitionDetailbean> getEnCompositionDetailById(@Query("enCompositionId") String chCompositionId);

    @GET("nrms-web/searchQuestions/enCClassificationListJson")
    Observable<EnCompositionClassifyBean> getEnCompositionClassify();

    @GET("nrms-web/searchQuestions/enCompositionListByClassification")
    Observable<EnCompositionTitleArray> getEnCompositionTitleListByClassifyId(@Query("classificationId") String classificationId);

    //获取英文单词列表
    @GET("nrms-web/searchQuestions/englishDictionaryListJson")
    Observable<EnWordListArray> getEnWordListArray();

    //通过单词查询英文单词
    @GET("nrms-web/searchQuestions/englishDictionaryListByWordJson")
    Observable<EnWordListArray> getEnWordListArrayByWord(@Query("word") String word);

    //英语单词详情
    @GET("nrms-web/searchQuestions/englishDictionaryByIdJson")
    Observable<EnWordDetailBean> getEnWordDetailById(@Query("wordId") String wordId);

}
