package com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.referential;

import org.springframework.data.annotation.Id;

@org.springframework.data.mongodb.core.mapping.Document
@org.springframework.data.elasticsearch.annotations.Document(indexName = "gestion_profil_mcs", type = "Classification")
public class Classification {

	/**
     * Classification identifier
     *
     * @type {string}
     * @memberof Classification
     */
    @Id
    private String id;
    
    /**
     * Classification label
    *
    * @type {string}
    * @memberof Classification
    */
   private String label;
   
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

}
