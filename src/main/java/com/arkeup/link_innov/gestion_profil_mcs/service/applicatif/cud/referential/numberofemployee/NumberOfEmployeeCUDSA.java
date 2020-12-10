package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.referential.numberofemployee;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.referential.NumberOfEmployeeDTO;

public interface NumberOfEmployeeCUDSA {

	public NumberOfEmployeeDTO addNumberOfEmployee(NumberOfEmployeeDTO numberOfEmployeeDTO);

	public NumberOfEmployeeDTO updateNumberOfEmployee(NumberOfEmployeeDTO numberOfEmployeesDTO);

	public NumberOfEmployeeDTO deleteNumberOfEmployee(String idNumberOfEmployee);

	public void initDatabase();

}
