package me.suzdalnitsky.weather.data.network.parser;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import me.suzdalnitsky.weather.util.Util;

public final class GsonParser implements ResponseParser {

    private static final String UTF_8_CHARSET_NAME = "UTF-8"; 

    @NonNull
    private Gson gson;

    public GsonParser(@NonNull Gson gson) {
        this.gson = Util.requireNonNull(gson);
    }

    @Override
    public <T> T parse(InputStream source, Class<T> clazz) throws IOException {
        JsonReader reader = new JsonReader(
                new InputStreamReader(source, UTF_8_CHARSET_NAME)
        );

        T object = gson.fromJson(reader, clazz);

        reader.close();
        return object;
    }

}
