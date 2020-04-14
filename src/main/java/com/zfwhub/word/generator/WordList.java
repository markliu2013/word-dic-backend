package com.zfwhub.word.generator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.alibaba.fastjson.JSON;
import com.zfwhub.word.dto.CategorySentenceWrapper;
import com.zfwhub.word.dto.TagAddDto;
import com.zfwhub.word.dto.WordAddDto;
import com.zfwhub.word.dto.WordDetailAddDto;

public class WordList {
    
    static {
        TrustManager[] trustAllCertificates = new TrustManager[] {
            new X509TrustManager() {
                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                
                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    
                }
                
                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    
                }
            }
        };

        HostnameVerifier trustAllHostnames = new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true; // Just allow them all.
            }
        };

        try {
            System.setProperty("jsse.enableSNIExtension", "false");
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCertificates, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(trustAllHostnames);
        }
        catch (GeneralSecurityException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static List<String> getWordListCET4(String url) {
        List<String> wordList = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(url).get();
            Elements elements = doc.select(".xqy_core_text p");
            for (Element element : elements) {
                String wordHtml = element.html();
                Pattern pattern = Pattern.compile("([a-zA-Z]+)\\/ (.+?)\\/");
                Matcher matcher = pattern.matcher(wordHtml);
                while (matcher.find()) {
                    wordList.add(matcher.group(1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wordList;
    }

    public static void saveCet4() throws Exception {
        String baseUrl = "https://cet4.koolearn.com/20131106/783888.html";
        List<String> urlList = new ArrayList<>();
        urlList.add(baseUrl);
        for (int i = 0; i < 14; i++) {
            String url = "https://cet4.koolearn.com/20131106/783888_" + (i + 2) + ".html";
            urlList.add(url);
        }
        for (String url : urlList) {
            List<String> wordList = getWordListCET4(url);
            StringBuilder sb = new StringBuilder();
            for (String word : wordList) {
                sb.append(word);
                sb.append("\n");
            }
            File file = new File("cet4.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWritter = new FileWriter(file.getName(), true);
            BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
            System.out.println(sb.toString());
            bufferWritter.write(sb.toString());
            bufferWritter.close();
        }
    }

    
    public static void addWord(String wordStr) {
        WordAddDto wordAddDto = new WordAddDto(wordStr);
        try {
            Document doc = Jsoup.connect("http://dict.youdao.com/w/"+wordStr+"/").get();
            wordAddDto.setPhoneticSymbol(doc.select(".wt-container .phonetic").html());
            String starStr = doc.select(".wt-container .star").attr("class");
            wordAddDto.setFrequency(starStr.charAt(starStr.length()-1)-'0');
            String tagStr = doc.select(".wt-container .rank").html();
            String[] tagArr = tagStr.split(" ");
            List<TagAddDto> tagAddDtos = new ArrayList<>();
            for (int i = 0; i < tagArr.length; i++) {
                tagAddDtos.add(new TagAddDto(tagArr[i]));
            }
            wordAddDto.setTagAddDtos(tagAddDtos);
            List<CategorySentenceWrapper> categorySentenceWrappers = new ArrayList<>();
            Elements elements = doc.select("#collinsResult ul li");
            for (Element element : elements) {
                CategorySentenceWrapper categorySentenceWrapper = new CategorySentenceWrapper();
                String wordHtml = element.html();
                if (wordHtml.indexOf("see also") < 0) {
                    String collinsMajorTrans = element.select(".collinsMajorTrans").html();
                    Pattern pattern = Pattern.compile("<p>.*<span class=\"additional\" title=\".*\">([A-Z,-]*?)</span>([^\u4e00-\u9fa5]*)(.*)</p>");
                    Matcher matcher = pattern.matcher(collinsMajorTrans);
                    if (matcher.find()) {
                        String categoryStr = matcher.group(1).trim();
                        String definition = matcher.group(2).trim();
                        categorySentenceWrapper.setCategoryName(categoryStr);
                        categorySentenceWrapper.setDefinition(definition);
                    }
                    List<String> sentenceContentList = new ArrayList<>();
                    Elements exampleElements = element.select(".examples");
                    for (Element exampleElement : exampleElements) {
                        String exampleHtml = exampleElement.html();
                        Pattern pattern1 = Pattern.compile("<p>([^\u4e00-\u9fa5]*)</p>");
                        Matcher matcher1 = pattern1.matcher(exampleHtml);
                        if (matcher1.find()) {
                            String sentenceStr = matcher1.group(1).trim();
                            sentenceContentList.add(sentenceStr);
                        }
                    }
                    categorySentenceWrapper.setSentenceContentList(sentenceContentList);
                }
                categorySentenceWrappers.add(categorySentenceWrapper);
            }
            WordDetailAddDto wordDetailAddDto = new WordDetailAddDto();
            wordDetailAddDto.setWordAddDto(wordAddDto);
            wordDetailAddDto.setCategorySentenceWrappers(categorySentenceWrappers);
            insertWord(wordDetailAddDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void addWord2(String wordStr) {
        WordAddDto wordAddDto = new WordAddDto(wordStr);
        try {
            Document doc = Jsoup.connect("http://www.iciba.com/"+wordStr).get();
            System.out.println(doc.select(".base-speak").html());
//            wordAddDto.setPhoneticSymbol(doc.select(".dictentry .pron").html());
//            String starStr = doc.select(".wt-container .star").attr("class");
//            wordAddDto.setFrequency(starStr.charAt(starStr.length()-1)-'0');
//            String tagStr = doc.select(".wt-container .rank").html();
//            String[] tagArr = tagStr.split(" ");
//            List<TagAddDto> tagAddDtos = new ArrayList<>();
//            for (int i = 0; i < tagArr.length; i++) {
//                tagAddDtos.add(new TagAddDto(tagArr[i]));
//            }
//            wordAddDto.setTagAddDtos(tagAddDtos);
//            List<CategorySentenceWrapper> categorySentenceWrappers = new ArrayList<>();
//            Elements elements = doc.select("#collinsResult ul li");
//            for (Element element : elements) {
//                CategorySentenceWrapper categorySentenceWrapper = new CategorySentenceWrapper();
//                String wordHtml = element.html();
//                if (wordHtml.indexOf("see also") < 0) {
//                    String collinsMajorTrans = element.select(".collinsMajorTrans").html();
//                    Pattern pattern = Pattern.compile("<p>.*<span class=\"additional\" title=\".*\">([A-Z,-]*?)</span>([^\u4e00-\u9fa5]*)(.*)</p>");
//                    Matcher matcher = pattern.matcher(collinsMajorTrans);
//                    if (matcher.find()) {
//                        String categoryStr = matcher.group(1).trim();
//                        String definition = matcher.group(2).trim();
//                        categorySentenceWrapper.setCategoryName(categoryStr);
//                        categorySentenceWrapper.setDefinition(definition);
//                    }
//                    List<String> sentenceContentList = new ArrayList<>();
//                    Elements exampleElements = element.select(".examples");
//                    for (Element exampleElement : exampleElements) {
//                        String exampleHtml = exampleElement.html();
//                        Pattern pattern1 = Pattern.compile("<p>([^\u4e00-\u9fa5]*)</p>");
//                        Matcher matcher1 = pattern1.matcher(exampleHtml);
//                        if (matcher1.find()) {
//                            String sentenceStr = matcher1.group(1).trim();
//                            sentenceContentList.add(sentenceStr);
//                        }
//                    }
//                    categorySentenceWrapper.setSentenceContentList(sentenceContentList);
//                }
//                categorySentenceWrappers.add(categorySentenceWrapper);
//            }
//            WordDetailAddDto wordDetailAddDto = new WordDetailAddDto();
//            wordDetailAddDto.setWordAddDto(wordAddDto);
//            wordDetailAddDto.setCategorySentenceWrappers(categorySentenceWrappers);
//            insertWord(wordDetailAddDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void insertWord(WordDetailAddDto wordDetailAddDto) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("http://127.0.0.1:8080/wordDetail");
        httpPost.setHeader("Content-Type", "application/json;charset=utf8");
        String json = JSON.toJSON(wordDetailAddDto).toString();
        StringEntity entity = new StringEntity(json, "UTF-8");
        httpPost.setEntity(entity);
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();
            System.out.println("响应状态为:" + response.getStatusLine());
            if (responseEntity != null) {
                System.out.println("响应内容长度为:" + responseEntity.getContentLength());
                System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args) throws Exception {
        addWord2("abandon");
    }

}
