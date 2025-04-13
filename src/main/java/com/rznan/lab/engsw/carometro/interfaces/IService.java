package com.rznan.lab.engsw.carometro.interfaces;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IService<Dto, ID> {
	List<Dto> getAll();
	List<Dto> getAllById(List<ID> idList);
	Dto getById(ID id);
	Dto save(Dto dto);
	Dto update(Dto dto);
	void delete(ID id);
}
