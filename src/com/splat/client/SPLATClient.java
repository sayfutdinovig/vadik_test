package com.splat.client;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Клиент, выполняющий getAmount и addAmount запросы из разных потоков
 */
public class SPLATClient {

    public static void main(String[] args) throws IOException {

        // Задаем настройки в файле client.properties
        Properties props = new Properties();
        props.load(new FileInputStream("data/client.properties"));
        int rCount = Integer.parseInt(props.getProperty("rCount"));
        int wCount = Integer.parseInt(props.getProperty("wCount"));
        int idList = Integer.parseInt(props.getProperty("idList"));
        String serverName = props.getProperty("serverName");

        //запросы на чтение
        for (int i = 0; i < rCount; ++i) {
            GetAmountThread getAmountThread = new GetAmountThread();
            getAmountThread.setIdList(idList);
            getAmountThread.setServerName(serverName);
            Thread getThread = new Thread(getAmountThread);
            getThread.start();
        }
        //запросы на изменени
        for (int i = 0; i < wCount; ++i) {
            AddAmountThread addAmountThread = new AddAmountThread();
            addAmountThread.setIdList(idList);
            addAmountThread.setValue((long) (i * idList));
            addAmountThread.setServerName(serverName);
            Thread addThread = new Thread(addAmountThread);
            addThread.start();
        }
    }
}
