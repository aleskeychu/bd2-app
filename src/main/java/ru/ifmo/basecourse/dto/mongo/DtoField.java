package ru.ifmo.basecourse.dto.mongo;

import java.io.Serializable;

/**
 * Author: siziyman
 * Date: 26-May-18.
 */
public class DtoField implements Serializable {

    public DtoField( String name ) {
        this.name = name;
    }

    public String getName() {

        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    private String name;
}
