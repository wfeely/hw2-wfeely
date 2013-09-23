/** AnswerScoreAnnotator.java
 * @author Weston Feely
 */

package edu.cmu.deiis.annotators;

import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.jcas.JCas;

import edu.cmu.deiis.types.*;

/**
 * Annotator that scores answers by finding shared ngrams between question and answers.
 */
public class AnswerScoreAnnotator extends JCasAnnotator_ImplBase {
  public void process (JCas aJCas) {
    // get answers
    FSIndex answerIndex = aJCas.getAnnotationIndex(Answer.type);
    // loop over answers
    Iterator answerIter = answerIndex.iterator();
    while (answerIter.hasNext()) {
      // grab an answer
      Answer ans = (Answer) answerIter.next();
      // create a new AnswerScore object for this answer
      AnswerScore score = new AnswerScore(aJCas);
      score.setAnswer(ans);
      score.setBegin(ans.getBegin());
      score.setEnd(ans.getEnd());
      // TODO: compute score for this answer
      // add score to indexes and iterate
      score.addToIndexes();
    }
  }
}
