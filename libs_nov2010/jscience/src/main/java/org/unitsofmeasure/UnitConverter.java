/*
 * JScience - Java(TM) Tools and Libraries for the Advancement of Sciences.
 * Copyright (C) 2010 - JScience (http://jscience.org/)
 * All rights reserved.
 *
 * Permission to use, copy, modify, and distribute this software is
 * freely granted, provided that this notice is preserved.
 */
package org.unitsofmeasure;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

/**
 * <p> This interface represents a converter of numeric values.</p>
 *
 * <p> Instances of this class are obtained through the
 *     {@link Unit#getConverterTo} method.</p>
 *
 * @author  <a href="mailto:jean-marie@dautelle.com">Jean-Marie Dautelle</a>
 * @author  <a href="mailto:jsr275@catmedia.us">Werner Keil</a>
 * @version 1.0
 */
public interface UnitConverter {

    /**
     * Indicates if this converter is the identity converter. The identity
     * converter does nothing (<code>ONE.convert(x) == x</code>).
     */
    boolean isIdentity();

    /**
     * Indicates if this converter is linear. A converter is linear if
     * <code>convert(u + v) == convert(u) + convert(v)</code> and
     * <code>convert(r * u) == r * convert(u)</code>. For linear converters the
     * following property always hold:[code]
     *     y1 = c1.convert(x1);
     *     y2 = c2.convert(x2);
     *     // then y1*y2 == c1.concatenate(c2).convert(x1*x2)
     * [/code] </p>
     *
     * @return <code>true</code> if this converter is linear;
     *         <code>false</code> otherwise.
     */
    boolean isLinear();

    /**
     * Returns the inverse of this converter. If <code>x</code> is a valid
     * value, then <code>x == inverse().convert(convert(x))</code> to within
     * the accuracy of computer arithmetic.
     *
     * @return the inverse of this converter.
     */
    UnitConverter inverse();

    /**
     * Converts a <code>double</code> value.
     *
     * @param  value the numeric value to convert.
     * @return the <code>double</code> value after conversion.
     */
    double convert(double value);

    /**
     * Converts a {@link BigDecimal} number according to the specified
     * math context.
     *
     * @param value the decimal number to convert.
     * @param ctx the math context being used for conversion.
     * @return the decimal number after conversion.
     * @throws ArithmeticException if the result is inexact but the
     *         rounding mode is <code>MathContext.UNLIMITED</code> or
     *         <code>mathContext.precision == 0</code> and the quotient has a
     *         non-terminating decimal expansion.
     */
    BigDecimal convert(BigDecimal value, MathContext ctx) throws ArithmeticException;

    /**
     * Concatenates this converter with another converter. The resulting
     * converter is equivalent to first converting by the specified converter
     * (right converter), and then converting by this converter (left converter).
     *
     * @param  converter the other converter.
     * @return the concatenation of this converter with the other converter.
     */
    UnitConverter concatenate(UnitConverter converter);

    /**
     * Returns the chain of fundamental converters making up this converter
     * or <code>null</code> if this converter is a fundamental converter.
     * For example, <code>cvtr1.concatenate(cvtr2).getCompoundConverters()</code>
     * returns <code>{cvtr1, cvtr2}</code>
     *
     * @return the list of fundamental converters which concatenated makes up
     *         this converter.
     */
    List<UnitConverter> getCompoundConverters();

}
