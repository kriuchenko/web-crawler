package com.github.kriuchenko.webcrawler;

import org.jsoup.nodes.Element;

import java.util.List;
import java.util.stream.Collectors;

public class PathBuilder {
    public static String getPath(Element e){
        return getPath(e, "");
    }

    public static String getPath(Element e, String path){
        Element parent = e.parent();

        if(parent != null){
            List<Element> sameTypeElements = parent.children().stream().filter(child -> child.tagName() == e.tagName()).collect(Collectors.toList());
            if (sameTypeElements.size() == 1)
                path = getPath(parent,e.nodeName() + (path.isEmpty() ? "" : " > " + path));
            else
                path = getPath(parent,e.nodeName() + "[" + sameTypeElements.indexOf(e) + "]" + (path.isEmpty() ? "" : " > " + path));
        }

        return path;
    }
}
