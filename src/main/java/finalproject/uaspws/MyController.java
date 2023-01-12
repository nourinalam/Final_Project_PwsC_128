/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject.uaspws;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author user
 * Nama : Nourin Alam Adzani
 * Nim  : 20200140128 
 */
@RestController
@CrossOrigin
public class MyController {
	
	UaspwsJpaController control = new UaspwsJpaController();
	
	@PostMapping("/POST")
	public String sendData(HttpEntity<String> kiriman) throws Exception{
		Uaspws datas = new Uaspws();
		String d = kiriman.getBody();
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		datas = mapper.readValue(d, Uaspws.class);
	        control.create(datas);
		return d;
	}
	
	@PutMapping("/PUT")
	public String editData(HttpEntity<String> kiriman) throws Exception{
		Uaspws datas = new Uaspws();
		String d = kiriman.getBody();
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		datas = mapper.readValue(d, Uaspws.class);
	        control.edit(datas);
		return d;
	}
	
	@DeleteMapping("/DELETE")
	public String deleteData(HttpEntity<String> kiriman) throws Exception{
		Uaspws datas = new Uaspws();
		String d = kiriman.getBody();
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		datas = mapper.readValue(d, Uaspws.class);
	        control.destroy(datas.getId());
		return "id: "+datas.getId()+" berhasil di deleted";
	}
	
	@GetMapping("/GET")
	public List<Uaspws> getTabel(){
		List<Uaspws> list = new ArrayList<>();
		try {
			list = control.findUaspwsEntities();
		}
		catch (Exception e){}
		return list;
	}
	
}