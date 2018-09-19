package org.corrige.ai.services.implementations;

import java.util.Optional;

import org.corrige.ai.models.record.Record;
import org.corrige.ai.models.record.RecordBean;

public interface RecordService {
	Record register(RecordBean record);
	void update(Record record);
	Optional<Record> getByEssayId(String id);
}
