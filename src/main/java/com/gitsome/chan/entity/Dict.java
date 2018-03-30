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
@Table(name = "dict")
public class Dict extends BaseEntity{
    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    @Column(name = "id",nullable = false,length = 36,unique = true,columnDefinition="字典编号,唯一")
    private UUID id;
    @Column(name = "dict_code",nullable = false,columnDefinition="归属类型")
    private Integer dictCode ;
    @Column(name = "code",nullable = false,length = 20)
    private String code;
    @Column(name = "parent_code",nullable = false,length = 50,columnDefinition="父级节点")
    private String parentCode;
    @Column(name = "name",nullable = false,length = 50,columnDefinition="名称")
    private String name;

    @Column(name = "path",nullable = false,length = 200,columnDefinition="树状路径")
    private String path = "";
    @Column(name = "desc",nullable = false,length = 200,columnDefinition="描述")
    private String desc = "";

    @Column(name = "ext",nullable = false,length = 1000,columnDefinition="拓展, json存储")
    private String ext;
}
