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

}

