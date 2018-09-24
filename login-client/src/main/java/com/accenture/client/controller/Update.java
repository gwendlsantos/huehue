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
public class Update {

	@RequestMapping("updateUser")
	public String redirectToUpdate() {
		return "UpdateUser";
	}

	@RequestMapping("/updateRequest")
	public String updateUser(@RequestParam String selectUser,
			@RequestParam String firstName,
			@RequestParam String lastName,
			@RequestParam String username,
			@RequestParam String password) throws JSONException {
		JSONObject json = new JSONObject();
		json.put("firstName", firstName);
		json.put("lastName", lastName);
		json.put("username", username);
		json.put("password", password);

		Client client = new Client();

		WebResource wr = client
				.resource("http://localhost:8081/userservice/userUpdate");
		ClientResponse response = wr.type(MediaType.APPLICATION_JSON).post(
				ClientResponse.class, json.toString());
		@SuppressWarnings("unused")
		String restStr = response.getEntity(String.class);

		return "redirect:/homeadmin";
	}

}
