package com.neo4j.access.entity;

import lombok.Data;
import org.neo4j.ogm.annotation.NodeEntity;
import org.springframework.data.annotation.Id;

/**
 * <Description> <br>
 *
 * @author wang.xy<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2021/12/16 <br>
 * @see: com.example.springboot1.bean.corr.KpiNodeEntity <br>
 */
@Data
@NodeEntity
public class KpiNode {

    @Id
    private Long id;
    // 节点名称
    private String name;
    // 节点label
    private String label;
    // 节点层级
    private String level;
    // 节点场景
    private String scene;

//    public KpiNode(String name, String type) {
//        this.name = name;
//        this.type = type;
//    }

}