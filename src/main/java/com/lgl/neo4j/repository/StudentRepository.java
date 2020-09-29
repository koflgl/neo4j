package com.lgl.neo4j.repository;

import java.util.List;

import com.lgl.neo4j.node.StudentNode;
import com.lgl.neo4j.relation.FriendShipRelation;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * description
 * </p>
 *
 * @author lgl 2020/09/27 11:44
 * @since 1.0
 */
@Repository
public interface StudentRepository extends Neo4jRepository<StudentNode, Long> {
    /**
     * 通过name查找学生node
     * @param name
     * @return
     */
    StudentNode findByName(String name);

    /**
     * 根据name获取学生 in 友谊关系
     * @param name
     * @return
     */
    @Query("match " +
            "p=(a:StudentNode) - [r:FRIENDSHIP_RELATION*0..] -> " +
            "(b:StudentNode) " +
            "where b.name = {0} " +
            "return p")
    List<FriendShipRelation> inFriendship(String name);

    /**
     * 根据name获取学生 out 友谊关系
     * @param name
     * @return
     */
    @Query("match " +
            "p=(b:StudentNode) " +
            "- [rr:FRIENDSHIP_RELATION*0..] -> (c:StudentNode) " +
            "where b.name = {0} " +
            "return p")
    List<FriendShipRelation> outFriendship(String name);

    /**
     * 根据name获取学生 both 友谊关系
     * @param name
     * @return
     */
    @Query("match " +
            "p=(a:StudentNode) <- [r:FRIENDSHIP_RELATION*0..] -> " +
            "(b:StudentNode) " +
            "<- [rr:FRIENDSHIP_RELATION*0..] -> (c:StudentNode) " +
            "where b.name = {0} " +
            "return p")
    List<FriendShipRelation> bothFriendship(String name);
}
