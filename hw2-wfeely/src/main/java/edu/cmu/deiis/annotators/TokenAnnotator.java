package edu.cmu.deiis.annotators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.jcas.JCas;
import edu.cmu.deiis.types.*;

/**
 * Annotator that identifies tokens using Java 1.4 regular expressions.
 */
public class TokenAnnotator extends JCasAnnotator_ImplBase {
  // token regex; matches alphanumeric strings
  private Pattern tokenPattern =
           Pattern.compile("[A-Za-z0-9]+");
  public void process (JCas aJCas) {
    // get document text
    String text = aJCas.getDocumentText();
    String [] sentences = text.split("\\n");
    int docpos = 0;
    for (String sentence : sentences){
      // skip Q/A and 0/1 in beginning of sentence
      String sentenceText;
      if (sentence.substring(0, 1).equals("Q")){
        sentenceText = sentence.substring(2);
        docpos += 2;
      }
      else {
        sentenceText = sentence.substring(4);
        docpos += 4;
      }
      // search for tokens in each sentence
      int spos = 0;
      Matcher matcher = tokenPattern.matcher(sentenceText);
      while (matcher.find(spos)) {
        // found a token; create annotation
        Token token = new Token(aJCas);
        token.setBegin(docpos + matcher.start());
        token.setEnd(docpos + matcher.end());
        // add token to indexes and iterate
        token.addToIndexes();
        spos = matcher.end();
      }
      docpos += sentenceText.length() + 1;
    }
  }
}
