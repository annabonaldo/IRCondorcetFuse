/*
 * Terrier - Terabyte Retriever 
 * Webpage: http://terrier.org 
 * Contact: terrier{a.}dcs.gla.ac.uk
 * University of Glasgow - School of Computing Science
 * http://www.gla.ac.uk/
 * 
 * The contents of this file are subject to the Mozilla Public License
 * Version 1.1 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See
 * the License for the specific language governing rights and limitations
 * under the License.
 *
 * The Original Code is IF.java.
 *
 * The Original Code is Copyright (C) 2004-2016 the University of Glasgow.
 * All Rights Reserved.
 *
 * Contributor(s):
 *   Ben He <ben{a.}dcs.gla.ac.uk> 
 */
package org.terrier.matching.models.basicmodel;

/**
 * This class implements the IF basic model for randomness. 
 * @author  Ben He
  */
public class IF extends BasicModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** The name of the model. */
	protected String modelName = "IF";
	/** 
	 * A default constructor.
	 */
	public IF(){
		super();
	}
	/**
	 * Returns the name of the model.
	 * @return the name of the model
	 */
	public String getInfo(){
		return this.modelName;
	}
	/**
	 * This method computes the score for the implemented weighting model.
	 * @param tf The term frequency in the document
	 * @param documentFrequency The document frequency of the term
	 * @param termFrequency the term frequency in the collection
	 * @param keyFrequency The normalised query term frequency.
	 * @param documentLength The length of the document.
	 * @return the score returned by the implemented weighting model.
	 */
	public double score(
		double tf,
		double documentFrequency,
		double termFrequency,
		double keyFrequency,
		double documentLength){
		return keyFrequency * tf * i.idfDFR(termFrequency);
	}
}
