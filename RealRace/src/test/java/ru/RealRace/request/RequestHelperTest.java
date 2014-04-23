package ru.RealRace.request;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;

import org.junit.Test;


public class RequestHelperTest {

    @Test
    public void readStreamToStringTest() throws IOException {
        String messageResponse = "this message in response";

        InputStream stream = new ByteArrayInputStream(messageResponse.getBytes());
        String readRequest = RequestHelper.readStreamToString(stream, "UTF-8");
        assertEquals(readRequest, messageResponse);
    }

    @Test
    public void requestPostTest() throws IOException {
        QueryString q = new QueryString();
        q.add("login", "admin").add("password", "pass");

        String response = RequestHelper.requestPost("http://www.cmlt.ru", q);
        assertNotNull(response);
    }

    @Test
    public void requsetGetTest() throws IOException {
        QueryString q = new QueryString();
        q.add("login", "admin").add("password", "pass");

        String response = RequestHelper.requestGet("http://www.cmlt.ru", q);
        assertNotNull(response);
    }

}
