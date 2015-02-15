package local.javastudy.rest.service;

import java.util.List;

import local.javastudy.persist.entity.Task;

public interface TaskService {
	public long save(Task entity);
	
	public Task loadOldest();
	
	public Task loadLatest();
	
	public List<Task> getValidTasks();
	
	public void clear();
	
}
