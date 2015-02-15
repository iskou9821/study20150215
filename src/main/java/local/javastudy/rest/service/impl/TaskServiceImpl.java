package local.javastudy.rest.service.impl;

import java.util.List;

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

	@Override
	public Task loadOldest() {
		Task t = repo.findOldest();
		return t;
	}

	@Override
	public Task loadLatest() {
		Task t = repo.findLatest();
		return t;
	}

	@Override
	public List<Task> getValidTasks() {
		List<Task> tasks = repo.findValidTasks();
		return tasks;
	}

	@Override
	public void clear() {
		repo.deleteAll();
	}

	@Override
	public long delete(Task entity) {
		entity.setDeleted(true);
		entity.setOrd(Task.DELETED_ORD);
		repo.save(entity);
		return entity.getId();
	}

	@Override
	public void changeOrder(Task t1, Task t2) {
		if (t1.getId() == null || t2.getId() == null) {
			throw new IllegalArgumentException("順番を入れ替えるためには、永続化済みのデータであることが必要です");
		}
		int ord1 = t1.getOrd();
		int ord2 = t2.getOrd();
		t1.setOrd(ord2);
		t2.setOrd(ord1);
		repo.save(t1);
		repo.save(t2);
	}

}
