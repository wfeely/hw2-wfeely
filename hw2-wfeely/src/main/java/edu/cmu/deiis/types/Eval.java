

/* First created by JCasGen Mon Sep 23 22:05:18 EDT 2013 */
package edu.cmu.deiis.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.tcas.Annotation;


/** Evaluation type for ranking of answers and calculating precision @ N.
 * Updated by JCasGen Mon Sep 23 22:34:46 EDT 2013
 * XML source: /home/hermes/git/hw2-wfeely/hw2-wfeely/src/main/resources/descriptors/deiis_types.xml
 * @generated */
public class Eval extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Eval.class);
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
  protected Eval() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public Eval(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public Eval(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public Eval(JCas jcas, int begin, int end) {
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
  //* Feature: precisionAtN

  /** getter for precisionAtN - gets Precision @ N.
   * @generated */
  public double getPrecisionAtN() {
    if (Eval_Type.featOkTst && ((Eval_Type)jcasType).casFeat_precisionAtN == null)
      jcasType.jcas.throwFeatMissing("precisionAtN", "edu.cmu.deiis.types.Eval");
    return jcasType.ll_cas.ll_getDoubleValue(addr, ((Eval_Type)jcasType).casFeatCode_precisionAtN);}
    
  /** setter for precisionAtN - sets Precision @ N. 
   * @generated */
  public void setPrecisionAtN(double v) {
    if (Eval_Type.featOkTst && ((Eval_Type)jcasType).casFeat_precisionAtN == null)
      jcasType.jcas.throwFeatMissing("precisionAtN", "edu.cmu.deiis.types.Eval");
    jcasType.ll_cas.ll_setDoubleValue(addr, ((Eval_Type)jcasType).casFeatCode_precisionAtN, v);}    
   
    
  //*--------------*
  //* Feature: ranking

  /** getter for ranking - gets Ranking of AnswerScore objects, for calculating precision @ N.
   * @generated */
  public FSArray getRanking() {
    if (Eval_Type.featOkTst && ((Eval_Type)jcasType).casFeat_ranking == null)
      jcasType.jcas.throwFeatMissing("ranking", "edu.cmu.deiis.types.Eval");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Eval_Type)jcasType).casFeatCode_ranking)));}
    
  /** setter for ranking - sets Ranking of AnswerScore objects, for calculating precision @ N. 
   * @generated */
  public void setRanking(FSArray v) {
    if (Eval_Type.featOkTst && ((Eval_Type)jcasType).casFeat_ranking == null)
      jcasType.jcas.throwFeatMissing("ranking", "edu.cmu.deiis.types.Eval");
    jcasType.ll_cas.ll_setRefValue(addr, ((Eval_Type)jcasType).casFeatCode_ranking, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for ranking - gets an indexed value - Ranking of AnswerScore objects, for calculating precision @ N.
   * @generated */
  public AnswerScore getRanking(int i) {
    if (Eval_Type.featOkTst && ((Eval_Type)jcasType).casFeat_ranking == null)
      jcasType.jcas.throwFeatMissing("ranking", "edu.cmu.deiis.types.Eval");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((Eval_Type)jcasType).casFeatCode_ranking), i);
    return (AnswerScore)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((Eval_Type)jcasType).casFeatCode_ranking), i)));}

  /** indexed setter for ranking - sets an indexed value - Ranking of AnswerScore objects, for calculating precision @ N.
   * @generated */
  public void setRanking(int i, AnswerScore v) { 
    if (Eval_Type.featOkTst && ((Eval_Type)jcasType).casFeat_ranking == null)
      jcasType.jcas.throwFeatMissing("ranking", "edu.cmu.deiis.types.Eval");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((Eval_Type)jcasType).casFeatCode_ranking), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((Eval_Type)jcasType).casFeatCode_ranking), i, jcasType.ll_cas.ll_getFSRef(v));}
  }

    