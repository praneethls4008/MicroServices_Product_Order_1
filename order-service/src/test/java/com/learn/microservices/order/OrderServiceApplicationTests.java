package com.learn.microservices.order;

import com.learn.microservices.order.dto.OrderRequest;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.MySQLContainerProvider;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class OrderServiceApplicationTests {

	@ServiceConnection
	private static final MySQLContainer mySQLContainer = new MySQLContainer("mysql:8.3.0");

	@LocalServerPort
	private Integer localPort;

	@BeforeAll
	static void beforeAll(){
		mySQLContainer.start();
	}


	@BeforeEach
	void beforeEach(){
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = localPort;
	}

	@Test
	void shouldSubmitOrder() {
		String requestBody = """
				{
					"skuCode":"SAM-A20-32BLU-IN-4G",
					"price": 16000,
					"quantity": 1
				}
				""";
		RestAssured
				.given()
				.contentType("application/json")
				.body(requestBody)
				.post("/api/order")
				.then()
				.statusCode(201)
				.body("id", Matchers.notNullValue())
				.body("orderNumber", Matchers.notNullValue())
				.body("skuCode", Matchers.equalTo("SAM-A20-32BLU-IN-4G"))
				.body("price", Matchers.equalTo(16000))
				.body("quantity", Matchers.equalTo(1));	}
}
