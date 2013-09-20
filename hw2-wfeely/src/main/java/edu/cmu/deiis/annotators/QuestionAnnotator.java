package edu.cmu.deiis.annotators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.jcas.JCas;
import edu.cmu.deiis.types.*;

/**
 * Annotator that identifies questions using Java 1.4 regular expressions.
 */
public class QuestionAnnotator extends JCasAnnotator_ImplBase {
  // question regex; matches question strings
  private Pattern questionPattern =
           Pattern.compile("Q [A-Za-z ]+");
  public void process (JCas aJCas) {
    // get document text
    String text = aJCas.getDocumentText();
    // search for questions
    Matcher matcher = questionPattern.matcher(text);
    int pos = 0;
    while (matcher.find(pos)) {
      // found a question; create annotation
      Question question = new Question(aJCas);
      question.setBegin(matcher.start());
      question.setEnd(matcher.end());
      // add question to indexes and iterate
      question.addToIndexes();
      pos = matcher.end();
    }
  }
}
