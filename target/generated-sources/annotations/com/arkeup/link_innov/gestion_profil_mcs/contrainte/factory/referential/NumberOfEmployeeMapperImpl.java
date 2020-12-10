package com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.referential;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.referential.NumberOfEmployee;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.referential.NumberOfEmployeeDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class NumberOfEmployeeMapperImpl implements NumberOfEmployeeMapper {

    @Override
    public NumberOfEmployeeDTO numberOfEmployeeToNumberOfEmployeeDTO(NumberOfEmployee numberOfEmployee) {
        if ( numberOfEmployee == null ) {
            return null;
        }

        NumberOfEmployeeDTO numberOfEmployeeDTO = new NumberOfEmployeeDTO();

        numberOfEmployeeDTO.setId( numberOfEmployee.getId() );
        numberOfEmployeeDTO.setLabel( numberOfEmployee.getLabel() );
        numberOfEmployeeDTO.setCode( numberOfEmployee.getCode() );

        return numberOfEmployeeDTO;
    }

    @Override
    public NumberOfEmployee numberOfEmployeeDTOToNumberOfEmployee(NumberOfEmployeeDTO numberOfEmployeeDTO) {
        if ( numberOfEmployeeDTO == null ) {
            return null;
        }

        NumberOfEmployee numberOfEmployee = new NumberOfEmployee();

        numberOfEmployee.setId( numberOfEmployeeDTO.getId() );
        numberOfEmployee.setLabel( numberOfEmployeeDTO.getLabel() );
        numberOfEmployee.setCode( numberOfEmployeeDTO.getCode() );

        return numberOfEmployee;
    }

    @Override
    public List<NumberOfEmployeeDTO> numberOfEmployeeToNumberOfEmployeeDTOs(List<NumberOfEmployee> numberOfEmployees) {
        if ( numberOfEmployees == null ) {
            return null;
        }

        List<NumberOfEmployeeDTO> list = new ArrayList<NumberOfEmployeeDTO>( numberOfEmployees.size() );
        for ( NumberOfEmployee numberOfEmployee : numberOfEmployees ) {
            list.add( numberOfEmployeeToNumberOfEmployeeDTO( numberOfEmployee ) );
        }

        return list;
    }

    @Override
    public List<NumberOfEmployee> numberOfEmployeeDTOToNumberOfEmployees(List<NumberOfEmployeeDTO> numberOfEmployeeDTOs) {
        if ( numberOfEmployeeDTOs == null ) {
            return null;
        }

        List<NumberOfEmployee> list = new ArrayList<NumberOfEmployee>( numberOfEmployeeDTOs.size() );
        for ( NumberOfEmployeeDTO numberOfEmployeeDTO : numberOfEmployeeDTOs ) {
            list.add( numberOfEmployeeDTOToNumberOfEmployee( numberOfEmployeeDTO ) );
        }

        return list;
    }
}
