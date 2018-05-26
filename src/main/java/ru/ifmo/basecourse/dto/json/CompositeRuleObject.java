package ru.ifmo.basecourse.dto.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.ifmo.basecourse.dto.neo4j.LogicalOperation;

/**
 * Author: siziyman
 * Date: 26-May-18.
 */

public class CompositeRuleObject {

    public LogicalOperation getOperation() {
        return operation;
    }

    public Long getRuleId() {
        return ruleId;
    }

    public CompositeRuleObject getLeftOperand() {
        return leftOperand;
    }

    public CompositeRuleObject getRightOperand() {
        return rightOperand;
    }

    public String getName() {
        return name;
    }

    public String getBaseName() {
        return baseName;
    }

    public String getDescription() {
        return description;
    }

    @JsonProperty
    private LogicalOperation operation;

    @JsonProperty
    private Long ruleId;

    @JsonProperty
    private CompositeRuleObject leftOperand;

    @JsonProperty
    private CompositeRuleObject rightOperand;

    @JsonProperty
    private String name;

    @JsonProperty
    private String baseName;

    @JsonProperty

    private String description;

}
