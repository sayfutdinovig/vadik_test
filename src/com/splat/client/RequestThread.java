package com.splat.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Класс для отправки запросов
 */
public class RequestThread
{
    protected static final String ID_PARAM = "?id=";

    
    protected String serverName;
    protected Integer idList;
    
    /**
     * Задает правую границу диапазона ключей, используемую для тестирования
     * @param newIdList правая граница диапазона ключей, используемых для тестирования
     */
    public void setIdList(Integer newIdList)
    {
        idList = newIdList;
    }
    
    /**
     * Задает имя сервера, где находится сервис
     * @param newServerName имя сервера, где находится сервис
     */
    public void setServerName(String newServerName)
    {
        serverName = newServerName;
    }

    
    /**
     * Осуществляет запрос по указанному адресу
     * @param urlAddress адрес запроса
     * @param method метод запроса
     * @return ответ сервера
     * @throws Exception 
     */
    public String makeRequest(String urlAddress, String method) throws Exception
    {
        URL url = new URL(urlAddress);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod(method);
        
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while((null != (inputLine = in.readLine())))
        {
            response.append(inputLine);
        }
        in.close();
        
        return response.toString();
    }
}
