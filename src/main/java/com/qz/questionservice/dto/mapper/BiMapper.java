package com.qz.questionservice.dto.mapper;

import com.qz.questionservice.dto.Dto;

public interface BiMapper<T, V extends Dto> {
    V mapToDto(T entity);
    T mapToEntity(V dto);
}
