package com.splat.client;

/**
 * Класс для выполнения addAmount запросов в отдельном потоке
 */
public class AddAmountThread extends RequestThread implements Runnable
{
    private static final String VALUE_PARAM = "&value=";
    private static final String METHOD = "POST";
    private Long value;
    
    /**
     * Устанавливает значение баланса
     * @param newValue значение баланса
     */
    public void setValue(Long newValue)
    {
        value = newValue;
    }

    @Override
    public void run() 
    {
        String address = null;
        try
        {
            for (int id = 0; id < idList; ++id)
            {
                address = serverName + ID_PARAM + id + VALUE_PARAM + value;
                makeRequest(address,METHOD);
                System.out.println(String.format("AddAmountThread %s: OK", address));
            }
        }
        catch (Exception e)
        {
            System.out.println(String.format("AddAmountThread %s error: %s", address, e.toString()));
        }    
    }

}
