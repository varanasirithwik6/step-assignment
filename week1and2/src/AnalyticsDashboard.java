import java.util.*;

public class AnalyticsDashboard {

    static HashMap<String, Integer> pageViews = new HashMap<>();
    static HashMap<String, HashSet<String>> uniqueUsers = new HashMap<>();
    static HashMap<String, Integer> sources = new HashMap<>();

    static void processEvent(String url, String user, String source) {

        pageViews.put(url, pageViews.getOrDefault(url, 0) + 1);

        uniqueUsers.putIfAbsent(url, new HashSet<>());
        uniqueUsers.get(url).add(user);

        sources.put(source, sources.getOrDefault(source, 0) + 1);
    }

    static void getDashboard() {

        System.out.println("Top Pages:");

        for(String page : pageViews.keySet()) {
            System.out.println(page + " - " + pageViews.get(page) +
                    " views (" + uniqueUsers.get(page).size() + " unique)");
        }

        System.out.println("Traffic Sources:");

        for(String s : sources.keySet()) {
            System.out.println(s + " : " + sources.get(s));
        }
    }

    public static void main(String[] args) {

        processEvent("/article/news", "user1", "google");
        processEvent("/article/news", "user2", "facebook");
        processEvent("/sports/match", "user3", "direct");

        getDashboard();
    }
}
