package pl.akai;

import com.google.gson.Gson;
import pl.akai.subclasses.Author;
import pl.akai.subclasses.Book;

import javax.net.ssl.HttpsURLConnection;
import java.net.URL;
import java.util.*;

public class Main {
    /*
        Twoim zadaniem jest napisanie prostego programu do pobierania i transformowania danych
        udostępnianych przez API. Dokumentacje API możesz znależć pod poniższym linkiem:
        https://akai-recruitment.herokuapp.com/documentation.html

        Całe API zawiera jeden endpoint: https://akai-recruitment.herokuapp.com/book
        Endpoint ten zwraca liste książek zawierajacch informację takie jak:
        - id
        - tytuł
        - autor
        - ocena

        Twoim zadaniem jest:
        1. Stworzenie odpowiedniej klasy do przechowywania informacji o książce
        2. Sparsowanie danych udostępnianych przez endpoint. Aby ułatwić to zadanie,
           do projektu są dołaczone 3 najpopularniejsze biblioteki do parsowania JSONów
           do obiektów Javy - Gson, Org.Json, Jackson. Możesz wykorzystać dowolną z nich
        3. Po sparsowaniu JSONu do obiektów Javy, uzupełnij program o funkcję wypisującą 3 autorów z
           najwyższą średnią ocen. Na przykład, gdy osoba X jest autorem książki A z oceną 9 i B z oceną 8,
           to powinna zostać wyświetlona informacja: X - 8.5

       Projekt został utworzony przy użyciu najnowszej Javy 17,
       jednakże nic nie stoi na przeszkodzie użycia innej wersji jeśli chcesz
     */

    public static void main(String[] args) {
        try{
            URL url = new URL("https://akai-recruitment.herokuapp.com/book");
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            int responseCode = connection.getResponseCode();
            if (responseCode != 200){
                throw new RuntimeException("Http Response Code: " + responseCode);
            } else {
                StringBuilder booksString = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()){
                    booksString.append(scanner.nextLine());
                }
                scanner.close();

                Gson gson = new Gson();
                Book[] booksArray = gson.fromJson(String.valueOf(booksString), Book[].class);

                top3(booksArray);
            }
        } catch(Exception e){
            e.printStackTrace();
        }

    }

    public static void top3(Book[] books){
        HashMap<String, Author> authors = new HashMap<>();
        for (Book book : books){
            if (authors.containsKey(book.getAuthor())){
                authors.get(book.getAuthor()).update(book.getRating());
            }
            else{
                Author temporaryAuthor = new Author(book.getAuthor());
                temporaryAuthor.update(book.getRating());
                authors.put(temporaryAuthor.getName(), temporaryAuthor);
            }
        }
        List<Author> authorsList = new ArrayList<>(authors.values());
        authorsList.sort(Collections.reverseOrder());
        for(int i=0; i<3; i++){
            System.out.println(authorsList.get(i).getName() + " - " + authorsList.get(i).getRatingSum()/authorsList.get(i).getHowManyBooks());
        }
    }
}
