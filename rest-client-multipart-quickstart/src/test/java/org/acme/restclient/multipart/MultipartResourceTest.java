package org.acme.restclient.multipart;

import io.quarkus.test.junit.DisabledOnNativeImage;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

@QuarkusTest
@DisabledOnNativeImage("Because native port test does not use 8081 for tests")
public class MultipartResourceTest {

    @Test
    public void testMultipartDataIsSent() {
        given()
                .when().post("/client/multipart")
                .then()
                .statusCode(200)
                .body( containsString("Content-Disposition: form-data; name=\"file\""),
                        containsString("HELLO WORLD"),
                        containsString("Content-Disposition: form-data; name=\"fileName\""),
                        containsString("greeting.txt"));
    }

}
