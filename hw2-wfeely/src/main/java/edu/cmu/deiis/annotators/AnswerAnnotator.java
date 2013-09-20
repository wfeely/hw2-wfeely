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
           Pattern.compile("A ([01])");
  public void process (JCas aJCas) {
    // get document text
    String text = aJCas.getDocumentText();
    // search for answers
    Matcher matchtok = answerPattern.matcher(text);
    int pos = 0;
    while (matchtok.find(pos)) {
      // found an answer; create annotation
      Answer answer = new Answer(aJCas);
      answer.setBegin(matchtok.start());
      answer.setEnd(matchtok.end());
      // search for answer key
      Matcher matchkey = keyPattern.matcher(answer.getCoveredText());
      boolean key = false;
      if (matchkey.find()) {
        if (matchkey.group(1).equals("1"))
          key = true;
      }
      // add answer key to answer
      answer.setIsCorrect(key);
      // add answer to indexes and iterate
      answer.addToIndexes();
      pos = matchtok.end();
    }
  }
}
