/*
 * JScience - Java(TM) Tools and Libraries for the Advancement of Sciences.
 * Copyright (C) 2006 - JScience (http://jscience.org/)
 * All rights reserved.
 * 
 * Permission to use, copy, modify, and distribute this software is
 * freely granted, provided that this notice is preserved.
 */
package org.jscience.physics.measure;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import javolution.text.Cursor;
import org.jscience.mathematics.structure.Field;
import org.unitsofmeasure.quantity.Quantity;
import org.unitsofmeasure.Unit;
import org.unitsofmeasure.quantity.Dimensionless;
import org.unitsofmeasure.UnitConverter;
import org.unitsofmeasure.UnitFormat;
import org.jscience.physics.unit.converter.RationalConverter;
import javolution.lang.Realtime;
import javolution.lang.ValueType;
import javolution.text.Text;
import javolution.text.TextBuilder;
import javolution.text.TextFormat;
import javolution.xml.XMLFormat;
import javolution.xml.XMLSerializable;
import javolution.xml.stream.XMLStreamException;
import org.jscience.mathematics.number.Decimal;
import org.jscience.mathematics.number.FieldNumber;
import org.jscience.mathematics.number.Float64;

/**
 * <p> This class and sub-classes represents a determinate or estimated amount
 *     for which operations such as addition, subtraction, multiplication and
 *     division can be performed (they implement the {@link Field} interface).
 *     [code]
 *         // Calculates the distance traveled during 6 minutes at 50 miles/hour
 *         Measure<Float64, Velocity> v = Measure.valueOf(50, MILES_PER_HOUR);
 *         Measure<Float64, Duration> t = Measure.valueOf(6, MINUTE);
 *         Measure<Float64, Length> x = v.times(t).asType(Length.class); // Checks dimension consistency.
 *         System.out.println(x.to(KILO(METRE));
 * 
 *         > 8.045 km
 *     [/code]</p>
 *     
 * <p> Operations between different amounts may or may not be authorized
 *     based upon the current {@link javax.measure.unit.Dimension.Model
 *     physical model}. For example, adding <code>Measure&lt;?, Length&gt; and
 *     <code>Measure&lt;?, Duration&gt; is not allowed by the
 *     {@link org.jscience.physics.model.StandardModel standard model},
 *     but is authorized using the {@link
 *     org.jscience.physics.model.RelativisticModel relativistic model}.</p>
 *
 * <p> Amounts are typically created from static factory methods.
 *     [code]
 *         // Generic amounts (works with any {{@link FieldNumber} value).
 *         Measure<Real, Dimensionless> π_20 // Number pi with 20 digits precision.
 *             = Measure.valueOf(Real.valueOf("(3.1415926535897932385 ± 1E-20)");
 *         Measure<Rational, Length> gap // 1/12 inch, exact fraction.
 *             = Measure.valueOf(Rational.valueOf(1,12), INCH);
 *
 *         // Specialized amounts (sub-classes).
 *         Float64Measure<Mass> mass = Float64Measure.valueOf(123.4, MILLI(GRAM)); // Subclass of Measure<Float64, Q>
 *         DecimalAmount<Temperature> temp = DecimalAmount.valueOf("278.15", KELVIN); // Subclass of Measure<Decimal, Q>
 *         ComplexAmount<ElectricCurrent> current = ComplexAmount.valueOf(2.1, -3.2, MICRO(AMPERE)); // Subclass of Measure<Complex, Q>
 *
 *         System.out.println(π_20);
 *         System.out.println(gap);
 *         System.out.println(mass);
 *         System.out.println(temp);
 *         System.out.println(current);
 *
 *         > (3.1415926535897932385 ± 1E-20)
 *         > 1/12 in
 *         > 123.4 mg
 *         > 278.15 K
 *         > (2.1 - 3.2i) µA
 *    [/code]</p>
 *
 * <p> Any amount can be converted to a 64 bits floating point amount if required
 *     (e.g. {@link Constants physical constants}).[code]
 *
 *         // Retrieves a 64 bits constants from a higher accuracy one.
 *         Float64Measure<Dimensionless> pi = π_20.asFloat64();
 *
 *         // Converts exact rational amount to its 64 bits approximation.
 *         Float64Measure<Length> twelfthInch = gap.asFloat64();
 *     [/code]</p>
 *
 * <p> This class being a {@link Field field} (e.g. the product of
 *     an amount is also an amount), it can be used to resolve linear system
 *     of equation involving real world quantities (using the
 *     {@link org.jscience.mathematics.vector} package).</p>
 *
 * <p> <b>Note:</b> Instances of this class are {@link javax.measure.Measurable
 *     measurable}; but <b>are not</b> numbers (e.g. doubleValue()
 *     without a unit parameter is still undefined). Therefore, this class
 *     is part of the physics package and not the mathematics one.
 *     Specialized amounts may be defined in others packages as well,
 *     for example the {@link org.jscience.economics.money.MoneyAmount
 *     MoneyAmount} a sub-class of DecimalAmount&lt;Money&gt; located
 *     in the economics package.</p>
 *     
 * @author  <a href="mailto:jean-marie@dautelle.com">Jean-Marie Dautelle</a>
 * @version 5.0, November 20, 2009
 * @see <a href="http://en.wikipedia.org/wiki/Measuring">
 *       Wikipedia: Measuring</a>
 */
public abstract class Measure<N extends FieldNumber<N>, Q extends Quantity<Q>>
        implements Quantity<Q>, Field<Measure<N, ?>>, Realtime, ValueType, XMLSerializable {

    /**
     * Defines the default text format for amounts (formatting only).
     * This format consists of the amount value and unit separaed by
     * a space (e.g. rational amount "1/3 kg"). This representation uses
     * the current format associated to the amount's value.
     * @see TextFormat#getInstance
     */
    protected static final TextFormat<Measure> DEFAULT_AMOUNT_FORMAT = new TextFormat<Measure>(Measure.class) {

        @Override
        public Appendable format(Measure amount, Appendable out) throws IOException {
            Number value = amount.getValue();
            TextFormat<Number> valueFormat = TextFormat.getInstance(value.getClass());
            valueFormat.format(value, out);
            Unit unit = amount.getUnit();
            if (unit.equals(Unit.ONE))
                return out;
            out.append(' ');
            return UnitFormat.getInstance().format(unit, out);
        }

        @Override
        public boolean isParsingSupported() {
            return false;
        }

        @Override
        public Measure parse(CharSequence csq, Cursor cursor) throws IllegalArgumentException {
            throw new UnsupportedOperationException("Parsing not supported for generic amount.");
        }
    };

    /**
     * Returns the amount corresponding to the specified arguments.
     *
     * @param value the value stated in the specified unit.
     * @param unit the unit in which the value is stated.
     * @return the corresponding amount.
     */
    public static <N extends FieldNumber<N>, Q extends Quantity<Q>> Measure<N, Q> valueOf(N value, Unit<Q> unit) {
        return MeasureImpl.valueOf(value, unit);
    }

    /**
     * Returns the amount corresponding to the specified
     * dimensionless value.
     *
     * @param value the value (dimensionless).
     * @return the corresponding amount.
     */
    public static <N extends FieldNumber<N>>   Measure<N, Dimensionless> valueOf(N value) {
        return MeasureImpl.valueOf(value, Unit.ONE);
    }

    /**
     * Returns the 64 bits floating point amount corresponding to the specified
     * <code>double</code> value and unit (convenience method equivalent to
     * <code>Measure.valueOf(Float64.valueOf(doubleValue), unit)</code>).
     *
     * @param doubleValue the value as a <code>double</code> stated in the
     *        specified unit.
     * @param unit the unit in which the value is stated.
     * @return the corresponding amount.
     * @see Float64Measure#valueOf(double, javax.measure.unit.Unit)
     */
    public static <Q extends Quantity<Q>> Measure<Float64, Q> valueOf(double doubleValue, Unit<Q> unit) {
        return Float64Measure.valueOf(doubleValue, unit);
    }

    /**
     * Returns the 64 bits floating point dimensionless amount corresponding to
     * the specified <code>double</code> value (convenience method equivalent to
     * <code>Measure.valueOf(Float64.valueOf(doubleValue))</code>).
     *
     * @param doubleValue the dimensionless value as a <code>double</code>.
     * @return the corresponding amount.
     * @see Float64Measure#valueOf(double)
     */
    public static Measure<Float64, Dimensionless> valueOf(double doubleValue) {
        return Float64Measure.valueOf(doubleValue);
    }

    /**
     * Returns the 64 bits floating point amount corresponding to the specified
     * <code>int</code> value and unit (convenience method equivalent to
     * <code>Measure.valueOf(Float64.valueOf((double)intValue), unit)</code>).
     *
     * @param intValue the value as a <code>int</code> stated in the
     *        specified unit.
     * @param unit the unit in which the value is stated.
     * @return the corresponding amount.
     * @see Float64Measure#valueOf(double, javax.measure.unit.Unit)
     */
    public static <Q extends Quantity<Q>> Measure<Float64, Q> valueOf(int intValue, Unit<Q> unit) {
        return Float64Measure.valueOf((double) intValue, unit);
    }

    /**
     * Returns the 64 bits floating point dimensionless amount corresponding to
     * the specified <code>int</code> value (convenience method equivalent to
     * <code>Measure.valueOf(Float64.valueOf((double)intValue))</code>).
     *
     * @param intValue the dimensionless value as a <code>int</code>.
     * @return the corresponding amount.
     * @see Float64Measure#valueOf(double)
     */
    public static Measure<Float64, Dimensionless> valueOf(int intValue) {
        return Float64Measure.valueOf((double) intValue);
    }

    /**
     * Default constructor.
     */
    protected Measure() {
        super();
    }

    /**
     * Returns this amount numeric value.
     *
     * @return this amount value.
     */
    public abstract N getValue();

    /**
     * Returns this amount unit.
     *
     * @return this amount unit.
     */
    public abstract Unit<Q> getUnit();

    /**
     * Casts this amount to a parameterized unit of specified nature or throw a
     * <code>ClassCastException</code> if the dimension of the specified
     * quantity and this amount unit's dimension do not match.
     *
     * @param type the quantity class identifying the nature of the amount.
     * @return <code>this</code>
     * @throws ClassCastException if the dimension of this unit is different
     *         from the specified quantity dimension.
     * @throws UnsupportedOperationException
     *             if the specified quantity class does not have a public static
     *             field named "UNIT" holding the SI unit for the quantity.
     * @see Unit#asType(Class)
     */
    public <Q extends Quantity<Q>> Measure<N, Q> asType(Class<Q> type)
            throws ClassCastException {
        this.getUnit().asType(type); // Test unit dimension compatibility.
        return (Measure<N, Q>) this;
    }

    /**
     * Converts this amount to a 64 bits floating point amount type.
     *
     * @return <code>Float64Measure.valueOf(doubleValue(getUnit()), getUnit())</code>
     */
    public Float64Measure<Q> asFloat64() {
        Unit<Q> unit = this.getUnit();
        double value = doubleValue(unit);
        return Float64Measure.valueOf(value, unit);
    }

    public Measure<N, Q> to(Unit<Q> unit) {
        return to(unit, MathContext.DECIMAL32);
    }

    public Measure<N, Q> to(Unit<Q> unit, MathContext ctx) {
        if (this.getUnit().equals(unit))
            return this;
        UnitConverter cvtr = this.getUnit().getConverterTo(unit);
        if (cvtr == UnitConverter.IDENTITY)
            return Measure.valueOf(this.getValue(), unit);
        return Measure.valueOf(convert(this.getValue(), cvtr, ctx), unit);
    }

    // Try to convert the specified value.
    private static <N extends FieldNumber<N>>  N convert(N value, UnitConverter cvtr, MathContext ctx) {
        if (cvtr instanceof RationalConverter) { // Try converting through Field methods.
            RationalConverter rCvtr = (RationalConverter) cvtr;
            BigInteger dividend = rCvtr.getDividend();
            BigInteger divisor = rCvtr.getDivisor();
            if (dividend.abs().compareTo(BigInteger.valueOf(Long.MAX_VALUE)) > 0)
                throw new ArithmeticException("Multiplier overflow");
            if (divisor.compareTo(BigInteger.valueOf(Long.MAX_VALUE)) > 0)
                throw new ArithmeticException("Divisor overflow");
            return value.times(dividend.longValue()).divide(divisor.longValue());
        } else if (cvtr instanceof UnitConverter.Compound && cvtr.isLinear()) { // Do it in two parts.
            UnitConverter.Compound compound = (UnitConverter.Compound) cvtr;
            N firstConversion = convert(value, compound.getRight(), ctx);
            N secondConversion = convert(firstConversion, compound.getLeft(), ctx);
            return secondConversion;
        } else { // Try using BigDecimal as intermediate.
            BigDecimal decimalValue = value.decimalValue();
            BigDecimal newValue = cvtr.convert(decimalValue, ctx);
            if (((FieldNumber)value) instanceof Decimal)
                return (N)((FieldNumber)Decimal.valueOf(newValue));
            if (((FieldNumber)value) instanceof Float64)
                return (N)((FieldNumber)Float64.valueOf(newValue.doubleValue()));
            throw new ArithmeticException(
                    "Generic amount conversion not implemented for amount of type " + value.getClass());
        }
    }

    /**
     * Convenience method equivalent to {@link #to(javax.measure.unit.Unit)
     * to(getUnit().toSI())}.
     *
     * @return this amount or a new amount equivalent to this amount but
     *         stated in a SI unit.
     * @throws ArithmeticException if the conversion cannot be performed.
     */
    public Measure<N, Q> toSI() {
        return this.to(getUnit().toMetric());
    }


    /**
     * Returns the textual representation of this amount.
     * This method cannot be overriden, sub-classes should define their own
     * textual format which will automatically be used here.
     *
     * @return <code>TextFormat.getInstance(this.getClass()).format(this)</code>
     * @see #DEFAULT_AMOUNT_FORMAT
     */
    public final Text toText() {
        TextFormat<Measure> textFormat = TextFormat.getInstance(this.getClass());
        return textFormat.format(this);
    }

    /**
     * Returns the text representation of this number as a
     * <code>java.lang.String</code>.
     * This method cannot be overriden, sub-classes should define their own
     * textual format which will automatically be used here.
     *
     * @return <code>TextFormat.getInstance(this.getClass()).formatToString(this)</code>
     * @see #DEFAULT_AMOUNT_FORMAT
     */
    @Override
    public final String toString() {
        TextFormat<Measure> textFormat = TextFormat.getInstance(this.getClass());
        return textFormat.formatToString(this);
    }
    
    /**
     * Returns the <code>double</code> value of this amount when stated in the
     *  specified unit. The default implementation returns
     * <code>getUnit().getConverterTo(unit).convert(getValue().doubleValue())</code>
     *
     * @param unit the unit in which this returned value is stated.
     * @return the numeric value after conversion to type <code>double</code>.
     * @throws ArithmeticException if this amount cannot be represented
     *         by a <code>double</code> number in the specified unit.
     */
    public double doubleValue(Unit<Q> unit) throws ArithmeticException {
        return getUnit().getConverterTo(unit).convert(getValue().doubleValue());
    }

    /**
     * Returns the <code>BigDecimal</code> value of this amount when
     * stated in the specified unit. The default implementation returns
     * <code>getUnit().getConverterTo(unit).convert(
     * BigDecimal.valueOf(getValue().doubleValue()), ctx)</code>
     *
     * @param unit the unit in which the returned value is stated.
     * @param ctx the math context being used for conversion.
     * @return the decimal value after conversion.
     * @throws ArithmeticException if the result is inexact but the
     *         rounding mode is <code>UNNECESSARY</code> or
     *         <code>mathContext.precision == 0</code> and the quotient has a
     *         non-terminating decimal expansion.
     */
    public BigDecimal decimalValue(Unit<Q> unit, MathContext ctx) throws ArithmeticException {
        return getUnit().getConverterTo(unit).convert(
                BigDecimal.valueOf(getValue().doubleValue()), ctx);
    }

    /**
     * Returns the opposite of this amount.
     *
     * @return <code>-this</code>.
     */
    public abstract Measure<N, Q> opposite();

    /**
     * Returns the sum of this amount with the one specified.
     *
     * @param  that the amount to be added.
     * @return <code>this + that</code>.
     */
    public abstract Measure<N, Q> plus(Measure<N, ?> that);

    /**
     * Returns the difference between this amount and the one specified.
     *
     * @param  that the number to be subtracted.
     * @return <code>this - that</code>.
     */
    public Measure<N, Q> minus(Measure<N, ?> that) {
        return this.plus(that.opposite());
    }

    /**
     * Returns this amount multiplied by the specified factor.
     *
     * @param  n the factor multiplier.
     * @return <code>this * n</code>.
     */
    public abstract Measure<N, Q> times(long n);

    /**
     * Returns the product of this amount with the one specified.
     *
     * @param  that the number multiplier.
     * @return <code>this · that</code>.
     */
    public abstract Measure<N, ?> times(Measure<N, ?> that);

    /**
     * Returns this amount raised at the specified exponent.
     *
     * @param  exp the exponent.
     * @return <code>this<sup>exp</sup></code>
     */
    public abstract Measure<N, ?> pow(int exp);

    /**
     * Returns the reciprocal of this amount.
     *
     * @return <code>1 / this</code>.
     */
    public abstract Measure<N, ?> inverse();

    /**
     * Returns this amount divided by the specified divisor.
     *
     * @param  n the divisor.
     * @return <code>this / n</code>.
     */
    public abstract Measure<N, Q> divide(long n);

    /**
     * Returns this amount divided by the one specified.
     *
     * @param  that the amount divisor.
     * @return <code>this / that</code>.
     */
    public abstract Measure<N, ?> divide(Measure<N, ?> that);

    /**
     * Returns a copy of this amount (allocated in the current
     * {@link javolution.context.AllocatorContext context}).
     *
     * @return a deep copy of this.
     */
    public abstract Measure<N, Q> copy();
}
