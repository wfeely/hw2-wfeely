/** QuestionAnnotator.java
 * @author Weston Feely
 */

package edu.cmu.deiis.annotators;

import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSArray;

import edu.cmu.deiis.types.*;

/**
 * Annotator that identifies questions using Java 1.4 regular expressions.
 */
public class QuestionAnnotator extends JCasAnnotator_ImplBase {
  // question regex; matches question strings
  private Pattern questionPattern = Pattern.compile("Q [A-Za-z ']+");

  public void process(JCas aJCas) {
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
      question.setCoveredText(text.substring(question.getBegin() + 2, question.getEnd()+1));
      // get number of words in question string (-1 for "Q")
      int numWords = matcher.group().split(" ").length - 1;
      // add ngrams to question
      int numUni = 0;
      int numBi = 0;
      int numTri = 0;
      question.setUnigrams(new FSArray(aJCas, numWords));
      question.setBigrams(new FSArray(aJCas, numWords - 1));
      question.setTrigrams(new FSArray(aJCas, numWords - 2));
      // get ngrams
      FSIndex ngramIndex = aJCas.getAnnotationIndex(NGram.type);
      // loop over ngrams
      Iterator ngramIter = ngramIndex.iterator();
      while (ngramIter.hasNext()) {
        // grab an ngram
        NGram ngram = (NGram) ngramIter.next();
        // check if ngram sentence ID is 0 (question is always first sentence)
        if (ngram.getSentenceId() == 0) {
          // check order of ngram
          if (ngram.getOrder() == 1) {
            // add unigram to unigrams
            question.setUnigrams(numUni, ngram);
            numUni++;
          } else if (ngram.getOrder() == 2) {
            // add bigram to bigrams
            question.setBigrams(numBi, ngram);
            numBi++;
          } else {
            // add trigram to trigrams
            question.setTrigrams(numTri, ngram);
            numTri++;
          }
        }
      }
      // add question to indexes and iterate
      question.addToIndexes();
      pos = matcher.end();
    }
  }
}
