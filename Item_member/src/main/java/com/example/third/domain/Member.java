package com.example.third.domain;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Member {
    private Long id;
    private String loginId;
    private String name;
    private String password;

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", loginId='" + loginId + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
