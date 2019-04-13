package com.burhanudin.menampilkanlistapi.rest;

public class UtilsApi {
    private static final String BASE_URL = "https://test.shirobyte.com/API/";

    public static ApiInterface getAPIService() {
        return RetrofitClient.getClient(BASE_URL).create(ApiInterface.class);
    }
}
