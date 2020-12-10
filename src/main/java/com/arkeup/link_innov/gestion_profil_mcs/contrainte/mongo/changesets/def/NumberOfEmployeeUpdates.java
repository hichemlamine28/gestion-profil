package com.arkeup.link_innov.gestion_profil_mcs.contrainte.mongo.changesets.def;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.referential.NumberOfEmployee;
import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@ChangeLog(order = "001")
public class NumberOfEmployeeUpdates {
    @ChangeSet(order = "001", id = "updateNumberOfEmployee", runAlways = true, author = "André")
    public void updateNumberOfEmployee(MongoTemplate mongoTemplate)
    {
        String []idNumberOfEmployees = {
                                 "uuid-nbemployee-2-10", "uuid-nbemployee-11-50", "uuid-nbemployee-51-200",
                                 "uuid-nbemployee-201-500", "uuid-nbemployee-1001-5000"
                             };
        List<String> idNumberOfEmployeesList = Arrays.asList(idNumberOfEmployees);
        mongoTemplate.remove(new Query().addCriteria(Criteria.where("_id").in(idNumberOfEmployeesList)), NumberOfEmployee.class);
    }
}
