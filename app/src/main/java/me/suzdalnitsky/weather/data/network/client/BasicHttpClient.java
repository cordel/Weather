package me.suzdalnitsky.weather.data.network.client;

import android.support.annotation.NonNull;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

import me.suzdalnitsky.weather.data.network.parser.ResponseParser;
import me.suzdalnitsky.weather.util.Util;

public class BasicHttpClient implements HttpClient {

    private static final int READ_TIMEOUT = 3000;
    private static final int CONNECT_TIMEOUT = 3000;
    private static final String GET_REQUEST_METHOD = "GET";

    @NonNull
    private final ResponseParser parser;

    public BasicHttpClient(@NonNull ResponseParser parser) {
        this.parser = Util.requireNonNull(parser);
    }

    @Override
    public <T> T performGetRequest(String requestUrl, Class<T> clazz) throws IOException {
        URL url = new URL(requestUrl);
        HttpURLConnection connection = null;

        try {
            connection = (HttpURLConnection) url.openConnection();
            setUpConnection(connection);

            connection.connect();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new HttpRequestException(
                        connection.getURL().toString(),
                        connection.getResponseCode()
                );
            }

            try (InputStream stream = connection.getInputStream()) {
                return parser.parse(stream, clazz);
            }
            
        } finally {
            if (connection != null) connection.disconnect();
        }
    }

    private void setUpConnection(HttpURLConnection connection) throws ProtocolException {
        connection.setReadTimeout(READ_TIMEOUT);
        connection.setConnectTimeout(CONNECT_TIMEOUT);
        connection.setRequestMethod(GET_REQUEST_METHOD);
    }
}
