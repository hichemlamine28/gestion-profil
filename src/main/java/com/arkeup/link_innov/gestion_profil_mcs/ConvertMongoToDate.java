package com.arkeup.link_innov.gestion_profil_mcs;

import java.time.Instant;
import java.util.Date;

public class ConvertMongoToDate {

	public Instant convertToInstantFrom(String objectId) {
		return convertToDateFrom(objectId).toInstant();
	}

	public Date convertToDateFrom(String objectId) {
		return new Date(convertToTimestampFrom(objectId));
	}

	public long convertToTimestampFrom(String objectId) {
		return Long.parseLong(objectId.substring(0, 8), 16) * 1000;
	}

}
