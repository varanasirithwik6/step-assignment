

import java.util.*;

public class PlagiarismDetector1 {

    static int N = 5;

    public static List<String> generateNGrams(String text) {
        List<String> ngrams = new ArrayList<>();
        String[] words = text.split("\\s+");

        for (int i = 0; i <= words.length - N; i++) {
            StringBuilder gram = new StringBuilder();
            for (int j = 0; j < N; j++) {
                gram.append(words[i + j]).append(" ");
            }
            ngrams.add(gram.toString().trim());
        }

        return ngrams;
    }

    public static void main(String[] args) {

        // Database of documents
        Map<String, String> documents = new HashMap<>();

        documents.put("essay_089",
                "Artificial intelligence is transforming education and improving learning experiences for students");

        documents.put("essay_092",
                "Artificial intelligence is transforming education and improving learning experiences for students worldwide");

        // New document
        String newDoc =
                "Artificial intelligence is transforming education and improving learning experiences for students worldwide";

        List<String> newNgrams = generateNGrams(newDoc);

        System.out.println("Extracted " + newNgrams.size() + " n-grams");

        for (String docId : documents.keySet()) {

            List<String> dbNgrams = generateNGrams(documents.get(docId));

            int matchCount = 0;

            for (String gram : newNgrams) {
                if (dbNgrams.contains(gram)) {
                    matchCount++;
                }
            }

            double similarity = ((double) matchCount / newNgrams.size()) * 100;

            System.out.println("\nCompared with " + docId);
            System.out.println("Matching n-grams: " + matchCount);
            System.out.println("Similarity: " + String.format("%.2f", similarity) + "%");

            if (similarity > 60) {
                System.out.println("PLAGIARISM DETECTED");
            } else if (similarity > 10) {
                System.out.println("Suspicious");
            } else {
                System.out.println("Low similarity");
            }
        }
    }
}