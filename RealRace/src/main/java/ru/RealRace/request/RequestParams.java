package ru.RealRace.request;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class RequestParams {
    /**
     * ��������� �������.
     */
    private StringBuffer query;

    public RequestParams() {
        query = new StringBuffer();
    }

    /**
     * ���������� ��������� �������.
     * @param name  ������������.
     * @param value ��������.
     * @return ������ ������.
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



