package com.arkeup.link_innov.gestion_profil_mcs.donnee.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun.BaseDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.referential.ActivitySectorDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.referential.ClassificationDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.referential.KeywordDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.referential.NumberOfEmployeeDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.referential.ThematicAreaDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.referential.TypologyDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CorporationDTO extends BaseDTO {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> admins = new ArrayList<>();

    private List<String> adminsChatId = new ArrayList<>();

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String superAdmin;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private CategoryDTO type;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String description;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<KeywordDTO> keywords = new ArrayList<>();

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private NumberOfEmployeeDTO employeesNumber;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ActivitySectorDTO activityArea;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String siret;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("corporationName")
    private String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String structure;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private TypologyDTO typology;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ClassificationDTO classification;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String address;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String webSite;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String photo;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ThematicAreaDTO thematicArea;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer certificationQuota;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer premiumAccountQuota;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private MediaDTO mediaDTO;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String mediaId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private MediaDTO backgroundDTO;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String backgroundId;

    private Boolean isFollow;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date createDate;

    private Boolean hasMedia = false;

    private Boolean hasBackground = false;

    /*
     * don't remove these properties
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<UserDTO> certifiedUsers = new HashSet<>();

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<UserDTO> followers = new HashSet<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getAdmins() {
        return admins;
    }

    public void setAdmins(List<String> admins) {
        this.admins = admins;
    }

    public String getSuperAdmin() {
        return superAdmin;
    }

    public void setSuperAdmin(String superAdmin) {
        this.superAdmin = superAdmin;
    }

    public CategoryDTO getType() {
        return type;
    }

    public void setType(CategoryDTO type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<KeywordDTO> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<KeywordDTO> keywords) {
        this.keywords = keywords;
    }

    public NumberOfEmployeeDTO getEmployeesNumber() {
        return employeesNumber;
    }

    public void setEmployeesNumber(NumberOfEmployeeDTO employeesNumber) {
        this.employeesNumber = employeesNumber;
    }

    public ActivitySectorDTO getActivityArea() {
        return activityArea;
    }

    public void setActivityArea(ActivitySectorDTO activityArea) {
        this.activityArea = activityArea;
    }

    public String getSiret() {
        return siret;
    }

    public void setSiret(String siret) {
        this.siret = siret;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStructure() {
        return structure;
    }

    public void setStructure(String structure) {
        this.structure = structure;
    }

    public TypologyDTO getTypology() {
        return typology;
    }

    public void setTypology(TypologyDTO typology) {
        this.typology = typology;
    }

    public ClassificationDTO getClassification() {
        return classification;
    }

    public void setClassification(ClassificationDTO classification) {
        this.classification = classification;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public ThematicAreaDTO getThematicArea() {
        return thematicArea;
    }

    public void setThematicArea(ThematicAreaDTO thematicArea) {
        this.thematicArea = thematicArea;
    }

    public Integer getCertificationQuota() {
        return certificationQuota;
    }

    public void setCertificationQuota(Integer certificationQuota) {
        this.certificationQuota = certificationQuota;
    }

    public Integer getPremiumAccountQuota() {
        return premiumAccountQuota;
    }

    public void setPremiumAccountQuota(Integer premiumAccountQuota) {
        this.premiumAccountQuota = premiumAccountQuota;
    }

    public List<String> getAdminsChatId() {
        return adminsChatId;
    }

    public void setAdminsChatId(List<String> adminsChatId) {
        this.adminsChatId = adminsChatId;
    }

    public MediaDTO getMediaDTO() {
        return mediaDTO;
    }

    public void setMediaDTO(MediaDTO mediaDTO) {
        this.mediaDTO = mediaDTO;
    }

    public MediaDTO getBackgroundDTO() {
        return backgroundDTO;
    }

    public void setBackgroundDTO(MediaDTO backgroundDTO) {
        this.backgroundDTO = backgroundDTO;
    }

    public Boolean getIsFollow() {
        return isFollow;
    }

    public void setIsFollow(Boolean isFollow) {
        this.isFollow = isFollow;
    }

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getBackgroundId() {
        return backgroundId;
    }

    public void setBackgroundId(String backgroundId) {
        this.backgroundId = backgroundId;
    }

    public Boolean getHasMedia() {
        return hasMedia;
    }

    public void setHasMedia(Boolean hasMedia) {
        this.hasMedia = hasMedia;
    }

    public Boolean getHasBackground() {
        return hasBackground;
    }

    public void setHasBackground(Boolean hasBackground) {
        this.hasBackground = hasBackground;
    }

	public Set<UserDTO> getCertifiedUsers() {
		return certifiedUsers;
	}

	public void setCertifiedUsers(Set<UserDTO> certifiedUsers) {
		this.certifiedUsers = certifiedUsers;
	}

	public Set<UserDTO> getFollowers() {
		return followers;
	}

	public void setFollowers(Set<UserDTO> followers) {
		this.followers = followers;
	}
}
