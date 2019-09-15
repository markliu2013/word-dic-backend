package com.zfwhub.word.generator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WordList {

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

    public static void main(String[] args) throws Exception {
        saveCet4();
    }

}
