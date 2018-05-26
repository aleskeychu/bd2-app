package ru.ifmo.basecourse.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.annotation.Depth;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import ru.ifmo.basecourse.dto.neo4j.CompositeRoot;

import java.util.Optional;

/**
 * Author: siziyman
 * Date: 26-May-18.
 */
public interface CompositeRuleRepository extends Neo4jRepository<CompositeRoot, Long> {

    Optional<CompositeRoot> findByCompositeRuleId( Long aLong);
}
