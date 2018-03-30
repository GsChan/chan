package com.gitsome.chan.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * @author : 130801(cgs)
 * Date : 2018-01-29
 * Time : 10:34
 */
@Data
@Entity
@Table(name = "user")
public class User extends BaseEntity{
    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    @Column(name = "id",nullable = false,length = 36)
    private UUID id;
    @Column(name = "name",nullable = false,length = 100)
    private String name;
    @Column(name = "account",nullable = false,length = 100)
    private String account;
    @Column(name = "password",nullable = false,length = 100)
    private String password;
}
