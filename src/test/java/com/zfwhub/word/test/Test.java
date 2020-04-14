package com.zfwhub.word.test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Test {
    
    public static List<String> getWordList() {
        List<String> wordList = new ArrayList<>();
        try {
            Document doc = Jsoup.connect("https://cet4.koolearn.com/20131106/783888.html").get();
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
        System.out.println(wordList);
        return wordList;
    }

    public static void getWordDetail(String wordStr) {
        //Word word = new Word(wordStr);
        try {
            Document doc = Jsoup.connect("http://dict.youdao.com/w/eng/"+wordStr+"/").get();
            Elements elements = doc.select("#collinsResult ul li");
            for (Element element : elements) {
                String wordHtml = element.html();
                if (wordHtml.indexOf("see also") < 0) {
                    String collinsMajorTrans = element.select(".collinsMajorTrans").html();
                    Pattern pattern = Pattern.compile("<p>.*<span class=\"additional\" title=\".*\">([A-Z,-]*?)</span>([^\u4e00-\u9fa5]*)(.*)</p>");
                    Matcher matcher = pattern.matcher(collinsMajorTrans);
                    if (matcher.find()) {
                        System.out.println(matcher.group(1).trim());
                        System.out.println(matcher.group(2).trim());
                    }
                    Elements exampleElements = element.select(".examples");
                    for (Element exampleElement : exampleElements) {
                        String exampleHtml = exampleElement.html();
                        Pattern pattern1 = Pattern.compile("<p>([^\u4e00-\u9fa5]*)</p>");
                        Matcher matcher1 = pattern1.matcher(exampleHtml);
                        if (matcher1.find()) {
                            System.out.println(matcher1.group(1).trim());
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        getWordDetail("belt");
//        String reg="\\d{3,6}";        
//        String test="61762828 176 2991 871";
//        System.out.println("文本："+test);
//        System.out.println("贪婪模式："+reg);
//        Pattern p1 =Pattern.compile(reg);
//        Matcher m1 = p1.matcher(test);
//         while(m1.find()){
//           System.out.println("匹配结果："+m1.group(0));
//        }
    }

    
    
}
