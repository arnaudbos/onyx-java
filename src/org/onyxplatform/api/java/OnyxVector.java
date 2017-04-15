package org.onyxplatform.api.java;

import clojure.lang.PersistentVector;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Serves as a base for any Onyx concept that is represented by a vector of
 * onyx maps, e.g. workflows.
 */
public class OnyxVector
{
	protected PersistentVector vContents;

	/**
	 * Constructs a new OnyxVector object with an empty contents vector.
	 * @return new OnyxVector object with an empty content vector
	 */
	protected OnyxVector() {
		vContents = PersistentVector.EMPTY;
	}

	/**
	 * Constructs a new OnyxVector object with an initial contents vector set
	 * to an existing passed vector
	 * @param  PersistentVector pv    existing vector to use as content vector
	 * @return                  new OnyxVector object
	 */
	protected OnyxVector(PersistentVector pv) {
		vContents = pv;
	}

	/**
	 * Creates a new ArrayList by converting the existing content
	 * vector (does not alter the existing content vector).
	 * Note: This method causes two compiler complaints, but because of the
	 * package architecture, any aberrant behavior will be caught and handled
	 * either in clojure during coercion or in a higher order nested behavior.
	 * @return newly created ArrayList representation of the content vector
	 */
	@SuppressWarnings({"unchecked", "rawtypes"})
   	public List<Map<String, Object>> toList() {
        	return new ArrayList(vContents);
    	}

	/**
	 * Adds an existing object to the content vector of the object, appending
	 * it to the end of the content vector. The object should represent a map.
	 * @param Object o object to be added to the existing content vector
	 */
	protected void addElement(Object o) {
	    vContents = vContents.cons(o);
	}

	/**
	 * Iterates over the existing content vector, ensuring each constituent
	 * map is a Clojure PersistentHashMap in a new PersistentVector container.
	 * @return new PersistentVector container containing guaranteed
	 * PersistentHashMap entities
	 */
	public PersistentVector toCljVector() {

		PersistentVector v = PersistentVector.EMPTY;

		for (Object e : vContents) {
			OnyxEntity oe = (OnyxEntity) e;
			v = v.cons(oe.toCljMap());
		}

		return v;
	}

	/**
	 * Produces a string representation of the
	 * contents of the content vector without modifying the actual vector.
	 * @return string representation of the content vector
	 */
	@Override
	public String toString() {
    	return Arrays.toString(vContents.toArray());
	}
}
