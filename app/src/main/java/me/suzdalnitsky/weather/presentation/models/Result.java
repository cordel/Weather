package me.suzdalnitsky.weather.presentation.models;

public class Result<T> {

    private T data;
    private Throwable error;

    public static <T> Result success(T data) {
        Result result = new Result();
        result.data = data;
        return result;
    }

    public static Result error(Throwable error) {
        Result result = new Result();
        result.error = error;
        return result;
    }

    public boolean isError() {
        return error != null;
    }

    public T getData() {
        return data;
    }

    public Throwable getError() {
        return error;
    }
}