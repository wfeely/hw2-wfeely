
/* First created by JCasGen Mon Sep 23 22:05:18 EDT 2013 */
package edu.cmu.deiis.types;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** Evaluation type for ranking of answers and calculating precision @ N.
 * Updated by JCasGen Mon Sep 23 22:34:46 EDT 2013
 * @generated */
public class Eval_Type extends Annotation_Type {
  /** @generated */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Eval_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Eval_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Eval(addr, Eval_Type.this);
  			   Eval_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Eval(addr, Eval_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Eval.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("edu.cmu.deiis.types.Eval");
 
  /** @generated */
  final Feature casFeat_precisionAtN;
  /** @generated */
  final int     casFeatCode_precisionAtN;
  /** @generated */ 
  public double getPrecisionAtN(int addr) {
        if (featOkTst && casFeat_precisionAtN == null)
      jcas.throwFeatMissing("precisionAtN", "edu.cmu.deiis.types.Eval");
    return ll_cas.ll_getDoubleValue(addr, casFeatCode_precisionAtN);
  }
  /** @generated */    
  public void setPrecisionAtN(int addr, double v) {
        if (featOkTst && casFeat_precisionAtN == null)
      jcas.throwFeatMissing("precisionAtN", "edu.cmu.deiis.types.Eval");
    ll_cas.ll_setDoubleValue(addr, casFeatCode_precisionAtN, v);}
    
  
 
  /** @generated */
  final Feature casFeat_ranking;
  /** @generated */
  final int     casFeatCode_ranking;
  /** @generated */ 
  public int getRanking(int addr) {
        if (featOkTst && casFeat_ranking == null)
      jcas.throwFeatMissing("ranking", "edu.cmu.deiis.types.Eval");
    return ll_cas.ll_getRefValue(addr, casFeatCode_ranking);
  }
  /** @generated */    
  public void setRanking(int addr, int v) {
        if (featOkTst && casFeat_ranking == null)
      jcas.throwFeatMissing("ranking", "edu.cmu.deiis.types.Eval");
    ll_cas.ll_setRefValue(addr, casFeatCode_ranking, v);}
    
   /** @generated */
  public int getRanking(int addr, int i) {
        if (featOkTst && casFeat_ranking == null)
      jcas.throwFeatMissing("ranking", "edu.cmu.deiis.types.Eval");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_ranking), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_ranking), i);
  return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_ranking), i);
  }
   
  /** @generated */ 
  public void setRanking(int addr, int i, int v) {
        if (featOkTst && casFeat_ranking == null)
      jcas.throwFeatMissing("ranking", "edu.cmu.deiis.types.Eval");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_ranking), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_ranking), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_ranking), i, v);
  }
 



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public Eval_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_precisionAtN = jcas.getRequiredFeatureDE(casType, "precisionAtN", "uima.cas.Double", featOkTst);
    casFeatCode_precisionAtN  = (null == casFeat_precisionAtN) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_precisionAtN).getCode();

 
    casFeat_ranking = jcas.getRequiredFeatureDE(casType, "ranking", "uima.cas.FSArray", featOkTst);
    casFeatCode_ranking  = (null == casFeat_ranking) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_ranking).getCode();

  }
}



    