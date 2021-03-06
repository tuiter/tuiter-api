package org.corrige.ai.services.implementations;

import java.util.Optional;

import org.corrige.ai.models.record.Record;
import org.corrige.ai.models.record.RecordBean;
import org.corrige.ai.repositories.RecordRepository;
import org.corrige.ai.services.interfaces.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecordServiceImpl implements RecordService{
	@Autowired
	private RecordRepository recordRepository;
	
	public Record register(RecordBean record) {
		Record newRecord = new Record(record.getEssayId(), record.getValue(), false);
		return this.recordRepository.save(newRecord);
	}
	
	public void update(Record record) {
		this.recordRepository.save(record);
	}
	
	public Optional<Record> getByEssayId(String id) {
		return this.recordRepository.findByEssayId(id);
	}
}
