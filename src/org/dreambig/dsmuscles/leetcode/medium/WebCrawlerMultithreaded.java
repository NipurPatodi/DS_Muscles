package org.dreambig.dsmuscles.leetcode.medium;

import java.util.*;
import java.util.concurrent.*;

/***
 * Given a URL startUrl and an interface HtmlParser, implement a Multi-threaded web crawler to crawl all links that are under the same hostname as startUrl.
 *
 * Return all URLs obtained by your web crawler in any order.
 *
 * Your crawler should:
 *
 * Start from the page: startUrl
 * Call HtmlParser.getUrls(url) to get all URLs from a webpage of a given URL.
 * Do not crawl the same link twice.
 * Explore only the links that are under the same hostname as startUrl.
 *
 * As shown in the example URL above, the hostname is example.org. For simplicity's sake, you may assume all URLs use HTTP protocol without any port specified. For example, the URLs http://leetcode.com/problems and http://leetcode.com/contest are under the same hostname, while URLs http://example.org/test and http://example.com/abc are not under the same hostname.
 *
 * The HtmlParser interface is defined as such:
 *
 * interface HtmlParser {
 *   // Return a list of all urls from a webpage of given url.
 *   // This is a blocking call, that means it will do HTTP request and return when this request is finished.
 *   public List<String> getUrls(String url);
 * }
 * Note that getUrls(String url) simulates performing an HTTP request. You can treat it as a blocking function call that waits for an HTTP request to finish. It is guaranteed that getUrls(String url) will return the URLs within 15ms. Single-threaded solutions will exceed the time limit so, can your multi-threaded web crawler do better?
 *
 * Below are two examples explaining the functionality of the problem. For custom testing purposes, you'll have three variables urls, edges and startUrl. Notice that you will only have access to startUrl in your code, while urls and edges are not directly accessible to you in code.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input:
 * urls = [
 *   "http://news.yahoo.com",
 *   "http://news.yahoo.com/news",
 *   "http://news.yahoo.com/news/topics/",
 *   "http://news.google.com",
 *   "http://news.yahoo.com/us"
 * ]
 * edges = [[2,0],[2,1],[3,2],[3,1],[0,4]]
 * startUrl = "http://news.yahoo.com/news/topics/"
 * Output: [
 *   "http://news.yahoo.com",
 *   "http://news.yahoo.com/news",
 *   "http://news.yahoo.com/news/topics/",
 *   "http://news.yahoo.com/us"
 * ]
 * Example 2:
 *
 *
 *
 * Input:
 * urls = [
 *   "http://news.yahoo.com",
 *   "http://news.yahoo.com/news",
 *   "http://news.yahoo.com/news/topics/",
 *   "http://news.google.com"
 * ]
 * edges = [[0,2],[2,1],[3,2],[3,1],[3,0]]
 * startUrl = "http://news.google.com"
 * Output: ["http://news.google.com"]
 * Explanation: The startUrl links to all other pages that do not share the same hostname.
 * Link => https://leetcode.com/problems/web-crawler-multithreaded/description/
 *
 **/
public class WebCrawlerMultithreaded {
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

        // URL need to be share by queue
        BlockingQueue<String> workQueue= new LinkedBlockingQueue<>();

       // We need to keep track of visited URLs
       Set<String> visited = new HashSet<>();

       //Result as String List
       List<String> res = new ArrayList<>();

       ExecutorService executor = Executors.newFixedThreadPool(4, runnable->{
         Thread t = new Thread(runnable);
         t.setDaemon(true);
        return t;
      });

       // Need to keep track of task
       Queue<Future> tasksQ= new LinkedList<>();
       workQueue.offer(startUrl);

       // Looping for queue
       while(true){
         String url = workQueue.poll();

         if(url!=null){
             if( !visited.contains(url) && getHostName(url).equals(hostName)){
               // New URL
               visited.add(url);
               res.add(url);

               // Passing this to new Executor Thread
               tasksQ.add( executor.submit(()->{
                 List<String> newUrls = htmlParser.getUrls(url);
                 for (String u: newUrls){
                   workQueue.offer(u);
                 }
               }));
             }

         }else{
           // waiting for tasks to complete and Queue to increase
           if(!tasksQ.isEmpty()){
             Future task = tasksQ.poll();
             try{
               // Blocking call
               task.get();
             }catch(InterruptedException | ExecutionException e){}
           }else{
             break; // nothing left to work upon
           }
         }
       }
       return res;
     }

}
