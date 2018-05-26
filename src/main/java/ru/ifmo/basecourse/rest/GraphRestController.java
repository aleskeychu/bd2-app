package ru.ifmo.basecourse.rest;

import org.neo4j.ogm.session.SessionFactory;
import org.neo4j.ogm.transaction.TransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ifmo.basecourse.dto.json.CompositeRuleObject;
import ru.ifmo.basecourse.dto.neo4j.CompositeRoot;
import ru.ifmo.basecourse.dto.neo4j.TreeBinaryOperation;
import ru.ifmo.basecourse.repository.CompositeRuleRepository;
import ru.ifmo.basecourse.repository.OperationRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.lang.reflect.MalformedParametersException;
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
    }

    @RequestMapping( "/getAll" )
    @GET
    public @ResponseBody
    List<TreeBinaryOperation> getAll(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer count) {
        if (page == null || page < 0) {
            page = 0;
        }
        if (count == null || count < 0) {
            count = 10;
        }
        Iterable<CompositeRoot> all = ruleRepository.findAll(PageRequest.of( page, count ), -1 );
        List<TreeBinaryOperation> ops = new ArrayList<>();
        for ( CompositeRoot compositeRoot : all ) {
            ops.add( compositeRoot.getRootLevelOperation() );
        }
        return ops;
    }

    @RequestMapping( value = "/getRule", params = "id" )
    @GET
    public ResponseEntity<TreeBinaryOperation> getById( @RequestParam Long id ) {
        Optional<CompositeRoot> operation = ruleRepository.findByCompositeRuleId( id );
        return operation.map( compositeRoot -> ResponseEntity.ok( compositeRoot.getRootLevelOperation() ) )
                .orElseGet( () -> ResponseEntity.notFound().build() );
    }

    @RequestMapping( value = "/addRule" )
    @Consumes("application/json")
    @POST
    public ResponseEntity<String> addRule(@RequestBody CompositeRuleObject ruleObject ) {

        CompositeRoot root = new CompositeRoot();
        TreeBinaryOperation operation;
        try {
            operation = buildOperation( ruleObject );
            operation.setRoot( true );
            root.setRootLevelOperation( operation );
        } catch ( MalformedParametersException e ) {
            return ResponseEntity.badRequest().body( "invalid request body" );
        }
        if ( ruleObject.getBaseName() == null || ruleObject.getBaseName().isEmpty() ) {
            return ResponseEntity.badRequest().body( "invalid ruleObject body, missing required parameter baseName" );
        }
        root.setName( ruleObject.getBaseName() );
        root.setDescription( ruleObject.getDescription() );
        repository.save( operation );
        ruleRepository.save( root );
        return ResponseEntity.ok( "ok" );
    }

    private TreeBinaryOperation buildOperation( CompositeRuleObject ruleObject ) {
        TreeBinaryOperation operation = new TreeBinaryOperation();
        operation.setName( ruleObject.getName() );
        operation.setRuleId( ruleObject.getRuleId() );
        operation.setBinaryOperation( ruleObject.getOperation() );
        if ( ruleObject.getLeftOperand() != null ) {
            if ( ruleObject.getRightOperand() == null || operation.getRuleId() != null ) {
                throw new MalformedParametersException();
            }
            operation.setLeftOperand( buildOperation( ruleObject.getLeftOperand() ) );
        }
        if ( ruleObject.getRightOperand() != null ) {
            operation.setRightOperand( buildOperation( ruleObject.getRightOperand() ) );
        }
        return operation;
    }



    private OperationRepository repository;

    private CompositeRuleRepository ruleRepository;

    private SessionFactory sessionFactory;

}
