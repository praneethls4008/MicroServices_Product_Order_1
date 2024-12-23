package com.learn.microservices.order;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@AutoConfigureWireMock(port = 0)
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

	@Before
	public void setup() {
		stubFor(get(urlEqualTo("/api/inventory?skuCode=iphone_15&quantity=1"))
				.willReturn(aResponse()
						.withStatus(200)
						.withHeader("Content-Type", "application/json")
						.withBody("{\"inStock\": true}")));
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
		String requestBody2 = """
				{
					"skuCode":"iphone_15",
					"price": 16000,
					"quantity": 1
				}
				""";
		RestAssured
				.given()
				.contentType("application/json")
				.body(requestBody2)
				.post("/api/order")
				.then()
				.statusCode(201)
				.body("id", Matchers.notNullValue())
				.body("orderNumber", Matchers.notNullValue())
				.body("skuCode", Matchers.equalTo("SAM-A20-32BLU-IN-4G"))
				.body("price", Matchers.equalTo(16000))
				.body("quantity", Matchers.equalTo(1));	}
}
