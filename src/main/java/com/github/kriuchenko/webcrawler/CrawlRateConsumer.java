package com.github.kriuchenko.webcrawler;

import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CrawlRateConsumer {
    private static String[] attributePriority = new String[]{"rel", "onclick", "class"};

    Attributes patternAttributes;
    Optional<Element> bestCandidate = Optional.empty();
    int bestRate = 0;
    Map<String, Integer> bestPoints = new HashMap<String, Integer>();

    public CrawlRateConsumer(Attributes patternAttributes) {
        this.patternAttributes = patternAttributes;
    }

    public void consume(Element e){
        Map<String, Integer> weights = weight(e);
        int rate = rate(weights);
        if(rate > bestRate){
            bestRate = rate;
            bestCandidate = Optional.of(e);
            bestPoints = weights;
        }
    }

    private Map<String, Integer> weight(Element e) {
        Map<String, Integer> points = new HashMap<String, Integer>();

        for(int weight = 0; weight < attributePriority.length; weight++) {
            String attributeName = attributePriority[weight];
            String patternAttribute = patternAttributes.get(attributeName);
            String attribute = e.attributes().get(attributeName);

            if (!patternAttribute.isEmpty() && patternAttribute.equalsIgnoreCase(attribute)) {
                points.put(attributeName, weight + 1);

            }
        }

        return points;
    }

    private int rate(Map<String, Integer> weights){
        return weights.values().stream().mapToInt(Integer::intValue).sum();
    }
}
