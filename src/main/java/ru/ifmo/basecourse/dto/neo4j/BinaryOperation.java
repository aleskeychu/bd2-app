package ru.ifmo.basecourse.dto.neo4j;

import org.neo4j.ogm.annotation.*;

/**
 * Author: siziyman
 * Date: 25-May-18.
 */

@NodeEntity
public class BinaryOperation {
    @Id
    @GeneratedValue
    Long operationNodeId;

    boolean isRoot = false;

    LogicalOperation binaryOperation;
}
