/*
 * JScience - Java(TM) Tools and Libraries for the Advancement of Sciences.
 * Copyright (C) 2010 - JScience (http://jscience.org/)
 * All rights reserved.
 *
 * Permission to use, copy, modify, and distribute this software is
 * freely granted, provided that this notice is preserved.
 */
package org.jscience.physics.measure;

import java.io.IOException;
import java.math.MathContext;
import org.unitsofmeasure.quantity.Quantity;
import org.unitsofmeasure.Unit;
import org.unitsofmeasure.UnitFormat;
import javolution.context.ObjectFactory;
import javolution.text.Cursor;
import javolution.text.TextFormat;
import org.jscience.mathematics.number.Complex;

/**
 * This class represents a {@link Complex} amount.
 *
 * @author  <a href="mailto:jean-marie@dautelle.com">Jean-Marie Dautelle</a>
 * @version 5.0, November 20, 2009
 */
public class ComplexMeasure<Q extends Quantity<Q>> extends Measure<Complex, Q> {

 
    /**
     * Holds the factory constructing new instances (possibly on the stack).
     */
    private static final ObjectFactory<ComplexMeasure> FACTORY = new ObjectFactory<ComplexMeasure>() {

        protected ComplexMeasure create() {
            return new ComplexMeasure();
        }
    };

    /**
     * Holds the Complex value stated in unit.
     */
    private Complex _value;

    /**
     * Holds the unit.
     */
    private Unit<Q> _unit;

    /**
     * Default constructor.
     */
    ComplexMeasure() {
    }

    /**
     * Creates a complex amount always on the heap
     * independently from the
     * current {@link javolution.context.AllocatorContext allocator context}.
     * To allow for custom object allocation policies, static factory methods
     * <code>valueOf(...)</code> are recommended.
     *
     * @param value the value stated in the specified unit.
     * @param unit the unit in which the value is stated.
     */
    public ComplexMeasure(Complex value, Unit<Q> unit) {
        _value = value;
        _unit = unit;
    }

    /**
     * Returns the Complex amount corresponding to the specified complex
     * value and unit.
     *
     * @param value the value stated in the specified unit.
     * @param unit the unit in which the value is stated.
     * @return the corresponding amount.
     */
    public static <Q extends Quantity<Q>> ComplexMeasure<Q> valueOf(Complex value, Unit<Q> unit) {
        ComplexMeasure amount = FACTORY.object();
        amount._value = value;
        amount._unit = unit;
        return amount;
    }

    /**
     * Returns the complex amount corresponding to the specified
     * real and imaginary value (convenience method equivalent to
     * <code>ComplexMeasure.valueOf(Complex.valueOf(real, imaginary), unit)</code>).
     *
     * @param real the real value stated in the specified unit.
     * @param imaginary the imaginary value stated in the specified unit.
     * @param unit the unit.
     * @return the corresponding amount.
     */
    public static <Q extends Quantity<Q>> ComplexMeasure<Q> valueOf(double real, double imaginary, Unit<Q> unit) {
        return ComplexMeasure.valueOf(Complex.valueOf(real, imaginary), unit);
    }

    @Override
    public Complex getValue() {
        return _value;
    }

    @Override
    public Unit<Q> getUnit() {
        return _unit;
    }

    @Override
    public ComplexMeasure<Q> opposite() {
        return ComplexMeasure.valueOf(_value.opposite(), _unit);
    }

    @Override
    public ComplexMeasure<Q> plus(Measure<Complex, ?> that) {
        Measure<Complex, ?> amount = that.to((Unit)_unit);
        return ComplexMeasure.valueOf(this._value.plus(amount.getValue()), _unit);
    }

    @Override
    public ComplexMeasure minus(Measure<Complex, ?> that) {
        Measure<Complex, ?> amount = that.to((Unit)_unit);
        return ComplexMeasure.valueOf(this._value.minus(amount.getValue()), _unit);
    }

    @Override
    public ComplexMeasure<Q> times(long n) {
        return ComplexMeasure.valueOf(_value.times(n), _unit);
    }

    @Override
    public ComplexMeasure<?> times(Measure<Complex, ?> that) {
        return ComplexMeasure.valueOf(_value.times(that.getValue()),
                this._unit.multiply(that.getUnit()));
    }

    @Override
    public ComplexMeasure<?> pow(int exp) {
        return ComplexMeasure.valueOf(_value.pow(exp), this._unit.pow(exp));
    }

    @Override
    public ComplexMeasure<?> inverse() {
        return ComplexMeasure.valueOf(_value.inverse(), this._unit.inverse());
    }

    @Override
    public ComplexMeasure<Q> divide(long n) {
        return ComplexMeasure.valueOf(_value.divide(n), _unit);
    }

    @Override
    public ComplexMeasure<?> divide(Measure<Complex, ?> that) {
        return ComplexMeasure.valueOf(_value.divide(that.getValue()),
                this._unit.divide(that.getUnit()));
    }

    @Override
    public ComplexMeasure<Q> copy() {
        return ComplexMeasure.valueOf(_value.copy(), _unit);
    }
}
