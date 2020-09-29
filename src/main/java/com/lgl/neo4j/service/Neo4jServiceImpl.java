package com.lgl.neo4j.service;

import java.util.Iterator;
import java.util.List;

import com.lgl.neo4j.node.StudentNode;
import com.lgl.neo4j.relation.FriendShipRelation;
import com.lgl.neo4j.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * description
 * </p>
 *
 * @author lgl 2020/09/27 11:47
 * @since 1.0
 */
@Service("neo4jServiceImpl")
public class Neo4jServiceImpl implements Neo4jService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public void saveFriendship(String fromName, String toName) {
        StudentNode from = studentRepository.findByName(fromName);
        StudentNode to = studentRepository.findByName(toName);

        FriendShipRelation FriendShipRelation = new FriendShipRelation();
        FriendShipRelation.setFrom(from);
        FriendShipRelation.setTo(to);

        //只需要在from节点保存关系即可
        from.addRelation(FriendShipRelation);
        studentRepository.save(from);
    }

    //删除节点时，使用find，save 需要@Transactional注解
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteFriendship(String fromName, String toName) {
        StudentNode fromStudentNode = studentRepository.findByName(fromName);
        List<FriendShipRelation> FriendShipRelationList = fromStudentNode.getFriendshipRelationList();
        for (Iterator<FriendShipRelation> iterator = FriendShipRelationList.iterator(); iterator.hasNext();) {
            FriendShipRelation relation = iterator.next();
            StudentNode fromNode = relation.getFrom();
            StudentNode toNode = relation.getTo();

            String fromNodeName = fromNode.getName();
            String toNodeName = toNode.getName();
            //判断 fromName 和 toName 需要删除的关系是否相等
            if(fromNodeName.equals(fromName) && toNodeName.equals(toName)){
                iterator.remove();
            }
        }
        //只需要在from节点保存关系即可
        studentRepository.save(fromStudentNode);
    }
}
