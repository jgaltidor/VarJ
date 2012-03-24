/*
 * JScience - Java(TM) Tools and Libraries for the Advancement of Sciences.
 * Copyright (C) 2009 - JScience (http://jscience.org/)
 * All rights reserved.
 *
 * Permission to use, copy, modify, and distribute this software is
 * freely granted, provided that this notice is preserved.
 */
package org.jscience.physics.measure;

import java.math.BigDecimal;
import java.math.MathContext;
import org.unitsofmeasure.quantity.Quantity;
import org.unitsofmeasure.Unit;
import org.unitsofmeasure.UnitConverter;
import javolution.context.ObjectFactory;
import org.jscience.mathematics.number.FieldNumber;

/**
 * This class hold the default {@link Measure} implementation.
 *
 * @author  <a href="mailto:jean-marie@dautelle.com">Jean-Marie Dautelle</a>
 * @version 5.0, November 20, 2009
 */
class MeasureImpl<N extends FieldNumber<N>, Q extends Quantity<Q>>  extends Measure<N, Q> {

    /**
     * Holds the factory constructing new instances (possibly on the stack).
     */
    private static final ObjectFactory<MeasureImpl> FACTORY = new ObjectFactory<MeasureImpl>() {

        protected MeasureImpl create() {
            return new MeasureImpl();
        }
    };

    /**
     * Holds the value stated in unit.
     */
    private N _value;
    
    /**
     * Holds the unit.
     */
    private Unit<Q> _unit;

    /**
     * Default constructor.
     */
    MeasureImpl() {
    }

    /**
     * Creates a numeric amount always on the heap independently from the
     * current {@link javolution.context.AllocatorContext allocator context}.
     * To allow for custom object allocation policies, static factory methods
     * <code>valueOf(...)</code> are recommended.
     *
     * @param value the value stated in the specified unit.
     * @param unit the unit in which the value is stated.
     */
    public MeasureImpl(N value, Unit<Q> unit) {
        _value = value;
        _unit = unit;
    }

    /**
     * Returns the numeric amount corresponding to the specified arguments.
     *
     * @param value the value stated in the specified unit.
     * @param unit the unit in which the value is stated.
     * @return the corresponding amount.
     */
    public static <N extends FieldNumber<N>, Q extends Quantity<Q>> MeasureImpl<N, Q>
            valueOf(N value, Unit<Q> unit) {
        MeasureImpl amount = FACTORY.object();
        amount._value = value;
        amount._unit = unit;
        return amount;
    }

    @Override
    public N getValue() {
        return _value;
    }

    @Override
    public Unit<Q> getUnit() {
        return _unit;
    }
    
    @Override
    public MeasureImpl<N, Q> opposite() {
        return MeasureImpl.valueOf(_value.opposite(), _unit);
    }

    @Override
    public MeasureImpl<N, Q> plus(Measure<N, ?> that) {
        Measure<N, ?> thatInThisUnit = that.to((Unit)_unit);
        return MeasureImpl.valueOf(this._value.plus(thatInThisUnit.getValue()), _unit);
    }

    @Override
    public MeasureImpl<N, Q> minus(Measure<N, ?> that) {
        Measure<N, ?> thatInThisUnit = that.to((Unit)_unit);
        return MeasureImpl.valueOf(this._value.minus(thatInThisUnit.getValue()), _unit);
    }

    @Override
    public MeasureImpl<N, Q> times(long n) {
        return MeasureImpl.valueOf(this._value.times(n), _unit);
    }

    @Override
    public MeasureImpl<N, ?> times(Measure<N, ?> that) {
        return MeasureImpl.valueOf(this._value.times(that.getValue()),
                this._unit.multiply(that.getUnit()));
    }

    @Override
    public MeasureImpl<N, ?> pow(int exp) {
        return MeasureImpl.valueOf(this._value.pow(exp), this._unit.pow(exp));
    }

    @Override
    public MeasureImpl<N, ?> inverse() {
        return MeasureImpl.valueOf(this._value.inverse(), this._unit.inverse());
    }

    @Override
    public MeasureImpl<N, Q> divide(long n) {
        return MeasureImpl.valueOf(this._value.divide(n), _unit);
    }

    @Override
    public MeasureImpl<N, ?> divide(Measure<N, ?> that) {
        return MeasureImpl.valueOf(this._value.divide(that.getValue()),
                this._unit.divide(that.getUnit()));
    }

    @Override
    public double doubleValue(Unit<Q> unit) throws ArithmeticException {
        UnitConverter cvtr = this.getUnit().getConverterTo(unit);
        return cvtr.convert(this.getValue().doubleValue());
    }

    @Override
    public BigDecimal decimalValue(Unit<Q> unit, MathContext ctx) throws ArithmeticException {
        return BigDecimal.valueOf(doubleValue(unit)); // Default for generic amount.
    }

    @Override
    public MeasureImpl<N, Q> copy() {
        return MeasureImpl.valueOf(_value.copy(), _unit);
    }

}
