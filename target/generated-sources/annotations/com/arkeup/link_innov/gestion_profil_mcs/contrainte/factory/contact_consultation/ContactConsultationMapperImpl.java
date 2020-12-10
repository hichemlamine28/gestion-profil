package com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.contact_consultation;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.ContactConsultation;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ContactConsultationDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class ContactConsultationMapperImpl implements ContactConsultationMapper {

    @Override
    public ContactConsultation contactConsultationDtoToContactConsultation(ContactConsultationDTO contactConsultationDTO) {
        if ( contactConsultationDTO == null ) {
            return null;
        }

        ContactConsultation contactConsultation = new ContactConsultation();

        contactConsultation.setId( contactConsultationDTO.getId() );
        contactConsultation.setContactConsultationLastDate( contactConsultationDTO.getContactConsultationLastDate() );
        contactConsultation.setUserId( contactConsultationDTO.getUserId() );

        return contactConsultation;
    }

    @Override
    public ContactConsultationDTO contactConsultationToContactConsultationDTO(ContactConsultation contactConsultation) {
        if ( contactConsultation == null ) {
            return null;
        }

        ContactConsultationDTO contactConsultationDTO = new ContactConsultationDTO();

        contactConsultationDTO.setId( contactConsultation.getId() );
        contactConsultationDTO.setContactConsultationLastDate( contactConsultation.getContactConsultationLastDate() );
        contactConsultationDTO.setUserId( contactConsultation.getUserId() );

        return contactConsultationDTO;
    }

    @Override
    public List<ContactConsultationDTO> listContactConsultationToListContactConsultationDTO(List<ContactConsultation> contactConsultation) {
        if ( contactConsultation == null ) {
            return null;
        }

        List<ContactConsultationDTO> list = new ArrayList<ContactConsultationDTO>( contactConsultation.size() );
        for ( ContactConsultation contactConsultation1 : contactConsultation ) {
            list.add( contactConsultationToContactConsultationDTO( contactConsultation1 ) );
        }

        return list;
    }
}
