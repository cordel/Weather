package me.suzdalnitsky.weather.data.network.client;

public interface HttpClient {

    <T> T performGetRequest(String uri, Class<T> clazz) throws Exception;
}
