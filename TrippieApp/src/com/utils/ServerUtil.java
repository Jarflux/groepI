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
                address = "192.168.1.137";
            }else{
                address = "tomcat.vincentverbist.be";
            }
        return address;
    }

}
