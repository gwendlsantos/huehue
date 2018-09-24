package com.accenture.client.controller;

import javax.ws.rs.core.MediaType;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

@Controller
public class DeleteUser {

	@RequestMapping("/deleteUser")
	public String redirectToDelete(){
		return "DeleteUser";
	}


	@RequestMapping("/deleteRequest")
	public String deleteUser(@RequestParam String selectUser) throws JSONException{

		JSONObject json = new JSONObject();
		String temp1 = selectUser.replaceAll(" ", "");
		String[] temp2 = temp1.split("-");
		json.put("userid", temp2[0]);

		Client client = new Client();

		WebResource wr = client.resource("http://localhost:8081/userservice/deleteUser");
		ClientResponse response = wr.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, json.toString());
		@SuppressWarnings("unused")
		String restStr = response.getEntity(String.class);

		return "redirect:/homeadmin";
	}
}
