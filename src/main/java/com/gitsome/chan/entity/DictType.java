package com.gitsome.chan.entity;

import lombok.Data;
import org.hibernate.annotations.Type;

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
@Table(name="dict_type")
public class DictType {
    /**
     * 字典类型ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id ;
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
     * 类型(0列表 1树)
     */
    @Column(name="type")
    private Integer type ;
    /**
     * create_time
     */
    @Column(name="create_time")
    private String createTime ;
    /**
     * 更新时间
     */
    @Column(name="update_time")
    private Date updateTime ;
}

