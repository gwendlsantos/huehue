package com.accenture.client.controller;

import java.util.Base64;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

@Controller
public class Login {


	@RequestMapping("/")
	public String start(){
		return "Login";
	}

	@RequestMapping("/login")
	public String login(@RequestParam String username, @RequestParam String password) {
		String url = "http://localhost:8081/securityservice/order";
		String user = username;
		String pass = password;

		String authString = user + ":" + pass; //gagawin nya prin ung sa json
		String authStr = Base64.getEncoder().encodeToString(
				authString.getBytes());

		Client restClient = new Client();
		WebResource wr = restClient.resource(url);
		ClientResponse resp = wr.type(MediaType.APPLICATION_JSON)
				.header("Authorization", "Basic " + authStr)
				.get(ClientResponse.class);

		String output = resp.getEntity(String.class);
		System.out.println(output);
		if (output.contains("Admin")) {
			return "redirect:/homeadmin";
		} else if (output.contains("Authorized")) {
			return "redirect:/homeuser";
		} else {
			return "redirect:/invalidacc";
		}
	}
}
