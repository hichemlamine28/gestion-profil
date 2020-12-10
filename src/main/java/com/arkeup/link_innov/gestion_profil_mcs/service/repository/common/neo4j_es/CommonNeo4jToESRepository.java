package com.arkeup.link_innov.gestion_profil_mcs.service.repository.common.neo4j_es;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.neo4j.BaseNeo4jObject;

import java.io.Serializable;

public interface CommonNeo4jToESRepository<
        T extends BaseNeo4jObject,
        ID extends Serializable,
        Neo4jRep extends Neo4jRepository<T,ID>,
        ESRep extends ElasticsearchRepository<T,ID>> extends PagingAndSortingRepository<T,ID> {

    void reindexES();
}
