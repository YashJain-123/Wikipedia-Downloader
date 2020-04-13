# Wikipedia-Downloader

This is simple mini-project to crawl the pages of Wikipedia using Java.

### Tools & Libraries Used:
1. Task Manager= In this project we have made the use of threads which would crawl pieces of information from Wikipedia. So, to manage those threads, we use Task Manager which prevents in exploding the size of the queue where the threads are waiting to be executed.

2. Gson= It is a Java library that can be used to convert Java Objects into their JSON representation. It can also be used to convert a JSON string to an equivalent Java object. To use it in the project, add the following code inside the pom.xml file.

```
        <dependency>
            <groupId>com.google.code.gson</groupId>
            <artifactId>gson</artifactId>
            <version>2.8.6</version>
        </dependency>
```

3. Jsoup= It is also a Java library that provides a very convenient API for fetching URLs and extracting and manipulating data, using the best of HTML5 DOM methods and CSS selectors. To use it in the project, add the following code inside the pom.xml file.

```
        <dependency>
            <groupId>org.jsoup</groupId>
            <artifactId>jsoup</artifactId>
            <version>1.13.1</version>
        </dependency>
```
        
