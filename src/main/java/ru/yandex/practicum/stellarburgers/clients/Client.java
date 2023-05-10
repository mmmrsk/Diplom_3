package ru.yandex.practicum.stellarburgers.clients;

import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

public class Client {

    protected final String URL = "https://stellarburgers.nomoreparties.site/api";
    protected final RequestSpecification reqSpec = given()
            .baseUri (URL)
            .header("Content-type", "application/json");
}