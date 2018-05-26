package ru.ifmo.basecourse;

import org.neo4j.ogm.config.ClasspathConfigurationSource;
import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import ru.ifmo.basecourse.dto.neo4j.BinaryOperation;
import ru.ifmo.basecourse.repository.OperationRepository;

import java.util.List;

@EnableNeo4jRepositories
//@EnableMongoRepositories
//@EnableCassandraRepositories
@SpringBootApplication
public class BasecourseApplication {

    public static void main( String[] args ) {
        ConfigurableApplicationContext run = SpringApplication.run( BasecourseApplication.class, args );

        OperationRepository bean = run.getBean( OperationRepository.class );

        List<BinaryOperation> allTreeRoots = bean.findAllByIsRoot(true);
        BinaryOperation next = allTreeRoots.iterator().next();
        next.getLeftOperand();
        System.out.println(allTreeRoots);
    }

    @Bean
    SessionFactory sessionFactory() {
        return new SessionFactory( configuration(), "ru.ifmo.basecourse.dto.neo4j" );
    }

    @Bean
    Configuration configuration() {
        return new Configuration.Builder(new ClasspathConfigurationSource("ogm.properties")).build();
    }
}
