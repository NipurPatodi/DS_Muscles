package org.dreambig.dsmuscles.leetcode.medium;

import java.util.List;

/***
 * Given a url startUrl and an interface HtmlParser, implement a web crawler to crawl all links that are under the same hostname as startUrl.

Return all urls obtained by your web crawler in any order.

Your crawler should:

Start from the page: startUrl
Call HtmlParser.getUrls(url) to get all urls from a webpage of given url.
Do not crawl the same link twice.
Explore only the links that are under the same hostname as startUrl.


As shown in the example url above, the hostname is example.org. For simplicity sake, you may assume all urls use http protocol without any port specified. For example, the urls http://leetcode.com/problems and http://leetcode.com/contest are under the same hostname, while urls http://example.org/test and http://example.com/abc are not under the same hostname.

The HtmlParser interface is defined as such:

interface HtmlParser {
  // Return a list of all urls from a webpage of given url.
  public List<String> getUrls(String url);
}
Below are two examples explaining the functionality of the problem, for custom testing purposes you'll have three variables urls, edges and startUrl. Notice that you will only have access to startUrl in your code, while urls and edges are not directly accessible to you in code.

Note: Consider the same URL with the trailing slash "/" as a different URL. For example, "http://news.yahoo.com", and "http://news.yahoo.com/" are different urls.



Example 1:



Input:
urls = [
  "http://news.yahoo.com",
  "http://news.yahoo.com/news",
  "http://news.yahoo.com/news/topics/",
  "http://news.google.com",
  "http://news.yahoo.com/us"
]
edges = [[2,0],[2,1],[3,2],[3,1],[0,4]]
startUrl = "http://news.yahoo.com/news/topics/"
Output: [
  "http://news.yahoo.com",
  "http://news.yahoo.com/news",
  "http://news.yahoo.com/news/topics/",
  "http://news.yahoo.com/us"
]
Example 2:



Input:
urls = [
  "http://news.yahoo.com",
  "http://news.yahoo.com/news",
  "http://news.yahoo.com/news/topics/",
  "http://news.google.com"
]
edges = [[0,2],[2,1],[3,2],[3,1],[3,0]]
startUrl = "http://news.google.com"
Output: ["http://news.google.com"]
Explanation: The startUrl links to all other pages that do not share the same hostname.
https://leetcode.com/problems/web-crawler/description/
 *
 **/
public class WebCrawler {



    static interface HtmlParser {
     public List<String> getUrls(String url) ;

  }

  private String getHostName(String url){
     // Removing "https://"
     String suffix = url.substring(7);
     return suffix.split("/")[0];
   }


  public List<String> crawl(String startUrl, HtmlParser htmlParser) {

         // 1. get Host Name form URL
         String hostName=getHostName(startUrl);
         // 2. Visited Node
         Set<String> visited = new HashSet<>();
         List<String> res= new ArrayList<>();
         //3. Result Node
         Queue<String> workLoad = new LinkedList<>();
         workLoad.add(startUrl);

         while(!workLoad.isEmpty()){
           String url = workLoad.poll();
           if(getHostName(url).equals(hostName) && !visited.contains(url)){
             visited.add(url);
             res.add(url);
             List<String> nUrl = htmlParser.getUrls(url);
             for (String n: nUrl){
               workLoad.add(n);
             }
           }

         }


         return res;

     }
 }

}
