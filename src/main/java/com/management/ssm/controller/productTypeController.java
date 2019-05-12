package com.management.ssm.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
import com.management.ssm.model.ProductType;
import com.management.ssm.repository.productTypeRepository;

@RestController
@CrossOrigin
@RequestMapping(value = "/producttype")
public class productTypeController {
	@Autowired
	private productTypeRepository producttyperepository;

//	Getting all records
	@RequestMapping(method = RequestMethod.GET)
	public String getAllProductTypes() throws JsonProcessingException {
		List<ProductType> productlist = producttyperepository.findAll();
		ObjectMapper mapper = new ObjectMapper();
		String value = mapper.writeValueAsString(productlist);
		return value;
	}
	
	//	Getting all records
	@RequestMapping(value="/{id}",method = RequestMethod.GET)
	public String getProductTypeAsString(@PathVariable long id) throws JsonProcessingException {
		ProductType productlist = producttyperepository.findOne(id);
		ObjectMapper mapper = new ObjectMapper();
		String value = mapper.writeValueAsString(productlist);
		return value;
	}

//	Entering a record
	@RequestMapping(value = "/enterproducttype", method = RequestMethod.POST)
	public String insertProduct(@RequestBody String data) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		ProductType pro = new ProductType();
		JSONObject object = new JSONObject(data);
		if (!object.has("name"))
			return "Please enter product type name";
		// Entering data into object of a product
		pro.setPRODUCTTYPE_NAME(object.getString("name"));
		pro.setPRODUCTTYPEIS_ACTIVE('Y');
		producttyperepository.saveAndFlush(pro);
		return mapper.writeValueAsString(pro);
	}
//	Updating a record
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public String updateProduct(@PathVariable long id, @RequestBody String data) throws UnknownHostException, JsonProcessingException {
		//Getting ip address, name and setting date
		InetAddress inetAddress=InetAddress.getLocalHost();
		SimpleDateFormat dateFormatter=new SimpleDateFormat("yyyy/MMM/dd HH:mm:ss");
		Date date = new Date();
		JSONObject object=new JSONObject(data);
		ObjectMapper mapper=new ObjectMapper();
		//Updation of producttype
		ProductType producttype=producttyperepository.findOne(id);
		if(object.has("name"))
			producttype.setPRODUCTTYPE_NAME(object.getString("name"));
		if(object.has("deactive"))
			producttype.setPRODUCTTYPEIS_ACTIVE('N');
		producttype.setPRODUCTTYPEMODIFIED_BY(inetAddress.getHostName());
		producttype.setPRODUCTTYPEMODIFIED_WORKSTATION(inetAddress.getHostAddress());
		producttype.setPRODUCTTYPEMODIFIED_WHEN(dateFormatter.format(date));
		//Saving in Database
		producttyperepository.saveAndFlush(producttype);
		return mapper.writeValueAsString(producttype);
	}
//	Deleting a record
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public String delete(@PathVariable long id) {		
		ProductType producttype=producttyperepository.findOne(id);
		producttyperepository.delete(id);
		return "\""+producttype.getPRODUCTTYPE_NAME()+"\" has been deleted";
	}
	
}
