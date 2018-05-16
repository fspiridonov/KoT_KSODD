package ru.ksodd.Helpers;

public class Configuration {

    public static String baseUrl = System.getProperty("selenide.baseUrl", "");

    public static class BD {
        public static final String db_url = "";
        public static final String db_login = "";
        public static final String db_password = "";
    }

}
