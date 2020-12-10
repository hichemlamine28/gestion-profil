package com.arkeup.link_innov.gestion_profil_mcs.service.repository.common.mongo_es;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.io.Serializable;
import java.util.Optional;

public class CommonMongoToESRepositoryImpl<T,ID extends Serializable,
        MongoRep extends MongoRepository<T,ID>,
        ESRep extends ElasticsearchRepository<T,ID>>  implements CommonMongoToESRepository<T,ID,MongoRep,ESRep > {

    @Autowired
    protected MongoRep mongoRepository;

    @Autowired
    protected ESRep esRepository;


    @Value("${es.reindex.page.size}")
    int reindexPageSize;


    @Override
    public Iterable<T> findAll(Sort sort) {
        return mongoRepository.findAll(sort);
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return mongoRepository.findAll(pageable);
    }

    @Override
    public <S extends T> S save(S s) {

        try {
            s = mongoRepository.save(s);
            s = esRepository.save(s);

        }
        catch (Exception e){

            mongoRepository.delete(s);
            esRepository.delete(s);

            throw e;

        }


        return s;
    }

    @Override
    public <S extends T> Iterable<S> saveAll(Iterable<S> iterable) {
        try {
            iterable = mongoRepository.saveAll(iterable);
            iterable = esRepository.saveAll(iterable);

        }
        catch (Exception e){

            mongoRepository.deleteAll(iterable);
            esRepository.deleteAll(iterable);

            throw e;

        }


        return iterable;
    }

    @Override
    public Optional<T> findById(ID s) {
        return mongoRepository.findById(s);
    }

    @Override
    public boolean existsById(ID s) {
        return mongoRepository.existsById(s);
    }

    @Override
    public Iterable<T> findAll() {
        return mongoRepository.findAll();
    }

    @Override
    public Iterable<T> findAllById(Iterable<ID> iterable) {
        return mongoRepository.findAllById(iterable);
    }

    @Override
    public long count() {
        return mongoRepository.count();
    }

    @Override
    public void deleteById(ID s) {
        mongoRepository.deleteById(s);
        esRepository.deleteById(s);
    }

    @Override
    public void delete(T post) {

        mongoRepository.delete(post);
        esRepository.delete(post);
    }

    @Override
    public void deleteAll(Iterable<? extends T> iterable) {
        mongoRepository.deleteAll(iterable);
        esRepository.deleteAll(iterable);
    }

    @Override
    public void deleteAll() {
        mongoRepository.deleteAll();
        esRepository.deleteAll();
    }

    @Override
    public void reindexES() {
        PageRequest pageRequest = PageRequest.of(0,reindexPageSize, Sort.Direction.ASC,"creationDate");

        Page<T> page = mongoRepository.findAll(pageRequest);
        if(page.getTotalElements() > 0){
            esRepository.saveAll(page.getContent());

            while (page.hasNext()){


                page = mongoRepository.findAll(page.nextPageable());
                esRepository.saveAll(page.getContent());


            }
        }
    }
}
