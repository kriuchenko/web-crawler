package com.github.kriuchenko.webcrawler;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class TestCrawler {

    @Test
    public void testPatterns() throws IOException {
        String patternFileName = "src/test/resources/sample-0-origin.html";
        String targetElementId = "make-everything-ok-button";

        Optional<String> path = Crawler.findMatch(patternFileName, "src/test/resources/sample-0-origin.html", targetElementId);
        assertEquals(Optional.of("html > body > div > div > div[2] > div[0] > div > div[1] > a"), path);

        path = Crawler.findMatch(patternFileName, "src/test/resources/sample-1-evil-gemini.html", targetElementId);
        assertEquals(Optional.of("html > body > div > div > div[2] > div[0] > div > div[1] > a[1]"), path);

        path = Crawler.findMatch(patternFileName, "src/test/resources/sample-2-container-and-clone.html", targetElementId);
        assertEquals(Optional.of("html > body > div > div > div[2] > div[0] > div > div[1] > div > a"), path);

        path = Crawler.findMatch(patternFileName, "src/test/resources/sample-3-the-escape.html", targetElementId);
        assertEquals(Optional.of("html > body > div > div > div[2] > div[0] > div > div[2] > a"), path);

        path = Crawler.findMatch(patternFileName, "src/test/resources/sample-4-the-mash.html", targetElementId);
        assertEquals(Optional.of("html > body > div > div > div[2] > div[0] > div > div[2] > a"), path);
    }

    @Test
    public void testNotFound() throws IOException {
        String patternFileName = "src/test/resources/sample-0-origin.html";

        assertThrows(IllegalArgumentException.class, () -> Crawler.findMatch(patternFileName, patternFileName, "x"));

        Optional<String> path = Crawler.findMatch(patternFileName, "src/test/resources/sample-5-not-found.html", "make-everything-ok-button");
        assertEquals(Optional.empty(), path);
    }

}
