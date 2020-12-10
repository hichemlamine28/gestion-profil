package com.arkeup.link_innov.gestion_profil_mcs.donnee.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.neo4j.User;

/**
 *
 * @author njaka
 */
public class PatentDTO extends ProductionsDTO {

    /**
     * The priority deposit date.
     */
    private Date priorityDepositDate;

    /**
     * The description.
     */
    private String description;

    /**
     * The inventors.
     */
    private List<User> inventors = new ArrayList<>();

    /**
     * The publication number.
     */
    private String publicationNumber;

    /**
     * The title non confidential.
     */
    private String titleNonConfidential;

    /**
     * The depositor.
     */
    private String depositor;

    /**
     * Date de publication.
     */
    private Date publicationDate;

    public Date getPriorityDepositDate() {
        return priorityDepositDate;
    }

    public void setPriorityDepositDate(Date priorityDepositDate) {
        this.priorityDepositDate = priorityDepositDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<User> getInventors() {
        return inventors;
    }

    public void setInventors(List<User> inventors) {
        this.inventors = inventors;
    }

    public String getPublicationNumber() {
        return publicationNumber;
    }

    public void setPublicationNumber(String publicationNumber) {
        this.publicationNumber = publicationNumber;
    }

    public String getTitleNonConfidential() {
        return titleNonConfidential;
    }

    public void setTitleNonConfidential(String titleNonConfidential) {
        this.titleNonConfidential = titleNonConfidential;
    }

    public String getDepositor() {
        return depositor;
    }

    public void setDepositor(String depositor) {
        this.depositor = depositor;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, inventors, priorityDepositDate, publicationNumber, titleNonConfidential);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (!(obj instanceof PatentDTO)) {
            return false;
        }
        PatentDTO other = (PatentDTO) obj;
        return Objects.equals(description, other.description) && Objects.equals(inventors, other.inventors)
                && Objects.equals(priorityDepositDate, other.priorityDepositDate)
                && Objects.equals(publicationNumber, other.publicationNumber)
                && Objects.equals(titleNonConfidential, other.titleNonConfidential)
                && Objects.equals(publicationDate, other.publicationDate);
    }

}
