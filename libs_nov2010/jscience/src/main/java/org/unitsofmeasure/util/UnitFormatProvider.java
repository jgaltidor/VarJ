/*
 * JScience - Java(TM) Tools and Libraries for the Advancement of Sciences.
 * Copyright (C) 2010 - JScience (http://jscience.org/)
 * All rights reserved.
 *
 * Permission to use, copy, modify, and distribute this software is
 * freely granted, provided that this notice is preserved.
 */
package org.unitsofmeasure.util;

import java.util.Locale;
import java.util.spi.LocaleServiceProvider;

/**
 * <p> The abstract class for service providers that provide concrete
 *     implementations of the {@link UnitFormat} class.</p>
 *
 * <p> An implementation of the {@link UnitFormatProvider} class should take
 *     the form of a jar file which contains the file:<pre><code>
 *     META-INF/services/org.unitsofmeasure.UnitFormatProvider
 *     </code></pre>
 *     The file <code>org.unitsofmeasure.UnitFormatProvider</code>
 *     should have a line such as:
 *         <code>org.jscience.physics.unit.UnitFormatProviderImpl</code>
 *     which is the fully qualified class name of the class implementing
 *     {@link UnitFormatProvider}.</p>
 *
 * @author <a href="mailto:jean-marie@dautelle.com">Jean-Marie Dautelle</a>
 * @version 1.0
 */
public abstract class UnitFormatProvider  extends LocaleServiceProvider {

    /**
     * Default constructor (for implementing class).
     */
    protected UnitFormatProvider() {
    }

    /**
     * Returns the standard instance (<a href="http://org.unitsofmeasure">UCUM</a>).
     *
     * @return the "Unified Code for Units of Measure" instance (UCUM).
     */
    public abstract UnitFormat getStandardInstance();

    /**
     * Returns the locale sensitive instance.
     *
     * @param locale the locale for the format.
     * @return the "Unified Code for Units of Measure" instance (UCUM).
     */
    public abstract UnitFormat getLocaleInstance(Locale locale);


}
