package com.arkeup.link_innov.gestion_profil_mcs.service.businessdelegate;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.IsImportedDTO;

public interface UploadBetaTestBDL {
    public IsImportedDTO uploadFileData(String inputFilePath);
}
