/*
 * JScience - Java(TM) Tools and Libraries for the Advancement of Sciences.
 * Copyright (C) 2006 - JScience (http://jscience.org/)
 * All rights reserved.
 * 
 * Permission to use, copy, modify, and distribute this software is
 * freely granted, provided that this notice is preserved.
 */
package org.jscience.physics.measure;

import org.unitsofmeasure.quantity.*;
import static org.unitsofmeasure.MetricSystem.*;
import org.unitsofmeasure.Unit;
import javolution.context.ImmortalContext;
import org.jscience.mathematics.number.LargeInteger;
import org.jscience.mathematics.number.Real;

/**
 * <p> This class provides most accurate physical constants measurement;
 *     the more accurate the constants, the higher the precision 
 *     of the calculations making use of these constants.</p>
 *     
 * <p> Constants are {@link Measure} with arbitrary precision numbers
 *     ({@link Real}). If the constant is exact then
 *     <code>constant.getValue().isExact()</code> returns <code>true</code>.</p>
 *
 * <p> Constants can be converted to their 64 bits best estimated value using
 *     the {@link Measure#asFloat64()} method.[code]
 *         Float64Amount<Dimensionless> pi = π.asFloat64();
 *     [/code]</p>
 *
 * <p> Constants names use the full range of Unicode characters and
 *     are mixed uppercase/lowercase to resemble symbolic names as much
 *     as possible </p>
 *
 * <p> Reference: <a href="http://physics.nist.gov/cuu/Constants/index.html">
 *     CODATA Internationally recommended values of the Fundamental Physical
 *     Constants (2002)</a></p>
 *
 * @author  <a href="mailto:jean-marie@dautelle.com">Jean-Marie Dautelle</a>
 * @version 5.0, December 27, 2009
 */
public final class Constants {

    /**
     * Default constructor (no derivation allows).
     */
    private Constants() {
    }

    ////////////////////////////////////////////////////////////////////////////
    // Ensures all constants are allocated in immortal memory (RTSJ).
    // After constants declaration, followed by ImmortalContext.exit()
    static {
        ImmortalContext.enter();
    }

    /**
     * Holds the 100 first digits of PI constant.
     */
    private static final String PI_DIGITS =
            "31415926535897932384"
            + "62643383279502884197"
            + "16939937510582097494"
            + "45923078164062862089"
            + "98628034825342117068";

    /**
     * Holds the standard acceleration due to gravity (approximately equal 
     * to the acceleration due to gravity on the Earth's surface).
     * @see <a href="http://en.wikipedia.org/wiki/Acceleration_due_to_gravity">
     *      Wikipedia: Acceleration due to gravity</a>
     */
    public final static Measure<Real, Acceleration> g =
            Measure.valueOf(Real.valueOf(980665, -5, 0), METRES_PER_SQUARE_SECOND);

    /**
     * Holds the electron rest mass.
     */
    public final static Measure<Real, Mass> me =
            Measure.valueOf(Real.valueOf(91093826, -38, 16), KILOGRAM);

    /**
     * Holds the proton rest mass.
     */
    public final static Measure<Real, Mass> mp =
            Measure.valueOf(Real.valueOf(167262171, -35, 29), KILOGRAM);

    /**
     * Holds the neutron rest mass.
     */
    public final static Measure<Real, Mass> mn =
            Measure.valueOf(Real.valueOf(167492728, -35, 29), KILOGRAM);

    /**
     * Holds the deuteron rest mass.
     */
    public final static Measure<Real, Mass> md =
            Measure.valueOf(Real.valueOf(334358335, -35, 57), KILOGRAM);

    /**
     * Holds the muon rest mass.
     */
    public final static Measure<Real, Mass> mμ =
            Measure.valueOf(Real.valueOf(188353140, -36, 33), KILOGRAM);

    /**
     * Holds the ratio of the circumference of a circle to its diameter
     * (34 digits precision equivalent to decimal 128 floating point).
     *
     * @see <a href="http://en.wikipedia.org/wiki/Decimal128_floating_point_format">
     *      Wikipedia: Decimal 128</a>
     */
    public final static Measure<Real, Dimensionless> π =
            Measure.valueOf(Real.valueOf(new LargeInteger(PI_DIGITS.substring(0, 34)), -33, 1), Unit.ONE);

    /**
     * Holds the speed of light in vacuum (exact).
     */
    public final static Measure<Real, Velocity> c =
            Measure.valueOf(Real.valueOf(299792458, 0), METRES_PER_SECOND);

    /**
     * Holds the Boltzmann constant.
     * @see <a href="http://en.wikipedia.org/wiki/Boltzmanns_constant">
     *      Wikipedia: Boltzmann constant</a>
     */
    public final static Measure<Real, ?> k =
            Measure.valueOf(Real.valueOf(13806505, -30, 24), JOULE.divide(KELVIN));

    /**
     * Holds the Planck constant.
     * @see <a href="http://en.wikipedia.org/wiki/Plank%27s_constant">
     *      Wikipedia: Plank's constant</a>
     */
    public final static Measure<Real, ?> ℎ =
            Measure.valueOf(Real.valueOf(66260693, -41, 33), JOULE.multiply(SECOND));

    /**
     * Holds the Planck constant over 2π.
     */
    public final static Measure<Real, ?> ℏ = ℎ.divide(π.times(2));

    /**
     * Holds the elementary charge (positron charge).
     * @see <a href="http://en.wikipedia.org/wiki/Elementary_charge">
     *      Wikipedia: Elementary Charge</a>
     */
    public final static Measure<Real, ElectricCharge> e =
            Measure.valueOf(Real.valueOf(160217653, -27, 14), COULOMB);

    /**
     * Holds the permeability of vacuum or magnetic constant
     * (4π×10−7 N/A²).
     * @see <a href="http://en.wikipedia.org/wiki/Permeability_%28electromagnetism%29">
     *      Wikipedia: Permeability (electromagnetism)</a>
     */
    public final static Measure<Real, ?> µ0 =
            π.times(4).times(Measure.valueOf(Real.valueOf(10, -7),
            NEWTON.divide(AMPERE.pow(2))));

    /**
     * Holds the permittivity of vacuum or electric constant (1/(µ0·c²))
     * @see <a href="http://en.wikipedia.org/wiki/Permittivity">
     *      Wikipedia: Permittivity</a>
     */
    public final static Measure<Real, ?> ε0 = µ0.times(c.pow(2)).inverse();

    /**
     * Holds the characteristic impedance of vacuum (µ0·c).
     */
    public final static Measure<Real, ElectricResistance> Z0 =
            µ0.times(c).asType(ElectricResistance.class);

    /**
     * Holds the fine structure constant (e²/(2·ε0·c·h))
     * @see <a href="http://en.wikipedia.org/wiki/Fine_structure_constant">
     *      Wikipedia: Fine Structure Constant</a>
     */
    public final static Measure<Real, Dimensionless> α =
            e.pow(2).divide(ε0.times(c).times(ℎ).times(2)).asType(Dimensionless.class);

    /**
     * Holds the Newtonian constant of gravitation.
     * @see <a href="http://en.wikipedia.org/wiki/Gravitational_constant">
     *      Wikipedia: Gravitational Constant</a>
     */
    public final static Measure<Real, ?> G =
            Measure.valueOf(Real.valueOf(66742, -15, 10),
            METRE.pow(3).divide(KILOGRAM).divide(SECOND.pow(2)));

    /**
     * Holds the Avogadro constant.
     * @see <a href="http://en.wikipedia.org/wiki/Avogadro%27s_number">
     *      Wikipedia: Avogadro's number</a>
     */
    public final static Measure<Real, ?> N =
            Measure.valueOf(Real.valueOf(60221415, 16, 10), Unit.ONE.divide(MOLE));

    /**
     * Holds the molar gas constant (N·k)
     * @see <a href="http://en.wikipedia.org/wiki/Gas_constant">
     *      Wikipedia: Gas constant</a>
     */
    public final static Measure<Real, ?> R = N.times(k);

    /**
     * Holds the Faraday constant (N·e)
     * @see <a href="http://en.wikipedia.org/wiki/Faraday_constant">
     *      Wikipedia: Faraday constant</a>
     */
    public final static Measure<Real, ?> F = N.times(e);

    /**
     * Holds the Stefan-Boltzmann constant ((π²/60)·k<sup>4</sup>/(ℏ³·c²))
     */
    public final static Measure<Real, ?> σ =
            π.pow(2).divide(60).times(k.pow(4)).divide(ℏ.pow(3).times(c.pow(2)));

    /**
     * Holds the unified atomic mass unit (0.001 kg/mol)/N
     */
    public final static Measure<Real, Mass> amu =
            Measure.valueOf(Real.valueOf(1, -3), KILOGRAM.divide(MOLE)).divide(N).asType(Mass.class);

    /**
     * Holds the Rydberg constant (α²·me·c/2h).
     * @see <a href="http://en.wikipedia.org/wiki/Rydberg_constant">
     *      Wikipedia: Rydberg constant</a>
     */
    public final static Measure<Real, ?> Rinf = // Do not use formula as experimental incertainty is very low.
            Measure.valueOf(Real.valueOf(10973731568525l, -6, 73), METRE.inverse());

    /**
     * Holds the Bohr radius (α/(4π·Rinf))
     */
    public final static Measure<Real, Length> a0 =
            α.divide(π.times(Rinf).times(4)).asType(Length.class);

    /**
     * Holds the Hartree energy (2Rinf·h·c)
     */
    public final static Measure<Real, ?> Eh = Rinf.times(ℎ).times(c).times(2);

    /**
     * Holds the magnetic flux quantum (h/2e)
     */
    public final static Measure<Real, MagneticFlux> Φ0 =
            ℎ.divide(e).divide(2).asType(MagneticFlux.class);

    /**
     * Holds the conductance quantum (2e²/h)
     */
    public final static Measure<Real, ElectricConductance> G0 = e.pow(2).divide(ℎ).times(2).asType(ElectricConductance.class);

    /**
     * Holds the Bohr magneton (ℏ·e/2me)
     */
    public final static Measure<Real, ?> µB = e.times(ℏ).divide(me).divide(2);

    /**
     * Holds the nuclear magneton (ℏ·e/2mp)
     */
    public final static Measure<Real, ?> µN = e.times(ℏ).divide(mp).divide(2);

    // Holds mP<sup>1/2</sup>
    private final static Measure<Real, ?> mP_square =
            ℏ.times(c).divide(G);

    /**
     * Holds the Planck mass (ℏ·c/G)<sup>1/2</sup>
     */
    public final static Measure<Real, Mass> mP =
            Measure.valueOf(mP_square.getValue().sqrt(),
            mP_square.getUnit().root(2)).asType(Mass.class);

    /**
     * Holds the Planck length (ℏ/(mP·c))
     */
    public final static Measure<Real, Length> lP =
            ℏ.divide(mP.times(c)).asType(Length.class);

    /**
     * Holds the Planck time (lP/c)
     */
    public final static Measure<Real, Time> tP =
            lP.divide(c).asType(Time.class);

    static {
        ImmortalContext.exit();
    }

    // Exits immortal memory context.
    ////////////////////////////////////////////////////////////////////////////

 }
