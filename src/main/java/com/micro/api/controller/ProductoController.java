package com.micro.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


import com.micro.commons.models.entity.Producto;
import com.micro.api.models.repository.service.IProductoService;
import  com.micro.commons.controller.ControllerUtil;

@RestController
@RequestMapping(value = "/product")
public class ProductoController extends ControllerUtil{
	
	

	@Autowired
	private IProductoService   productoService;
	
	@Autowired
	private Environment env;
	
	@Value("${server.port}")
	private Integer port;
	
	@RequestMapping(value = "/listar",method = RequestMethod.GET )
	public List<Producto> getListar(){
		
		return productoService.findAll().stream().map(p->{
			//p.setPort(Integer.valueOf(env.getProperty("local.server.port")));
			p.setPort(port);
			return p;
		}).collect(Collectors.toList());
	}
	
	@RequestMapping(value = "/search/{id}",method = RequestMethod.GET )
	public Producto getListarById(@PathVariable("id") long id) {
		
		Producto product =productoService.findById(id);
		product.setPort(Integer.valueOf(env.getProperty("local.server.port")));
		
		/*try {
			Thread.sleep(2000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	*/
		
		return product;
	}
	
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody Producto crear (@Valid @RequestBody Producto product,BindingResult result) {
		if (result.hasErrors()) {
			throw  new ResponseStatusException(HttpStatus.BAD_REQUEST,  getFormatMessage (result));
		}
		if (!productoService.existByNombre(product.getNombre())){
			productoService.save(product);
		}
		else
			throw  new ResponseStatusException(HttpStatus.FOUND,  getFormatMessage (result));
	 	
	 	 return product;
	}
	
	
	
	@PutMapping("/edit/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody Producto edit (@Valid @RequestBody Producto product,BindingResult result, @PathVariable("id") long id) {
		if (result.hasErrors()) {
			throw  new ResponseStatusException(HttpStatus.BAD_REQUEST,  getFormatMessage (result));
		}
		
		if (id>0 &&  productoService.exist(id)) {
			    product.setId(id);
		       	productoService.save(product);
		}
		else
			throw  new ResponseStatusException(HttpStatus.NOT_FOUND,  getFormatMessage (result));
	 	
	 	 return product;
	}
	
	
	
	@DeleteMapping("/delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public @ResponseBody void eliminar (@PathVariable("id") long id) {
		
		
		if (id>0 && productoService.exist(id))
		    	productoService.deleteById(id);
		else
			throw  new ResponseStatusException(HttpStatus.NOT_FOUND,"recurso no encontrado");
	 	
	 	 
	}
	
	
	
	
	
	
	
	
	
	

}
