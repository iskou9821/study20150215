package local.javastudy.rest.service.impl;

import javax.transaction.Transactional;

import local.javastudy.persist.entity.Task;
import local.javastudy.persist.repository.TaskRepository;
import local.javastudy.rest.service.TaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {
	@Autowired TaskRepository repo;
	
	@Override
	public long save(Task entity) {
		repo.save(entity);
		return entity.getId();
	}

}
