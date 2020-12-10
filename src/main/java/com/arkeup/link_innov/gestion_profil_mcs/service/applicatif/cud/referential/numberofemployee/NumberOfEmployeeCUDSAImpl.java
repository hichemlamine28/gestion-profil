package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.referential.numberofemployee;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ErrorsEnum;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.referential.NumberOfEmployeeMapper;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.referential.NumberOfEmployee;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.referential.NumberOfEmployeeDTO;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.referential.numberofemployee.NumberOfEmployeeCUDSM;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.referential.numberofemployee.NumberOfEmployeeRSM;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.NumberOfEmployeeRepository;

@Service
public class NumberOfEmployeeCUDSAImpl implements NumberOfEmployeeCUDSA {

	@Autowired
	private NumberOfEmployeeCUDSM numberOfEmployeeCUDSM;

	@Autowired
	private NumberOfEmployeeRSM numberOfEmployeeRSM;

	@Autowired
	private NumberOfEmployeeMapper numberOfEmployeeFactory;

	@Autowired
	private NumberOfEmployeeRepository numberOfEmployeeRepository;

	@Override
	public NumberOfEmployeeDTO addNumberOfEmployee(NumberOfEmployeeDTO numberOfEmployeeDTO) {

		if (StringUtils.isEmpty(numberOfEmployeeDTO.getLabel())) {
			numberOfEmployeeDTO.setError(true);
			numberOfEmployeeDTO.setErrorMessage(ErrorsEnum.ERR_MCS_PROFIL_0043.getErrorMessage());
			numberOfEmployeeDTO.setErrorCode("ERR_MCS_PROFIL_0043");
			return numberOfEmployeeDTO;
		}

		NumberOfEmployee result = numberOfEmployeeCUDSM
				.create(numberOfEmployeeFactory.numberOfEmployeeDTOToNumberOfEmployee(numberOfEmployeeDTO));

		if (result.getId() == null) {
			numberOfEmployeeDTO.setError(true);
			numberOfEmployeeDTO.setErrorMessage(ErrorsEnum.ERR_MCS_PROFIL_0043.getErrorMessage());
			numberOfEmployeeDTO.setErrorCode("ERR_MCS_PROFIL_0043");
		} else {
			numberOfEmployeeDTO.setError(false);
			numberOfEmployeeDTO.setMessage("NumberOfEmployee added");
			numberOfEmployeeDTO.setId(result.getId());
		}

		return numberOfEmployeeDTO;
	}

	@Override
	public NumberOfEmployeeDTO updateNumberOfEmployee(NumberOfEmployeeDTO numberOfEmployeeDTO) {

		if (StringUtils.isEmpty(numberOfEmployeeDTO.getLabel())) {
			numberOfEmployeeDTO.setError(true);
			numberOfEmployeeDTO.setErrorMessage(ErrorsEnum.ERR_MCS_PROFIL_0043.getErrorMessage());
			numberOfEmployeeDTO.setErrorCode("ERR_MCS_PROFIL_0043");
			return numberOfEmployeeDTO;
		}

		numberOfEmployeeCUDSM
				.update(numberOfEmployeeFactory.numberOfEmployeeDTOToNumberOfEmployee(numberOfEmployeeDTO));
		numberOfEmployeeDTO.setError(false);
		numberOfEmployeeDTO.setMessage("NumberOfEmployee updated");
		return numberOfEmployeeDTO;
	}

	@Override
	public NumberOfEmployeeDTO deleteNumberOfEmployee(String idNumberOfEmployee) {
		Optional<NumberOfEmployee> res = numberOfEmployeeRSM.findById(idNumberOfEmployee);

		NumberOfEmployeeDTO result = new NumberOfEmployeeDTO();
		result.setId(idNumberOfEmployee);
		if (res.isPresent()) {
			numberOfEmployeeCUDSM.delete(idNumberOfEmployee);
			result.setError(false);
			result.setMessage("NumberOfEmployee deleted");
		} else {
			result.setError(true);
			result.setErrorMessage(ErrorsEnum.ERR_MCS_OBJECT_NOT_FOUND.getErrorMessage());
			result.setErrorCode("ERR_MCS_OBJECT_NOT_FOUND");
		}
		return result;
	}

	@Override
	public void initDatabase() {

		List<NumberOfEmployee> numberOfEmployees = new ArrayList<>();
		numberOfEmployeeRepository.deleteAll();

		NumberOfEmployee numberOfEmployee = new NumberOfEmployee();
		numberOfEmployee.setId("uuid-nbemployee-nn");
		numberOfEmployee.setLabel("Etablissement non employeur");
		numberOfEmployee.setCode("NN");
		numberOfEmployees.add(numberOfEmployee);

		numberOfEmployee = new NumberOfEmployee();
		numberOfEmployee.setId("uuid-nbemployee-0");
		numberOfEmployee.setLabel("0");
		numberOfEmployee.setCode("0");
		numberOfEmployees.add(numberOfEmployee);

		numberOfEmployee = new NumberOfEmployee();
		numberOfEmployee.setId("uuid-nbemployee-1-2");
		numberOfEmployee.setLabel("1 ou 2");
		numberOfEmployee.setCode("01");
		numberOfEmployees.add(numberOfEmployee);

		numberOfEmployee = new NumberOfEmployee();
		numberOfEmployee.setId("uuid-nbemployee-3-5");
		numberOfEmployee.setLabel("3 à 5");
		numberOfEmployee.setCode("02");
		numberOfEmployees.add(numberOfEmployee);

		numberOfEmployee = new NumberOfEmployee();
		numberOfEmployee.setId("uuid-nbemployee-6-9");
		numberOfEmployee.setLabel("6 à 9");
		numberOfEmployee.setCode("03");
		numberOfEmployees.add(numberOfEmployee);

		numberOfEmployee = new NumberOfEmployee();
		numberOfEmployee.setId("uuid-nbemployee-1-1");
		numberOfEmployee.setLabel("10 à 19");
		numberOfEmployee.setCode("11");
		numberOfEmployees.add(numberOfEmployee);

		numberOfEmployee = new NumberOfEmployee();
		numberOfEmployee.setId("uuid-nbemployee-20-49");
		numberOfEmployee.setLabel("20 à 49");
		numberOfEmployee.setCode("12");
		numberOfEmployees.add(numberOfEmployee);

		numberOfEmployee = new NumberOfEmployee();
		numberOfEmployee.setId("uuid-nbemployee-50-99");
		numberOfEmployee.setLabel("50 à 99");
		numberOfEmployee.setCode("21");
		numberOfEmployees.add(numberOfEmployee);

		numberOfEmployee = new NumberOfEmployee();
		numberOfEmployee.setId("uuid-nbemployee-100-199");
		numberOfEmployee.setLabel("100 à 199");
		numberOfEmployee.setCode("22");
		numberOfEmployees.add(numberOfEmployee);

		numberOfEmployee = new NumberOfEmployee();
		numberOfEmployee.setId("uuid-nbemployee-200-249");
		numberOfEmployee.setLabel("200 à 249");
		numberOfEmployee.setCode("31");
		numberOfEmployees.add(numberOfEmployee);

		numberOfEmployee = new NumberOfEmployee();
		numberOfEmployee.setId("uuid-nbemployee-250-499");
		numberOfEmployee.setLabel("250 à 499");
		numberOfEmployee.setCode("32");
		numberOfEmployees.add(numberOfEmployee);

		numberOfEmployee = new NumberOfEmployee();
		numberOfEmployee.setId("uuid-nbemployee-501-1000");
		numberOfEmployee.setLabel("500 à 999");
		numberOfEmployee.setCode("41");
		numberOfEmployees.add(numberOfEmployee);

		numberOfEmployee = new NumberOfEmployee();
		numberOfEmployee.setId("uuid-nbemployee-1000-1999");
		numberOfEmployee.setLabel("1 000 à 1 999");
		numberOfEmployee.setCode("42");
		numberOfEmployees.add(numberOfEmployee);

		numberOfEmployee = new NumberOfEmployee();
		numberOfEmployee.setId("uuid-nbemployee-3-2");
		numberOfEmployee.setLabel("2 000 à 4 999");
		numberOfEmployee.setCode("51");
		numberOfEmployees.add(numberOfEmployee);

		numberOfEmployee = new NumberOfEmployee();
		numberOfEmployee.setId("uuid-nbemployee-5001-10000");
		numberOfEmployee.setLabel("5 000 à 9 999");
		numberOfEmployee.setCode("52");
		numberOfEmployees.add(numberOfEmployee);

		numberOfEmployee = new NumberOfEmployee();
		numberOfEmployee.setId("uuid-nbemployee-10001-more");
		numberOfEmployee.setLabel("10 000 et plus");
		numberOfEmployee.setCode("53");
		numberOfEmployees.add(numberOfEmployee);

		numberOfEmployeeRepository.saveAll(numberOfEmployees);

		/*

		numberOfEmployee = new NumberOfEmployee();
		numberOfEmployee.setId("uuid-nbemployee-2-10");
		numberOfEmployee.setLabel("2 à 10");
		numberOfEmployees.add(numberOfEmployee);

		numberOfEmployee = new NumberOfEmployee();
		numberOfEmployee.setId("uuid-nbemployee-11-50");
		numberOfEmployee.setLabel("11 à 50");
		numberOfEmployees.add(numberOfEmployee);

		numberOfEmployee = new NumberOfEmployee();
		numberOfEmployee.setId("uuid-nbemployee-51-200");
		numberOfEmployee.setLabel("51 à 200");
		numberOfEmployees.add(numberOfEmployee);

		numberOfEmployee = new NumberOfEmployee();
		numberOfEmployee.setId("uuid-nbemployee-201-500");
		numberOfEmployee.setLabel("201 à 500");
		numberOfEmployees.add(numberOfEmployee);

		numberOfEmployee = new NumberOfEmployee();
		numberOfEmployee.setId("uuid-nbemployee-1001-5000");
		numberOfEmployee.setLabel("1001 à 5000");
		numberOfEmployees.add(numberOfEmployee);

		*/

	}

}
