package com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.reasearchgate;

import com.google.common.base.Objects;

public class ProfilResearchGate {
    private String email;
    private String firstname;
    private String lastname;
    private String gender;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Boolean getBooleanGender() {
        if("Male".equals(getGender())){
            return true;
        }
        else if("Female".equals(getGender())){
            return false;
        }
        else {
            return null;
        }


    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProfilResearchGate profilResearchGate = (ProfilResearchGate) o;
        return Objects.equal(getEmail(), profilResearchGate.getEmail()) &&
                Objects.equal(getFirstname(), profilResearchGate.getFirstname()) &&
                Objects.equal(getLastname(), profilResearchGate.getLastname()) &&
                Objects.equal(getGender(), profilResearchGate.getGender());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getEmail(), getFirstname(), getLastname(), getGender());
    }
}
