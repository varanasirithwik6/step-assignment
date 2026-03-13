import java.util.*;
public class SocialMedia {
    // HashMap to store username and userId
        HashMap<String, Integer> usernameMap = new HashMap<>();

        // HashMap to store username attempt counts
        HashMap<String, Integer> attemptMap = new HashMap<>();


        // Check if username is available
        public boolean checkAvailability(String username) {

            // Increase attempt count
            int count = attemptMap.getOrDefault(username, 0);
            attemptMap.put(username, count + 1);

            // Check if username exists
            if(usernameMap.containsKey(username)) {
                return false;
            }

            return true;
        }


        // Register new user
        public void register(String username, int userId) {

            if(usernameMap.containsKey(username)) {
                System.out.println("Username already taken");
                return;
            }

            usernameMap.put(username, userId);
            System.out.println("User registered successfully");
        }


        // Suggest alternative usernames
        public List<String> suggestAlternatives(String username) {

            List<String> suggestions = new ArrayList<>();

            for(int i = 1; i <= 5; i++) {

                String newName = username + i;

                if(!usernameMap.containsKey(newName)) {
                    suggestions.add(newName);
                }
            }

            // Replace underscore with dot
            if(username.contains("_")) {

                String alt = username.replace("_", ".");

                if(!usernameMap.containsKey(alt)) {
                    suggestions.add(alt);
                }
            }

            return suggestions;
        }


        // Find most attempted username
        public String getMostAttempted() {

            String maxUser = "";
            int maxCount = 0;

            for(String user : attemptMap.keySet()) {

                int count = attemptMap.get(user);

                if(count > maxCount) {
                    maxCount = count;
                    maxUser = user;
                }
            }

            return maxUser + " (" + maxCount + " attempts)";
        }


        public static void main(String[] args) {

            SocialMedia system = new SocialMedia();

            system.register("john_doe", 101);
            system.register("alex99", 102);

            System.out.println(system.checkAvailability("john_doe"));
            System.out.println(system.checkAvailability("jane_smith"));

            System.out.println(system.suggestAlternatives("john_doe"));

            System.out.println(system.getMostAttempted());
        }
    }

