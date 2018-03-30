package com.gitsome.chan.entity;

import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

/**
 * @author : 130801(cgs)
 * Date : 2018-02-11
 * Time : 16:06
 */
@Data
public class BaseEntity {
    @Column(name = "create_time",nullable = false,columnDefinition="创建时间")
    private Date createTime;
    @Column(name = "update_time",nullable = false,columnDefinition="字修改时间")
    private Date updateTime;
}
