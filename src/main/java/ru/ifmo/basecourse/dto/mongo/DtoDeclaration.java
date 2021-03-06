package ru.ifmo.basecourse.dto.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Author: siziyman
 * Date: 26-May-18.
 */

@Document(collection = "dtos")
public class DtoDeclaration {

    public DtoDeclaration( List<String> fields, String name ) {
        this.fields = fields;
        this.name = name;
    }

    public List<String> getFields() {
        return fields;
    }

    public void setFields( List<String> fields ) {
        this.fields = fields;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId( String id ) {
        this.id = id;
    }

    @Id
    private String id;

    private List<String> fields;

    private String name;
}
