package com.picon.agbsn;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

public interface RegisterAPI {

    @FormUrlEncoded
    @POST("/agbsn/register.php")
    void location(
            @Field("name") String name,
            @Field("fname") String fname,
            @Field("gender") String gender,
            @Field("marital") String marital,
            @Field("address") String address,
            @Field("state") String state,
            @Field("city") String city,
            @Field("manglik") String manglik,
            @Field("birthD") String birthD,
            @Field("birthT") String birthT,
            @Field("birthP") String birthP,
            @Field("mobile1") String mobile1,
            @Field("mobile2") String mobile2,
            @Field("educ") String educ,
            @Field("otherEduc") String otherEduc,
            @Field("profession") String profession,
            @Field("heigh") String heigh,
            @Field("caste") String caste,
            @Field("encodedImage") String encodedImage,
            @Field("cards") String cards,
            @Field("idcardno") String idcardno,
            Callback<Response> callback);
}

