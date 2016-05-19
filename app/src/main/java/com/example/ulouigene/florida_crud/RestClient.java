package com.example.ulouigene.florida_crud;
import java.util.List;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;
/**
 * Created by ulouigene on 5/11/2016.
 */
public interface RestClient {

    //Retrofit turns our institute WEB API into a Java interface.
    //So these are the list available in our WEB API and the methods look straight forward

    //i.e. http://localhost/api/Students
    @GET("/PowerBall")
    public void getPowerBall(Callback<List<PowerBall>> callback);

    //i.e. http://localhost/api/Students/1
    //Get student record base on ID
    @GET("/PowerBall/{id}")
    public void getPowerBallById(@Path("id") Integer id,Callback<PowerBall> callback);

    //i.e. http://localhost/api/Students/1
    //Delete student record base on ID
    @DELETE("/PowerBall/{id}")
    public void deletePowerBallById(@Path("id") Integer id,Callback<PowerBall> callback);

    //i.e. http://localhost/api/Students/1
    //PUT student record and post content in HTTP request BODY
    @PUT("/PowerBall/{id}")
    public void updatePowerBallById(@Path("id") Integer id,@Body PowerBall student,Callback<PowerBall> callback);

    //i.e. http://localhost/api/Students
    //Add student record and post content in HTTP request BODY
    @POST("/PowerBall")
    public void addPowerBall(@Body PowerBall student,Callback<PowerBall> callback);
}

