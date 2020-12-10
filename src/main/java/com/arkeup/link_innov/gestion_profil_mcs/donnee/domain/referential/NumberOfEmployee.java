package com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.referential;

import org.springframework.data.annotation.Id;

@org.springframework.data.mongodb.core.mapping.Document
@org.springframework.data.elasticsearch.annotations.Document(indexName = "gestion_profil_mcs", type = "NumberOfEmployee")
public class NumberOfEmployee {

    /**
     * NumberOfEmployee identifier
     *
     * @type {string}
     * @memberof NumberOfEmployee
     */
    @Id
    private String id;

    /**
     * NumberOfEmployee label
     *
     * @type {string}
     * @memberof NumberOfEmployee
     */
    private String label;

    /**
     * code for siren
     *
     * @type {string}
     * @memberof NumberOfEmployee
     */
    private String code;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
