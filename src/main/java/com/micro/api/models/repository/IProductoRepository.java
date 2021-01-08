package com.micro.api.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.micro.commons.models.entity.Producto;



public interface IProductoRepository extends JpaRepository<Producto, Long> {
	
	
	boolean existsByNombre (String nombre);
	
	

}
