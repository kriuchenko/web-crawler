package com.github.kriuchenko.webcrawler;

import java.io.IOException;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {
        if(args.length < 2) exitWithError("At a minimum input_origin_file_path and input_other_sample_file_path params are required to run!");

	    String originFilePath = args[0];
	    String otherFilePath = args[1];
	    String targetElementId = args.length > 2 ? args[2] : "make-everything-ok-button";
	    String debug = args.length > 3 ? args[3] : "INFO";

        try {
            Optional<String> path = Crawler.findMatch(originFilePath, otherFilePath, targetElementId);
            System.out.println(path.orElseThrow(() -> new IllegalArgumentException("No match found!")));
        } catch (IllegalArgumentException e) {
            exitWithError(e.getMessage());
        } catch (IOException e) {
            exitWithError(e.getMessage());
        }
    }

    private static void exitWithError(String error){
        System.out.println(error);
        System.exit(-1);
    }
}
