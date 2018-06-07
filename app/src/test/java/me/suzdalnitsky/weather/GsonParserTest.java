package me.suzdalnitsky.weather;

import com.google.gson.Gson;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import me.suzdalnitsky.weather.data.network.parser.GsonParser;
import me.suzdalnitsky.weather.util.Util;

public class GsonParserTest {

    private static final String JSON_STRING = "{\n" +
            "  \"names\": [\n" +
            "    {\n" +
            "      \"name\": \"max\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"sam\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"sum\": 2\n" +
            "}";

    private static final NamesList JAVA_OBJECT = new NamesList(
            Util.listOf(
                    new Name("max"), new Name("sam")
            ),
            2
    );

    private InputStream stream;
    private GsonParser parser;

    @Before
    public void setUp() {
        stream = new ByteArrayInputStream(JSON_STRING.getBytes(StandardCharsets.UTF_8));
        parser = new GsonParser(new Gson());
    }

    @Test
    public void testSuccessfullyParsesJsonStream() {
        NamesList namesList = null;

        try {
            namesList = parser.parse(stream, NamesList.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Assert.assertNotNull("Parsed object is null.", namesList);
        Assert.assertEquals("Parsed object doesn't match expected.", JAVA_OBJECT, namesList);
    }

    private static class NamesList {
        private List<Name> names;
        private int sum;

        NamesList(List<Name> names, int sum) {
            this.names = names;
            this.sum = sum;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            NamesList namesList = (NamesList) o;

            if (sum != namesList.sum) return false;
            return names != null ? names.equals(namesList.names) : namesList.names == null;
        }

        @Override
        public int hashCode() {
            int result = names != null ? names.hashCode() : 0;
            result = 31 * result + sum;
            return result;
        }
    }

    private static class Name {
        private String name;

        Name(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Name name1 = (Name) o;

            return name != null ? name.equals(name1.name) : name1.name == null;
        }

        @Override
        public int hashCode() {
            return name != null ? name.hashCode() : 0;
        }
    }

}
