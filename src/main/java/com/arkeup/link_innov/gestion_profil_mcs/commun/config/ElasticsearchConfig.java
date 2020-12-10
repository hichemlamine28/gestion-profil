package com.arkeup.link_innov.gestion_profil_mcs.commun.config;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 *
 * https://www.baeldung.com/spring-data-elasticsearch-tutorial
 */
@Configuration
@EnableElasticsearchRepositories(basePackages = "com.arkeup.link_innov.gestion_profil_mcs.service.repository.elastic_search")
public class ElasticsearchConfig {

    @Value("${es.host}")
    String esHost;

    @Value("${es.port}")
    int esPort;

    @Value("${elasticsearch.clustername}")
    String esClusterName;

    @Bean
    public Client client() throws UnknownHostException {

        Settings esSettings = Settings.builder()
                .put("cluster.name", esClusterName)
//              .put("client.transport.sniff",true)
                .build();

        //https://www.elastic.co/guide/en/elasticsearch/guide/current/_transport_client_versus_node_client.html
        return new PreBuiltTransportClient(esSettings)
                .addTransportAddress(
                        new InetSocketTransportAddress(InetAddress.getByName(esHost), esPort));
    }

    @Bean
    public ElasticsearchOperations elasticsearchTemplate() throws Exception {
        return new ElasticsearchTemplate(client());
    }
}
