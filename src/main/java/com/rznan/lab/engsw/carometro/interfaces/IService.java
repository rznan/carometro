package com.rznan.lab.engsw.carometro.interfaces;

import java.util.List;

public interface IService<Entity, DTO, ID> {
	public List<Entity> getAll();
	public Entity getAllById(List<ID> idList);
	public Entity getById(ID id);
	public void save(DTO dto);
	public void delete(ID id);
}
