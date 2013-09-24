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
  public void process(JCas aJCas) {
    // get question
    FSIndex questionIndex = aJCas.getAnnotationIndex(Question.type);
    Iterator questionIter = questionIndex.iterator();
    Question question = null;
    while (questionIter.hasNext())
      question = (Question) questionIter.next();
    // get answers
    FSIndex answerIndex = aJCas.getAnnotationIndex(Answer.type);
    // loop over answers
    Iterator answerIter = answerIndex.iterator();
    while (answerIter.hasNext()) {
      // grab an answer
      Answer answer = (Answer) answerIter.next();
      // create a new AnswerScore object for this answer
      AnswerScore score = new AnswerScore(aJCas);
      score.setAnswer(answer);
      score.setBegin(answer.getBegin());
      score.setEnd(answer.getEnd());
      // compute score for this answer
      // compute unigram matches
      int numUni = 0;
      int matchUni = 0;
      for (int i = 0; i < question.getUnigrams().size(); i++) {
        for (int j = 0; j < answer.getUnigrams().size(); j++) {
          NGram qNgram = (NGram) question.getUnigrams().get(i);
          NGram aNgram = (NGram) answer.getUnigrams().get(j);
          if (qNgram.getCoveredText().equals(aNgram.getCoveredText())) {
            // increment matching unigrams count
            matchUni++;
          }
        }
        // increment total unigrams count
        numUni++;
      }
      // compute bigram matches
      int numBi = 0;
      int matchBi = 0;
      for (int i = 0; i < question.getBigrams().size(); i++) {
        for (int j = 0; j < answer.getBigrams().size(); j++) {
          NGram qNgram = (NGram) question.getBigrams().get(i);
          NGram aNgram = (NGram) answer.getBigrams().get(j);
          if (qNgram.getCoveredText().equals(aNgram.getCoveredText())) {
            // increment matching bigrams count
            matchBi++;
          }
        }
        // increment total bigrams count
        numBi++;
      }
      // compute trigram match
      int numTri = 0;
      int matchTri = 0;
      for (int i = 0; i < question.getTrigrams().size(); i++) {
        for (int j = 0; j < answer.getTrigrams().size(); j++) {
          NGram qNgram = (NGram) question.getTrigrams().get(i);
          NGram aNgram = (NGram) answer.getTrigrams().get(j);
          if (qNgram.getCoveredText().equals(aNgram.getCoveredText())) {
            // increment matching trigrams count
            matchTri++;
          }
        }
        // increment total trigrams count
        numTri++;
      }
      // calculate score
      score.setScore((((double) matchUni + (double) matchBi + (double) matchTri) / ((double) numUni
              + (double) numBi + (double) numTri)));
      // score.setScore((((double) matchUni / (double) numUni) + ((double) matchBi / (double) numBi)
      // +
      // ((double) matchTri / (double) numTri)) / 3.0);
      // add score to indexes and iterate
      score.addToIndexes();
    }
  }
}