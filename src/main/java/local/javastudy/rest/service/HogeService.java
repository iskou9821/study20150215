package local.javastudy.rest.service;

import local.javastudy.persist.entity.Hoge;
import local.javastudy.rest.resource.model.HogeModel;

public interface HogeService {
	public HogeModel[] findAll();
	public HogeModel findOne(Long id);
	public HogeModel save(HogeModel model);
	public boolean delete(Long id);
	public HogeModel toModel(Hoge entity);
	public Hoge toEntity(HogeModel model);
}
