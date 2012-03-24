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
import java.math.BigDecimal;
import java.math.MathContext;
import org.unitsofmeasure.quantity.Quantity;
import org.unitsofmeasure.Unit;
import org.unitsofmeasure.UnitFormat;
import javolution.context.ObjectFactory;
import javolution.text.Cursor;
import javolution.text.TextFormat;
import org.jscience.mathematics.number.Decimal;

/**
 * This class represents a {@link Decimal} amount.
 *
 * @author  <a href="mailto:jean-marie@dautelle.com">Jean-Marie Dautelle</a>
 * @version 5.0, November 20, 2009
 */
public class DecimalMeasure<Q extends Quantity<Q>> extends Measure<Decimal, Q> {

    /**
     * Holds the factory constructing new instances (possibly on the stack).
     */
    private static final ObjectFactory<DecimalMeasure> FACTORY = new ObjectFactory<DecimalMeasure>() {

        protected DecimalMeasure create() {
            return new DecimalMeasure();
        }
    };

    /**
     * Holds the decimal value stated in unit.
     */
    private Decimal _value;

    /**
     * Holds the unit.
     */
    private Unit<Q> _unit;

    /**
     * Default constructor.
     */
    DecimalMeasure() {
    }

    /**
     * Creates a decimal amount always on the heap
     * independently from the
     * current {@link javolution.context.AllocatorContext allocator context}.
     * To allow for custom object allocation policies, static factory methods
     * <code>valueOf(...)</code> are recommended.
     *
     * @param value the value stated in the specified unit.
     * @param unit the unit in which the value is stated.
     */
    public DecimalMeasure(Decimal value, Unit<Q> unit) {
        _value = value;
        _unit = unit;
    }

    /**
     * Returns the decimal amount corresponding to the specified decimal
     * value and unit.
     *
     * @param value the value stated in the specified unit.
     * @param unit the unit in which the value is stated.
     * @return the corresponding amount.
     */
    public static <Q extends Quantity<Q>> DecimalMeasure<Q> valueOf(Decimal value, Unit<Q> unit) {
        DecimalMeasure amount = FACTORY.object();
        amount._value = value;
        amount._unit = unit;
        return amount;
    }

    /**
     * Returns the decimal amount corresponding to the specified
     * character sequence holding the value stated in the specified unit
     * (convenience method equivalent to
     * <code>DecimalMeasure.valueOf(Decimal.valueOf(csq), unit)</code>).
     *
     * @param csq the character sequence holding the value.
     * @param unit the unit.
     * @return the corresponding amount.
     * @throws IllegalArgumentException if the specified character sequence
     *         does not contains a parsable decimal value.
     */
    public static <Q extends Quantity<Q>> DecimalMeasure<Q> valueOf(CharSequence csq, Unit<Q> unit) {
        return DecimalMeasure.valueOf(Decimal.valueOf(csq), unit);
    }

    @Override
    public Decimal getValue() {
        return _value;
    }

    @Override
    public Unit<Q> getUnit() {
        return _unit;
    }

    @Override
    public DecimalMeasure<Q> opposite() {
        return DecimalMeasure.valueOf(_value.opposite(), _unit);
    }

    @Override
    public DecimalMeasure<Q> plus(Measure<Decimal, ?> that) {
        Measure<Decimal, ?> amount = that.to((Unit)_unit);
        return DecimalMeasure.valueOf(this._value.plus(amount.getValue()), _unit);
    }

    @Override
    public DecimalMeasure minus(Measure<Decimal, ?> that) {
        Measure<Decimal, ?> amount = that.to((Unit)_unit);
        return DecimalMeasure.valueOf(this._value.minus(amount.getValue()), _unit);
    }

    @Override
    public DecimalMeasure<Q> times(long n) {
        return DecimalMeasure.valueOf(_value.times(n), _unit);
    }

    @Override
    public DecimalMeasure<?> times(Measure<Decimal, ?> that) {
        return DecimalMeasure.valueOf(_value.times(that.getValue()),
                this._unit.multiply(that.getUnit()));
    }

    @Override
    public DecimalMeasure<?> pow(int exp) {
        return DecimalMeasure.valueOf(_value.pow(exp), this._unit.pow(exp));
    }

    @Override
    public DecimalMeasure<?> inverse() {
        return DecimalMeasure.valueOf(_value.inverse(), this._unit.inverse());
    }

    @Override
    public DecimalMeasure<Q> divide(long n) {
        return DecimalMeasure.valueOf(_value.divide(n), _unit);
    }

    @Override
    public DecimalMeasure<?> divide(Measure<Decimal, ?> that) {
        return DecimalMeasure.valueOf(_value.divide(that.getValue()),
                this._unit.divide(that.getUnit()));
    }

    /**
     * Returns the <code>BigDecimal</code> value of this amount when
     * stated in the specified unit. The default implementation returns
     * <code>getUnit().getConverterTo(unit).convert(getValue().asBigDecimal()), ctx)</code>
     *
     * @param unit the unit in which the returned value is stated.
     * @param ctx the math context being used for conversion.
     * @return the decimal value after conversion.
     * @throws ArithmeticException if the result is inexact but the
     *         rounding mode is <code>UNNECESSARY</code> or
     *         <code>mathContext.precision == 0</code> and the quotient has a
     *         non-terminating decimal expansion.
     */
    @Override
    public BigDecimal decimalValue(Unit<Q> unit, MathContext ctx) throws ArithmeticException {
        if (_unit.equals(unit))
            return _value.decimalValue();
        return _unit.getConverterTo(unit).convert(_value.decimalValue(), ctx);
    }

    @Override
    public DecimalMeasure<Q> copy() {
        return DecimalMeasure.valueOf(_value.copy(), _unit);
    }
}
