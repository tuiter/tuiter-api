package org.corrige.ai.services.implementations;

import java.util.Collection;
import java.util.Date;

import org.corrige.ai.enums.PackageType;
import org.corrige.ai.models.pack.CreatePackBean;
import org.corrige.ai.models.pack.Pack;
import org.corrige.ai.repositories.PackRepository;
import org.corrige.ai.validations.exceptions.PackNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PacksService {
	@Autowired
	private PackRepository packRepository;

	public Pack add(CreatePackBean packData) {
		Pack pack = new Pack(packData.getUserId(),
				packData.getPackageType(),
				getCounterForPack(packData.getPackageType()),
				new Date());
		
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

	public Pack getMostRecentPack(String userId) {
		Collection<Pack> packs = this.getByUser(userId);
		if(packs.isEmpty())
			throw new PackNotFoundException();
		if(packs.size() == 1)
			return (Pack) packs.toArray()[0];
		
		return packs.stream().sorted().findFirst().get();
	}

	public void remove(String id) {
		this.packRepository.deleteById(id);
	}

}
