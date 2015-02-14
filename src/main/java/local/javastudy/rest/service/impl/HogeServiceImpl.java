package local.javastudy.rest.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import local.javastudy.persist.entity.Hoge;
import local.javastudy.persist.repository.HogeRepository;
import local.javastudy.rest.resource.model.HogeModel;
import local.javastudy.rest.service.HogeService;

import org.apache.commons.beanutils.BeanUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class HogeServiceImpl implements HogeService {
	@Autowired HogeRepository repo;
	
	@Override
	public HogeModel[] findAll() {
		List<HogeModel> res = new ArrayList<HogeModel>();
		for (Hoge hoge : repo.findAll()) {
			res.add(toModel(hoge));
		}
		return res.toArray(new HogeModel[0]);
	}

	@Override
	public HogeModel findOne(Long id) {
		if (id == null) return null;
		return toModel(repo.findOne(id));
	}

	@Override
	public HogeModel save(HogeModel model) {
		Hoge entity = toEntity(model);
		repo.save(entity);
		return toModel(entity);
	}
	
	@Override
	public boolean delete(Long id) {
		Hoge hoge = repo.findOne(id);
		repo.delete(hoge);
		return true;
	}
	
	@Override
	public HogeModel toModel(Hoge entity) {
		HogeModel model = new HogeModel();
		try {
			BeanUtils.copyProperties(model, entity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new IllegalStateException(e);
		}
		return model;
	}

	@Override
	public Hoge toEntity(HogeModel model) {
		Hoge entity;
		if (model.getId() != null && model.getId() != 0L) {
			entity = repo.findOne(model.getId());
		} else {
			entity = new Hoge();
		}
		try {
			ObjectMapper mapper = new ObjectMapper();
			@SuppressWarnings("unchecked")
			Map<String, Object> properties = mapper.convertValue(model, Map.class);
			properties.remove("id"); //IDをコピー対象から除く(なんか手続きが冗長なので、BeanUtilsBeanとか使って動きをカスタマイズした方が良いか？？？)
			BeanUtils.populate(entity, properties);
			if (entity.getId() != null && entity.getId() == 0L) {
				entity.setId(null);
			}
			return entity;
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new IllegalStateException(e);
		}
	}

}
