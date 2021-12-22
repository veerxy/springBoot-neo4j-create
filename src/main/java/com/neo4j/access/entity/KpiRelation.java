package com.neo4j.access.entity;

import lombok.Data;
import org.neo4j.ogm.annotation.Id;

/**
 * <Description> <br>
 *
 * @author wang.xy<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2021/12/16 <br>
 * @see: com.example.springboot1.bean.corr.KpiRelationEntity <br>
 */
@Data
public class KpiRelation {
    @Id
    private Long id;

    private String value;

    private String label;

    private String type;

//    public KpiRelation(String value, String type) {
//        this.value = value;
//        this.type = type;
//    }

}