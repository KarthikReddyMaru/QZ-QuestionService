package com.qz.questionservice.mapper;

public interface Mapper<T, V> {
    V map(T obj);
}
