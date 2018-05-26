package ru.ifmo.basecourse.dto.neo4j;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.neo4j.ogm.annotation.*;

/**
 * Author: siziyman
 * Date: 25-May-18.
 */

@NodeEntity( label = "BinaryOperation" )
public class TreeBinaryOperation {

    public Long getOperationNodeId() {
        return operationNodeId;
    }

    public void setOperationNodeId( Long operationNodeId ) {
        this.operationNodeId = operationNodeId;
    }

    public Long getRuleId() {
        return ruleId;
    }

    public void setRuleId( Long ruleId ) {
        this.ruleId = ruleId;
    }

    public boolean isRoot() {
        return isRoot;
    }

    public void setRoot( boolean root ) {
        isRoot = root;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public LogicalOperation getBinaryOperation() {
        return binaryOperation;
    }

    public void setBinaryOperation( LogicalOperation binaryOperation ) {
        this.binaryOperation = binaryOperation;
    }

    public TreeBinaryOperation getLeftOperand() {
        return leftOperand;
    }

    public void setLeftOperand( TreeBinaryOperation leftOperand ) {
        this.leftOperand = leftOperand;
    }

    public TreeBinaryOperation getRightOperand() {
        return rightOperand;
    }

    public void setRightOperand( TreeBinaryOperation rightOperand ) {
        this.rightOperand = rightOperand;
    }

    @Id
    @GeneratedValue
    @JsonProperty
    private Long operationNodeId;

    @Property
    @JsonProperty
    private Long ruleId;

    @Property
    @JsonProperty
    private boolean isRoot = false;

    @Property
    @JsonProperty
    private String name;

    @Property
    @JsonProperty
    private LogicalOperation binaryOperation;

    @Relationship( type = "toLeftOperand", direction = "OUTGOING" )
    private TreeBinaryOperation leftOperand;

    @Relationship( type = "toRightOperand", direction = "OUTGOING" )
    private TreeBinaryOperation rightOperand;
}
