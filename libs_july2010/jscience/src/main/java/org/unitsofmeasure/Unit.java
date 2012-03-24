/*
 * JScience - Java(TM) Tools and Libraries for the Advancement of Sciences.
 * Copyright (C) 2010 - JScience (http://jscience.org/)
 * All rights reserved.
 *
 * Permission to use, copy, modify, and distribute this software is
 * freely granted, provided that this notice is preserved.
 */
package org.unitsofmeasure;

import org.unitsofmeasure.quantity.Quantity;
import org.unitsofmeasure.util.UnitFormat;
import java.util.Map;
import org.unitsofmeasure.quantity.Dimensionless;

/**
 * <p> This interface represents a determinate {@link Quantity quantity}
 *     (as of length, time, heat, or value) adopted as a standard of
 *     measurement.</p>
 *
 * <p> It is helpful to think of instances of this class as recording the
 *     history by which they are created. Thus, for example, the string "g/kg"
 *     (which is a {@link #isDimensionless}  unit) would result from invoking
 *     the method toString() on a unit that was created by dividing a gram unit
 *     by a kilogram unit. Yet, "kg" divided by "kg" returns {@link #ONE} and
 *     not "kg/kg" due to automatic unit factorization.</p>
 *
 * <p> This interface supports the multiplication of offsets units. The result
 *     is usually a unit not convertible to its {@link #toMetric metric unit}.
 *     Such units may appear in derivative quantities. For example Celsius per
 *     meter is an unit of gradient, which is common in atmospheric and
 *     oceanographic research.</p>
 *
 * <p> Units raised at non-integral powers are not supported. For example,
 *     <code>LITRE.root(2)</code> raises an <code>ArithmeticException</code>;
 *     but <code>LITRE.toMetric().root(2)</code> returns <code>METRE</code>.</p>
 *
 * <p> Unit instances should be immutable.</p>
 *
 * @param <Q> The type of the quantity measured by this unit.
 *
 * @author <a href="mailto:jean-marie@dautelle.com">Jean-Marie Dautelle</a>
 * @author <a href="mailto:steve@unidata.ucar.edu">Steve Emmerson</a>
 * @author  <a href="mailto:desruisseaux@users.sourceforge.net">Martin Desruisseaux</a>
 * @see     <a href="http://en.wikipedia.org/wiki/Units_of_measurement"> Wikipedia:
 *          Units of measurement</a>
 * @version 1.0
 */
public interface Unit<Q extends Quantity<Q>>  {

    /*******************/
    /** Units Queries **/
    /*******************/
    /**
     * Returns the symbol (if any) of this unit.
     *
     * @return this unit symbol or <code>null</code> if this unit has not
     *         specific symbol associated with (e.g. product of units).
     */
    String getSymbol();

    /**
     * Returns the simple units and their exponent whose product is
     * this unit or <code>null</code> if this unit is a simple unit (not a
     * product of existing units).
     *
     * @return the simple units and their exponent making up this unit.
     */
    Map<Unit<?>, Integer> getProductUnits();

    /**
     * Returns the unscaled metric unit from which this unit is derived.
     *
     * <p> Metric units are either base units, {@link #alternate alternate
     *     units} or product of rational powers of metric units.</p>
     *
     * <p> Because the metric unit is unique by quantity type, it can be
     *     be used to identify the quantity given the unit. For example:[code]
     *     static boolean isAngularVelocity(Unit<?> unit) {
     *         return unit.toMetric().equals(RADIAN.divide(SECOND));
     *     }
     *     assert(REVOLUTION.divide(MINUTE).isAngularVelocity()); // Returns true.
     * [/code]
     *
     * @return the metric unit this unit is derived from or <code>this</code>
     *         if this unit {@link #isMetric() is a metric} unit.
     * @see #isMetric()
     */
    Unit<Q> toMetric();

    /**
     * Indicates if this unit is compatible with the unit specified. Units don't
     * need to be equals to be compatible. For example:[code]
     *     RADIAN.equals(ONE) == false
     *     RADIAN.isCompatible(ONE) == true
     * [/code]
     *
     * @param that the other unit.
     * @return <code>this.getDimension().equals(that.getDimension())</code>
     * @see #getDimension()
     */
    boolean isCompatible(Unit<?> that);

    /**
     * Casts this unit to a parameterized unit of specified nature or throw a
     * <code>ClassCastException</code> if the dimension of the specified
     * quantity and this unit's dimension do not match. For example:[code]
     *      Unit<Velocity> C = METRE.times(299792458).divide(SECOND).asType(Velocity.class);
     * [/code]
     *
     * @param  <T> The type of the quantity measured by the unit.
     * @param type the quantity class identifying the nature of the unit.
     * @return this unit parameterized with the specified type.
     * @throws ClassCastException if the dimension of this unit is different
     *         from the specified quantity dimension.
     * @throws UnsupportedOperationException if the specified type is
     *         not recognized.
     */
    <T extends Quantity<T>>   Unit<T> asType(Class<T> type);

    /**
     * Returns the dimension of this unit.
     *
     * @return the dimension of this unit for the current model.
     */
    Dimension getDimension();

    /**
     * Returns a converter of numeric values from this unit to another unit of
     * same type (convenience method not raising checked exception).
     *
     * @param that the unit of same type to which to convert the numeric values.
     * @return the converter from this unit to <code>that</code> unit.
     * @throws UnconvertibleException if a converter cannot be constructed.
     */
    UnitConverter getConverterTo(Unit<Q> that) throws UnconvertibleException;

    /**
     * Returns a converter from this unit to the specified unit of type unknown.
     * This method can be used when the quantity type of the specified unit
     * is unknown at compile-time or when dimensional analysis allows for
     * conversion between units of different type.
     * <p> To convert to a unit having the same parameterized type,
     * {@link #getConverterTo(Unit)} is preferred (no checked exception raised).</p>
     *
     * @param that the unit to which to convert the numeric values.
     * @return the converter from this unit to <code>that</code> unit.
     * @throws IncommensurableException if
     *         <code>!this.getDimension().equals(that.getDimension())</code>
     * @throws UnconvertibleException if a converter cannot be constructed.
     */
    UnitConverter getConverterToAny(Unit<?> that) throws IncommensurableException,
            UnconvertibleException;

    /**********************/
    /** Units Operations **/
    /**********************/
    /**
     * Returns a metric unit equivalent to this unscaled metric unit but used
     * in expressions to distinguish between quantities of a different nature
     * but of the same dimensions.
     *
     * <p> Examples of alternate units:[code]
     *     Unit<Angle> RADIAN = ONE.alternate("rad").asType(Angle.class);
     *     Unit<Force> NEWTON = METRE.times(KILOGRAM).divide(SECOND.pow(2)).alternate("N").asType(Force.class);
     *     Unit<Pressure> PASCAL = NEWTON.divide(METRE.pow(2)).alternate("Pa").asType(Pressure.class);
     * [/code]
     * </p>
     *
     * @param symbol the new symbol for the alternate unit.
     * @return the alternate unit.
     * @throws UnsupportedOperationException if this unit is not an unscaled metric unit.
     * @throws IllegalArgumentException if the specified symbol is already
     *         associated to a different unit.
     */
    Unit<?> alternate(String symbol);

    /**
     * Returns the unit derived from this unit using the specified converter.
     * The converter does not need to be linear. For example:[code]
     *     Unit<Dimensionless> DECIBEL = Unit.ONE.transform(
     *         new LogConverter(10).inverse().concatenate(
     *             new RationalConverter(1, 10)));
     * [/code]
     *
     * @param operation the converter from the transformed unit to this unit.
     * @return the unit after the specified transformation.
     */
    Unit<Q> transform(UnitConverter operation);

    /**
     * Returns the result of adding an offset to this unit. The returned unit is
     * convertible with all units that are convertible with this unit.
     *
     * @param offset the offset added (expressed in this unit, e.g.
     * <code>CELSIUS = KELVIN.add(273.15)</code>).
     * @return this unit offset by the specified value.
     */
    Unit<Q> add(double offset);

    /**
     * Returns the result of multiplying this unit by the specified factor.
     * If the factor is an integer value, the multiplication is exact
     * (recommended). For example:<pre><code>
     *    FOOT = METRE.multiply(3048).divide(10000); // Exact definition.
     *    ELECTRON_MASS = KILOGRAM.multiply(9.10938188e-31); // Approximation.
     * </code></pre>
     *
     * @param factor the factor
     * @return this unit scaled by the specified factor.
     */
    Unit<Q> multiply(double factor);

    /**
     * Returns the product of this unit with the one specified.
     *
     * @param that the unit multiplicand.
     * @return <code>this * that</code>
     */
    Unit<?> multiply(Unit<?> that);

    /**
     * Returns the inverse of this unit.
     *
     * @return <code>1 / this</code>
     */
    Unit<?> inverse();

    /**
     * Returns the result of dividing this unit by an approximate divisor.
     * If the factor is an integer value, the division is exact.
     * For example:<pre><code>
     *    QUART = GALLON_LIQUID_US.divide(4); // Exact definition.
     * </code></pre>
     * @param divisor the divisor value.
     * @return this unit divided by the specified divisor.
     */
    Unit<Q> divide(double divisor);

    /**
     * Returns the quotient of this unit with the one specified.
     *
     * @param that the unit divisor.
     * @return <code>this / that</code>
     */
    Unit<?> divide(Unit<?> that);

    /**
     * Returns a unit equals to the given root of this unit.
     *
     * @param n the root's order.
     * @return the result of taking the given root of this unit.
     * @throws ArithmeticException if <code>n == 0</code> or if this operation
     *         would result in an unit with a fractional exponent.
     */
    Unit<?> root(int n);

    /**
     * Returns a unit equals to this unit raised to an exponent.
     *
     * @param n the exponent.
     * @return the result of raising this unit to the exponent.
     */
    Unit<?> pow(int n);
}
