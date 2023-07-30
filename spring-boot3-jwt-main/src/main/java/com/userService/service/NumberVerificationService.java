package com.userService.service;


import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class NumberVerificationService {
    private final String NUM_VERIFY_API_URL = "https://api.apilayer.com/number_verification/validate?number=";
    private final String NAME = "apikey";
    private final String KEY = "kqzDoPD457h6wvdol2Ufe4yRDZyEGFta";
    public Object verification(String number) throws IOException {

        OkHttpClient client = new OkHttpClient().newBuilder()
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .build();

        Request request = new Request.Builder()
                    .url(NUM_VERIFY_API_URL + number)
                    .addHeader(NAME ,KEY)
                    .build();
        Response response = client.newCall(request).execute();
        log.info(String.valueOf(response.body().string()));

        return response.body().string();
    }
}
