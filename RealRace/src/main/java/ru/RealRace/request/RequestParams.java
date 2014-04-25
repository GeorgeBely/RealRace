package ru.RealRace.request;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class RequestParams {
    /**
     * Параметры запроса.
     */
    private StringBuffer query;

    public RequestParams() {
        query = new StringBuffer();
    }

    /**
     * Добавление параметра запроса.
     * @param name  наименование.
     * @param value значение.
     * @return данный объект.
     */
    public synchronized RequestParams add(Object name, Object value) {
        try {
            if (!query.toString().trim().equals(""))
                query.append("&");
            query.append(URLEncoder.encode(name.toString(), "UTF-8"));
            query.append("=");
            query.append(URLEncoder.encode(value.toString(), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return this;
    }

    public String toString() {
        return query.toString();
    }

}



