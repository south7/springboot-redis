package com.example.springbootredis.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class User  implements Serializable {

    private String id;
    private String userName;
}
