package ru.ifmo.basecourse.rest;

import org.neo4j.ogm.session.LoadStrategy;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ifmo.basecourse.dto.neo4j.BinaryOperation;
import ru.ifmo.basecourse.dto.neo4j.CompositeRoot;
import ru.ifmo.basecourse.repository.CompositeRuleRepository;
import ru.ifmo.basecourse.repository.OperationRepository;

import javax.inject.Inject;
import javax.ws.rs.GET;
import java.util.*;

/**
 * Author: siziyman
 * Date: 26-May-18.
 */

@RestController
@RequestMapping( "/composites" )
public class GraphRestController {

    @Inject
    public GraphRestController( OperationRepository operationRepository, CompositeRuleRepository ruleRepository,
            SessionFactory sessionFactory ) {
        this.repository = operationRepository;
        this.ruleRepository = ruleRepository;
        this.sessionFactory = sessionFactory;
        this.sessionFactory.setLoadStrategy( LoadStrategy.PATH_LOAD_STRATEGY );
    }

    @RequestMapping( "/getAll" )
    @GET
    public @ResponseBody
    List<BinaryOperation> getAll() {
        Iterable<CompositeRoot> all = ruleRepository.findAll();
        List<BinaryOperation> ops = new ArrayList<>();
        for ( CompositeRoot compositeRoot : all ) {
            ops.add( compositeRoot.getRootLevelOperation() );
        }
        return ops;
    }

    @RequestMapping( value = "/getCompositeRule", params = "id" )
    @GET
    public ResponseEntity<BinaryOperation> getById( @RequestParam Long id ) {
        Optional<CompositeRoot> operation = ruleRepository.findByCompositeRuleId( id );
        return operation.map( compositeRoot -> ResponseEntity.ok( compositeRoot.getRootLevelOperation() ) )
                .orElseGet( () -> ResponseEntity.notFound().build() );
    }

    private OperationRepository repository;

    private CompositeRuleRepository ruleRepository;

    private SessionFactory sessionFactory;

}
