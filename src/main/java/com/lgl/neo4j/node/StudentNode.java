package com.lgl.neo4j.node;

import java.util.ArrayList;
import java.util.List;

import com.lgl.neo4j.relation.FriendShipRelation;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

/**
 * <p>
 * description
 * </p>
 *
 * @author lgl 2020/09/27 11:30
 * @since 1.0
 */
@Setter
@Getter
@NodeEntity(label = "Student")
public class StudentNode {
    /**
     * neo4j 生成的id
     */
    @Id
    @GeneratedValue
    private Long id;
    /**
     * 属性，name
     */
    private String name;
    /**
     * 关系，定义为友谊
     */
    @Relationship(type = "FRIENDSHIP_RELATION")
    private List<FriendShipRelation> friendshipRelationList;
    /**
     * 添加友谊的关系
     * @param friendshipRelation
     */
    public void addRelation(FriendShipRelation friendshipRelation){
        if(this.friendshipRelationList == null){
            this.friendshipRelationList = new ArrayList<>();
        }
        this.friendshipRelationList.add(friendshipRelation);
    }
}
