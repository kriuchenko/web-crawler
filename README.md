# web-crawler
**Usage**

java -jar web-crawler-jar-with-dependencies.jar <input_origin_file_path> [<pattern_element_id>]

**Examples**

java -jar web-crawler-jar-with-dependencies.jar sample-0-origin.html sample-1-evil-gemini.html

Winner points: {onclick=2, class=3}

html > body > div > div > div[2] > div[0] > div > div[1] > a[1]

java -jar web-crawler-jar-with-dependencies.jar sample-0-origin.html sample-2-container-and-clone

Winner points: {rel=1}

html > body > div > div > div[2] > div[0] > div > div[1] > div > a

java -jar web-crawler-jar-with-dependencies.jar sample-0-origin.html sample-3-the-escape.html

Winner points: {onclick=2, rel=1, class=3}

html > body > div > div > div[2] > div[0] > div > div[2] > a

java -jar web-crawler-jar-with-dependencies.jar sample-0-origin.html sample-4-the-mash.html

Winner points: {rel=1, class=3}

html > body > div > div > div[2] > div[0] > div > div[2] > a

