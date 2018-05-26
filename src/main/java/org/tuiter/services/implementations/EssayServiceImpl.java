package org.tuiter.services.implementations;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tuiter.beans.EditEssayBean;
import org.tuiter.beans.modelbeans.EssayBean;
import org.tuiter.errors.exceptions.EmptyFieldsException;
import org.tuiter.errors.exceptions.EssayNotExistsException;
import org.tuiter.errors.exceptions.UserNotExistsException;
import org.tuiter.errors.exceptions.UserNotFoundException;
import org.tuiter.models.Essay;
import org.tuiter.models.User;
import org.tuiter.repositories.EssayRepository;
import org.tuiter.services.interfaces.EssayService;
import org.tuiter.services.interfaces.UserService;
@Service
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
			Essay essay = new Essay(user.getId(), bean.getTitle(), bean.getTheme(), bean.getContent(), bean.getType());
			return essayRepository.save(essay);
		} else {
			throw new UserNotExistsException();
		}
	}

	@Override
	public Essay update(String id, EditEssayBean bean) throws EssayNotExistsException, EmptyFieldsException{
		Optional<Essay> essay_opt = essayRepository.findById(id);
		if(essay_opt.isPresent()) {
			Essay essay = essay_opt.get();
			if(!bean.getTitle().isEmpty() && !bean.getTheme().isEmpty() && !bean.getContent().isEmpty()) {
				essay.setTitle(bean.getTitle());
				essay.setTheme(bean.getTheme());
				essay.setContent(bean.getContent());
				essay.setType(bean.getType());
				essayRepository.save(essay);
				return essay;
			} else {
				throw new EmptyFieldsException();
			}
			
		} else {
			throw new EssayNotExistsException();
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
			return essayRepository.findAllByUserId(user.getId());
		} else {
			throw new UserNotExistsException();
		}
	}

	@Override
	public Iterable<Essay> findAllByUserId(String id) throws UserNotExistsException, UserNotFoundException{
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

	@Override
	public Essay findById(String id) throws EssayNotExistsException {
		Optional<Essay> essay = essayRepository.findById(id);
		if(essay.isPresent()) {
			return essay.get();
		} else {
			throw new EssayNotExistsException();
		}
	}
}
