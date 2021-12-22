package com.neo4j.access;


import org.neo4j.ogm.session.SessionFactory;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * <Description> <br>
 *
 * @author wang.xy<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2021/12/17 <br>
 * @see: com.neo4j.access.Neo4jConfig <br>
 */
@Configuration
@EnableNeo4jRepositories(basePackages = "com.neo4j.access.repositories")// 开启Neo4jRepositories的支持
@EntityScan(basePackages = "com.neo4j.access.entity")// 开启实体类扫描
@EnableTransactionManagement // 开启事务
public class Neo4jConfig {
    @Bean
    public SessionFactory sessionFactory() {
        // with domain entity base package(s)
        return new SessionFactory(configuration(),"com.neo4j.access.entity");
    }

    @Bean
    public org.neo4j.ogm.config.Configuration configuration() {
        // ConfigurationSource properties = new
        // ClasspathConfigurationSource("ogm.properties");
        org.neo4j.ogm.config.Configuration configuration = new org.neo4j.ogm.config.Configuration.Builder()
                .uri("bolt://127.0.0.1:7687").credentials("neo4j", "NEO4j").build();
        return configuration;
    }

    @Bean
    public Neo4jTransactionManager transactionManager() {
        return new Neo4jTransactionManager(sessionFactory());
    }
}