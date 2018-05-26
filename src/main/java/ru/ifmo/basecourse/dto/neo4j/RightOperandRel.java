package ru.ifmo.basecourse.dto.neo4j;

import org.neo4j.ogm.annotation.*;

/**
 * Author: siziyman
 * Date: 26-May-18.
 */
@RelationshipEntity( type = "toRightOperand" )
public class RightOperandRel {

    @Id
    private Long id;

    public BinaryOperation getParentOperation() {
        return parentOperation;
    }

    public void setParentOperation( BinaryOperation parentOperation ) {
        this.parentOperation = parentOperation;
    }

    public BinaryOperation getRightChildOperation() {
        return rightChildOperation;
    }

    public void setRightChildOperation( BinaryOperation rightChildOperation ) {
        this.rightChildOperation = rightChildOperation;
    }

    @StartNode
    private BinaryOperation parentOperation;

    @EndNode
    private BinaryOperation rightChildOperation;
}
