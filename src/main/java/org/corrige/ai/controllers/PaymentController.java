package org.corrige.ai.controllers;

import org.corrige.ai.models.record.Record;
import org.corrige.ai.models.record.RecordBean;
import org.corrige.ai.services.implementations.RecordService;
import org.corrige.ai.util.ServerConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(ServerConstants.SERVER_REQUEST 
		+ ServerConstants.PAYMENT_REQUEST)
public class PaymentController {
	@Autowired
	private RecordService recordService;
	
	@PostMapping
	public ResponseEntity<Record> register(@RequestBody RecordBean record) {
		return ResponseEntity.ok(this.recordService.register(record));
	}
}
