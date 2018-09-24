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
public class InsertUser {

	@RequestMapping("/insertUser")
	public String redirectToInput() {
		return "InsertUser"; // html page for insert
	}

	@RequestMapping("/insert")
	public String registerUser(@RequestParam String firstname,
			@RequestParam String lastname, @RequestParam String password,
			@RequestParam String role, @RequestParam String username) throws JSONException {

		boolean usertype;
		if(role.equalsIgnoreCase("admin")){
			usertype = true;
		}else{
			usertype = false;
		}

		JSONObject json = new JSONObject();
		json.put("firstName", firstname);
		json.put("lastName", lastname);
		json.put("password", password);
		json.put("role", usertype);
		json.put("username", username);

		Client client = new Client();

		WebResource wr = client.resource("http://localhost:8081/userservice/register");
		ClientResponse response = wr.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, json.toString());
		String restString = response.getEntity(String.class);

		return "redirect:/homeadmin";
	}
}
