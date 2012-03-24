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
import org.unitsofmeasure.quantity.Dimensionless;
import org.unitsofmeasure.quantity.Quantity;
import org.unitsofmeasure.Unit;
import org.unitsofmeasure.UnitFormat;
import javolution.context.ObjectFactory;
import javolution.lang.MathLib;
import javolution.text.Cursor;
import javolution.text.TextFormat;
import javolution.text.TypeFormat;
import org.jscience.mathematics.number.Float64;

/**
 * This class represents a 64 bits floating point amount.
 *
 * @author  <a href="mailto:jean-marie@dautelle.com">Jean-Marie Dautelle</a>
 * @version 5.0, November 20, 2009
 */
public class Float64Measure<Q extends Quantity<Q>> extends Measure<Float64, Q> {

    /**
     * Holds the factory constructing new instances (possibly on the stack).
     */
    private static final ObjectFactory<Float64Measure> FACTORY = new ObjectFactory<Float64Measure>() {

        protected Float64Measure create() {
            return new Float64Measure();
        }
    };

    /**
     * Holds the value stated in unit.
     */
    private double _value;

    /**
     * Holds the unit.
     */
    private Unit<Q> _unit;

    /**
     * Default constructor.
     */
    Float64Measure() {
    }

    /**
     * Creates a 64 bits floating point amount always on the heap
     * independently from the
     * current {@link javolution.context.AllocatorContext allocator context}.
     * To allow for custom object allocation policies, static factory methods
     * <code>valueOf(...)</code> are recommended.
     *
     * @param value the value stated in the specified unit.
     * @param unit the unit in which the value is stated.
     */
    public Float64Measure(double value, Unit<Q> unit) {
        _value = value;
        _unit = unit;
    }

    /**
     * Returns the 64 bits floating point amount corresponding to
     * the specified arguments.
     *
     * @param value the value stated in the specified unit.
     * @param unit the unit in which the value is stated.
     * @return the corresponding amount.
     */
    public static <Q extends Quantity<Q>> Float64Measure<Q> valueOf(double value, Unit<Q> unit) {
        Float64Measure amount = FACTORY.object();
        amount._value = value;
        amount._unit = unit;
        return amount;
    }

    /**
     * Returns the 64 bits floating point dimensionless amount corresponding to
     * the specified value.
     *
     * @param value the dimensionless value.
     * @return the corresponding amount.
     */
    public static Float64Measure<Dimensionless> valueOf(double value) {
        return Float64Measure.valueOf(value, Unit.ONE);
    }

    /**
     * Returns the product of this amount with the specified dimensionless value.
     *
     * @param  value the value multiplier.
     * @return <code>this · value</code>.
     */
    public Float64Measure<Q> times(double value) {
        return Float64Measure.valueOf(_value * value, _unit);
    }

    /**
     * Returns this amount divided by the specified dimensionless value.
     *
     * @param  value the value divisor.
     * @return <code>this / value</code>.
     */
    public Float64Measure<Q> divide(double value) {
        return Float64Measure.valueOf(_value / value, _unit);
    }

    /**
     * Returns the positive square root of this amount.
     *
     * @return <code>sqrt(this)</code>.
     */
    public Float64Measure<?> sqrt() {
        return Float64Measure.valueOf(MathLib.sqrt(_value), _unit.root(2));
    }

    /**
     * Returns the positive nth root of this amount.
     *
     * @param n the root's order.
     * @return <code>pow(this, 1.0 / n)</code>.
     */
    public Float64Measure<?> root(int n) {
        return Float64Measure.valueOf(Math.pow(_value, 1.0 / n), _unit.root(n));
    }

    @Override
    public Float64 getValue() {
        return Float64.valueOf(_value);
    }

    @Override
    public Unit<Q> getUnit() {
        return _unit;
    }

    @Override
    public Float64Measure<Q> to(Unit<Q> unit) {
        if (_unit.equals(unit))
            return this;
        double newValue = this._unit.getConverterTo(unit).convert(_value);
        return Float64Measure.valueOf(newValue, unit);
    }

    @Override
    public Float64Measure<Q> toSI() {
        return this.to(_unit.toMetric());
    }

    @Override
    public Float64Measure<Q> opposite() {
        return Float64Measure.valueOf(-_value, _unit);
    }

    @Override
    public Float64Measure<Q> plus(Measure<Float64, ?> that) {
        return Float64Measure.valueOf(_value + that.doubleValue((Unit) _unit), _unit);
    }

    @Override
    public Float64Measure<Q> minus(Measure<Float64, ?> that) {
        return Float64Measure.valueOf(_value - that.doubleValue((Unit) _unit), _unit);
    }

    @Override
    public Float64Measure<Q> times(long n) {
        return Float64Measure.valueOf(_value * n, _unit);
    }

    @Override
    public Float64Measure<?> times(Measure<Float64, ?> that) {
        final Unit thatUnit = that.getUnit();
        return Float64Measure.valueOf(_value * that.doubleValue(thatUnit),
                this._unit.multiply(thatUnit));
    }

    @Override
    public Float64Measure<?> pow(int exp) {
        return Float64Measure.valueOf(MathLib.pow(_value, exp), this._unit.pow(exp));
    }

    @Override
    public Float64Measure<?> inverse() {
        return Float64Measure.valueOf(1.0 / _value, this._unit.inverse());
    }

    @Override
    public Float64Measure<Q> divide(long n) {
        return Float64Measure.valueOf(_value / n, _unit);
    }

    @Override
    public Float64Measure<?> divide(Measure<Float64, ?> that) {
        final Unit thatUnit = that.getUnit();
        return Float64Measure.valueOf(_value / that.doubleValue(thatUnit),
                this._unit.divide(thatUnit));
    }

    @Override // Optimization.
    public double doubleValue(Unit<Q> unit) throws ArithmeticException {
        if ((_unit == unit) || _unit.equals(unit))
            return _value;
        return _unit.getConverterTo(unit).convert(_value);
    }


    @Override
    public Float64Measure<Q> copy() {
        return Float64Measure.valueOf(_value, _unit);
    }
}
