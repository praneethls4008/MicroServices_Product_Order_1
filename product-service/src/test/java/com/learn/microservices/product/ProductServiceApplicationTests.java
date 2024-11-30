package com.learn.microservices.product;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Testcontainers;


@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
class ProductServiceApplicationTests {

	// MongoDB Testcontainer for integration testing
	private static final MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0.5");

	@LocalServerPort
	private Integer localServerPort;

	// Initialize RestAssured configuration for each test
	@BeforeEach
	void setup() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = localServerPort;
	}

	// Start the MongoDB container before any tests run
	@BeforeAll
	static void startContainer() {
		mongoDBContainer.start();
		System.setProperty("spring.data.mongodb.uri", mongoDBContainer.getReplicaSetUrl());
	}

	@Test
	void createProduct() {
		String requestBody = """
				{
					"name" : "Samsung A20",
					"description": "Samsung A20 mobile 6gb ram",
					"price": 15000
				}
				""";
		RestAssured.given()
				.contentType("application/json")
				.body(requestBody)
				.when()
				.post("/api/product")
				.then()
				.statusCode(201)
				.body("id", Matchers.notNullValue())
				.body("name", Matchers.equalTo("Samsung A20"))
				.body("description", Matchers.equalTo("Samsung A20 mobile 6gb ram"))
				.body("price", Matchers.equalTo(15000));
	}

}
