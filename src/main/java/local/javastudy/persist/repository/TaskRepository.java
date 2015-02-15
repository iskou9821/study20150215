package local.javastudy.persist.repository;

import local.javastudy.persist.entity.Task;

import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Long> {

}
