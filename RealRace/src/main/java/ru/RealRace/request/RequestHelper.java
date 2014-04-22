package ru.RealRace.request;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;


public class RequestHelper {

    /**
     * ����� ������ �� ������ ������ � ����������� � ������
     * @param in - ������� �����
     * @param encoding - ��������� ������
     * @return - ������ � ���� ������
     */
    String readStreamToString(java.io.InputStream in, String encoding) throws IOException {
        StringBuilder b = new StringBuilder();
        InputStreamReader r = new InputStreamReader(in, encoding);
        int c;
        while ((c = r.read()) != -1) {
            b.append((char)c);
        }
        return b.toString();
    }

    /**
     * ������ POST ������
     * @param url ����� �������.
     * @param query ��������� �������.
     * @return ����� �������.
     */
    public String postRequest(String url, QueryString query) throws IOException {
        //������������� ����������
        URLConnection conn = new URL(url).openConnection();
        //�� ����� ������ POST ������ � out stream
        conn.setDoOutput(true);

        OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), "ASCII");
        out.write(query.toString());
        out.write("\r\n");
        out.flush();
        out.close();

        //���������� ��, ��� ����� ��� ������
        return readStreamToString(conn.getInputStream(), "UTF-8");

    }

    /**
     * ������ GET ������
     * @param url ����� �������.
     * @param query ��������� �������.
     * @return ����� �������.
     */
    public String getRequest(String url, QueryString query) throws IOException {
        //������������� ����������
        URLConnection conn = new URL(url + "?" + query).openConnection();
        //�������� header request ��������, ����� � �� ���������
        conn.setRequestProperty("Referer", "http://google.com/http.example.html");
        conn.setRequestProperty("Cookie", "a=1");
        //����� ���������� � ������ ��������, ����� ��� User-Agent

        //���������� ��, ��� ����� ��� ������
        return readStreamToString(conn.getInputStream(), "UTF-8");
    }

}
