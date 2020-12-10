package com.arkeup.link_innov.gestion_profil_mcs.service.repository.common.mongo_es;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;

public interface CommonMongoToESRepository<T,ID extends Serializable,
        MongoRep extends MongoRepository<T,ID>,
        ESRep extends ElasticsearchRepository<T,ID>> extends PagingAndSortingRepository<T,ID> {

    void reindexES();
}
