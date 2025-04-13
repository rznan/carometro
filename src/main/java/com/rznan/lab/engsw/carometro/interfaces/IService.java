package com.rznan.lab.engsw.carometro.interfaces;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IService<Entity, DTO, ID> {
	public List<Entity> getAll();
	public List<Entity> getAllById(List<ID> idList);
	public Entity getById(ID id);
	public void save(DTO dto);
	public void delete(ID id);
}
