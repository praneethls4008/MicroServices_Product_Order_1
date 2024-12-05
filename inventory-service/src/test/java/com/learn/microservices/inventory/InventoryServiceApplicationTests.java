package com.learn.microservices.inventory;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class InventoryServiceApplicationTests {

	@ServiceConnection
	private final static MySQLContainer mySQLContainer = new MySQLContainer("mysql:8.3.0");

	@LocalServerPort
	int localServerPort;

	@BeforeAll
	public static void beforeAll(){
		mySQLContainer.start();
	}

	@BeforeEach
	public void beforeEach(){
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = localServerPort;
	}

	@Test
	void shouldCheckStock() {
		RestAssured
				.given()
				.when()
				.get("/api/inventory?skuCode=iphone_15&quantity=1")
				.then()
				.statusCode(200)
				.body("isInStock", Matchers.equalTo(true));
	}

}
