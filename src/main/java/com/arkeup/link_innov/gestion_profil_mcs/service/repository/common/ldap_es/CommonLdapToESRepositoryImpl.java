package com.arkeup.link_innov.gestion_profil_mcs.service.repository.common.ldap_es;

import java.util.Optional;

import javax.naming.Name;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.ldap.repository.LdapRepository;

public class CommonLdapToESRepositoryImpl<T,
        LdapRep extends LdapRepository<T>,
        ESRep extends ElasticsearchRepository<T,Name>>  implements CommonLdapToESRepository<T, LdapRep,ESRep > {

    @Autowired
    protected LdapRep ldapRepository;

    @Autowired
    protected ESRep esRepository;

    @Value("${es.reindex.page.size}")
    int reindexPageSize;


    @Override
    public <S extends T> S save(S s) {

        try {
            s = ldapRepository.save(s);
            s = esRepository.save(s);

        }
        catch (Exception e){

            ldapRepository.delete(s);
            esRepository.delete(s);

            throw e;

        }


        return s;
    }

    @Override
    public <S extends T> Iterable<S> saveAll(Iterable<S> iterable) {
        try {
            iterable = ldapRepository.saveAll(iterable);
            iterable = esRepository.saveAll(iterable);

        }
        catch (Exception e){

            ldapRepository.deleteAll(iterable);
            esRepository.deleteAll(iterable);

            throw e;

        }


        return iterable;
    }

    @Override
    public Optional<T> findById(Name name) {
        return ldapRepository.findById(name);
    }

    @Override
    public boolean existsById(Name name) {
        return ldapRepository.existsById(name);
    }

    @Override
    public Iterable<T> findAll() {
        return ldapRepository.findAll();
    }

    @Override
    public Iterable<T> findAllById(Iterable<Name> iterable) {
        return ldapRepository.findAllById(iterable);
    }

    @Override
    public long count() {
        return ldapRepository.count();
    }

    @Override
    public void deleteById(Name name) {
        ldapRepository.deleteById(name);
        esRepository.deleteById(name);
    }

    @Override
    public void delete(T post) {

        ldapRepository.delete(post);
        esRepository.delete(post);
    }

    @Override
    public void deleteAll(Iterable<? extends T> iterable) {
        ldapRepository.deleteAll(iterable);
        esRepository.deleteAll(iterable);
    }

    @Override
    public void deleteAll() {
        ldapRepository.deleteAll();
        esRepository.deleteAll();
    }


    @Override
    public void reindexES() {
       
        Iterable<T> page = ldapRepository.findAll();
        esRepository.saveAll(page);

    }
}
