package ru.ifmo.basecourse.dto.neo4j;

import org.neo4j.ogm.annotation.*;

/**
 * Author: siziyman
 * Date: 26-May-18.
 */

@RelationshipEntity( type = "toLeftOperand" )
public class LeftOperandRel {

    @Id
    private Long id;

    public BinaryOperation getParentOperation() {
        return parentOperation;
    }

    public void setParentOperation( BinaryOperation parentOperation ) {
        this.parentOperation = parentOperation;
    }

    public BinaryOperation getLeftChildOperation() {
        return leftChildOperation;
    }

    public void setLeftChildOperation( BinaryOperation leftChildOperation ) {
        this.leftChildOperation = leftChildOperation;
    }

    @StartNode
    private BinaryOperation parentOperation;

    @EndNode
    private BinaryOperation leftChildOperation;
}
