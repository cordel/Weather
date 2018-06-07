package me.suzdalnitsky.weather.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class Util {

    /**
     * Checks that the specified object reference is not {@code null}. This
     * method is designed primarily for doing parameter validation in methods
     * and constructors, as demonstrated below:
     * <blockquote><pre>
     * public Foo(Bar bar) {
     *     this.bar = Objects.requireNonNull(bar);
     * }
     * </pre></blockquote>
     *
     * @param obj the object reference to check for nullity
     * @param <T> the type of the reference
     * @return {@code obj} if not {@code null}
     * @throws NullPointerException if {@code obj} is {@code null}
     */
    public static <T> T requireNonNull(T obj) {
        if (obj == null)
            throw new NullPointerException();
        return obj;
    }

    /**
     * Returns a new read-only list of given elements.
     */
    public static <T> List<T> listOf(T... objects) {
        if (objects.length > 0) {
            return Arrays.asList(objects);
        } else {
            return Collections.emptyList();
        }
    }

    public static <T, R> R find(Map<T, R> map, Function<T, Boolean> predicate) {
        for (T t : map.keySet()) {
            if (predicate.invoke(t)) return map.get(t);
        }
        throw new NoSuchElementException("Couldn't find matching key.");
    }

    public static <T, R> List<T> map(List<R> i, Function<R, T> mapper) {
        if (i.isEmpty()) return Collections.emptyList();

        List<T> output = new ArrayList<>(i.size());
        for (R element : i) {
            output.add(mapper.invoke(element));
        }

        return output;
    }
}
