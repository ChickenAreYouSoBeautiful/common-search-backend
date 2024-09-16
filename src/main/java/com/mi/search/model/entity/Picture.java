package com.mi.search.model.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author mi11
 * @version 1.0
 * @project backend-administration-template
 * @description
 * @ClassName Picture
 */
@Data
public class Picture implements Serializable {
    private String title;

    private String url;
    private static final long serialVersionUID = 1L;
}
