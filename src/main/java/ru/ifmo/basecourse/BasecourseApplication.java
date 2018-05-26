package ru.ifmo.basecourse;

import org.neo4j.ogm.config.ClasspathConfigurationSource;
import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.session.LoadStrategy;
import org.neo4j.ogm.session.SessionFactory;
import org.neo4j.ogm.transaction.TransactionManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.ifmo.basecourse.dto.neo4j.TreeBinaryOperation;
import ru.ifmo.basecourse.repository.OperationRepository;

import java.util.List;

@EnableNeo4jRepositories
@EnableTransactionManagement
@EnableMongoRepositories
//@EnableCassandraRepositories
//@EnableMongoRepositories
@EnableCassandraRepositories
@SpringBootApplication
public class BasecourseApplication {

    public static void main( String[] args ) {
        ConfigurableApplicationContext run = SpringApplication.run( BasecourseApplication.class, args );

        OperationRepository bean = run.getBean( OperationRepository.class );

        List<TreeBinaryOperation> allTreeRoots = bean.findAllByIsRoot(true);
        System.out.println(allTreeRoots);
    }

    @Bean
    SessionFactory sessionFactory() {
        if (factory == null) {
            SessionFactory sessionFactory = new SessionFactory( configuration(), "ru.ifmo.basecourse.dto.neo4j" );
            sessionFactory.setLoadStrategy( LoadStrategy.PATH_LOAD_STRATEGY );
            factory = sessionFactory;
        }
        return factory;

    }

    @Bean
    Configuration configuration() {
        return new Configuration.Builder(new ClasspathConfigurationSource("ogm.properties")).build();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        Neo4jTransactionManager transactionManager = new Neo4jTransactionManager(  );
        transactionManager.setSessionFactory(sessionFactory() );
//        transactionManager.setBeanFactory(  );
        return transactionManager;
    }

    private static SessionFactory factory = null;
}
