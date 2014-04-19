package ru.RealRace.request;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class RequestHelperTest {
    private RequestHelper requestHelper;

    @Before
    public void lookup() {
        requestHelper = new RequestHelper();
    }

    @Test
    public void readStreamToStringTest() throws IOException {
        String messageResponse = "this message in response";

        InputStream stream = new ByteArrayInputStream(messageResponse.getBytes());
        String readRequest = requestHelper.readStreamToString(stream, "UTF-8");
        assertEquals(readRequest, messageResponse);
    }

    @Test
    public void postRequest() throws IOException {
        QueryString q = new QueryString();
        q.add("login", "admin").add("password", "pass");

        RequestHelper e = new RequestHelper();
        e.postrequest("http://192.168.1.160:8008/ads", q);
    }

    @Test
    public void getRequest() throws IOException {
        QueryString q = new QueryString();
        q.add("login", "admin").add("password", "pass");

        RequestHelper e = new RequestHelper();
        e.getRequest("http://192.168.1.160:8008/ads", q);
    }

}
