package com.zfwhub.word.generator;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;

public class Test {
    
    
    public static void main(String[] args) {
        String url = "https://www.collinsdictionary.com/dictionary/english/abandon";
        Connection connect = Jsoup.connect(url);
        try {
            Response response = connect.execute();
            System.out.println(response.body());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

}
