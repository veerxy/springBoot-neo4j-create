package com.neo4j.access.repositories;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import com.neo4j.access.entity.KpiNode;
/**
 * <Description> <br>
 *
 * @author wang.xy<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2021/12/17 <br>
 * @see: com.neo4j.access.repositories.NodeRepository <br>
 */
@Repository
public interface NodeRepository extends Neo4jRepository<KpiNode, Long> {

}