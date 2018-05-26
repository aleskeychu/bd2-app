package ru.ifmo.basecourse.dto.mongo;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Author: siziyman
 * Date: 26-May-18.
 */

@Document( collection = "rules" )
public class ValidationRule {

    public String getId() {
        return id;
    }

    public void setId( String id ) {
        this.id = id;
    }

    public Long getRuleId() {
        return ruleId;
    }

    public void setRuleId( Long ruleId ) {
        this.ruleId = ruleId;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription( String description ) {
        this.description = description;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction( String function ) {
        this.function = function;
    }

    public ObjectId getValidatee() {
        return validatee;
    }

    public void setValidatee( ObjectId validatee ) {
        this.validatee = validatee;
    }

    @Id
    private String id;

    private Long ruleId;

    private String name;

    private String description;

    private String function;

    private ObjectId validatee;

}