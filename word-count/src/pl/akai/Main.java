package pl.akai;

import java.util.*;

public class Main {

    private static String[] sentences = {
            "Taki mamy klimat",
            "Wszędzie dobrze ale w domu najlepiej",
            "Wyskoczył jak Filip z konopii",
            "Gdzie kucharek sześć tam nie ma co jeść",
            "Nie ma to jak w domu",
            "Konduktorze łaskawy zabierz nas do Warszawy",
            "Jeżeli nie zjesz obiadu to nie dostaniesz deseru",
            "Bez pracy nie ma kołaczy",
            "Kto sieje wiatr ten zbiera burzę",
            "Być szybkim jak wiatr",
            "Kopać pod kimś dołki",
            "Gdzie raki zimują",
            "Gdzie pieprz rośnie",
            "Swoją drogą to gdzie rośnie pieprz?",
            "Mam nadzieję, że poradzisz sobie z tym zadaniem bez problemu",
            "Nie powinno sprawić żadnego problemu, bo Google jest dozwolony"
    };

    public static void main(String[] args) {
        /* TODO Twoim zadaniem jest wypisanie na konsoli trzech najczęściej występujących słów
                w tablicy 'sentences' wraz z ilością ich wystąpień..

                Przykładowy wynik:
                1. "mam" - 12
                2. "tak" - 5
                3. "z" - 2
        */
        Map<String, Integer> result = counter();
        Iterator<Map.Entry<String, Integer>> itr = result.entrySet().iterator();
        Map.Entry<String, Integer> entry = itr.next();
        for(int i=0; i<3; i++){
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(key + " " + Integer.toString(value));
            entry = itr.next();
        }

    }
//
    public static Map<String, Integer> counter(){
        Map<String, Integer> dict = new HashMap<>();

        for (String sentence: sentences) {
            String[] words = sentence.split(" ");
            wordProcessing(words, dict);
        }

        LinkedHashMap<String, Integer> sortedDict = new LinkedHashMap<>();

        dict.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> sortedDict.put(x.getKey(), x.getValue()));

        return sortedDict;
    }

    public static void wordProcessing(String[] words, Map<String, Integer> dict){
        for (String word: words){
            String processedWord = word.replaceAll("[^a-zA-Ząęćłóżź0-9]", "");
            processedWord = processedWord.toLowerCase(Locale.ROOT);
            dict.merge(processedWord, 1, Integer::sum);
        }
    }
}