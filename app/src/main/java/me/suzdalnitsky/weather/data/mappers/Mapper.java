package me.suzdalnitsky.weather.data.mappers;

import java.util.List;

import me.suzdalnitsky.weather.util.Util;

public interface Mapper<I, O> {

    O map(I i);

    default List<O> map(List<I> i) {
        return Util.map(i, this::map);
    }
}
