package com.utils;

import java.net.Inet4Address;
import java.net.UnknownHostException;

/**
 * Created with IntelliJ IDEA.
 * User: Mitch Va Daele
 * Date: 14-3-13
 * Time: 16:14
 * To change this template use File | Settings | File Templates.
 */
public class ServerUtil {

    public static String getServerAddres(Boolean local){
        String address = "";
            if(local){
                address = "http://192.168.1.137:8080";
            }else{
                address = "http://tomcat.vincentverbist.be:8080";
            }
        return address;
    }

}
