package com.expertsoft.phoneshop.service.convertor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

@Component
public class SortConvertor implements Converter<String, Sort> {
    private static final Pattern PATTERN = Pattern.compile("([A-z]+)-(asc|desc)");

    @Override
    public Sort convert(String source) {
        Sort result = Sort.unsorted();
        if (source != null) {
            Matcher matcher = PATTERN.matcher(source);
            if (matcher.matches()) {
                result = Sort.by(Direction.fromString(matcher.group(2)), matcher.group(1));
            }
        }
        return result;
    }

}
