package com.example.hotel_langit_7;

public class ApiRequest {
    private static String baseUrl = "http://192.168.43.133:9802/";

    public static String login = baseUrl + "api/login";
    public static String changePasword = baseUrl + "api/password";
    public static String check = baseUrl + "api/checkBooking";

}
