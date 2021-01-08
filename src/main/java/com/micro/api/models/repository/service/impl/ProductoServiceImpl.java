package com.micro.api.models.repository.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.micro.commons.models.entity.Producto;
import com.micro.api.models.repository.IProductoRepository;
import com.micro.api.models.repository.service.IProductoService;

@Service
public class ProductoServiceImpl implements IProductoService {
	
	@Autowired
	private IProductoRepository productImp;
	
	
	@Override
	@Transactional(readOnly = true)
	public List<Producto> findAll(){
		
				
		return productImp.findAll();
		
		
	}


	@Override
	@Transactional(readOnly = true)
	public Producto findById(long id) {
		
		return productImp.findById(id).orElse(null);
		
	}


	@Override
	@Transactional
	public void save(Producto product) {
		productImp.save(product);
		
	}


	@Transactional
	public void deleteById(long id) {
		productImp.deleteById(id);
		
	}


	@Override
	public boolean exist(long id) {
		
		return productImp.existsById(id);
	}


	@Override
	public boolean existByNombre(String nombre) {
		
		return productImp.existsByNombre(nombre);
	}

}
