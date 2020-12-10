package com.arkeup.link_innov.gestion_profil_mcs.service.businessdelegate;

import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ErrorsEnum;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.FunctionalInvalidDataException;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.TechnicalException;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.CategoryDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.CorporationDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.IsImportedDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.UserAuthDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.inscription.SignUpDTO;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.SignUpSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.category.CategoryRSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.user_auth.UserAuthRSA;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UploadBetaTestBDLImpl implements UploadBetaTestBDL {

    private Logger log = LoggerFactory.getLogger(UploadBetaTestBDL.class);

    @Autowired
    private SignUpSA signUpSA;

    @Autowired
    private CategoryRSA categoryRSA;

    @Autowired
    private UserAuthRSA userAuthRSA;

    @Override
    public IsImportedDTO uploadFileData(String inputFilePath) {
        IsImportedDTO isImportedDTO = new IsImportedDTO();
        Workbook workbook = null;
        Sheet sheet = null;

        workbook = getWorkBook(new File(inputFilePath));
        sheet = workbook.getSheetAt(0);

        /*Read and process each Row*/
        List<SignUpDTO> signUpDTOList = new ArrayList<>();
        Iterator<Row> rowIterator = sheet.iterator();

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            //Read and process each column in row

            String lastName = getCellValueBasedOnCellType(row, 0);
            String firstName = getCellValueBasedOnCellType(row, 1);
            String type = getCellValueBasedOnCellType(row, 2);
            String mail = getCellValueBasedOnCellType(row, 3);
            String password = getCellValueBasedOnCellType(row, 4);
            String profession = getCellValueBasedOnCellType(row, 5);
            String employeur = getCellValueBasedOnCellType(row, 6);
            String sexe = getCellValueBasedOnCellType(row, 7);


            if (!StringUtils.isEmpty(lastName) && !StringUtils.isEmpty(firstName)
                    && !StringUtils.isEmpty(type) && !StringUtils.isEmpty(mail)
                    && !StringUtils.isEmpty(password) && !StringUtils.isEmpty(profession)
                    && !StringUtils.isEmpty(employeur) && !StringUtils.isEmpty(sexe)) {

                String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(mail);
                boolean isMailValid = matcher.matches();
                if (!isMailValid)
                    throw new FunctionalInvalidDataException(new IsImportedDTO(), ErrorsEnum.ERR_MCS_PROFIL_00041);

                CategoryDTO categoryDTO = categoryRSA.findByName(type);

                if (categoryDTO == null) {
                    categoryDTO = new CategoryDTO();
                    categoryDTO.setId("uuid-category-academique");
                    categoryDTO.setName("Académique");
                }

                CorporationDTO corporationDTO = new CorporationDTO();
                categoryDTO.setName(employeur);

                SignUpDTO signUpDTO = new SignUpDTO();
                signUpDTO.setLastName(lastName);
                signUpDTO.setFirstName(firstName);
                signUpDTO.setPassword(password);
                signUpDTO.setMail(mail);
                signUpDTO.setEmployer(corporationDTO);
                signUpDTO.setProfession(profession);
                if (sexe.equals("M"))
                    signUpDTO.setMale(true);
                else
                    signUpDTO.setMale(false);

                signUpDTO.setType(categoryDTO);

                signUpDTOList.add(signUpDTO);
            }
        }

        if (signUpDTOList.size() > 0) {
            signUpSA.importBetaTesteurs(signUpDTOList);
            isImportedDTO.setImported(Boolean.TRUE);
        } else
            throw new FunctionalInvalidDataException(new IsImportedDTO(), ErrorsEnum.ERR_MCS_EMPTY_CONTENT);

        return isImportedDTO;
    }

    private Workbook getWorkBook(File fileName) {
        Workbook workbook = null;
        try {
            String myFileName = fileName.getName();
            String extension = myFileName.substring(myFileName.lastIndexOf("."));
            if (extension.equalsIgnoreCase(".xls")) {
                workbook = new HSSFWorkbook(Files.newInputStream(Paths.get("/tmp/" + myFileName)));
            } else if (extension.equalsIgnoreCase(".xlsx")) {
                workbook = new XSSFWorkbook(Files.newInputStream(Paths.get("/tmp/" + myFileName)));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new TechnicalException(new IsImportedDTO(), ErrorsEnum.ERR_MCS_READ_FILE_EXCEL);
        }
        return workbook;
    }

    private String getCellValueBasedOnCellType(Row rowData, int columnPosition) {
        String cellValue = null;
        Cell cell = rowData.getCell(columnPosition);
        if (cell != null) {

            if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                String inputCellValue = cell.getStringCellValue();
                if (inputCellValue.endsWith(".0")) {
                    inputCellValue = inputCellValue.substring(0, inputCellValue.length() - 2);
                }
                cellValue = inputCellValue;
            } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                Double doubleVal = new Double(cell.getNumericCellValue());
                cellValue = Integer.toString(doubleVal.intValue());
            }
        }
        return cellValue;
    }

}
