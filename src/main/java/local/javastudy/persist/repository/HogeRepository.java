package local.javastudy.persist.repository;

import local.javastudy.persist.entity.Hoge;

import org.springframework.data.repository.CrudRepository;

public interface HogeRepository extends CrudRepository<Hoge, Long> {

}
