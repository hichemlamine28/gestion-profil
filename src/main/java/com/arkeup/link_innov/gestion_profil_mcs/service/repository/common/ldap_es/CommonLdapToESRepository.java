package com.arkeup.link_innov.gestion_profil_mcs.service.repository.common.ldap_es;

import javax.naming.Name;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.ldap.repository.LdapRepository;
import org.springframework.data.repository.CrudRepository;

public interface CommonLdapToESRepository<T,
        LdapRep extends LdapRepository<T>,
        ESRep extends ElasticsearchRepository<T,Name>> extends CrudRepository<T,Name> {


    void reindexES();
}
