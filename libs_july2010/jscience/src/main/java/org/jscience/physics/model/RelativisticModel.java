/*
 * JScience - Java(TM) Tools and Libraries for the Advancement of Sciences.
 * Copyright (C) 2006 - JScience (http://jscience.org/)
 * All rights reserved.
 * 
 * Permission to use, copy, modify, and distribute this software is
 * freely granted, provided that this notice is preserved.
 */
package org.jscience.physics.model;

import java.math.BigInteger;
import org.jscience.physics.unit.converter.RationalConverter;
import org.unitsofmeasure.UnitConverter;
import org.unitsofmeasure.BaseUnit;
import org.unitsofmeasure.Dimension;
import static org.unitsofmeasure.MetricSystem.*;

/**
 * This class represents the relativistic model.
 *
 * @author  <a href="mailto:jean-marie@dautelle.com">Jean-Marie Dautelle</a>
 * @version 3.1, April 22, 2006
 */
public class RelativisticModel extends PhysicalModel {

    
    /**
     * Holds the meter to time transform.
     */
    private static RationalConverter METRE_TO_TIME 
        = new RationalConverter(BigInteger.ONE, BigInteger.valueOf(299792458));
    
    /**
     * Holds the single instance of this class.
     */
    private final static RelativisticModel INSTANCE = new RelativisticModel();

    /**
     * Selects the relativistic model as the current model.
     */
    public static void select() {
        PhysicalModel.setCurrent(INSTANCE);
    }

    // Implements Dimension.Model
    public Dimension getDimension(BaseUnit<?> unit) {
        if (unit.equals(METRE)) return Dimension.TIME;
        return Dimension.Model.STANDARD.getDimension(unit);
    }

    // Implements Dimension.Model
    public UnitConverter getTransform(BaseUnit<?> unit) {
        if (unit.equals(METRE)) return METRE_TO_TIME;
        return Dimension.Model.STANDARD.getTransform(unit);
    }
}