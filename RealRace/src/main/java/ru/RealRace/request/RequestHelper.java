package ru.RealRace.request;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;


public class RequestHelper {

    /**
     * Метод читает из потока данные и преобразует в строку
     * @param in - входной поток
     * @param encoding - кодировка данных
     * @return - данные в виде строки
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
        //устанавливаем соединение
        URLConnection conn = new URL(url).openConnection();
        //мы будем писать POST данные в out stream
        conn.setDoOutput(true);

        OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), "ASCII");
        out.write(query.toString());
        out.write("\r\n");
        out.flush();
        out.close();

        //читаем то, что отдал нам сервер
        String html = readStreamToString(conn.getInputStream(), "UTF-8");

        //выводим информацию в консоль
        System.out.println("URL:" + url);
        System.out.println("Html:\n" + html);
    }

    public void getRequest(String url, QueryString query) throws IOException {
        //устанавливаем соединение
        URLConnection conn = new URL(url + "?" + query).openConnection();
        //заполним header request парамеры, можно и не заполнять
        conn.setRequestProperty("Referer", "http://google.com/http.example.html");
        conn.setRequestProperty("Cookie", "a=1");
        //можно установить и другие парамеры, такие как User-Agent

        //читаем то, что отдал нам сервер
        String html = readStreamToString(conn.getInputStream(), "UTF-8");

        //выводим информацию в консоль
        System.out.println("URL:" + url);
        System.out.println("Html:\n" + html);
    }

}
