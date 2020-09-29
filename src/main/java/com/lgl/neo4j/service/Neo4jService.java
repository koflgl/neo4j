package com.lgl.neo4j.service;

/**
 * <p>
 * description
 * </p>
 *
 * @author lgl 2020/09/27 11:49
 * @since 1.0
 */
public interface Neo4jService {
    void saveFriendship(String fromName, String toName);

    void deleteFriendship(String fromName, String toName);

}
