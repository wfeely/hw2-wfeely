package edu.cmu.deiis.annotators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.jcas.JCas;
import edu.cmu.deiis.types.*;

/**
 * Annotator that identifies answers using Java 1.4 regular expressions.
 */
public class AnswerAnnotator extends JCasAnnotator_ImplBase {
  private Pattern answerPattern =
           Pattern.compile("A [01] [A-Za-z ]+");
  private Pattern keyPattern =
           Pattern.compile("A [01]");
  public void process (JCas aJCas) {
    // get document text
    String text = aJCas.getDocumentText();
    // search for tokens
    Matcher matcher = answerPattern.matcher(text);
    int pos = 0;
    while (matcher.find(pos)) {
      // found one - create annotation
      Answer answer = new Answer(aJCas);
      answer.setBegin(matcher.start());
      answer.setEnd(matcher.end());
      answer.addToIndexes();
      pos = matcher.end();
    }
  }
}
