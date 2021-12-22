package com.neo4j.access.service;

import com.neo4j.access.entity.KpiNode;
import com.neo4j.access.entity.KpiRelation;
import org.neo4j.ogm.model.Result;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.HashMap;

/**
 * <Description> <br>
 *
 * @author wang.xy<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2021/12/17 <br>
 * @see: com.neo4j.access.service.kpiService <br>
 */
@Service
public class KpiService {

    @Autowired
    private SessionFactory sessionFactory;

    public void createRelation() {
        Session session = sessionFactory.openSession();
        // 1.创建父节点
        KpiNode rDad = new KpiNode();
        rDad.setName("002节点");
        rDad.setLabel("Node");
        rDad.setLevel("BI");
        // 2.创建子节点
        KpiNode rSon = new KpiNode();
        rSon.setName("004节点");
        rSon.setLabel("Node");
        rSon.setLevel("VI");
        // 3.创建关系
        KpiRelation edge = new KpiRelation();
        edge.setValue("0.65");
        edge.setLabel("Relationship");
        edge.setType("indicatorReverse");
        // 4.进行创建
        createNode(rDad, session);
        createNode(rSon, session);
        createRelation(edge, rSon, rDad, session);
        System.out.println("创建成功");

    }

    /**
     * 创建节点
     *
     * @param rNode
     * @throws Exception
     */
    public static void createNode(KpiNode rNode, Session session) {
        Result srcNode = queryNode(rNode, session);
        //查node是否已經存在了，不存在則創建
        if(CollectionUtils.isEmpty((Collection<?>) srcNode.queryResults())){
            String cypherSql = "create (:" + rNode.getLabel() + "{name:'" + rNode.getName() + "',type:'" + rNode.getLevel() + "'})";
//            String cypherSql = String.format("create (:%s{name:%s,type:%s})", rNode.getLabel(), rNode.getName(), rNode.getNodeType());
            System.out.println(cypherSql);
            session.query(cypherSql, new HashMap<>());
            System.out.println("创建节点：" + rNode.getLabel() + "成功！");
        } else {
            System.err.println("节点已存在，跳过创建");
        }
    }

    /**
     * 创建关系
     *
     * @param srcNode
     * @param tarNode
     * @throws Exception
     */
    public static void createRelation(KpiRelation kRela, KpiNode srcNode, KpiNode tarNode, Session session) {
        Result edge = queryRelation(srcNode, tarNode, kRela, session);
        if(CollectionUtils.isEmpty((Collection<?>) edge.queryResults())){
            String cypherSql = "match(a),(b) where a.name='" + srcNode.getName() + "'and b.name='" + tarNode.getName() + "' create (a)-[r:" +
                    kRela.getLabel() + "{value:'" + kRela.getValue() + "', type:'" + kRela.getType() + "'}]->(b)";
            System.out.println(cypherSql);
            session.query(cypherSql, new HashMap<>());
            System.err.println("创建关系：" + kRela.getType() + "成功！");
        } else {
            System.err.println("关系已存在，跳过创建");
        }
    }

    /**
     * 查询节点
     *
     * @param rNode
     * @return
     */
    public static Result queryNode(KpiNode rNode, Session session) {

        String cypherSql = "match(n:" + rNode.getLabel() + ") where n.name = '" + rNode.getName() + "' return n";
        Result result = session.query(cypherSql, new HashMap<>());
        return result;
    }

    /**
     * 查询关系
     *
     * @return
     */
    public static Result queryRelation(KpiNode srcNode, KpiNode tarNode, KpiRelation krela, Session session) {
        String cypherSql = "match(n)-[r:" + krela.getLabel() + "]-(b) where n.name = '" + srcNode.getName() + "' and b.name = '" +
                tarNode.getName() + "' and r.type = '" + krela.getType() + "' return r";
        Result result = session.query(cypherSql, new HashMap<>());
        return result;
    }

}