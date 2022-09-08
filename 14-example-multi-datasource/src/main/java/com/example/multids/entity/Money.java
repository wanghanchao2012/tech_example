package com.example.multids.entity;

import lombok.Data;

import java.util.StringJoiner;

@Data
public class Money {

    private String id;
    private String name;
    private String money;
    private String is_deleted;
    private String create_at;
    private String update_at;


    @Override
    public String toString() {
        return new StringJoiner(", ", Money.class.getSimpleName() + "[", "]")
                .add("id='" + id + "'")
                .add("name='" + name + "'")
                .add("money='" + money + "'")
                .add("is_deleted='" + is_deleted + "'")
                .add("create_at='" + create_at + "'")
                .add("update_at='" + update_at + "'")
                .toString();
    }
}
