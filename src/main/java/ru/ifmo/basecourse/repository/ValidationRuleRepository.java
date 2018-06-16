package ru.ifmo.basecourse.repository;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import ru.ifmo.basecourse.dto.mongo.ValidationRule;

import java.util.List;

/**
 * Author: siziyman
 * Date: 26-May-18.
 */
public interface ValidationRuleRepository extends MongoRepository<ValidationRule, String> {

    Page<ValidationRule> findAll( Pageable pageable );

    List<ValidationRule> findAllByValidatee( ObjectId validatee );

    List<ValidationRule> findAllByName( String name );

    ValidationRule findByRuleId( ObjectId ruleId );

    void deleteByRuleId( ObjectId ruleId );

    void deleteByValidatee( ObjectId validatee );
}
