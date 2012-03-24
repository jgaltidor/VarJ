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
 * Signals that a problem of some sort has occurred due to the
 * incommensurability of some quantities/units. Only commensurable quantities
 * (quantities with the same dimensions) may be compared, equated, added, or
 * subtracted. Also, conversion from one unit to another unit is possible only
 * if they are commensurable.
 *
 * @author  <a href="mailto:jean-marie@dautelle.com">Jean-Marie Dautelle</a>
 * @see     <a href="http://en.wikipedia.org/wiki/Unit_commensurability#Commensurability">
 *          Wikipedia: Unit Commensurability</a>
 * @see Unit#getConverterToAny(org.unitsofmeasure.Unit) 
 * @version 1.0
 */
public class IncommensurableException extends Exception {

    /**
     * Constructs a <code>IncommensurableException</code> with the specified detail
     * message.
     *
     * @param  message the detail message.
     */
    public IncommensurableException(String message) {
        super(message);
    }
}