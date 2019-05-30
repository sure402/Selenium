import java.io.*;

public class BulkSpellChecker extends ParseCleanCheck {

	public static void main(String[] args) throws IOException {
		String parsedWords = PageScanner();
		SpellChecker spellTest = new SpellChecker();
		spellTest.verifySpellCheck(parsedWords);
	}
}