package com.example;

import io.quarkus.test.junit.QuarkusIntegrationTest;
import org.junit.jupiter.api.Disabled;

@QuarkusIntegrationTest
@Disabled("TODO: Re-enable after configuring mock OIDC server for integration tests")
class StationResourceIT extends StationResourceTest {
    // Execute the same tests but in packaged mode.
}
