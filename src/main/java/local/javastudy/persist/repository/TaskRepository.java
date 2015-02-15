package local.javastudy.persist.repository;

import java.util.List;

import local.javastudy.persist.entity.Task;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Long> {
	
	@Query("select t from Task t where ord = (select Max(t2.ord) from Task t2)")
    public Task findLatest();
	
	@Query("select t from Task t where ord = (select MIN(t2.ord) from Task t2 where t2.deleted = false)")
    public Task findOldest();
	
	@Query("select t from Task t where t.deleted = false order by t.ord asc")
	public List<Task> findValidTasks();
}
