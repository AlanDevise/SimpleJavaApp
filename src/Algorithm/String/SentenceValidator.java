package Algorithm.String;

import java.util.HashSet;
import java.util.Set;

public class SentenceValidator {
    private Set<String> corpus;

    public SentenceValidator(String[] corpusTexts) {
        corpus = new HashSet<>();
        for (String text : corpusTexts) {
            String[] words = text.split("\\s+");
            for (String word : words) {
                corpus.add(word.toLowerCase());
            }
        }
    }

    public boolean isValid(String sentence) {
        String[] words = sentence.split("\\s+");
        for (String word : words) {
            if (!corpus.contains(word.toLowerCase())) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // 示例语料库
        String[] corpusTexts = {
                "my name is john",
                "i like programming",
                "java is a great language"
        };
        SentenceValidator validator = new SentenceValidator(corpusTexts);

        String validSentence = "my name is john";
        String invalidSentence = "my amne si dskjfd";

        System.out.println("Valid sentence: " + validator.isValid(validSentence));
        System.out.println("Invalid sentence: " + validator.isValid(invalidSentence));
    }
}