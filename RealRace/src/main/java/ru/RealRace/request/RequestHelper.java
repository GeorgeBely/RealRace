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

    public void postrequest(String url, QueryString query) throws IOException {
        //������������� ����������
        URLConnection conn = new URL(url).openConnection();
        //�� ����� ������ POST ������ � out stream
        conn.setDoOutput(true);

        OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), "ASCII");
        out.write(query.toString());
        out.write("\r\n");
        out.flush();
        out.close();

        //������ ��, ��� ����� ��� ������
        String html = readStreamToString(conn.getInputStream(), "UTF-8");

        //������� ���������� � �������
        System.out.println("URL:" + url);
        System.out.println("Html:\n" + html);
    }

    public void getRequest(String url, QueryString query) throws IOException {
        //������������� ����������
        URLConnection conn = new URL(url + "?" + query).openConnection();
        //�������� header request ��������, ����� � �� ���������
        conn.setRequestProperty("Referer", "http://google.com/http.example.html");
        conn.setRequestProperty("Cookie", "a=1");
        //����� ���������� � ������ ��������, ����� ��� User-Agent

        //������ ��, ��� ����� ��� ������
        String html = readStreamToString(conn.getInputStream(), "UTF-8");

        //������� ���������� � �������
        System.out.println("URL:" + url);
        System.out.println("Html:\n" + html);
    }

}
