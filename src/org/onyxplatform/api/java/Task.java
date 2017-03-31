package org.onyxplatform.api.java;

import clojure.lang.PersistentHashMap;
import java.util.Map;

/**
 * A task specifies a map that represents a single onyx unit of work. This
 * corresponds to an actual system function and piece of code to run in the
 * context of an onyx job.
 * Tasks derive from OnyxEntity.
 */
public class Task extends OnyxEntity
{
	protected static String coerceKw = "catalog-entry";

	/**
	 * Creates a new Task object using OnyxEntity superconstructor.
	 * @return new Task object
	 */
	public Task() {
	}

	/**
	 * Creates a new Task object using an existing content map.
	 * Uses OnyxEntity superconstructor.
	 * @param  PersistentHashMap ent           existing map to use for new Task
	 * @return                   new Task object
	 */
	private Task(PersistentHashMap ent) {
    	super(ent);
	}

	/**
	 * Coerces Task object content map into proper onyx Task.
	 * Returns the onyx representation without altering the existing content map.
	 * @param  Map<String, Object>       jMap content map to coerce
	 * @return             onyx representation of content map
	 */
	protected PersistentHashMap coerce(Map<String, Object> jMap) {
		return (PersistentHashMap) castTypesFn.invoke(coerceKw,jMap);
	}
}
