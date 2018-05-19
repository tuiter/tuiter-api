package org.tuiter.services.implementations;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.tuiter.beans.modelbeans.EssayBean;
import org.tuiter.errors.exceptions.EssayNotExistsException;
import org.tuiter.errors.exceptions.UserNotExistsException;
import org.tuiter.models.Essay;
import org.tuiter.models.User;
import org.tuiter.repositories.EssayRepository;
import org.tuiter.services.interfaces.EssayService;
import org.tuiter.services.interfaces.UserService;

public class EssayServiceImpl implements EssayService{
	private EssayRepository essayRepository;
	private UserService userService;
		
	@Autowired
	public void setEssayRepository(EssayRepository essayRepository) {
		this.essayRepository = essayRepository;
	}

	@Autowired
	public void setUserService(UserServiceImpl userService) {
		this.userService = userService;
	}

	@Override
	public Essay create(EssayBean bean) throws UserNotExistsException{
		User user = userService.findByUsername(bean.getUserUsername());
		if(user != null) {
			Essay essay = new Essay(user, bean.getTitle(), bean.getTheme(), bean.getContent());
			return essayRepository.save(essay);
		} else {
			throw new UserNotExistsException();
		}
	}

	@Override
	public Essay update(EssayBean bean) throws UserNotExistsException, EssayNotExistsException{
		User user = userService.findByUsername(bean.getUserUsername());
		if(user != null) {
			Essay essay = essayRepository.findByTitleAndUserId(bean.getTitle(), user.getId());
			if (essay != null) {
				return essayRepository.save(essay);
			} else {
				throw new EssayNotExistsException();
			}
		} else {
			throw new UserNotExistsException();
		}
	}

	@Override
	public Essay delete(String id) throws EssayNotExistsException {
		Optional<Essay> essay = essayRepository.findById(id);
		if(essay.isPresent()) {
			essayRepository.delete(essay.get());
			return essay.get();
		} else {
			throw new EssayNotExistsException();
		}
	}

	@Override
	public Iterable<Essay> findAllByUserUsername(String username) throws UserNotExistsException{
		User user = userService.findByUsername(username);
		if (user != null) {
			return essayRepository.findAllByUserUsername(username);
		} else {
			throw new UserNotExistsException();
		}
	}

	@Override
	public Iterable<Essay> findAllByUserId(String id) throws UserNotExistsException{
		User user = userService.findById(id);
		if (user != null) {
			return essayRepository.findAllByUserId(id);
		} else {
			throw new UserNotExistsException();
		}
	}
	@Override
	public Iterable<Essay> findAll() {
		return essayRepository.findAll();
	}

	@Override
	public Essay findByTitleAndUserId(String id, String userId) {
		return essayRepository.findByTitleAndUserId(id, userId);
	}
}
