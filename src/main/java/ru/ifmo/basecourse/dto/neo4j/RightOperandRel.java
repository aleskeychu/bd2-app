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

    public TreeBinaryOperation getParentOperation() {
        return parentOperation;
    }

    public void setParentOperation( TreeBinaryOperation parentOperation ) {
        this.parentOperation = parentOperation;
    }

    public TreeBinaryOperation getRightChildOperation() {
        return rightChildOperation;
    }

    public void setRightChildOperation( TreeBinaryOperation rightChildOperation ) {
        this.rightChildOperation = rightChildOperation;
    }

    @StartNode
    private TreeBinaryOperation parentOperation;

    @EndNode
    private TreeBinaryOperation rightChildOperation;
}
