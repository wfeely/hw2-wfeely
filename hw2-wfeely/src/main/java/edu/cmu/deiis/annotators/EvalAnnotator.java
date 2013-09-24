/** AnswerScoreAnnotator.java
 * @author Weston Feely
 */

package edu.cmu.deiis.annotators;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSArray;

import edu.cmu.deiis.types.*;

/**
 * Annotator that ranks answer scores, computes precision at N, and writes to standard out.
 */
public class EvalAnnotator extends JCasAnnotator_ImplBase {
  public void process(JCas aJCas) {
    // get question
    FSIndex questionIndex = aJCas.getAnnotationIndex(Question.type);
    Iterator questionIter = questionIndex.iterator();
    Question question = null;
    while (questionIter.hasNext())
      question = (Question) questionIter.next();
    // get answer scores
    FSIndex answerScoreIndex = aJCas.getAnnotationIndex(AnswerScore.type);
    // initialize N (number of correct answers)
    int N = 0;
    // create eval annotation
    Eval eval = new Eval(aJCas);
    eval.setRanking(new FSArray(aJCas, answerScoreIndex.size()));
    // create arrayList for answerScores
    ArrayList<AnswerScore> answerScores = new ArrayList<AnswerScore>();
    // loop over answer scores
    Iterator answerScoreIter = answerScoreIndex.iterator();
    while (answerScoreIter.hasNext()) {
      // grab an answer score
      AnswerScore answerScore = (AnswerScore) answerScoreIter.next();
      // increment N for correct answers
      if (answerScore.getAnswer().getIsCorrect())
        N++;
      // put answerScores into an arrayList
      answerScores.add(answerScore);
    }
    // sort answerScores arrayList
    for (int i = 0; i < eval.getRanking().size(); i++) {
      double bestScore = 0.0;
      int bestIndex = 0;
      for (int j = 0; j < answerScores.size(); j++) {
        if (answerScores.get(j).getScore() > bestScore) {
          bestScore = answerScores.get(j).getScore();
          bestIndex = j;
        }
      }
      eval.setRanking(i, answerScores.get(bestIndex));
      answerScores.remove(bestIndex);
    }
    // count up correct answers in top N of ranking
    int numCorrect = 0;
    for (int i = 0; i < N; i++) {
      if (eval.getRanking(i).getAnswer().getIsCorrect() == true)
        numCorrect++;
    }
    // calculate precision at N
    eval.setPrecisionAtN((double) numCorrect / (double) N);
    // print question to stdout
    System.out.println("Question: " + question.getCoveredText());
    // print each answerScore to stdout
    for (int i = 0; i < eval.getRanking().size(); i++) {
      AnswerScore answerScore = (AnswerScore) eval.getRanking().get(i);
      // print "+" for isCorrect "-" otherwise
      if (answerScore.getAnswer().getIsCorrect())
        System.out.print("+ ");
      else
        System.out.print("- ");
      // print score
      System.out.print((Math.floor(answerScore.getScore() * 100) / 100) + " ");
      // print covered text of answer
      System.out.println(answerScore.getAnswer().getCoveredText());
    }
    // print precision at N
    System.out.println("Precision at " + N + ": " + eval.getPrecisionAtN());
    System.out.println();
  }
}