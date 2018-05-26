package ru.ifmo.basecourse.dto.neo4j;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.neo4j.ogm.annotation.*;

/**
 * Author: siziyman
 * Date: 26-May-18.
 */

@NodeEntity
public class CompositeRoot {

    public Long getCompositeRuleId() {
        return compositeRuleId;
    }

    public void setCompositeRuleId( Long compositeRuleId ) {
        this.compositeRuleId = compositeRuleId;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public BinaryOperation getRootLevelOperation() {
        return rootLevelOperation;
    }

    public void setRootLevelOperation( BinaryOperation rootLevelOperation ) {
        this.rootLevelOperation = rootLevelOperation;
    }

    @Id
    @GeneratedValue
    @JsonProperty
    private Long compositeRuleId;

    @Property
    @JsonProperty
    private String name;

    @Relationship(type="ToUpperLevelOperation")
    @JsonProperty
    private BinaryOperation rootLevelOperation;
}
