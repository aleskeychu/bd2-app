package ru.ifmo.basecourse.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.ifmo.basecourse.dto.mongo.DtoDeclaration;

import java.util.List;

/**
 * Author: siziyman
 * Date: 26-May-18.
 */


public interface DtoDeclarationRepository extends MongoRepository<DtoDeclaration, String> {

    List<DtoDeclaration> findAllByName( String name );
}
