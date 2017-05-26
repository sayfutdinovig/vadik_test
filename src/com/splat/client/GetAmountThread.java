package com.splat.client;

/**
 * Класс для выполнения getAmount запросов в отдельном потоке
 */
public class GetAmountThread extends RequestThread implements Runnable 
{

    private static final String METHOD = "GET";

    @Override
    public void run() 
    {
        String address = null;
        try
        {
            for (int id = 0; id < idList; ++id)
            {
                address = serverName + ID_PARAM + id;
                System.out.println(String.format("GetAmountThread %s, result = %s", address, makeRequest(address,METHOD)));
            }
        }
        catch (Exception e)
        {
            System.out.println(String.format("GetAmountThread %s error: %s", address, e.getMessage()));
        }
    }
    
}
