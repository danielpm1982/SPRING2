package com.danielpm1982.Maven_Web_Spring_JPA_AOP_NoXML_Security_REST_WS.controller;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.danielpm1982.Maven_Web_Spring_JPA_AOP_NoXML_Security_REST_WS.entity.EntityModel;
//import com.danielpm1982.Maven_Web_Spring_JPA_AOP_NoXML_Security_REST_WS.error.MyIndexOutOfBoundsException;
import com.danielpm1982.Maven_Web_Spring_JPA_AOP_NoXML_Security_REST_WS.service.EntityModelService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
@RequestMapping("/controller/api")
public class MyWebServiceRESTController {
	@Autowired
	private EntityModelService entityModelService;
	@GetMapping("/entityModels")
	public List<EntityModel> getEntityModels() throws JsonParseException, JsonMappingException, IOException {
		List<EntityModel> entityModelList = entityModelService.findAll();
		return entityModelList;
	}
//	@GetMapping("/entityModels/{index}")
//	public EntityModel getEntityModel(@PathVariable int index) throws JsonParseException, JsonMappingException, IOException {
//		List<EntityModel> entityModelList = entityModelService.findAll();
//		if(index>=entityModelList.size()||index<0) {
//			throw new MyIndexOutOfBoundsException("Index is invalid for list of size: "+entityModelList.size()+". Index (path variable) must be between 0 and "+(entityModelList.size()-1));
//		}
//		return entityModelList.get(index);
//	}
	@GetMapping("/entityModels/{id}")
	public EntityModel getEntityModel(@PathVariable int id) throws JsonParseException, JsonMappingException, IOException {
		EntityModel entityModel = entityModelService.findById(id);
		if(entityModel == null) {
			throw new RuntimeException("No EntityModel found for id = "+id+"!");
		}
		return entityModel;
	}
	@PostMapping("/entityModels")
	public EntityModel postEntityModels(@RequestBody EntityModel entityModel) throws JsonParseException, JsonMappingException, IOException {
		entityModel.setId(0); //setting 0 will guarantee any POST request will lead to saving as a new entity instead of updating any pre-existing one (with the same id).
		LocalDateTime localDateTime = LocalDateTime.now();
		entityModel.setDateTimeEpochInSeconds (localDateTime.toEpochSecond(ZoneOffset.UTC));
		entityModel.setDateTimeStringified(localDateTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL, FormatStyle.MEDIUM)));
		long id = entityModelService.saveOrUpdate(entityModel);
		if(id<=0) {
			throw new RuntimeException("EntityModel saving failed !");
		}
		entityModel = entityModelService.findById(id);
		return entityModel;
	}
//	@RequestMapping(value="/entityModels/{id}", method=RequestMethod.DELETE)
	@DeleteMapping("/entityModels/{id}")
	public EntityModel deleteEntityModel(@PathVariable int id) throws JsonParseException, JsonMappingException, IOException {
		EntityModel entityModel = entityModelService.findById(id);
		boolean deleted = entityModelService.delete(id);
		if(!deleted) {
			throw new RuntimeException("Deletion failed! Maybe EntityModel not found for id = "+id+"!");
		}
		return entityModel;
	}
	@PutMapping("/entityModels")
	public EntityModel putEntityModels(@RequestBody EntityModel entityModel) throws JsonParseException, JsonMappingException, IOException {
		entityModelService.saveOrUpdate(entityModel);
		EntityModel entityModel2 = entityModelService.findById(entityModel.getId());
		if(!compareEntityModel(entityModel2, entityModel)) {
			throw new RuntimeException("EntityModel update failed !");
		}
		return entityModel2;
	}
	private boolean compareEntityModel(EntityModel e1, EntityModel e2) {
		boolean a = false;
		boolean b = false;
		boolean c = false;
		if(e1.getId()==e2.getId()) {
			a=true;
		}
		if(e1.getDateTimeEpochInSeconds()==e2.getDateTimeEpochInSeconds()) {
			b=true;
		}
		if(e1.getEntityDescription().compareTo(e2.getEntityDescription())==0) {
			c=true;
		}
		return a&&b&&c;
	}
}

/*
This local @RestController WebService API receives requests in .json format, addressed to each end point (request mapping url), and returns the response 
POJO object as .json format as well.

A full CRUD REST service api for the business entity (entityModel) is implemented here. For each htp method type: GET, POST, DELETE and PUT.
GET requests, if no pathVariable is present at the url, leads to a full list query. If a pathVariable is present at the url, either index of the
list or the id of the entityModel instance at the DB is used at the query for a single result. For POST requests, including a request body (using PostMan), 
and with .json as the content-type of the body of the request, the @RequestBody annotation is used to map the .json unserialized body to a POJO (entityModel),
later using this object with the persistence implemented services to save this new instance at the DB, but always resetting entityModel attribute values,
except the description one: id is substituted for the auto-generated one, and the two dateTime attributes are resetted for the current dateTime. With
DELETE requests, basically a pathVariable is used to receive the id value from the request url, and then delete that id instance using the persistence
services. PUT requests, differently from POST ones, are used to update the entityModel instances at the DB, instead of inserting new ones. As the updating 
attribute values could be anyone (existing id and any dateTime), they're not resetted and are used as received. A better checking for that updating values 
could had been implemented.
All method services at this controller return the affected entityModel instance (or list), in a .json format, to the requesting user, in response to
the also .json format request. No html/xml formats are used when accessing this REST WS api. Only when accessing the request mapping urls of the rest
of the app (non REST WS controllers).
*/

/*
The ExceptionHandlers above were refactored and moved to the @ControllerAdvice class, so that they can be reused for other Controller classes instead 
of only for this one.
*/
