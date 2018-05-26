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

    public TreeBinaryOperation getRootLevelOperation() {
        return rootLevelOperation;
    }

    public void setRootLevelOperation( TreeBinaryOperation rootLevelOperation ) {
        this.rootLevelOperation = rootLevelOperation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription( String description ) {
        this.description = description;
    }

    @Id
    @GeneratedValue
    @JsonProperty
    private Long compositeRuleId;

    @Relationship( type = "ToUpperLevelOperation" )
    @JsonProperty
    private TreeBinaryOperation rootLevelOperation;

    @Property
    @JsonProperty
    private String name;

    @Property
    @JsonProperty
    private String description;
}
