import java.io.*;
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;

public class ParseCleanCheck {

	static Hashtable<String, String> dictionary;// To store all the words of the
	// dictionary
	static boolean suggestWord;// To indicate whether the word is spelled
								// correctly or not.

	static Scanner urlInput = new Scanner(System.in);
	public static String cleanString;
	public static String url = "";
	public static boolean correct = true;

	/**
	 * PARSER METHOD
	 */
	public static String PageScanner() throws IOException {
		System.out.println("Pick an english website to scan.");

		// This do-while loop allows the user to try again after a mistake
		do {
			try {
				System.out.println("Enter a URL, starting with http://");
				url = urlInput.nextLine();
				// This creates a document out of the HTML on the web page
				Document doc = Jsoup.connect(url).get();
				// This converts the document into a string to be cleaned
				String htmlToClean = doc.toString();
				cleanString = Jsoup.clean(htmlToClean, Whitelist.none());

				correct = false;
			} catch (Exception e) {
				System.out.println("Incorrect format for a URL. Please try again.");
			}
		} while (correct);
		return cleanString;
	}

}