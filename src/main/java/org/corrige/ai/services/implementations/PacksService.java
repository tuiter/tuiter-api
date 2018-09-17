package org.corrige.ai.services.implementations;

import java.util.Collection;
import java.util.Date;

import org.corrige.ai.enums.PackageType;
import org.corrige.ai.models.pack.Pack;
import org.corrige.ai.repositories.PackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PacksService {
	@Autowired
	private PackRepository packRepository;

	public Pack add(String userId, PackageType packageId) {
		Pack pack = new Pack(userId, packageId, getCounterForPack(packageId), new Date());
		return this.packRepository.save(pack);
	}
	
	private Integer getCounterForPack(PackageType packageId) {
		Integer basicPackCounter = 5;
		Integer platinumPackCounter = 10;
		
		if(packageId.equals(PackageType.BASIC))
			return basicPackCounter;
		return platinumPackCounter;
	}

	public Collection<Pack> getByUser(String userId) {
		return this.packRepository.findAllByUserId(userId);
	}

}
