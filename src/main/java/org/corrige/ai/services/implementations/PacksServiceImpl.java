package org.corrige.ai.services.implementations;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

import org.corrige.ai.enums.PackageType;
import org.corrige.ai.models.pack.CreatePackBean;
import org.corrige.ai.models.pack.Pack;
import org.corrige.ai.repositories.PackRepository;
import org.corrige.ai.services.interfaces.PackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PacksServiceImpl implements PackService {
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

	public Optional<Pack> getMostRecentPack(String userId) {
		return this.getByUser(userId).stream().sorted().findFirst();
	}
	
	public void update(Pack pack) {
		this.packRepository.save(pack);
	}

	public void remove(String id) {
		this.packRepository.deleteById(id);
	}

}
