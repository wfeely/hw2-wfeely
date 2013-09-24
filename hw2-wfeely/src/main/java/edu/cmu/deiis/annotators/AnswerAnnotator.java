/** AnswerAnnotator.java
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
import org.apache.uima.jcas.cas.FSList;

import edu.cmu.deiis.types.*;

/**
 * Annotator that identifies answers using Java 1.4 regular expressions.
 */
public class AnswerAnnotator extends JCasAnnotator_ImplBase {
  // answer regex; matches answer strings
  private Pattern answerPattern = Pattern.compile("A [01] [A-Za-z ']+");

  public void process(JCas aJCas) {
    // get document text
    String text = aJCas.getDocumentText();
    // search for answers
    Matcher matcher = answerPattern.matcher(text);
    int pos = 0; // document position
    int i = 0; // answer counter
    while (matcher.find(pos)) {
      // found an answer; create annotation
      i++;
      Answer answer = new Answer(aJCas);
      answer.setBegin(matcher.start());
      answer.setEnd(matcher.end());
      answer.setCoveredText(text.substring(answer.getBegin() + 4, answer.getEnd()+1));
      // get number of words in answer string (-2 for "A" and "0/1")
      int numWords = matcher.group().split(" ").length - 2;
      // get answer key
      boolean key = false;
      if (text.charAt(answer.getBegin()+2) == '1')
        key = true;
      // add answer key to answer
      answer.setIsCorrect(key);
      // add ngrams to answer
      int numUni = 0;
      int numBi = 0;
      int numTri = 0;
      answer.setUnigrams(new FSArray(aJCas, numWords));
      answer.setBigrams(new FSArray(aJCas, numWords - 1));
      answer.setTrigrams(new FSArray(aJCas, numWords - 2));
      // get ngrams
      FSIndex ngramIndex = aJCas.getAnnotationIndex(NGram.type);
      // loop over ngrams
      Iterator ngramIter = ngramIndex.iterator();
      while (ngramIter.hasNext()) {
        // grab an ngram
        NGram ngram = (NGram) ngramIter.next();
        // check if ngram sentence ID matches current answer counter
        if (ngram.getSentenceId() == i) {
          // check order of ngram
          if (ngram.getOrder() == 1) {
            // add unigram to unigrams
            answer.setUnigrams(numUni, ngram);
            numUni++;
          } else if (ngram.getOrder() == 2) {
            // add bigram to bigrams
            answer.setBigrams(numBi, ngram);
            numBi++;
          } else {
            // add trigram to trigrams
            answer.setTrigrams(numTri, ngram);
            numTri++;
          }
        }
      }
      // add answer to indexes and iterate
      answer.addToIndexes();
      pos = matcher.end();
    }
  }
}
