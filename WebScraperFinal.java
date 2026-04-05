import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.util.Scanner;

public class WebScraperFinal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            // Input number of pages
            System.out.print("Enter number of pages to scrape (1-5): ");
            int pages = sc.nextInt();

            FileWriter csvWriter = new FileWriter("products.csv");

            // CSV Header
            csvWriter.append("Name,Price,Rating\n");

            int count = 0;

            for (int i = 1; i <= pages; i++) {

                String url = "http://books.toscrape.com/catalogue/page-" + i + ".html";

                // Connect to website
                Document doc = Jsoup.connect(url).get();

                // Select all products
                Elements products = doc.select("article.product_pod");

                for (Element product : products) {

                    // Extract data
                    String name = product.select("h3 a").attr("title");
                    String price = product.select(".price_color").text();
                    String rating = product.select(".star-rating").attr("class");

                    // Clean rating (convert "star-rating Three" → "Three")
                    if (rating.contains(" ")) {
                        rating = rating.split(" ")[1];
                    } else {
                        rating = "N/A";
                    }

                    // Clean commas (for CSV safety)
                    name = name.replace(",", " ");

                    // Print to console
                    System.out.println(name + " | " + price + " | " + rating);

                    // Write to CSV
                    csvWriter.append(name).append(",")
                             .append(price).append(",")
                             .append(rating).append("\n");

                    count++;
                }
            }

            // Add summary
            csvWriter.append("\nTotal Products: " + count + "\n");

            csvWriter.flush();
            csvWriter.close();

            System.out.println("\nData saved to products.csv");
            System.out.println("Total products scraped: " + count);

        } catch (Exception e) {
            System.out.println("Error occurred while scraping!");
        }

        sc.close();
    }
}