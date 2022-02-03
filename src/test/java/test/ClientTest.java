package test;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.junit.jupiter.api.Test;
import test.api.TestApi;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.ok;

public class ClientTest {
    WireMockServer wireMockServer = new WireMockServer(WireMockConfiguration.options().port(8089));

    @Test
    public void testClient() {
        wireMockServer.start();

        wireMockServer.stubFor(get("/test").willReturn(ok().withBody("{\"foo\": \"bar\"}")));
        var testApi = new ApiClient().setBasePath("http://localhost:8089").buildClient(TestApi.class);
        System.out.println(testApi.testWithHttpInfo());
        System.out.println(testApi.testWithHttpInfo().getData());
        System.out.println(testApi.test());
        wireMockServer.stop();
    }
}
