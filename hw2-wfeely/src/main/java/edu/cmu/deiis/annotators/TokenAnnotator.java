/** TokenAnnotator.java
 * @author Weston Feely
 */

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
           Pattern.compile("[A-Za-z0-9']+");
  public void process (JCas aJCas) {
    // get document text
    String text = aJCas.getDocumentText();
    // split document text into sentences
    String [] sentences = text.split("\\n");
    // maintain position in document for annotations, as int
    int docpos = 0;
    for (int i=0; i<sentences.length; i++) {
      // grab next sentence
      String sentence = sentences[i];
      // skip Q/A and 0/1 in beginning of sentence
      String sentenceText;
      // Check whether sentence is question or answer
      if (sentence.substring(0, 1).equals("Q")){
        // Case 1: question; start sentenceText 2 chars in
        sentenceText = sentence.substring(2);
        // update position in document
        docpos += 2;
      }
      else {
        // Case 2: answer; start sentenceText 4 chars in
        sentenceText = sentence.substring(4);
        // update position in document
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
        token.setSentenceId(i);
        token.setCoveredText(text.substring(token.getBegin(), token.getEnd()));
        // add token to indexes and iterate
        token.addToIndexes();
        spos = matcher.end();
      }
      // update position in document (with +1 for removed newline char)
      docpos += sentenceText.length() + 1;
    }
  }
}
