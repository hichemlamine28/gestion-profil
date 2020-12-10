package com.arkeup.link_innov.gestion_profil_mcs.donnee.domain;

import java.util.Date;

import org.springframework.data.annotation.Id;

@org.springframework.data.mongodb.core.mapping.Document
@org.springframework.data.elasticsearch.annotations.Document(indexName = "gestion_profil_mcs", type = "Qualification")
public class Qualification {
	
	/**
     * UserInformation identifier
     *
     * @type {string}
     * @memberof Qualification
     */
    @Id
    public String id;
    
    /**
     * Institution
     *
     * @type {string}
     * @memberof Qualification
     */
    public String institution;
	
    /**
     * Degree
     *
     * @type {string}
     * @memberof Qualification
     */
    public String degree;
	
    /**
     * Field
     *
     * @type {string}
     * @memberof Qualification
     */
    public String field;
    
    /**
     * Obtained result
     *
     * @type {string}
     * @memberof Qualification
     */
    public String result; 
	
    /**
     * Date start 
     *
     * @type {date}
     * @memberof Qualification
     */
    public Date startDate; 
	
    /**
     * Date end
     *
     * @type {date}
     * @memberof Qualification
     */
    public Date endDate;
	
    /**
     * Description
     *
     * @type {string}
     * @memberof Qualification
     */
    public String description;
    
    /**
     * id de l'utilisateur
     *
     * @type {string}
     * @memberof Qualification
     */
    public String userId;
    
    /**
     * Date of creation
     *
     * @type {date}
     * @memberof Qualification
     */
    public Date createDate; 
	
    /**
     * Date of modification
     *
     * @type {date}
     * @memberof Qualification
     */
    public Date modifDate;
}
