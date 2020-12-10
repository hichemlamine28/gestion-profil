package com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.profil;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Profil;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.ProfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author bona
 */

@Service
public class ProfilCUDSMImpl implements ProfilCUDSM {

    @Autowired
    private ProfilRepository profilRepository;

    @Override
    public Profil update(Profil profil) {
        return profilRepository.save(profil);
    }

    @Override
    public void deleteById(String profilId) {
        if (profilId != null && profilRepository.existsById(profilId)) {
            profilRepository.deleteById(profilId);
        }
    }

    @Override
    public List<Profil> updateAllProfil(List<Profil> profils) {
        List<Profil> profilList = new ArrayList<>();
        Iterable<Profil> profilIterator = profilRepository.saveAll(profils);
        profilIterator.forEach(profilList::add);
        return profilList;
    }
}
