import java.io.IOException;
import java.util.List;

import org.languagetool.JLanguageTool;
import org.languagetool.language.BritishEnglish;
import org.languagetool.rules.RuleMatch;

public class SpellChecker {
	
	public void verifySpellCheck(String words) throws IOException {
		JLanguageTool langTool = new JLanguageTool(new BritishEnglish());
		List<RuleMatch> matches = langTool.check(words);
		for (RuleMatch match : matches) {
		  System.out.println("Potential error at characters " +
		      match.getFromPos() + "-" + match.getToPos() + ": " +
		      match.getMessage());
		  System.out.println("Suggested correction(s): " +
		      match.getSuggestedReplacements());
		}
	}

}
