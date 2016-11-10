package com.uknowho.sample.soap.entity;

import java.io.Serializable;

/**
 * This GenericEntity class provides methods for base hibernate entity.  
 * 
 * Created date <21_Jul-2016>
 * 
 * @version 1.0.1
 * @since 1.0.1
 * 
 * @author <a href="mailto:tony87c0086@hotmail.com"> Xiaoyu Zhang (Tony) </a>
 * @param <T>
 * 
 */

public abstract class GenericEntity<T> implements Serializable {

	private static final long serialVersionUID = -2093137771198332134L;

	/**
	 * Get object string 
	 * @return String
	 */
	public abstract String toString();
	
	/**
	 * Compare object with other object  
	 * @return boolean
	 */
    public abstract boolean equals(Object o);

    /**
	 * Hash object for unique value
	 * @return hashCode
	 */
    public abstract int hashCode();
    
    /**
	 * Clone object for unique value
	 * @return hashCode
	 */
    public abstract T clone();
}
