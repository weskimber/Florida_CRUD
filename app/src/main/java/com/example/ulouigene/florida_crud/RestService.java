package com.example.ulouigene.florida_crud;

/**
 * Created by ulouigene on 5/11/2016.
 */
public class RestService {
    //You need to change the IP if you testing environment is not local machine
    //or you may have different URL than we have here
    //private static final String URL = "http://AndroidWebAPI.louigene.info/api";
    private static final String URL = "http://AndroidWebAPI.louigene.info/api";
    private retrofit.RestAdapter restAdapter;
    private RestClient apiService;

    public RestService()
    {
        restAdapter = new retrofit.RestAdapter.Builder()
                .setEndpoint(URL)
                .setLogLevel(retrofit.RestAdapter.LogLevel.FULL)
                .build();

        apiService = restAdapter.create(RestClient.class);
    }

    public RestClient getService()
    {
        return apiService;
    }
}
