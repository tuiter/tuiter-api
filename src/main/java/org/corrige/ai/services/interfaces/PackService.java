package org.corrige.ai.services.interfaces;

import java.util.Collection;
import java.util.Optional;

import org.corrige.ai.models.pack.CreatePackBean;
import org.corrige.ai.models.pack.Pack;

public interface PackService {
	Pack add(CreatePackBean packData);
	Collection<Pack> getByUser(String userId);
	Optional<Pack> getMostRecentPack(String userId);
	void update(Pack pack);
	void remove(String id);
}
