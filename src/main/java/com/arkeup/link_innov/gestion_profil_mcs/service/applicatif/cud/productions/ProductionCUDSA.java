package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.productions;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ProductionHasMediaDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ProductionsDTO;

public interface ProductionCUDSA {
    ProductionsDTO setProductionHasMedia(ProductionHasMediaDTO productionHasMediaDTO);
    boolean updateHasMediaProduction();
}
