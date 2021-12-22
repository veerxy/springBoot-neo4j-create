package com.neo4j.access.controller;

import com.neo4j.access.repositories.NodeRepository;
import com.neo4j.access.service.KpiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.neo4j.access.entity.KpiNode;

/**
 * <Description> <br>
 *
 * @author wang.xy<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2021/12/17 <br>
 * @see: com.neo4j.access.controller.nodeController <br>
 */
@RestController
public class nodeController {
    @Autowired
    NodeRepository nodeRepository;
    @Autowired
    KpiService kpiService;

    @GetMapping("/findAllUser")
    public Object findAllUser() {
        Iterable<KpiNode> findAll = nodeRepository.findAll();
        return findAll;
    }

    @GetMapping("/addNodeRelation")
    public void addNodeRelation() throws Exception {
        kpiService.createRelation();
    }
}