/*
 * JScience - Java(TM) Tools and Libraries for the Advancement of Sciences.
 * Copyright (C) 2010 - JScience (http://jscience.org/)
 * All rights reserved.
 *
 * Permission to use, copy, modify, and distribute this software is
 * freely granted, provided that this notice is preserved.
 */
package org.unitsofmeasure.util;

import org.unitsofmeasure.Unit;
import org.unitsofmeasure.quantity.Angle;
import org.unitsofmeasure.quantity.Area;
import org.unitsofmeasure.quantity.Energy;
import org.unitsofmeasure.quantity.Length;
import org.unitsofmeasure.quantity.Mass;
import org.unitsofmeasure.quantity.Pressure;
import org.unitsofmeasure.quantity.Time;
import org.unitsofmeasure.quantity.Velocity;
import org.unitsofmeasure.quantity.Volume;

/**
 * <p> This convenience class holds non-metric units that are important and
 *      widely used.</p>
 *
 * <p> All units defined here are retrieved from their name using the
 *     {@link UnitFormat#getStandard() standard format} (UCUM) for units.
 *     For example:<code>LITRE = UnitFormat.getStandard().parse("L")</code></p>
 *
 * @author  <a href="mailto:jean-marie@dautelle.com">Jean-Marie Dautelle</a>
 * @see <a href="http://aurora.regenstrief.org/~ucum/ucum.html#section-NonMetric-Unit-Atoms">UCUM: International NonMetric Units</a>
 * @version 1.0
 */
public final class NonMetric {

    /**
     * Holds the UCUM unit format instance.
     */
    private static final UnitFormat UCUM = UnitFormat.getStandard();

    /**
     * Private constructor (utility class, cannot be instantiated).
     */
    private NonMetric() {
    }

    /////////////////////////////////////////////////////////////
    // Units from ISO 1000, ISO 2955 and ANSI X3.50 - UCUM §31 //
    /////////////////////////////////////////////////////////////

    /**
    * A unit of plane angle (UCUM name
    * <code>deg</code>).
     */
    public static final Unit<Angle> DEGREE_ANGLE =
            UCUM.parse("deg").asType(Angle.class);

   /**
    * A unit of plane angle equal to <code>deg/60</code> (UCUM name
    * <code>'</code>).
     */
    public static final Unit<Angle> MINUTE_ANGLE =
            UCUM.parse("'").asType(Angle.class);

   /**
    * A unit of plane angle equal to <code>'/60</code> (UCUM name
    * <code>''</code>).
     */
    public static final Unit<Angle> SECOND_ANGLE =
            UCUM.parse("''").asType(Angle.class);

   /**
    * A unit of volume equal to <code>1 dm3</code> (UCUM name
    * <code>L</code>).
     */
    public static final Unit<Volume> LITRE =
            UCUM.parse("L").asType(Volume.class);

   /**
    * Equivalent to {@link #LITRE}.
     */
    public static final Unit<Volume> LITER = LITRE;

   /**
    * A unit of surface equal to <code>100 m2</code> (UCUM name
    * <code>ar</code>).
     */
    public static final Unit<Volume> ARE =
            UCUM.parse("ar").asType(Volume.class);

   /**
    * A unit of time equal to <code>60 s</code> (UCUM name
    * <code>min</code>).
     */
    public static final Unit<Time> MINUTE =
            UCUM.parse("min").asType(Time.class);

   /**
    * A unit of time equal to <code>60 min</code> (UCUM name
    * <code>h</code>).
     */
    public static final Unit<Time> HOUR =
            UCUM.parse("h").asType(Time.class);
    
   /**
    * A unit of time equal to <code>24 h</code> (UCUM name
    * <code>d</code>).
     */
    public static final Unit<Time> DAY =
            UCUM.parse("d").asType(Time.class);

   /**
    * A unit of time equal to <code>365.24219 d</code> (UCUM name
    * <code>a_t</code>).
     */
    public static final Unit<Time> TROPICAL_YEAR =
            UCUM.parse("a_t").asType(Time.class);
    
   /**
    * A unit of time equal to <code>365.25 d</code> (UCUM name
    * <code>a_j</code>).
     */
    public static final Unit<Time> MEAN_JULIAN_YEAR =
            UCUM.parse("a_j").asType(Time.class);
    
   /**
    * A unit of time equal to <code>365.2425 d</code> (UCUM name
    * <code>a_g</code>).
     */
    public static final Unit<Time> MEAN_GREGORIAN_YEAR =
            UCUM.parse("a_g").asType(Time.class);
    
   /**
    * A unit of time equal to <code>a_j</code> (UCUM name
    * <code>a</code>).
    * @see #MEAN_JULIAN_YEAR
     */
    public static final Unit<Time> YEAR =
            UCUM.parse("a").asType(Time.class);

   /**
    * A unit of time equal to <code>7 d</code> (UCUM name
    * <code>wk</code>).
     */
    public static final Unit<Time> WEEK =
            UCUM.parse("wk").asType(Time.class);
    
   /**
    * A unit of time equal to <code>29.53059 d</code> (UCUM name
    * <code>mo_s</code>).
     */
    public static final Unit<Time> SYNODAL_MONTH =
            UCUM.parse("mo_s").asType(Time.class);

   /**
    * A unit of time equal to <code>a_j/12</code> (UCUM name
    * <code>mo_j</code>).
     */
    public static final Unit<Time> MEAN_JULIAN_MONTH =
            UCUM.parse("mo_j").asType(Time.class);
    
   /**
    * A unit of time equal to <code>a_g/12</code> (UCUM name
    * <code>mo_g</code>).
     */
    public static final Unit<Time> MEAN_GREGORIAN_MONTH =
            UCUM.parse("mo_g").asType(Time.class);

   /**
    * A unit of time equal to <code>mo_j</code> (UCUM name
    * <code>mo</code>).
    * @see #MEAN_JULIAN_MONTH
     */
    public static final Unit<Time> MONTH =
            UCUM.parse("mo").asType(Time.class);

   /**
    * A unit of mass equal to <code>1000 kg</code> (UCUM name
    * <code>t</code>).
     */
    public static final Unit<Mass> TONNE =
            UCUM.parse("t").asType(Mass.class);

   /**
    * A unit of pressure equal to <code>100000 Pa</code> (UCUM name
    * <code>bar</code>).
     */
    public static final Unit<Pressure> BAR =
            UCUM.parse("bar").asType(Pressure.class);

   /**
    * A unit of mass equal to <code>1.6605402 × 10-24 g</code> (UCUM name
    * <code>u</code>).
     */
    public static final Unit<Mass> UNIFIED_ATOMIC_MASS =
            UCUM.parse("u").asType(Mass.class);

   /**
    * A unit of energy equal to <code>1 [e].V</code> (UCUM name
    * <code>eV</code>).
     */
    public static final Unit<Energy> ELECTRON_VOLT =
            UCUM.parse("eV").asType(Energy.class);

        /**
     * A unit of length equal to <code>149597.870 Mm</code> (UCUM name
    * <code>AU</code>).
     */
    public static final Unit<Length> ASTRONOMIC_UNIT =
            UCUM.parse("AU").asType(Length.class);
        /**
     * A unit of length equal to <code>30.85678 Pm</code> (UCUM name
    * <code>pc</code>).
     */
    public static final Unit<Length> PARSEC =
            UCUM.parse("pc").asType(Length.class);

    //////////////////////////////////////////////
    // International customary units - UCUM §34 //
    //////////////////////////////////////////////

   /**
     * A unit of length equal to <code>2.54 cm</code> (UCUM name
    * <code>[in_i]</code>).
     */
    public static final Unit<Length> INCH =
            UCUM.parse("[in_i").asType(Length.class);

   /**
     * A unit of length equal to <code>12 [in_i]</code> (UCUM name
    * <code>[ft_i]</code>).
     */
    public static final Unit<Length> FOOT =
            UCUM.parse("[ft_i").asType(Length.class);

   /**
     * A unit of length equal to <code>3 [ft_i]</code> (UCUM name
    * <code>[yd_i]</code>).
     */
    public static final Unit<Length> YARD =
            UCUM.parse("[yd_i").asType(Length.class);

   /**
     * A unit of length equal to <code>5280 [ft_i]</code> (UCUM name
    * <code>[mi_i]</code>).
     */
    public static final Unit<Length> STATUTE_MILE =
            UCUM.parse("[mi_i").asType(Length.class);

   /**
     * A unit of depth of water equal to <code>6 [ft_i]</code> (UCUM name
    * <code>[fth_i]</code>).
     */
    public static final Unit<Length> FATHOM =
            UCUM.parse("[fth_i").asType(Length.class);

   /**
     * A unit of length equal to <code>1852 m</code> (UCUM name
    * <code>[nmi_i]</code>).
     */
    public static final Unit<Length> NAUTICAL_MILE =
            UCUM.parse("[nmi_i").asType(Length.class);

   /**
     * A unit of velocity equal to <code>[nmi_i]/h</code> (UCUM name
    * <code>[nmi_i]</code>).
     */
    public static final Unit<Velocity> KNOT =
            UCUM.parse("[kn_i").asType(Velocity.class);

   /**
     * A unit of area equal to <code>[in_i]2</code> (UCUM name
    * <code>[sin_i]</code>).
     */
    public static final Unit<Area> SQUARE_INCH =
            UCUM.parse("[sin_i").asType(Area.class);

   /**
     * A unit of area equal to <code>[ft_i]2</code> (UCUM name
    * <code>[sft_i]</code>).
     */
    public static final Unit<Area> SQUARE_FOOT =
            UCUM.parse("[sft_i").asType(Area.class);

   /**
     * A unit of area equal to <code>[yd_i]2</code> (UCUM name
    * <code>[syd_i]</code>).
     */
    public static final Unit<Area> SQUARE_YARD =
            UCUM.parse("[syd_i").asType(Area.class);

   /**
     * A unit of volume equal to <code>[in_i]3</code> (UCUM name
    * <code>[cin_i]</code>).
     */
    public static final Unit<Volume> CUBIC_INCH =
            UCUM.parse("[cin_i").asType(Volume.class);

   /**
     * A unit of volume equal to <code>[ft_i]3</code> (UCUM name
    * <code>[cft_i]</code>).
     */
    public static final Unit<Volume> CUBIC_FOOT =
            UCUM.parse("[cft_i").asType(Volume.class);

   /**
     * A unit of volume equal to <code>[yd_i]3</code> (UCUM name
    * <code>[cyd_i]</code>).
     */
    public static final Unit<Volume> CUBIC_YARD =
            UCUM.parse("[cyd_i").asType(Volume.class);

    ////////////////////////////////////
    // Avoirdupois weights - UCUM §39 //
    ////////////////////////////////////

   /**
     * A unit of mass (avoirdupois) equal to <code>64.79891 mg</code> (UCUM name
    * <code>[gr]</code>).
     */
    public static final Unit<Mass> GRAIN =
            UCUM.parse("[gr").asType(Mass.class);

   /**
     * A unit of mass (avoirdupois) equal to <code>7000 [gr]</code> (UCUM name
    * <code>[lb_av]</code>).
     */
    public static final Unit<Mass> POUND =
            UCUM.parse("[lb_av").asType(Mass.class);

    /**
     * A unit of mass (avoirdupois) equal to <code>[lb_av]/16 </code> (UCUM name
    * <code>[oz_av]</code>).
     */
    public static final Unit<Mass> OUNCE =
            UCUM.parse("[oz_av").asType(Mass.class);


    ////////////////////////////////////
    // Other legacy units - UCUM §43  //
    ////////////////////////////////////

    /**
     * A unit of temperature (UCUM name <code>[degF]</code>).
     */
    public static final Unit<Mass> FAHRENHEIT =
            UCUM.parse("[degF").asType(Mass.class);

}