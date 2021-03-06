

/* First created by JCasGen Wed Sep 11 13:44:28 EDT 2013 */
package edu.cmu.deiis.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



/** 
 * Updated by JCasGen Mon Sep 23 22:34:46 EDT 2013
 * XML source: /home/hermes/git/hw2-wfeely/hw2-wfeely/src/main/resources/descriptors/deiis_types.xml
 * @generated */
public class Token extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Token.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated  */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected Token() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public Token(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public Token(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public Token(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {/*default - does nothing empty block */}
     
  //*--------------*
  //* Feature: sentenceId

  /** getter for sentenceId - gets Integer sentence ID for this token.
   * @generated */
  public int getSentenceId() {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_sentenceId == null)
      jcasType.jcas.throwFeatMissing("sentenceId", "edu.cmu.deiis.types.Token");
    return jcasType.ll_cas.ll_getIntValue(addr, ((Token_Type)jcasType).casFeatCode_sentenceId);}
    
  /** setter for sentenceId - sets Integer sentence ID for this token. 
   * @generated */
  public void setSentenceId(int v) {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_sentenceId == null)
      jcasType.jcas.throwFeatMissing("sentenceId", "edu.cmu.deiis.types.Token");
    jcasType.ll_cas.ll_setIntValue(addr, ((Token_Type)jcasType).casFeatCode_sentenceId, v);}    
   
    
  //*--------------*
  //* Feature: coveredText

  /** getter for coveredText - gets Covered text from document span, for this token.
   * @generated */
  public String getCoveredText() {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_coveredText == null)
      jcasType.jcas.throwFeatMissing("coveredText", "edu.cmu.deiis.types.Token");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Token_Type)jcasType).casFeatCode_coveredText);}
    
  /** setter for coveredText - sets Covered text from document span, for this token. 
   * @generated */
  public void setCoveredText(String v) {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_coveredText == null)
      jcasType.jcas.throwFeatMissing("coveredText", "edu.cmu.deiis.types.Token");
    jcasType.ll_cas.ll_setStringValue(addr, ((Token_Type)jcasType).casFeatCode_coveredText, v);}    
  }

    