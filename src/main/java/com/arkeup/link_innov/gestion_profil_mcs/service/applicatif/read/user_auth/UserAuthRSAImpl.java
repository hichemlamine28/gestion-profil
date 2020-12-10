package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.user_auth;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.user_auth.UserAuthMapper;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.UserAuth;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.UserAuthDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun.CustomPageDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.utils.HelpPage;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.user_auth.UserAuthRSM;

/**
 * Created by bgh on 18/10/18.
 */
@Service
public class UserAuthRSAImpl implements UserAuthRSA {

    @Autowired
    private UserAuthRSM userAuthCUDSM;

    @Autowired
    private UserAuthMapper userAuthMapper;

    @Value("${dataflow.search.admin.role}")
    private String role;

    @Override
    public UserAuthDTO findByUserName(String username) {

        UserAuth userAuth = userAuthCUDSM.findByUserName(username);
        return userAuthMapper.userAuthToUserAuthDTO(userAuth);
    }

    @Override
    public CustomPageDTO<String> findAllAdmin(int page, int size) {
        CustomPageDTO<String> res = new CustomPageDTO<>();
        Page<String> pageResult = new PageImpl<>(new ArrayList<>(1));
        Pageable pageable = PageRequest.of(page, size);
        Page<UserAuth> userAuths = userAuthCUDSM.findByRole(role, pageable);
        if (userAuths.hasContent()) {
            List<UserAuthDTO> userAuthDTOs = userAuthMapper.userAuthListToUserAuthDTOList(userAuths.getContent());
            List<String> userMail = new ArrayList<>(userAuthDTOs.size());
            userAuthDTOs.forEach(user -> userMail.add(user.getUsername()));
            pageResult = new PageImpl<>(userMail, pageable, userAuths.getTotalElements());
            HelpPage<String> helpPage = new HelpPage<>(pageResult.getContent(), pageResult.getPageable(), pageResult.getTotalElements());
            res.setPageDTOs(helpPage);
        }
        return res;
    }
}
