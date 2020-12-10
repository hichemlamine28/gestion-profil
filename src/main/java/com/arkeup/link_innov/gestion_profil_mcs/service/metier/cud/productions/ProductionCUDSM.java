package com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.productions;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Productions;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ProductionHasMediaDTO;

import java.util.List;

public interface ProductionCUDSM {
    Productions setProductionHasMedia(ProductionHasMediaDTO productionHasMediaDTO);

    List<Productions> updateAll(List<Productions> productions);
}
