package com.micro.api.models.repository.service;

import java.util.List;

import com.micro.commons.models.entity.Producto;

public interface IProductoService {
	
	List<Producto> findAll();
	
	Producto findById(long id);
	
	void save (Producto product);
	
	void deleteById(long id);
	
	boolean exist (long id);
	
	boolean existByNombre (String nombre);
	

}
