package com.github.kriuchenko.webcrawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class Crawler {
    private static String CHARSET_NAME = "utf8";

    public static Optional<String> findMatch(String patternFileName, String fileName, String targetElementId) throws IOException {
        File patternFile = new File(patternFileName);
        File file = new File(fileName);
        Document patternDoc = Jsoup.parse(patternFile, CHARSET_NAME, patternFile.getAbsolutePath());
        Document doc = Jsoup.parse(file, CHARSET_NAME, file.getAbsolutePath());

        Element patternElement = Optional.ofNullable(patternDoc.getElementById(targetElementId)).orElseThrow(() -> new IllegalArgumentException("No element found with id=" + targetElementId));
        Attributes patternAttributes = patternElement.attributes();

        CrawlRateConsumer crawlRateConsumer = new CrawlRateConsumer(patternAttributes);

        doc.getElementsByTag(patternElement.tagName()).forEach(e -> crawlRateConsumer.consume(e));

        System.out.println("Winner points: " + crawlRateConsumer.bestPoints.toString());

        return crawlRateConsumer.bestCandidate.map(element -> PathBuilder.getPath(element));
    }



}
