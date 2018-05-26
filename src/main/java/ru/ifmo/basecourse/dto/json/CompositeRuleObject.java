package ru.ifmo.basecourse.dto.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.ifmo.basecourse.dto.neo4j.BinaryOperation;
import ru.ifmo.basecourse.dto.neo4j.LogicalOperation;

/**
 * Author: siziyman
 * Date: 26-May-18.
 */


public class CompositeRuleObject {

    @JsonProperty
    private LogicalOperation operation;

    @JsonProperty
    private Long ruleId;

    @JsonProperty
    private CompositeRuleObject leftOperand;

    @JsonProperty
    private CompositeRuleObject rightOperand;

}
