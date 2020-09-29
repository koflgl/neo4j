package com.lgl.neo4j.relation;

import com.lgl.neo4j.node.StudentNode;
import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.*;
import org.springframework.stereotype.Component;

/**
 * <p>
 * description
 * </p>
 *
 * @author lgl 2020/09/27 11:41
 * @since 1.0
 */
@Getter
@Setter
@Component
@RelationshipEntity(type = "FRIENDSHIP_RELATION")
public class FriendShipRelation {
    @Id
    @GeneratedValue
    private Long id;

    @StartNode
    private StudentNode from;

    @EndNode
    private StudentNode to;
}
