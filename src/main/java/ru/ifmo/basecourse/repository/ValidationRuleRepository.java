package ru.ifmo.basecourse.repository;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import ru.ifmo.basecourse.dto.mongo.DtoDeclaration;
import ru.ifmo.basecourse.dto.mongo.ValidationRule;

import java.util.List;

/**
 * Author: siziyman
 * Date: 26-May-18.
 */
public interface ValidationRuleRepository extends MongoRepository<ValidationRule, String> {

    Page<ValidationRule> findAllByValidatee( ObjectId validatee, Pageable pageable );

    Page<ValidationRule> findAllByName(String name, Pageable pageable);
}
