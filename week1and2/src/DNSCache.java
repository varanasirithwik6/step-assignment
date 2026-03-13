import java.util.*;

class DNSEntry{
    String ip;
    long expiry;

    DNSEntry(String ip,long ttl){
        this.ip=ip;
        this.expiry=System.currentTimeMillis()+ttl;
    }
}

public class DNSCache {

    static HashMap<String,DNSEntry> cache = new HashMap<>();

    static String resolve(String domain){

        if(cache.containsKey(domain)){

            DNSEntry e = cache.get(domain);

            if(System.currentTimeMillis()<e.expiry){
                return "Cache HIT "+e.ip;
            }
        }

        String newIP = "172.217.14."+new Random().nextInt(200);

        cache.put(domain,new DNSEntry(newIP,5000));

        return "Cache MISS "+newIP;
    }

    public static void main(String[] args){

        System.out.println(resolve("google.com"));
        System.out.println(resolve("google.com"));
    }
}