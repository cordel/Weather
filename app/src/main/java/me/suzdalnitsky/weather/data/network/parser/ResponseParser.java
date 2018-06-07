package me.suzdalnitsky.weather.data.network.parser;

import java.io.IOException;
import java.io.InputStream;

public interface ResponseParser {

    <T> T parse(InputStream source, Class<T> clazz) throws IOException;
}
