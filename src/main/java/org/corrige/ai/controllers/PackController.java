package org.corrige.ai.controllers;

import java.util.Collection;

import org.corrige.ai.models.pack.CreatePackBean;
import org.corrige.ai.models.pack.Pack;
import org.corrige.ai.services.implementations.PacksService;
import org.corrige.ai.util.ServerConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping( ServerConstants.SERVER_REQUEST 
		+ ServerConstants.PACKS_REQUESTS)
public class PackController {
	@Autowired
	private PacksService packsService;
	
	@PostMapping
	public ResponseEntity<Pack> addPack(@RequestBody CreatePackBean pack) {
		return ResponseEntity.ok(this.packsService.add(pack));
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<Collection<Pack>> getByUser(@RequestParam("userId") String userId) {
		return ResponseEntity.ok(this.packsService.getByUser(userId));
	}
	
	@PostMapping("/{userId}/actual")
	public void getMostRecentPack() {
		
	}
	
}
