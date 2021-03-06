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

    public TreeBinaryOperation getParentOperation() {
        return parentOperation;
    }

    public void setParentOperation( TreeBinaryOperation parentOperation ) {
        this.parentOperation = parentOperation;
    }

    public TreeBinaryOperation getLeftChildOperation() {
        return leftChildOperation;
    }

    public void setLeftChildOperation( TreeBinaryOperation leftChildOperation ) {
        this.leftChildOperation = leftChildOperation;
    }

    @StartNode
    private TreeBinaryOperation parentOperation;

    @EndNode
    private TreeBinaryOperation leftChildOperation;
}
