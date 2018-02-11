package com.gitsome.chan.entity;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

/**
 * @author : 130801(cgs)
 * Date : 2018-01-29
 * Time : 10:34
 */
@Data
@Entity
@Table(name = "user")
public class DictType {
    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    @Column(name = "id",nullable = false,length = 36,unique = true,columnDefinition="类型编号,唯一")
    private UUID id;
    @Column(name = "type",nullable = false,columnDefinition="结构类型")
    private Integer type ;
    @Column(name = "code",nullable = false,length = 20)
    private String code;
    @Column(name = "name",nullable = false,length = 20,columnDefinition="名称")
    private String name;
    @Column(name = "desc",nullable = false,length = 200,columnDefinition="描述")
    private String desc = "";
    @Column(name = "ext",nullable = false,length = 1000,columnDefinition="拓展, json存储")
    private String ext;
}
