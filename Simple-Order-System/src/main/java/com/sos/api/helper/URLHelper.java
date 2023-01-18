package com.sos.api.helper;


import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;

@Slf4j
@Component
public class URLHelper {



    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    private String restfulGet(URI uri) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(new SimpleClientHttpRequestFactory());
        SimpleClientHttpRequestFactory rf = (SimpleClientHttpRequestFactory) restTemplate.getRequestFactory();
        rf.setReadTimeout(3000);
        rf.setConnectTimeout(3000);
        ClientHttpRequest cf;
        try {
            cf = rf.createRequest(uri, HttpMethod.GET);
            ClientHttpResponse response = cf.execute();
            return StreamUtils.copyToString(response.getBody(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            return null;
        }

    }

    private Response restfulPost(String url, String body)  {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(JSON, body);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            return null;
        }
        return response;
    }

    private Response restfulPut(String url, String body)  {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(JSON, body);
        Request request = new Request.Builder()
                .url(url)
                .put(requestBody)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            return null;
        }
        return response;
    }

    private Response restfulPatch(String url, String body)  {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(JSON, body);
        Request request = new Request.Builder()
                .url(url)
                .patch(requestBody)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            return null;
        }
        return response;
    }

    private Response restfulDelete(String url, String body)  {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(JSON, body);
        Request request = new Request.Builder()
                .url(url)
                .delete(requestBody)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            return null;
        }
        return response;
    }

    private URI uriMaker(String url){
        log.info("::: Calling on {} :::", url);
        try {
            return new URI(url);
        } catch (Exception e) {
            return null;
        }

    }

    public String get(String url) {
        return restfulGet(uriMaker(url));
    }

    public Response post(String url, String body) { return restfulPost(url, body); }

    public Response put(String url, String body) {
        return restfulPut(url, body);
    }

    public Response patch(String url, String body) {
        return restfulPatch(url, body);
    }

    public Response delete(String url, String body) {
        return restfulDelete(url, body);
    }
}
