package com.qz.questionservice.convertor;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Converter
public class ListToStringConvertor implements AttributeConverter<List<String>, String> {

    private final String delimiter = ";";

    @Override
    public List<String> convertToEntityAttribute(String options) {
        return !options.isEmpty()
                ? Arrays.stream(options.split(delimiter))
                .collect(Collectors.toList())
                : List.of();
    }

    @Override
    public String convertToDatabaseColumn(List<String> options) {
        return !options.isEmpty()
                ? String.join(delimiter, options)
                : "";
    }
}
