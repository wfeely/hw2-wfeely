package edu.cmu.deiis.annotators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.jcas.JCas;
import edu.cmu.deiis.types.*;

/**
 * Annotator that detects tokens using Java 1.4 regular expressions.
 */
public class TokenAnnotator extends JCasAnnotator_ImplBase {
  private Pattern tokenPattern =
           Pattern.compile("[A-Za-z0-9]+");
  public void process (JCas aJCas) {
    // get document text
    String text = aJCas.getDocumentText();
    // search for tokens
    Matcher matcher = tokenPattern.matcher(text);
    int pos = 0;
    while (matcher.find(pos)) {
      // found one - create annotation
      Token token = new Token(aJCas);
      token.setBegin(matcher.start());
      token.setEnd(matcher.end());
      token.addToIndexes();
      pos = matcher.end();
    }
  }
}
