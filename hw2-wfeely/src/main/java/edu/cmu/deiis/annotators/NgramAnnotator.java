/** NgramAnnotator.java
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
import org.apache.uima.tutorial.RoomNumber;

import edu.cmu.deiis.types.*;

/**
 * Annotator that creates 1-,2-, and 3-grams from annotated tokens.
 */
public class NgramAnnotator extends JCasAnnotator_ImplBase {
  public void process (JCas aJCas) {
    // get tokens
    FSIndex tokenIndex = aJCas.getAnnotationIndex(Token.type);
    // loop over tokens
    Iterator tokenIter = tokenIndex.iterator();
    Token penult = null;
    Token antepenult = null;
    int sentenceNum = 0;
    while (tokenIter.hasNext()) {
      // grab a token
      Token token = (Token) tokenIter.next();
      // check if we're on a new sentence
      if (sentenceNum != token.getSentenceId()) {
        // new sentence, update sentenceNum counter and reset previous token buffers
        sentenceNum = token.getSentenceId();
        penult = null;
        antepenult = null;
      }
      // check previous tokens
      if (penult == null && antepenult == null) {
        // Case 1: Previous two tokens are not set
        // set previous token
        penult = token;
      }
      else if (antepenult == null)
      {
        // Case 2: Penultimate token is set, antepenultimate token is not set
        // check whether previous two tokens are from same sentence
        if (penult.getSentenceId() == token.getSentenceId())
        {
          // make a bigram
          NGram bigram = new NGram(aJCas);
          bigram.setOrder(2);
          bigram.setElementType("Token");
          bigram.setElements(new FSArray(aJCas, 2));
          // add tokens to bigram
          bigram.setElements(0,penult);
          bigram.setElements(1,token);
          // set bigram begin, end, sentenceId, and coveredText
          bigram.setBegin(penult.getBegin());
          bigram.setEnd(token.getEnd());
          bigram.setSentenceId(token.getSentenceId());
          bigram.setCoveredText(penult.getCoveredText() + " " + token.getCoveredText());
          // add bigram to indexes
          bigram.addToIndexes();
        }
        // set previous two tokens
        antepenult = penult;
        penult = token;
      }
      else {
        // Case 3: Previous two tokens are set
        // check whether previous two tokens are from same sentence
        if (penult.getSentenceId() == token.getSentenceId())
        {
          // make a bigram
          NGram bigram = new NGram(aJCas);
          bigram.setOrder(2);
          bigram.setElementType("Token");
          bigram.setElements(new FSArray(aJCas, 2));
          // add tokens to bigram
          bigram.setElements(0,penult);
          bigram.setElements(1,token);
          // set bigram begin, end, sentence ID, and covered text
          bigram.setBegin(penult.getBegin());
          bigram.setEnd(token.getEnd());
          bigram.setSentenceId(token.getSentenceId());
          bigram.setCoveredText(penult.getCoveredText() + " " + token.getCoveredText());
          // add bigram to indexes
          bigram.addToIndexes();
        }
        // check whether previous three tokens are from same sentence
        if (antepenult.getSentenceId() == penult.getSentenceId() &&
                penult.getSentenceId() == token.getSentenceId())
        {
          // make a trigram
          NGram trigram = new NGram(aJCas);
          trigram.setOrder(3);
          trigram.setElementType("Token");
          trigram.setElements(new FSArray(aJCas, 3));
          // add tokens to trigram
          trigram.setElements(0,antepenult);
          trigram.setElements(1,penult);
          trigram.setElements(2,token);
          // set trigram begin, end, sentence ID, and covered text
          trigram.setBegin(antepenult.getBegin());
          trigram.setEnd(token.getEnd());
          trigram.setSentenceId(token.getSentenceId());
          trigram.setCoveredText(antepenult.getCoveredText() + " " +
                  penult.getCoveredText() + " " + token.getCoveredText());
          // add trigram to indexes
          trigram.addToIndexes();
        }
        // set previous two tokens
        antepenult = penult;
        penult = token;
      }
      // set up a unigram
      NGram unigram = new NGram(aJCas);
      unigram.setOrder(1);
      unigram.setElementType("Token");
      unigram.setElements(new FSArray(aJCas, 1));
      // add token to unigram
      unigram.setElements(0,token);
      // set unigram begin, end, sentence ID, and covered text
      unigram.setBegin(token.getBegin());
      unigram.setEnd(token.getEnd());
      unigram.setSentenceId(token.getSentenceId());
      unigram.setCoveredText(token.getCoveredText());
      // add unigram to indexes
      unigram.addToIndexes();
    }
  }
}
