package com.company.SecurityApp.SecurityApplication;

import com.company.SecurityApp.SecurityApplication.entities.User;
import com.company.SecurityApp.SecurityApplication.services.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class SecurityApplicationTests {

	@Autowired
	private JwtService jwtService;

	@Test
	void contextLoads() {
		User user = new User(4L,"yash@gmail.com","1234","yash");

		String token = jwtService.generateAccessToken(user);
		System.out.println(token);

		Long id = jwtService.getUserIdFromToken(token);

		System.out.println(id);
	}
}
