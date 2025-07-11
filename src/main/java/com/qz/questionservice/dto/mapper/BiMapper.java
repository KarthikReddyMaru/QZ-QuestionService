package com.qz.questionservice.dto.mapper;

public interface BiMapper<T, V> {
    V mapToDto(T entity);
    T mapToEntity(V dto);
}
