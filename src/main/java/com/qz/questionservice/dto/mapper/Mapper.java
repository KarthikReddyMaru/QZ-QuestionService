package com.qz.questionservice.dto.mapper;

public interface Mapper<T, V> {
    V map(T obj);
}
