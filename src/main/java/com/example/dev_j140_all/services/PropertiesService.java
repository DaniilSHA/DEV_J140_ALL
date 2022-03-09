package com.example.dev_j140_all.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesService {
    public final static String URL = "url";
    public final static String LOGIN = "login";
    public final static String PASSWORD = "password";
    private static PropertiesService propertiesService;

    private PropertiesService() {
    }

    public static PropertiesService getPropertiesService(){
        if (propertiesService == null) propertiesService = new PropertiesService();
        return propertiesService;
    }

    public String[] getConnectionData() {
        String[] data = null;
        File file = new File("src/main/resources/datasource.prop");
        try {
            if (!file.exists()) file.createNewFile();
            else {
                Properties properties = new Properties();
                properties.load(new FileInputStream(file));
                data = new String[]{
                        properties.getProperty(PropertiesService.URL),
                        properties.getProperty(PropertiesService.LOGIN),
                        properties.getProperty(PropertiesService.PASSWORD),
                };
            }
        } catch (IOException e) {
            System.out.println("Не удалось получить данные с файла для соединения c БД");
        }
        return data;
    }
}