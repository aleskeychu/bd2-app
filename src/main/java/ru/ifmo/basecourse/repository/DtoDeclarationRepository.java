package ru.ifmo.basecourse.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.ifmo.basecourse.dto.mongo.DtoDeclaration;

/**
 * Author: siziyman
 * Date: 26-May-18.
 */


public interface DtoDeclarationRepository extends MongoRepository<DtoDeclaration, String> {

}
