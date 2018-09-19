package org.corrige.ai.services.interfaces;

import java.util.Collection;

import org.corrige.ai.enums.PackageType;
import org.corrige.ai.models.pack.CreatePackBean;
import org.corrige.ai.models.pack.Pack;

public interface PackService {
	Pack add(CreatePackBean packData);
	Collection<Pack> getByUser(String userId);
	Pack getMostRecentPack(String userId);
	void update(Pack pack);
	void remove(String id);
}
