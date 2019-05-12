package com.management.ssm.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.management.ssm.model.Product;
import com.management.ssm.repository.productRepository;
import com.management.ssm.repository.productTypeRepository;
@CrossOrigin
@RestController
@RequestMapping(value="/product")
public class productController {
	@Autowired
	private productRepository productrepository;
	@Autowired
	private productTypeRepository producttyperepository;
	
//	Get all records including NOTACTIVE
	@RequestMapping(method=RequestMethod.GET)
	public String getAllProducts() throws JsonProcessingException {
		ArrayList<Product> productlist=(ArrayList<Product>) productrepository.findAll();
		ObjectMapper mapper=new ObjectMapper();
		String value=mapper.writeValueAsString(productlist);
		return value;
	}
//	Get all active records
	@RequestMapping(value="/all",method=RequestMethod.GET)
	public String getAllActiveProducts() throws JsonProcessingException {
		ArrayList<Product> productlist=(ArrayList<Product>) productrepository.findActive();
		ObjectMapper mapper=new ObjectMapper();
		String value=mapper.writeValueAsString(productlist);
		return value;
	}
//	Get a single record on the basis of id
	@RequestMapping(value="{id}",method=RequestMethod.GET)
	public String getProduct(@PathVariable long id) throws JsonProcessingException {
		Product product=productrepository.findOne(id);
		ObjectMapper mapper=new ObjectMapper();
		String value=mapper.writeValueAsString(product);
		return value;
	}
	
//	Posting a record in table
	@RequestMapping(method=RequestMethod.POST)
	public String insertProduct(@RequestBody String data) throws JsonProcessingException {
		ObjectMapper mapper=new ObjectMapper();
		Product product=new Product();
		JSONObject object=new JSONObject(data);
		if(!(object.has("name")&&object.has("purprice")&&object.has("saleprice")
				&&object.has("type")&& object.has("stock")))
			return "Please enter all information";
		//Entering data into object of a product
		product.setPRODUCT_NAME(object.getString("name"));
		product.setPRODUCTPURCHASE_PRICE(object.getDouble("purprice"));
		product.setPRODUCTSALE_PRICE(object.getDouble("saleprice"));
		product.setPRODUCT_STOCK(object.getDouble("stock"));
		product.setPRODUCTTYPE_ID(producttyperepository.findOne(object.getLong("type")));
		product.setPRODUCTIS_ACTIVE('Y');
		productrepository.saveAndFlush(product);
		return mapper.writeValueAsString(product);
	}
	
//	Updating a record
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public String updateProduct(@PathVariable long id, @RequestBody String data) throws UnknownHostException, JsonProcessingException {
		//Getting ip address and name
		InetAddress inetAddress=InetAddress.getLocalHost();
		SimpleDateFormat dateFormatter=new SimpleDateFormat("yyyy/MMM/dd HH:mm:ss");
		Date date = new Date();
		JSONObject object=new JSONObject(data);
		ObjectMapper mapper=new ObjectMapper();
		//Updation of product
		Product product=productrepository.findOne(id);
		if(object.has("name"))
			product.setPRODUCT_NAME(object.getString("name"));
		if(object.has("stock"))
			product.setPRODUCT_STOCK(object.getDouble("stock"));
		if(object.has("saleprice"))
			product.setPRODUCT_STOCK(object.getDouble("saleprice"));
		if(object.has("purprice"))
			product.setPRODUCTPURCHASE_PRICE(object.getDouble("purprice"));
		if(object.has("deactive"))
			product.setPRODUCTIS_ACTIVE('N');
		product.setPRODUCTMODIFIED_BY(inetAddress.getHostName());
		product.setPRODUCTMODIFIED_WORKSTATION(inetAddress.getHostAddress());
		product.setPRODUCTMODIFIED_WHEN(dateFormatter.format(date));
		//Saving in Database
		productrepository.saveAndFlush(product);
		return mapper.writeValueAsString(product);		
	}	
	
//	Deleting a record
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public String delete(@PathVariable long id) {		
		Product product=productrepository.findOne(id);
		productrepository.delete(id);
		return product.getPRODUCT_NAME()+" has been deleted";
	}
	
//	Obtaining active record by search
	@RequestMapping(value="/search",method=RequestMethod.GET)
	public String getBySearch(@RequestBody String data) throws JsonProcessingException {
		JSONObject object=new JSONObject(data);
		if(!object.has("search"))
			return "Search string is missing";
		ArrayList<Product> productlist=(ArrayList<Product>) productrepository.findBySearch(object.getString("search"));
		ObjectMapper mapper=new ObjectMapper();
		String value=mapper.writeValueAsString(productlist);
		return value;
	}
	
//	Obtaining all record by search including non-actives
	@RequestMapping(value="/search/all",method=RequestMethod.GET)
	public String getAllBySearch(@RequestBody String data) throws JsonProcessingException {
		JSONObject object=new JSONObject(data);
		if(!object.has("search"))
			return "Search string is missing";
		ArrayList<Product> productlist=(ArrayList<Product>) productrepository.findAllBySearch(object.getString("search"));
		ObjectMapper mapper=new ObjectMapper();
		String value=mapper.writeValueAsString(productlist);
		return value;
	}
	
}
