/*
 * JScience - Java(TM) Tools and Libraries for the Advancement of Sciences.
 * Copyright (C) 2010 - JScience (http://jscience.org/)
 * All rights reserved.
 *
 * Permission to use, copy, modify, and distribute this software is
 * freely granted, provided that this notice is preserved.
 */
package org.unitsofmeasure;

/**
 * Signals that a problem of some sort has occurred due to the impossibility
 * of constructing a converter between two units. For example, the mutiplication
 * of offset units are usually units not convertible to their
 * {@link Unit#toMetric metric units}.
 *
 * @author  <a href="mailto:jean-marie@dautelle.com">Jean-Marie Dautelle</a>
 * @see Unit#getConverterTo(org.unitsofmeasure.Unit) 
 * @version 1.0
 */
public class UnconvertibleException extends RuntimeException {

    /**
     * Constructs a <code>UnconvertibleException</code> with the specified detail
     * message.
     *
     * @param  message the detail message.
     */
    public UnconvertibleException(String message) {
        super(message);
    }
}