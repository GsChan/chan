package com.gitsome.chan.core.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author : 130801(cgs)
 * Date : 2018-02-11
 * Time : 15:42
 */
public enum StructureType {

    TREE(1,"树"),
    LIST(0,"列表");

    private Integer type;
    private String name;

    StructureType(Integer type,String name){
        this.type = type;
        this.name = name;
    }
    public static StructureType valueOf(Integer type) {
        for (StructureType structureType : values()) {
            if (structureType.getType().equals(type)) {
                return structureType;
            }
        }
        throw new RuntimeException("structureType (" + type + ") not exists");
    }

    @JsonValue
    public Integer getType() {
        return type;
    }
}
