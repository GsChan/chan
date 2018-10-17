package com.gitsome.chan.entity;

import lombok.Data;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

import java.util.UUID;

/**
 * 字典类型表
 * <p>
 * @author gitsome<br>
 * 
 */
@Data
@Entity
@Table(name="dict")
public class Dict {
    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="dict_id")
    private Long dictId ;
    /**
     * ID
     */
    @Column(name="dict_type_id")
    private String dictTypeId ;
    /**
     * 类型编号
     */
    @Column(name="code")
    private String code ;
    /**
     * 名称
     */
    @Column(name="name")
    private String name ;
    /**
     * 创建时间
     */
    @Column(name="parent_id")
    private String parentId ;
    /**
     * 更新时间
     */
    @Column(name="path")
    private Date path ;
    /**
     * 创建时间
     */
    @Column(name="create_time")
    private Date createTime ;
    /**
     * 更新时间
     */
    @Column(name="update_time")
    private Date updateTime ;
}

