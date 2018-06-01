package ru.ifmo.basecourse.rest;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ifmo.basecourse.dto.mongo.*;
import ru.ifmo.basecourse.repository.DtoDeclarationRepository;
import ru.ifmo.basecourse.repository.ValidationRuleRepository;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Author: siziyman
 * Date: 26-May-18.
 */

@RestController
@RequestMapping( "/basic" )
public class MongoRestController {

    @Inject
    public MongoRestController( DtoDeclarationRepository dtoDeclarationRepository, ValidationRuleRepository
            validationRuleRepository ) {
        this.dtoDeclarationRepository = dtoDeclarationRepository;
        this.validationRuleRepository = validationRuleRepository;
    }

    @RequestMapping( "/getDtos" )
    @GET
    public @ResponseBody
    List<DtoDeclaration> getDtos(
            @RequestParam( required = false ) Integer page,
            @RequestParam( required = false ) Integer count ) {
        if ( page == null || page < 0 ) {
            page = 0;
        }
        if ( count == null || count < 0 ) {
            count = 10;
        }
        Page<DtoDeclaration> all = dtoDeclarationRepository.findAll( PageRequest.of( page, count ) );
        return all.getContent();
    }

    @RequestMapping( "/getRules" )
    @GET
    public @ResponseBody
    List<ValidationRule> getRules(
            @RequestParam( required = false ) Integer page,
            @RequestParam( required = false ) Integer count ) {
        if ( page == null || page < 0 ) {
            page = 0;
        }
        if ( count == null || count < 0 ) {
            count = 10;
        }
        Page<ValidationRule> all = validationRuleRepository.findAll( PageRequest.of( page, count ) );
        return all.getContent();
    }

    @RequestMapping( "/deleteRule" )
    @POST
    public ResponseEntity<String> deleteRuleById( @RequestParam Long ruleId ) {
        if ( ruleId <= 0 ) {
            return ResponseEntity.badRequest().body( "incorrect ID" );
        }
        if ( validationRuleRepository.findByRuleId( ruleId ) != null ) {
            validationRuleRepository.deleteByRuleId( ruleId );
            return ResponseEntity.ok( "ok" );
        }
        return ResponseEntity.notFound().build();
    }

    @RequestMapping( "/deleteDto" )
    @POST
    public ResponseEntity<String> deleteDtoById( @RequestParam String id ) {
        if ( id == null || id.isEmpty() ) {
            return ResponseEntity.badRequest().body( "incorrect ID" );
        }
        if ( dtoDeclarationRepository.findById( id ).isPresent() ) {
            dtoDeclarationRepository.deleteById( id );
            return ResponseEntity.ok( "ok" );
        }
        return ResponseEntity.notFound().build();
    }

    @RequestMapping( "/getDtoByName" )
    @GET
    public ResponseEntity<List<DtoDeclaration>> getDtosByName( @RequestParam String name ) {
        if ( name.isEmpty() ) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok( dtoDeclarationRepository.findAllByName( name ) );
    }

    @RequestMapping( "/getRulesByTargetObject" )
    @GET
    public ResponseEntity<List<ValidationRule>> getRuleByValidatee( @RequestParam String id ) {
        if ( id.isEmpty() ) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok( validationRuleRepository.findAllByValidatee( new ObjectId( id ) ) );
    }

    @RequestMapping( "/getRulesByName" )
    @GET
    public ResponseEntity<List<ValidationRule>> getRuleByName( @RequestParam String name ) {
        if ( name.isEmpty() ) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok( validationRuleRepository.findAllByName( name ) );
    }

    @RequestMapping( "/addRule" )
    @POST
    public ResponseEntity<String> addRule( @RequestParam String name, @RequestParam(required = false) String description, @RequestParam
            String function, @RequestParam String objectId ) {

        if ( description == null ) {
            description = "";
        }
        if ( name.isEmpty() || function.isEmpty() || objectId.isEmpty() ) {
            return ResponseEntity.badRequest().body( "invalid input" );
        }

        ObjectId validatee = null;
        try {
            validatee = new ObjectId( objectId );
        }
        catch ( Exception e ) {
            return ResponseEntity.badRequest().body( "invalid objectId" );
        }
        ValidationRule rule = new ValidationRule( name, description, function, validatee );
        ValidationRule insert = validationRuleRepository.insert( rule );
        return ResponseEntity.ok( "ok" );
    }

    @RequestMapping( "/addDto" )
    @POST
    public ResponseEntity<String> addDto( @RequestParam String name, @RequestParam(name = "fields") String fieldString) {
        if (name.isEmpty() ||fieldString.length()< 1) {
            return ResponseEntity.badRequest().body("invalid input");
        }

        String[] fields = fieldString.split(",");
        List<String> strings = Arrays.asList( fields );
        List<String> fieldsList = strings.stream().filter( it -> !it.isEmpty() ).collect( Collectors.toList() );
        if (fieldsList.isEmpty()) {
            return ResponseEntity.badRequest().body( "invalid field set" );
        }
        DtoDeclaration dto = new DtoDeclaration( fieldsList, name );
        DtoDeclaration insert = dtoDeclarationRepository.insert( dto );
        return ResponseEntity.ok( "ok" );
    }


    private DtoDeclarationRepository dtoDeclarationRepository;

    private ValidationRuleRepository validationRuleRepository;
}
