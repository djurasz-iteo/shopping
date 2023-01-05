package com.iteo.shopping

import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc
import com.jayway.restassured.module.mockmvc.response.MockMvcResponse
import groovy.json.JsonBuilder
import groovy.json.JsonSlurper

class TestHelper {

    static String productRequest() {
        return """{ "name": "givenProduct", "price": 10 }""".stripIndent()
    }

    MockMvcResponse post(String uri, String jsonBody) {
        return RestAssuredMockMvc.with().contentType("application/json").body(jsonBody).post(uri).thenReturn()
    }
}
