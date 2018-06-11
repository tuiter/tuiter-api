package org.tuiter.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tuiter.models.Essay;

@Repository
public interface EssayRepository extends JpaRepository<Essay, Long> {
	
	public Collection<Essay> findAllByUserId(String id);

	public Essay findByTitleAndUserId(String title, String id);

}
