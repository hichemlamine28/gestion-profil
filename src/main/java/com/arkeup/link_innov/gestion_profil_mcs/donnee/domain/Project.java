package com.arkeup.link_innov.gestion_profil_mcs.donnee.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.neo4j.User;

/**
 * @author Stéphan R.
 *
 */
@org.springframework.data.mongodb.core.mapping.Document
@org.springframework.data.elasticsearch.annotations.Document(indexName = "gestion_profil_mcs", type = "Project")
public class Project extends Productions {

	private List<User> collaborators = new ArrayList<>();

	private Date date;

	private Date dateDebut;

	private Date dateFin;

	private String description;

	public List<User> getCollaborators() {
		return collaborators;
	}

	public void setCollaborators(List<User> collaborators) {
		this.collaborators = collaborators;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		return Objects.hash(collaborators, date, dateDebut, dateFin, description);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!super.equals(obj)) {
			return false;
		}

		if (!(obj instanceof Project)) {
			return false;
		}

		Project other = (Project) obj;

		return Objects.equals(collaborators, other.collaborators)
				&& Objects.equals(date, other.date)
				&& Objects.equals(dateDebut, other.dateDebut)
				&& Objects.equals(dateFin, other.dateFin)
				&& Objects.equals(description, other.description);
	}
}
