package me.suzdalnitsky.weather.data.network.client;

import android.support.annotation.Nullable;

import java.io.IOException;

public class HttpRequestException extends IOException {

    @Nullable
    private String requestUrl;

    private int statusCode;

    HttpRequestException(@Nullable String requestUrl, int statusCode) {
        super("Request URL: " + requestUrl + ", Response status code: " + statusCode);

        this.requestUrl = requestUrl;
        this.statusCode = statusCode;
    }

    @Nullable
    public String getRequestUrl() {
        return requestUrl;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
