package org.techtown.findmeapplication;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitApi {
    @POST("/user/login")
    Call<LoginResponse> userLogin(@Body LoginData data);
    @POST("/user/register/phone")
    Call<PhoneResponse> userPhone (@Body PhoneData data);
    @POST("/user/register/email")
    Call<EmailResponse> userEmail(@Body EmailData data);
    @POST("/user/register")
    Call<RegisterResponse> userRegister(@Body RegisterData data);
    @POST("user/question")
    Call<QuestionResponse> userQuestion(@Body userQuestionData data);
    @POST("user/question_register")
    Call<Register_question_Response> userRegisterQuestion(@Body Register_question_Data data); //등록
    @POST("user/diary_register")
    Call<insert_Diary_user_Response> userRegisterDairy(@Body Insert_Diary_user_Data data);//일기 내용 추가
    @POST("user/question_check")
    Call<questionCheckResponse> userQuestionCheck(@Body questionCheckData data);//버튼 활성화 인서트 판정
    @POST("user/prequestionUp")
    Call<prequestionUpdateResponse> userQuestionUp(@Body prequestionUpdateDate data);
    @POST("user/findid")
    Call<FindIdResponse> findId (@Body FindIdData data);
    @POST("user/changepwd")
    Call<changePwdResponse> changepwd(@Body org.techtown.findmeapplication.changePwdData data);
    @POST("user/getdiary")
    Call<DiaryContentResponse> userdiary (@Body DiaryContentData data);

}

