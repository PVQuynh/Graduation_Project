package com.example.hust_learning_server.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

public class PageUtils {

    public static Pageable getPageable(Integer page, Integer size, String orderBy, boolean ascending) {
        if (StringUtils.isNoneBlank(orderBy)) {
            Sort sortable;
            if (ascending) {
                sortable = Sort.by(Direction.ASC, orderBy);
            } else {
                sortable = Sort.by(Direction.DESC, orderBy);
            }
            return PageRequest.of(page, size, sortable);
        } else {
            return PageRequest.of(page, size);
        }
    }
}
