package com.arkeup.link_innov.gestion_profil_mcs.infrastructure.utils;

public interface PermissionsAndStatusUtils {

    String STATUSINACTIF = "status_inactif";

    String STATUSBLOCKED = "status_blocked";

    String ROLEUSER = "hasAnyAuthority('perm_user')";

    String ROLEADMIN = "hasAnyAuthority('perm_admin')";

    String ROLEETL = "hasAnyAuthority('perm_etl')";

    String ROLEUSERANDADMIN = "hasAnyAuthority('perm_user','perm_admin')";

    String ROLEUSERANDETL = "hasAnyAuthority('perm_user','perm_etl')";

    String ROLEADDCONTACT = "hasAnyAuthority('perm_add_contact')";

    String ROLEANNONCEPUBLISH = "hasAnyAuthority('perm_annonce_publish')";

    String ROLEANNONCEPUBLISHANNONYMOUS = "hasAnyAuthority('perm_annonce_publish_annonymous')";

    String ROLEANNONCEVIEW = "hasAnyAuthority('perm_annonce_view')";

    String ROLEBASICPROFILVIEW = "hasAnyAuthority('perm_basic_profil_view')";

    String ROLECONSULTVIEWERS = "hasAnyAuthority('perm_consult_viewers')";

    String ROLEGROUPCREATE = "hasAnyAuthority('perm_group_create')";

    String ROLELV1CHAT = "hasAnyAuthority('perm_level1_chat')";

    String ROLELV2CHAT = "hasAnyAuthority('perm_level2_chat')";

    String ROLELV1CONTACTFULLPROFILVIEW = "hasAnyAuthority('perm_level1_contact_full_profil_view')";

    String ROLELV2CONTACTFULLPROFILVIEW = "hasAnyAuthority('perm_level2_contact_full_profile_view')";

    String ROLELV2CONTACTBASICPROFILVIEW = "hasAnyAuthority('perm_level2_contact_basic_profile_view')";

    String ROLEPRODUCTIONDOWNLOAD = "hasAnyAuthority('perm_production_download')";

    String ROLEPRODUCTIONVIEW = "hasAnyAuthority('perm_production_view')";

    String ROLERESEARCHPROFILINVISIBLE = "hasAnyAuthority('perm_research_profille_invisible')";

    String ROLERESEARCHPROFILVISIBLE = "hasAnyAuthority('perm_research_profile_visible')";

    String ROLERESEARCHBASICSACCESS = "hasAnyAuthority('perm_research_results_basic_access')";

    String ROLERESEARCHSTATISTICSSACCESS = "hasAnyAuthority('perm_research_results_statistics_access')";

    String ROLEREWARDS = "hasAnyAuthority('perm_rewards')";

    String ROLEWALLACCESS = "hasAnyAuthority('perm_wall_access')";
}
