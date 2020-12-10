package com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors;

import java.util.HashMap;
import java.util.Map;

public enum ErrorsEnum {

    ERR_MCS_USER_ALREADY_EXIST("ERR_MCS_USER_0001", "User Already exist.", true, false),
    ERR_MCS_ERROR2("ERR_MCS_0002", "Error 2 message.", true, false),
    ERR_MCS_SEND_NOTIFICATION("ERR_MCS_0003", "Error while sending notification.", true, false),
    ERR_MCS_USER("ERR_MCS_USER_0002", "User Already exist.", true, false),
    // Global error message
    ERR_MCS_PROFIL_1000("ERR_MCS_PROFIL_1000", "Validation Error", true, false),
    UNEXPECTED_ERROR("UNEXPECTED_ERROR_500", "Unexpected Error", true, false),
    // Info Profil
    ERR_MCS_PROFIL_0001("ERR_MCS_PROFIL_0001", "Username cannot be null.", true, false),
    ERR_MCS_PROFIL_0002("ERR_MCS_PROFIL_0002", "Lastname is wrong.", true, false),
    ERR_MCS_PROFIL_00021("ERR_MCS_PROFIL_00021", "Lastname too long", true, false),
    ERR_MCS_PROFIL_0003("ERR_MCS_PROFIL_0003", "Firstname is wrong.", true, false),
    ERR_MCS_PROFIL_00031("ERR_MCS_PROFIL_00031", "Firstname too long.", true, false),
    ERR_MCS_PROFIL_0004("ERR_MCS_PROFIL_0004", "Email cannot be null.", true, false),
    ERR_MCS_PROFIL_00041("ERR_MCS_PROFIL_00041", "Invalid Email format.", true, false),
    ERR_MCS_PROFIL_0005("ERR_MCS_PROFIL_0005", "Password cannot be null.", true, false),
    ERR_MCS_PROFIL_0006("ERR_MCS_PROFIL_0006", "Cannot add user information.", true, false),
    ERR_MCS_PROFIL_0007("ERR_MCS_PROFIL_0007", "User not in BD.", true, false),
    ERR_MCS_PROFIL_0008("ERR_MCS_PROFIL_0008", "The institution cannot be null.", true, false),
    ERR_MCS_PROFIL_0009("ERR_MCS_PROFIL_0009", "The degree cannot be null.", true, false),
    ERR_MCS_PROFIL_0010("ERR_MCS_PROFIL_0010", "The field cannot be null.", true, false),
    ERR_MCS_PROFIL_0011("ERR_MCS_PROFIL_0011", "The start date cannot be null.", true, false),
    ERR_MCS_PROFIL_0012("ERR_MCS_PROFIL_0012", "Cannot add user qualification.", true, false),
    ERR_MCS_PROFIL_0015("ERR_MCS_PROFIL_0015", "Cannot find qualification with id : ", true, false),
    // signup
    ERR_MCS_PROFIL_0400("ERR_MCS_PROFIL_0400", "Employer must not be empty.", true, false),
    ERR_MCS_PROFIL_0401("ERR_MCS_PROFIL_0401", "Category must not be empty.", true, false),
    ERR_MCS_PROFIL_0402("ERR_MCS_PROFIL_0402", "PseudoName already exist.", true, false),
    ERR_MCS_PROFIL_0403("ERR_MCS_PROFIL_0403", "Address email already exist.", true, false),
    // Corporation
    ERR_MCS_PROFIL_0013("ERR_MCS_PROFIL_0013", "Name cannot be null.", true, false),
    ERR_MCS_PROFIL_0014("ERR_MCS_PROFIL_0014", "Cannot add corporation.", true, false),
    ERR_MCS_PROFIL_0016("ERR_MCS_PROFIL_0016", "Cannot find corporation with id ", true, false),
    ERR_MCS_PROFIL_0026("ERR_MCS_PROFIL_0026", "Type cannot be null", true, false),
    // Parcours
    ERR_MCS_PROFIL_0017("ERR_MCS_PROFIL_0017", "Cannot find parcours with id : ", true, false),
    ERR_MCS_PROFIL_0020("ERR_MCS_PROFIL_0020", "Cannot add user parcours : ", true, false),
    ERR_MCS_PROFIL_0021("ERR_MCS_PROFIL_0021", "The category cannot be null.", true, false),
    // Compétence
    ERR_MCS_PROFIL_0018("ERR_MCS_PROFIL_0018", "Cannot find skill with id : ", true, false),
    ERR_MCS_PROFIL_0019("ERR_MCS_PROFIL_0019", "Cannot add user skill.", true, false),
    ERR_MCS_PROFIL_0022("ERR_MCS_PROFIL_0022", "Password is too short the minimum is 7 characters", true, false),
    ERR_MCS_PROFIL_0023("ERR_MCS_PROFIL_0023", "Profile Id cannot be null", true, false),
    ERR_MCS_PROFIL_0024("ERR_MCS_PROFIL_0024", "Profile Id cannot empty.", true, false),
    // Corporation
    ERR_MCS_PROFIL_0025("ERR_MCS_PROFIL_0025", "Sorry, but you can't add an Admin to this corporation.", true, false),
    ERR_MCS_PROFIL_0027("ERR_MCS_PROFIL_0027", "Type cannot be null.", true, false),
    ERR_MCS_PROFIL_0028("ERR_MCS_PROFIL_0028", "Typology cannot be null for academic type.", true, false),
    ERR_MCS_PROFIL_0029("ERR_MCS_PROFIL_0029", "Thematic area cannot be null for academic type.", true, false),
    ERR_MCS_PROFIL_0030("ERR_MCS_PROFIL_0030", "Siret cannot be null for enterprise type.", true, false),
    ERR_MCS_PROFIL_0031("ERR_MCS_PROFIL_0031", "Employees number cannot be null for industriel type.", true, false),
    ERR_MCS_PROFIL_0032("ERR_MCS_PROFIL_0032", "Activity area cannot be null for industriel type.", true, false),
    ERR_MCS_PROFIL_0033("ERR_MCS_PROFIL_0033", "Classification cannot be null for other actors type.", true, false),
    ERR_MCS_PROFIL_0034("ERR_MCS_PROFIL_0034", "Keyword must not exceed 50 characters.", true, false),
    ERR_MCS_PROFIL_0035("ERR_MCS_PROFIL_0035", "Siret must not exceed 14 characters.", true, false),
    ERR_MCS_PROFIL_0036("ERR_MCS_PROFIL_0036", "Description must not exceed 2000 characters.", true, false),
    ERR_MCS_PROFIL_0037("ERR_MCS_PROFIL_0037", "Website must be a valid URL.", true, false),
    ERR_MCS_PROFIL_0038("ERR_MCS_PROFIL_0038", "Activity area cannot be null for other actors type.", true, false),
    ERR_MCS_PROFIL_0039("ERR_MCS_PROFIL_0039", "Category not found.", true, false),
    ERR_MCS_PROFIL_0040("ERR_MCS_PROFIL_0040", "Corporation not found.", true, false),
    ERR_MCS_PROFIL_0041("ERR_MCS_PROFIL_0041", "Error while trying to save corporation.", true, false),
    ERR_MCS_PROFIL_0042("ERR_MCS_PROFIL_0042", "Certification limit exceeded.", true, false),
    ERR_MCS_PROFIL_0043("ERR_MCS_PROFIL_0043", "Label cannot be null.", true, false),
    ERR_MCS_PROFIL_0044("ERR_MCS_PROFIL_0044", "Room name cannot be null.", true, false),
    ERR_MCS_PROFIL_0045("ERR_MCS_PROFIL_0045", "Room name must not exceed 50 characters.", true, false),
    ERR_MCS_PROFIL_0046("ERR_MCS_PROFIL_0046", "The description not confidential must not exceed 2000 characters.",
            true, false),
    ERR_MCS_PROFIL_0047("ERR_MCS_PROFIL_0047", "The group is not in the DB.", true, false),
    ERR_MCS_PROFIL_0048("ERR_MCS_PROFIL_0048", "The group is already exist, try another name.", true, false),
    ERR_MCS_PROFIL_0049("ERR_MCS_PROFIL_0049", "Please add member in the group conversation.", true, false),
    ERR_MCS_PROFIL_0050("ERR_MCS_PROFIL_0050", "you don't leave group, but you can close this.", true, false),
    ERR_MCS_PROFIL_0051("ERR_MCS_PROFIL_0051", "Cannot get media token.", true, false),
    ERR_MCS_PROFIL_0052("ERR_MCS_PROFIL_0052", "Corporation cannot be empty.", true, false),
    ERR_MCS_PROFIL_0053("ERR_MCS_PROFIL_0053", "Media cannot be empty.", true, false),
    ERR_MCS_PROFIL_0054("ERR_MCS_PROFIL_0054", "Cannot delete media.", true, false),
    ERR_MCS_PROFIL_0055("ERR_MCS_PROFIL_0055", "Group id cannot be empty.", true, false),
    ERR_MCS_PROFIL_0056("ERR_MCS_PROFIL_0056", "Can not shared profil.", true, false),
    ERR_MCS_PROFIL_0057("ERR_MCS_PROFIL_0057", "You don't have permission to accept an invitation to join this group.",
            true, false),
    // Favorite
    ERR_MCS_PROFIL_0058("ERR_MCS_PROFIL_0058", "You don't have permission for this favorite.", true, false),
    ERR_MCS_PROFIL_0059("ERR_MCS_PROFIL_0059", "Favorite not in DB.", true, false),
    ERR_MCS_PROFIL_0060("ERR_MCS_PROFIL_0060", "Empty Favorite.", true, false),
    ERR_MCS_PROFIL_0061("ERR_MCS_PROFIL_0061", "Empty target for Favorite.", true, false),
    ERR_MCS_PROFIL_0062("ERR_MCS_PROFIL_0062", "Invalid type for Favorite.", true, false),
    ERR_MCS_PROFIL_0063("ERR_MCS_PROFIL_0063", "Cannot save Favorite.", true, false),
    ERR_MCS_PROFIL_0064("ERR_MCS_PROFIL_0064", "Cannot delete Favorite.", true, false),
    ERR_MCS_PROFIL_0065("ERR_MCS_PROFIL_0065", "Production not found.", true, false),
    ERR_MCS_PROFIL_0066("ERR_MCS_PROFIL_0066", "the user is not member of this group", true, false),
    ERR_MCS_PROFIL_0067("ERR_MCS_PROFIL_0067", "Invalid Corporation.", true, false),
    ERR_MCS_PROFIL_0068("ERR_MCS_PROFIL_0068", "Invalid Media.", true, false),
    ERR_MCS_PROFIL_0069("ERR_MCS_PROFIL_0069", "Post cannot be empty.", true, false),
    ERR_MCS_PROFIL_0070("ERR_MCS_PROFIL_0070", "Error while trying to save profil.", true, false),
    ERR_MCS_PROFIL_0071("ERR_MCS_PROFIL_0071", "Group name must not exceed 50 characters.", true, false),
    ERR_MCS_PROFIL_0072("ERR_MCS_PROFIL_0072", "Group name must not be empty.", true, false),
    ERR_MCS_PROFIL_0073("ERR_MCS_PROFIL_0073", "Error while trying to save group.", true, false),
    ERR_MCS_PROFIL_0074("ERR_MCS_PROFIL_0074", "The export file is not ready.", true, false),
    ERR_MCS_PROFIL_0075("ERR_MCS_PROFIL_0075", "Export file not found", true, false),
    ERR_MCS_PROFIL_0076("ERR_MCS_PROFIL_0076", "Occupation must not exceed 100 characters.", true, false),
    ERR_MCS_PROFIL_0077("ERR_MCS_PROFIL_0077", "the name of corporation already exist. please try another.", true,
            false),
    ERR_MCS_PROFIL_0078("ERR_MCS_PROFIL_0078", "Error while trying to save profil view.", true, false),
    ERR_MCS_PROFIL_0079("ERR_MCS_PROFIL_0079", "Group not found.", true, false),
    ERR_MCS_PROFIL_0080("ERR_MCS_PROFIL_0080", "Can not shared post corporation.", true, false),
    ERR_MCS_PROFIL_0081("ERR_MCS_PROFIL_0081", "Corporation Id cannot empty.", true, false),
    ERR_MCS_PROFIL_0082("ERR_MCS_PROFIL_0082", "Profil not found.", true, false),
    ERR_MCS_PROFIL_0083("ERR_MCS_PROFIL_0083", "Publication Number is already exist.", true, false),

    ERR_MCS_PROFIL_EMPTY_USERNAME("ERR_MCS_PROFIL_EMPTY_USERNAME", "Username cannot be empty.", true, false),
    ERR_MCS_MULTIPART_FILE_EMPTY("ERR_MCS_MULTIPART_FILE_EMPTY", "MultipartFile cannot be empty.", true, false),
    ERR_MCS_FILE_EXCEPTION("ERR_MCS_FILE_EXCEPTION", "Erro while trying upload file.", true, false),
    ERR_MCS_READ_FILE_EXCEL("ERR_MCS_READ_FILE_EXCEL", "Erro while trying read file excel.", true, false),
    ERR_MCS_PROFIL_RG_ERROR("ERR_MCS_PROFIL_RG_ERROR", "ResearchGate data extraction failed", true, false),
    ERR_MCS_PROFIL_RG_LOGIN_ERROR("ERR_MCS_PROFIL_RG_LOGIN_ERROR",
            "username or password incorrect, try again later or contact an administrator.", true, false),
    ERR_MCS_PROFIL_GROUP_PERMISSION("ERR_MCS_PROFIL_GROUP_PERMISSION", "You don't have permission for this actions.",
            true, false),
    ERR_MCS_PROFIL_PASSWORD_RECOVERY("ERR_MCS_PROFIL_PASSWORD_RECOVERY", "Unknown identifier", true, false),
    ERR_MCS_PROFIL_BLOCKED_ACCOUNT("ERR_MCS_PROFIL_BLOCKED_ACCOUNT",
            "Account blocked, please contact a site administrator", true, false),
    ERR_MCS_PROFIL_INACTIVE_ACCOUNT("ERR_MCS_PROFIL_INACTIVE_ACCOUNT",
            "Inactive account: please activate your account via the link that was sent to you by mail", true, false),
    ERR_MCS_PROFIL_INVALID_PASSWORD("ERR_MCS_PROFIL_INVALID_PASSWORD", "Invalid password or identifiant", true, false),
    ERR_MCS_PROFIL_NEW_PASSWORD_SAME_OLD_PASSWORD("ERR_MCS_PROFIL_NEW_PASSWORD_SAME_OLD_PASSWORD", "New password is same old password", true, false),
    ERR_MCS_PROFIL_MAIL_ALREADY_EXIST("ERR_MCS_PROFIL_MAIL_ALREADY_EXIST",
            "invalid mail, this is empty or already exist, please choose other", true, false),
    ERR_MCS_PROFIL_REGISTRATION_EXPIRED("ERR_MCS_PROFIL_REGISTRATION_EXPIRED", "Registration expired", true, false),
    ERR_MCS_NULL_POINTER("ERR_MCS_NULL_POINTER", "Null pointer exception occurred.", Boolean.TRUE, Boolean.FALSE),
    ERR_MCS_OBJECT_NOT_FOUND("ERR_MCS_OBJECT_NOT_FOUND", "Object Not Found.", Boolean.TRUE, Boolean.FALSE),
    ERR_MCS_UNKW("ERR_MCS_UNKW", "Unknown exception occurred.", Boolean.TRUE, Boolean.FALSE),
    ERR_MCS_SEND_AMQP_MESSAGE("ERR_MCS_0020", "Error while trying to create connection and sending amqp message",
            Boolean.TRUE, Boolean.FALSE),
    ERR_MCS_CERTIFICATION_NOT_ACADEMIC("ERR_MCS_CERTIFICATION_NOT_ACADEMIC",
            "Only a corporation of academic type can certify.", true, false),
    ERR_MCS_MAX_PAGE_SIZE("ERR_MCS_MAX_PAGE_SIZE", "Page size must not be grather than 100.", true, false),
    ERR_MCS_USER_NOT_FRIEND("ERR_MCS_USER_NOT_FRIEND", "The user is not in the list of friends.", true, false),
    ERR_MCS_INPUT_OUTPUT("ERR_MCS_INPUT_OUTPUT", "Input output error.", true, false),
    ERR_MCS_INACTIF_USER("ERR_MCS_INACTIF_USER",
            "Your account is not validated, would you like to receive a new activation email? ?", true, false),
    ERR_MCS_BLOCKED_USER("ERR_MCS_BLOCKED_USER",
            "Your account is blocked. Please contact an administrator for more details.", true, false),
    // Dynamic Page
    ERR_MCS_EMPTY_CONTENT("ERR_MCS_EMPTY_CONTENT", "Content cannot be empty.", true, false),
    ERR_MCS_EMPTY_LABEL_URL("ERR_MCS_EMPTY_LABEL_URL", "Label URL cannot be empty.", true, false),
    ERR_MCS_PAGE_NOT_EXIST("ERR_MCS_PAGE_NOT_EXIST", "Page not exist.", true, false),
    // permission error 
    ERR_MCS_PERM_GROUP("ERR_MCS_PERM_GROUP", "You don't have permission to add more group.", true, false),
    ERR_MCS_ACTIVE_SUBSCRIPTION_NOT_FOUND("ERR_MCS_ACTIVE_SUBSCRIPTION_NOT_FOUND", "you don't have active subscription, please contact the admins.", true, false),
    ERR_MCS_PERM_COUNT_NOT_FOUNDS("ERR_MCS_Perm_COUNT_NOT_FOUNDS", "Permission counter not founds", true, false),
    ERR_MCS_SUB_NOT_FOUNDS("ERR_MCS_SUB_NOT_FOUNDS", "subscription not founds", true, false),
    ERR_MCS_LIMIT_REACHED("ERR_MCS_LIMIT_REACHED", "You have already reached the limit for this month", true, false),
    ERR_MCS_SUBSCRIPTION_EXPIRED("ERR_MCS_SUBSCRIPTION_EXPIRED", "your subscription is expired", true, false),
     ERR_MCS_PROFIL_EMAIL_ALREADY_EXIST("ERR_MCS_PROFIL_EMAIL_ALREADY_EXIST",
            "invalid mail, this is already exist, please choose other", true, false),
    ERR_MCS_PROFIL_DATA_BETATESTEUR("ERR_MCS_PROFIL_DATA_BETATESTEUR",
            "FirstName or LastName or mail and type cannot be empty.", true, false),
 	ERR_MCS_XSS_INJECTION("ERR_MCS_XSS_INJECTION", "Invelid content : xss injection.", true, false);


    private final String errorCode;
    private final String errorMessage;
    private final Boolean error;
    private final Boolean warning;

    /**
     * liste de correspondance entre un enum et errorCode
     */
    private static final Map<String, ErrorsEnum> BYID = new HashMap<>();

    static {
        for (ErrorsEnum e : ErrorsEnum.values()) {
            if (BYID.put(e.getErrorCode(), e) != null) {
                throw new IllegalArgumentException("duplicate id: " + e.getErrorCode());
            }
        }
    }

    private ErrorsEnum(String errorCode, String errorMessage, Boolean error, Boolean warning) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.error = error;
        this.warning = warning;
    }

    @Override
    public String toString() {
        return "ErrorCode : " + errorCode + " errorMessage : " + errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public static ErrorsEnum getById(String id) {
        return BYID.get(id);
    }

    public Boolean getError() {
        return error;
    }

    public Boolean getWarning() {
        return warning;
    }
}
