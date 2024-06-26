package ru.gb.controllers.testOptionsClasses;

import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = TestConfig.class)
@ActiveProfiles("test")
@AutoConfigureWebTestClient
public abstract class JUnitSpringBootBase {

}
