/*
 * JScience - Java(TM) Tools and Libraries for the Advancement of Sciences.
 * Copyright (C) 2010 - JScience (http://jscience.org/)
 * All rights reserved.
 *
 * Permission to use, copy, modify, and distribute this software is
 * freely granted, provided that this notice is preserved.
 */
package org.unitsofmeasure.service;

import java.io.IOException;
import java.text.ParsePosition;
import org.unitsofmeasure.Unit;

/**
 * <p> This interface provides methods for OSGi bundles to parse/format units
 *     as per the <a href="http://www.unitsofmeasure.org/">
 *     Unified Code for Units of Measure (UCUM)</a> specification.</p>
 *
 * @author <a href="mailto:jean-marie@dautelle.com">Jean-Marie Dautelle</a>
 * @see <a href="http://aurora.regenstrief.org/~ucum/ucum.html">UCUM Full Specification</a>
 * @version 1.0
 */
public interface UnitFormatService {

    /**
     * Formats the specified unit.
     *
     * @param unit the unit to format.
     * @param appendable the appendable destination.
     * @return the appendable destination passed in with formatted text appended.
     * @throws IOException if an error occurs.
     */
    Appendable format(Unit<?> unit, Appendable appendable)
            throws IOException;

    /**
     * Parses a portion of the specified {@link CharSequence} from the
     * specified position to produce a unit. If there is no unit to parse
     * the unitary unit (dimensionless) is returned.
     *
     * @param csq the <code>CharSequence</code> to parse.
     * @param cursor the cursor holding the current parsing index or <code>
     *        null</code> to parse the whole character sequence.
     * @return the unit parsed from the specified character sub-sequence.
     * @throws IllegalArgumentException if any problem occurs while parsing the
     *         specified character sequence (e.g. illegal syntax).
     */
    Unit<?> parse(CharSequence csq, ParsePosition cursor)
            throws IllegalArgumentException;
}
