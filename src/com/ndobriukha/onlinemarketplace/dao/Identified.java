package com.ndobriukha.onlinemarketplace.dao;

import java.io.Serializable;

/**
 * Identifiable interface
 * @author Nikita_Dobriukha
 *
 * @param <PK>
 */
public interface Identified<PK extends Serializable> {

	/**
	 * Return Id of object
	 */
	public PK getId();
}
