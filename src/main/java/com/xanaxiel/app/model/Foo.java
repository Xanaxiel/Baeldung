package com.xanaxiel.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Foo{
    Long id;
    String name;

    public static Foo defaultFoo() {
        return new Foo(1L, "Foo");
    }

}
