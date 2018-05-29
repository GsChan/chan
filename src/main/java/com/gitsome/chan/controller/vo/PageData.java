package com.gitsome.chan.controller.vo;

import lombok.Data;

import java.util.List;

/**
 * @author : CGS
 * Date : 2018-04-01
 * Time : 19:04
 */
@Data
public class PageData<T> {
    private List<T> data;
    private Integer count;
}
