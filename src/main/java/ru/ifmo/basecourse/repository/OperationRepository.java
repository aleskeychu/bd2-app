package ru.ifmo.basecourse.repository;

import org.springframework.data.neo4j.annotation.Depth;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import ru.ifmo.basecourse.dto.neo4j.TreeBinaryOperation;

import java.util.List;

/**
 * Author: siziyman
 * Date: 26-May-18.
 */

public interface OperationRepository extends Neo4jRepository<TreeBinaryOperation, Long> {

    @Depth(-1)
    List<TreeBinaryOperation> findAllByIsRoot(boolean root);
}
