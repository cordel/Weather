package me.suzdalnitsky.weather;

import android.support.test.filters.SmallTest;

import com.google.gson.Gson;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.net.HttpURLConnection;

import me.suzdalnitsky.weather.data.network.client.BasicHttpClient;
import me.suzdalnitsky.weather.data.network.client.HttpClient;
import me.suzdalnitsky.weather.data.network.client.HttpRequestException;
import me.suzdalnitsky.weather.data.network.parser.GsonParser;

@SmallTest
public class BasicHttpClientTest {

    private static final String RESPONSE_CODE_200_URL = "http://www.mocky.io/v2/5b1c2aaf3000003b132a487c";
    private static final String RESPONSE_CODE_400_URL = "http://www.mocky.io/v2/5b1cca663200006700c36cfd";

    private HttpClient client;

    @Before
    public void setUp() {
        Gson gson = new Gson();
        GsonParser parser = new GsonParser(gson);

        client = new BasicHttpClient(parser);
    }

    @Test
    public void testReturnsObjectOn200() throws Exception {
        Object object = client.performGetRequest(RESPONSE_CODE_200_URL, Object.class);

        Assert.assertNotNull("Request response is null.", object);
    }

    @Test
    public void testThrowsHttpRequestExceptionOn400() {
        Exception exception = null;

        try {
            client.performGetRequest(RESPONSE_CODE_400_URL, Object.class);
        } catch (Exception e) {
            exception = e;
        }

        Assert.assertNotNull("Thrown exception is null.", exception);
        Assert.assertTrue(
                "Thrown exception is not a HttpRequestException.",
                exception instanceof HttpRequestException
        );

        HttpRequestException httpRequestException = (HttpRequestException) exception;
        Assert.assertEquals(
                "Response code is not a HTTP_BAD_REQUEST.",
                HttpURLConnection.HTTP_BAD_REQUEST,
                httpRequestException.getStatusCode()
        );
    }

}
