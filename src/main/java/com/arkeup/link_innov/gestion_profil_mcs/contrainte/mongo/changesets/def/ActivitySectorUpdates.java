package com.arkeup.link_innov.gestion_profil_mcs.contrainte.mongo.changesets.def;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.referential.ActivitySector;
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
public class ActivitySectorUpdates {
    @ChangeSet(order = "001", id = "updateActivitySector", runAlways = true, author = "André")
    public void updateActivitySector(MongoTemplate mongoTemplate)
    {
        List<String> idActivitySectors = Arrays.asList("uuid-activity-sector-informatique","uuid-activity-sector-vente");
        mongoTemplate.remove(new Query().addCriteria(Criteria.where("_id").in(idActivitySectors)), ActivitySector.class);
    }
}


