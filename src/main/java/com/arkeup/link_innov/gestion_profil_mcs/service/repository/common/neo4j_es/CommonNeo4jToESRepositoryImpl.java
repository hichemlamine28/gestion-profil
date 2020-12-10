package com.arkeup.link_innov.gestion_profil_mcs.service.repository.common.neo4j_es;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.neo4j.BaseNeo4jObject;

import java.io.Serializable;
import java.util.Optional;
import java.util.UUID;

public class CommonNeo4jToESRepositoryImpl<T extends BaseNeo4jObject,ID extends Serializable,
        Neo4jRep extends Neo4jRepository<T,ID>,
        ESRep extends ElasticsearchRepository<T,ID>>  implements CommonNeo4jToESRepository<T,ID, Neo4jRep,ESRep > {

    @Autowired
    protected Neo4jRep neo4jRepository;

    @Autowired
    protected ESRep esRepository;


    @Value("${es.reindex.page.size}")
    int reindexPageSize;


    @Override
    public Iterable<T> findAll(Sort sort) {
        return neo4jRepository.findAll(sort);
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return neo4jRepository.findAll(pageable);
    }

    @Override
    public <S extends T> S save(S s) {

        try {
            if(s.getId() == null || s.getId().isEmpty()){
                s.setId(UUID.randomUUID().toString());
            }


            s = neo4jRepository.save(s);
            s = esRepository.save(s);

        }
        catch (Exception e){

            neo4jRepository.delete(s);
            esRepository.delete(s);

            throw e;

        }


        return s;
    }

    @Override
    public <S extends T> Iterable<S> saveAll(Iterable<S> iterable) {
        try {

            iterable.forEach(s -> {
                if(s.getId() == null || s.getId().isEmpty()){
                    s.setId(UUID.randomUUID().toString());
                }
            });

            iterable = neo4jRepository.saveAll(iterable);
            iterable = esRepository.saveAll(iterable);

        }
        catch (Exception e){

            neo4jRepository.deleteAll(iterable);
            esRepository.deleteAll(iterable);

            throw e;

        }


        return iterable;
    }

    @Override
    public Optional<T> findById(ID s) {
        return neo4jRepository.findById(s);
    }

    @Override
    public boolean existsById(ID s) {
        return neo4jRepository.existsById(s);
    }

    @Override
    public Iterable<T> findAll() {
        return neo4jRepository.findAll();
    }

    @Override
    public Iterable<T> findAllById(Iterable<ID> iterable) {
        return neo4jRepository.findAllById(iterable);
    }

    @Override
    public long count() {
        return neo4jRepository.count();
    }

    @Override
    public void deleteById(ID s) {
        neo4jRepository.deleteById(s);
        esRepository.deleteById(s);
    }

    @Override
    public void delete(T post) {

        neo4jRepository.delete(post);
        esRepository.delete(post);
    }

    @Override
    public void deleteAll(Iterable<? extends T> iterable) {
        neo4jRepository.deleteAll(iterable);
        esRepository.deleteAll(iterable);
    }

    @Override
    public void deleteAll() {
        neo4jRepository.deleteAll();
        esRepository.deleteAll();
    }

    @Override
    public void reindexES() {
        PageRequest pageRequest = PageRequest.of(0,reindexPageSize, Sort.Direction.ASC,"creationDate");

        Page<T> page = neo4jRepository.findAll(pageRequest);
        esRepository.saveAll(page.getContent());

        while (page.hasNext()){


            page = neo4jRepository.findAll(page.nextPageable());
            esRepository.saveAll(page.getContent());


        }
    }
}
