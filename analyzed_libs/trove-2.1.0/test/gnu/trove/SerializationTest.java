///////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2001-2006, Eric D. Friedman All Rights Reserved.
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public
// License along with this program; if not, write to the Free Software
// Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
///////////////////////////////////////////////////////////////////////////////
package gnu.trove;

import junit.framework.TestCase;

import java.io.*;
import java.util.Map;
import java.util.Set;

import gnu.trove.decorator.*;


/**
 *
 */
public class SerializationTest extends TestCase {
    // If non-null, we'll save the serialized objects so we can tets migration 
    private static File serialized_object_output_dir = null;
    static {
        if ( System.getProperty( "trove.test.serialization_output_dir" ) != null ) {
            serialized_object_output_dir =
                new File( System.getProperty( "trove.test.serialization_output_dir" ) );

            if ( !serialized_object_output_dir.exists() &&
                !serialized_object_output_dir.mkdirs() ) {

                System.err.println( "Unable to access or create serialization test " +
                    "output dir (" + serialized_object_output_dir + ") " );
                serialized_object_output_dir = null;
            }

            System.out.println( "Serialized objects will be written to " +
                serialized_object_output_dir );
        }
    }

    
    public SerializationTest( String name ) {
        super( name );
    }

    public void testP2PMap() {
        // Long-long
        TLongLongHashMap llmap = new TLongLongHashMap();
        Map<Long,Long> llmap_dec = Decorators.wrap( llmap );
        assertTrue( serializesCorrectly( llmap, "p2p-ll-1" ) );
        assertTrue( serializesCorrectly( llmap_dec, "p2p-ll-1d" ) );
        llmap.put( 0, 1 );
        assertTrue( serializesCorrectly( llmap, "p2p-ll-2" ) );
        assertTrue( serializesCorrectly( llmap_dec, "p2p-ll-2d" ) );
        llmap.put( Long.MIN_VALUE, Long.MIN_VALUE );
        assertTrue( serializesCorrectly( llmap, "p2p-ll-3" ) );
        assertTrue( serializesCorrectly( llmap_dec, "p2p-ll-3d" ) );
        llmap.put( Long.MAX_VALUE, Long.MAX_VALUE );
        assertTrue( serializesCorrectly( llmap, "p2p-ll-4" ) );
        assertTrue( serializesCorrectly( llmap_dec, "p2p-ll-4d" ) );

        // Int-int
        TIntIntHashMap iimap = new TIntIntHashMap();
        Map<Integer,Integer> iimap_dec = Decorators.wrap( iimap );
        assertTrue( serializesCorrectly( iimap, "p2p-ii-1" ) );
        assertTrue( serializesCorrectly( iimap_dec, "p2p-ii-1d" ) );
        iimap.put( 0, 1 );
        assertTrue( serializesCorrectly( iimap, "p2p-ii-2" ) );
        assertTrue( serializesCorrectly( iimap_dec, "p2p-ii-2d" ) );
        iimap.put( Integer.MIN_VALUE, Integer.MIN_VALUE );
        assertTrue( serializesCorrectly( iimap, "p2p-ii-3" ) );
        assertTrue( serializesCorrectly( iimap_dec, "p2p-ii-3d" ) );
        iimap.put( Integer.MAX_VALUE, Integer.MAX_VALUE );
        assertTrue( serializesCorrectly( iimap, "p2p-ii-4" ) );
        assertTrue( serializesCorrectly( iimap_dec, "p2p-ii-4d" ) );

        // Double-double
        TDoubleDoubleHashMap ddmap = new TDoubleDoubleHashMap();
        Map<Double,Double> ddmap_dec = Decorators.wrap( ddmap );
        assertTrue( serializesCorrectly( ddmap, "p2p-dd-1" ) );
        assertTrue( serializesCorrectly( ddmap_dec, "p2p-dd-1d" ) );
        ddmap.put( 0, 1 );
        assertTrue( serializesCorrectly( ddmap, "p2p-dd-2" ) );
        assertTrue( serializesCorrectly( ddmap_dec, "p2p-dd-2d" ) );
        ddmap.put( Double.MIN_VALUE, Double.MIN_VALUE );
        assertTrue( serializesCorrectly( ddmap, "p2p-dd-3" ) );
        assertTrue( serializesCorrectly( ddmap_dec, "p2p-dd-3d" ) );
        ddmap.put( Double.MAX_VALUE, Double.MAX_VALUE );
        assertTrue( serializesCorrectly( ddmap, "p2p-dd-4" ) );
        assertTrue( serializesCorrectly( ddmap_dec, "p2p-dd-4d" ) );
        ddmap.put( Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY );
        assertTrue( serializesCorrectly( ddmap, "p2p-dd-5" ) );
        assertTrue( serializesCorrectly( ddmap_dec, "p2p-dd-5d" ) );
        ddmap.put( Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY );
        assertTrue( serializesCorrectly( ddmap, "p2p-dd-6" ) );
        assertTrue( serializesCorrectly( ddmap_dec, "p2p-dd-6d" ) );
        // NOTE: trove doesn't deal well with NaN
//        ddmap.put( Double.NaN, Double.NaN );
//        assertTrue( serializesCorrectly( ddmap ) );

        // Float-float
        TFloatFloatHashMap ffmap = new TFloatFloatHashMap();
        Map<Float,Float> ffmap_dec = Decorators.wrap( ffmap );
        assertTrue( serializesCorrectly( ffmap, "p2p-ff-1" ) );
        assertTrue( serializesCorrectly( ffmap_dec, "p2p-ff-1d" ) );
        ffmap.put( 0, 1 );
        assertTrue( serializesCorrectly( ffmap, "p2p-ff-2" ) );
        assertTrue( serializesCorrectly( ffmap_dec, "p2p-ff-2d" ) );
        ffmap.put( Float.MIN_VALUE, Float.MIN_VALUE );
        assertTrue( serializesCorrectly( ffmap, "p2p-ff-3" ) );
        assertTrue( serializesCorrectly( ffmap_dec, "p2p-ff-3d" ) );
        ffmap.put( Float.MAX_VALUE, Float.MAX_VALUE );
        assertTrue( serializesCorrectly( ffmap, "p2p-ff-4" ) );
        assertTrue( serializesCorrectly( ffmap_dec, "p2p-ff-4d" ) );
        ffmap.put( Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY );
        assertTrue( serializesCorrectly( ffmap, "p2p-ff-5" ) );
        assertTrue( serializesCorrectly( ffmap_dec, "p2p-ff-5d" ) );
        ffmap.put( Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY );
        assertTrue( serializesCorrectly( ffmap, "p2p-ff-6" ) );
        assertTrue( serializesCorrectly( ffmap_dec, "p2p-ff-6d" ) );
        // NOTE: trove doesn't deal well with NaN
//        ffmap.put( Float.NaN, Float.NaN );
//        assertTrue( serializesCorrectly( ffmap ) );
    }


    public void testP2OMap() {
        // Long-long
        TLongObjectHashMap<Long> lomap = new TLongObjectHashMap<Long>();
        Map<Long,Long> lomap_dec = Decorators.wrap( lomap );
        assertTrue( serializesCorrectly( lomap, "p2o-l-1" ) );
        assertTrue( serializesCorrectly( lomap_dec, "p2o-l-1d" ) );
        lomap.put( 0, Long.valueOf( 1 ) );
        assertTrue( serializesCorrectly( lomap, "p2o-l-2" ) );
        assertTrue( serializesCorrectly( lomap_dec, "p2o-l-2d" ) );
        lomap.put( Long.MIN_VALUE, Long.valueOf( Long.MIN_VALUE ) );
        assertTrue( serializesCorrectly( lomap, "p2o-l-3" ) );
        assertTrue( serializesCorrectly( lomap_dec, "p2o-l-3d" ) );
        lomap.put( Long.MAX_VALUE, Long.valueOf( Long.MAX_VALUE ) );
        assertTrue( serializesCorrectly( lomap, "p2o-l-4" ) );
        assertTrue( serializesCorrectly( lomap_dec, "p2o-l-4d" ) );

        // Int-int
        TIntObjectHashMap<Integer> iomap = new TIntObjectHashMap<Integer>();
        Map<Integer,Integer> iomap_dec = Decorators.wrap( iomap );
        assertTrue( serializesCorrectly( iomap, "p2o-i-1" ) );
        assertTrue( serializesCorrectly( iomap_dec, "p2o-i-1d" ) );
        iomap.put( 0, Integer.valueOf( 1 ) );
        assertTrue( serializesCorrectly( iomap, "p2o-i-2" ) );
        assertTrue( serializesCorrectly( iomap_dec, "p2o-i-2d" ) );
        iomap.put( Integer.MIN_VALUE, Integer.valueOf( Integer.MIN_VALUE ) );
        assertTrue( serializesCorrectly( iomap, "p2o-i-3" ) );
        assertTrue( serializesCorrectly( iomap_dec, "p2o-i-3d" ) );
        iomap.put( Integer.MAX_VALUE, Integer.valueOf( Integer.MAX_VALUE ) );
        assertTrue( serializesCorrectly( iomap, "p2o-i-4" ) );
        assertTrue( serializesCorrectly( iomap_dec, "p2o-i-4d" ) );

        // Double-double
        TDoubleObjectHashMap<Double> domap = new TDoubleObjectHashMap<Double>();
        Map<Double,Double> domap_dec = Decorators.wrap( domap );
        assertTrue( serializesCorrectly( domap, "p2o-d-1" ) );
        assertTrue( serializesCorrectly( domap_dec, "p2o-d-1d" ) );
        domap.put( 0, Double.valueOf( 1 ) );
        assertTrue( serializesCorrectly( domap, "p2o-d-2" ) );
        assertTrue( serializesCorrectly( domap_dec, "p2o-d-2d" ) );
        domap.put( Double.MIN_VALUE, Double.valueOf( Double.MIN_VALUE ) );
        assertTrue( serializesCorrectly( domap, "p2o-d-3" ) );
        assertTrue( serializesCorrectly( domap_dec, "p2o-d-3d" ) );
        domap.put( Double.MAX_VALUE, Double.valueOf( Double.MAX_VALUE ) );
        assertTrue( serializesCorrectly( domap, "p2o-d-4" ) );
        assertTrue( serializesCorrectly( domap_dec, "p2o-d-4d" ) );
        domap.put( Double.POSITIVE_INFINITY, Double.valueOf( Double.POSITIVE_INFINITY ) );
        assertTrue( serializesCorrectly( domap, "p2o-d-5" ) );
        assertTrue( serializesCorrectly( domap_dec, "p2o-d-5d" ) );
        domap.put( Double.NEGATIVE_INFINITY, Double.valueOf( Double.NEGATIVE_INFINITY ) );
        assertTrue( serializesCorrectly( domap, "p2o-d-6" ) );
        assertTrue( serializesCorrectly( domap_dec, "p2o-d-6d" ) );
        // NOTE: trove doesn't deal well with NaN
//        ddmap.put( Double.NaN, Double.NaN );
//        assertTrue( serializesCorrectly( ddmap ) );

        // Float-float
        TFloatObjectHashMap<Float> fomap = new TFloatObjectHashMap<Float>();
        Map<Float,Float> fomap_dec = Decorators.wrap( fomap );
        assertTrue( serializesCorrectly( fomap, "p2o-f-1" ) );
        assertTrue( serializesCorrectly( fomap_dec, "p2o-f-1d" ) );
        fomap.put( 0, Float.valueOf( 1 ) );
        assertTrue( serializesCorrectly( fomap, "p2o-f-2" ) );
        assertTrue( serializesCorrectly( fomap_dec, "p2o-f-2d" ) );
        fomap.put( Float.MIN_VALUE, Float.valueOf( Float.MIN_VALUE ) );
        assertTrue( serializesCorrectly( fomap, "p2o-f-3" ) );
        assertTrue( serializesCorrectly( fomap_dec, "p2o-f-3d" ) );
        fomap.put( Float.MAX_VALUE, Float.valueOf( Float.MAX_VALUE ) );
        assertTrue( serializesCorrectly( fomap, "p2o-f-4" ) );
        assertTrue( serializesCorrectly( fomap_dec, "p2o-f-4d" ) );
        fomap.put( Float.POSITIVE_INFINITY, Float.valueOf( Float.POSITIVE_INFINITY ) );
        assertTrue( serializesCorrectly( fomap, "p2o-f-5" ) );
        assertTrue( serializesCorrectly( fomap_dec, "p2o-f-5d" ) );
        fomap.put( Float.NEGATIVE_INFINITY, Float.valueOf( Float.NEGATIVE_INFINITY ) );
        assertTrue( serializesCorrectly( fomap, "p2o-f-6" ) );
        assertTrue( serializesCorrectly( fomap_dec, "p2o-f-6d" ) );
        // NOTE: trove doesn't deal well with NaN
//        ffmap.put( Float.NaN, Float.NaN );
//        assertTrue( serializesCorrectly( ffmap ) );
    }

    public void testO2PMap() {
        // Long-long
        TObjectLongHashMap<Long> olmap = new TObjectLongHashMap<Long>();
        Map<Long,Long> olmap_dec = Decorators.wrap( olmap );
        assertTrue( serializesCorrectly( olmap, "o2p-l-1" ) );
        assertTrue( serializesCorrectly( olmap_dec, "o2p-l-1d" ) );
        olmap.put( Long.valueOf( 0 ), 1 );
        assertTrue( serializesCorrectly( olmap, "o2p-l-2" ) );
        assertTrue( serializesCorrectly( olmap_dec, "o2p-l-2d" ) );
        olmap.put( Long.valueOf( Long.MIN_VALUE ), Long.MIN_VALUE );
        assertTrue( serializesCorrectly( olmap, "o2p-l-3" ) );
        assertTrue( serializesCorrectly( olmap_dec, "o2p-l-3d" ) );
        olmap.put( Long.valueOf( Long.MAX_VALUE ), Long.MAX_VALUE );
        assertTrue( serializesCorrectly( olmap, "o2p-l-4" ) );
        assertTrue( serializesCorrectly( olmap_dec, "o2p-l-4d" ) );

        // Int-int
        TObjectIntHashMap<Integer> oimap = new TObjectIntHashMap<Integer>();
        Map<Integer,Integer> oimap_dec = Decorators.wrap( oimap );
        assertTrue( serializesCorrectly( oimap, "o2p-i-1" ) );
        assertTrue( serializesCorrectly( oimap_dec, "o2p-i-1d" ) );
        oimap.put( Integer.valueOf( 0 ), 1 );
        assertTrue( serializesCorrectly( oimap, "o2p-i-2" ) );
        assertTrue( serializesCorrectly( oimap_dec, "o2p-i-2d" ) );
        oimap.put( Integer.valueOf( Integer.MIN_VALUE ), Integer.MIN_VALUE );
        assertTrue( serializesCorrectly( oimap, "o2p-i-3" ) );
        assertTrue( serializesCorrectly( oimap_dec, "o2p-i-3d" ) );
        oimap.put( Integer.valueOf( Integer.MAX_VALUE ), Integer.MAX_VALUE );
        assertTrue( serializesCorrectly( oimap, "o2p-i-4" ) );
        assertTrue( serializesCorrectly( oimap_dec, "o2p-i-4d" ) );

        // Double-double
        TObjectDoubleHashMap<Double> odmap = new TObjectDoubleHashMap<Double>();
        Map<Double,Double> odmap_dec = Decorators.wrap( odmap );
        assertTrue( serializesCorrectly( odmap, "o2p-d-1" ) );
        assertTrue( serializesCorrectly( odmap_dec, "o2p-d-1d" ) );
        odmap.put( Double.valueOf( 0 ), 1 );
        assertTrue( serializesCorrectly( odmap, "o2p-d-2" ) );
        assertTrue( serializesCorrectly( odmap_dec, "o2p-d-2d" ) );
        odmap.put( Double.valueOf( Double.MIN_VALUE ), Double.MIN_VALUE );
        assertTrue( serializesCorrectly( odmap, "o2p-d-3" ) );
        assertTrue( serializesCorrectly( odmap_dec, "o2p-d-3d" ) );
        odmap.put( Double.valueOf( Double.MAX_VALUE ), Double.MAX_VALUE );
        assertTrue( serializesCorrectly( odmap, "o2p-d-4" ) );
        assertTrue( serializesCorrectly( odmap_dec, "o2p-d-4d" ) );
        odmap.put( Double.valueOf( Double.POSITIVE_INFINITY ), Double.POSITIVE_INFINITY );
        assertTrue( serializesCorrectly( odmap, "o2p-d-5" ) );
        assertTrue( serializesCorrectly( odmap_dec, "o2p-d-5d" ) );
        odmap.put( Double.valueOf( Double.NEGATIVE_INFINITY ), Double.NEGATIVE_INFINITY );
        assertTrue( serializesCorrectly( odmap, "o2p-d-6" ) );
        assertTrue( serializesCorrectly( odmap_dec, "o2p-d-6d" ) );
        // NOTE: trove doesn't deal well with NaN
//        ddmap.put( Double.NaN, Double.NaN );
//        assertTrue( serializesCorrectly( ddmap ) );

        // Float-float
        TObjectFloatHashMap<Float> ofmap = new TObjectFloatHashMap<Float>();
        Map<Float,Float> ofmap_dec = Decorators.wrap( ofmap );
        assertTrue( serializesCorrectly( ofmap, "o2p-f-1" ) );
        assertTrue( serializesCorrectly( ofmap_dec, "o2p-f-1d" ) );
        ofmap.put( Float.valueOf( 0 ), 1 );
        assertTrue( serializesCorrectly( ofmap, "o2p-f-2" ) );
        assertTrue( serializesCorrectly( ofmap_dec, "o2p-f-2d" ) );
        ofmap.put( Float.valueOf( Float.MIN_VALUE ), Float.MIN_VALUE );
        assertTrue( serializesCorrectly( ofmap, "o2p-f-3" ) );
        assertTrue( serializesCorrectly( ofmap_dec, "o2p-f-3d" ) );
        ofmap.put( Float.valueOf( Float.MAX_VALUE ), Float.MAX_VALUE );
        assertTrue( serializesCorrectly( ofmap, "o2p-f-4" ) );
        assertTrue( serializesCorrectly( ofmap_dec, "o2p-f-4d" ) );
        ofmap.put( Float.valueOf( Float.POSITIVE_INFINITY ), Float.POSITIVE_INFINITY );
        assertTrue( serializesCorrectly( ofmap, "o2p-f-5" ) );
        assertTrue( serializesCorrectly( ofmap_dec, "o2p-f-5d" ) );
        ofmap.put( Float.valueOf( Float.NEGATIVE_INFINITY ), Float.NEGATIVE_INFINITY );
        assertTrue( serializesCorrectly( ofmap, "o2p-f-6" ) );
        assertTrue( serializesCorrectly( ofmap_dec, "o2p-f-6d" ) );
        // NOTE: trove doesn't deal well with NaN
//        ffmap.put( Float.NaN, Float.NaN );
//        assertTrue( serializesCorrectly( ffmap ) );
    }


    public void testList() {
        // Long-long
        TLongArrayList llist = new TLongArrayList();
        assertTrue( serializesCorrectly( llist, "list-l-1" ) );
        llist.add( 0 );
        llist.add( 1 );
        assertTrue( serializesCorrectly( llist, "list-l-2" ) );
        llist.add( Long.MIN_VALUE );
        assertTrue( serializesCorrectly( llist, "list-l-3" ) );
        llist.add( Long.MAX_VALUE );
        assertTrue( serializesCorrectly( llist, "list-l-4" ) );

        // Int-int
        TIntArrayList ilist = new TIntArrayList();
        assertTrue( serializesCorrectly( ilist, "list-i-1" ) );
        ilist.add( 0 );
        ilist.add( 1 );
        assertTrue( serializesCorrectly( ilist, "list-i-2" ) );
        ilist.add( Integer.MIN_VALUE );
        assertTrue( serializesCorrectly( ilist, "list-i-3" ) );
        ilist.add( Integer.MAX_VALUE );
        assertTrue( serializesCorrectly( ilist, "list-i-4" ) );

        // Double-double
        TDoubleArrayList dlist = new TDoubleArrayList();
        assertTrue( serializesCorrectly( dlist, "list-d-1" ) );
        dlist.add( 0 );
        dlist.add( 1 );
        assertTrue( serializesCorrectly( dlist, "list-d-2" ) );
        dlist.add( Double.MIN_VALUE );
        assertTrue( serializesCorrectly( dlist, "list-d-3" ) );
        dlist.add( Double.MAX_VALUE );
        assertTrue( serializesCorrectly( dlist, "list-d-4" ) );
        // NOTE: trove doesn't deal well with NaN
//        ddmap.add( Double.NaN, Double.NaN );
//        assertTrue( serializesCorrectly( ddmap ) );
        dlist.add( Double.POSITIVE_INFINITY );
        assertTrue( serializesCorrectly( dlist, "list-d-5" ) );
        dlist.add( Double.NEGATIVE_INFINITY );
        assertTrue( serializesCorrectly( dlist, "list-d-6" ) );

        // Float-float
        TFloatArrayList flist = new TFloatArrayList();
        assertTrue( serializesCorrectly( flist, "list-f-1" ) );
        flist.add( 0 );
        flist.add( 1 );
        assertTrue( serializesCorrectly( flist, "list-f-2" ) );
        flist.add( Float.MIN_VALUE );
        assertTrue( serializesCorrectly( flist, "list-f-3" ) );
        flist.add( Float.MAX_VALUE );
        assertTrue( serializesCorrectly( flist, "list-f-4" ) );
        flist.add( Float.POSITIVE_INFINITY );
        assertTrue( serializesCorrectly( flist, "list-f-5" ) );
        flist.add( Float.NEGATIVE_INFINITY );
        assertTrue( serializesCorrectly( flist, "list-f-6" ) );
        // NOTE: trove doesn't deal well with NaN
//        ffmap.add( Float.NaN );
//        assertTrue( serializesCorrectly( ffmap ) );
    }


    public void testSet() {
        // Long-long
        TLongHashSet llist = new TLongHashSet();
        Set<Long> llist_dec = Decorators.wrap( llist );
        assertTrue( serializesCorrectly( llist, "set-l-1" ) );
        assertTrue( serializesCorrectly( llist_dec, "set-l-1d" ) );
        llist.add( 0 );
        llist.add( 1 );
        assertTrue( serializesCorrectly( llist, "set-l-2" ) );
        assertTrue( serializesCorrectly( llist_dec, "set-l-2d" ) );
        llist.add( Long.MIN_VALUE );
        assertTrue( serializesCorrectly( llist, "set-l-3" ) );
        assertTrue( serializesCorrectly( llist_dec, "set-l-3d" ) );
        llist.add( Long.MAX_VALUE );
        assertTrue( serializesCorrectly( llist, "set-l-4" ) );
        assertTrue( serializesCorrectly( llist_dec, "set-l-4d" ) );

        // Int-int
        TIntHashSet ilist = new TIntHashSet();
        Set<Integer> ilist_dec = Decorators.wrap( ilist );
        assertTrue( serializesCorrectly( ilist, "set-i-1" ) );
        assertTrue( serializesCorrectly( ilist_dec, "set-i-1d" ) );
        ilist.add( 0 );
        ilist.add( 1 );
        assertTrue( serializesCorrectly( ilist, "set-i-2" ) );
        assertTrue( serializesCorrectly( ilist_dec, "set-i-2d" ) );
        ilist.add( Integer.MIN_VALUE );
        assertTrue( serializesCorrectly( ilist, "set-i-3" ) );
        assertTrue( serializesCorrectly( ilist_dec, "set-i-3d" ) );
        ilist.add( Integer.MAX_VALUE );
        assertTrue( serializesCorrectly( ilist, "set-i-4" ) );
        assertTrue( serializesCorrectly( ilist_dec, "set-i-4d" ) );

        // Double-double
        TDoubleHashSet dlist = new TDoubleHashSet();
        Set<Double> dlist_dec = Decorators.wrap( dlist );
        assertTrue( serializesCorrectly( dlist, "set-d-1" ) );
        assertTrue( serializesCorrectly( dlist_dec, "set-d-1d" ) );
        dlist.add( 0 );
        dlist.add( 1 );
        assertTrue( serializesCorrectly( dlist, "set-d-2" ) );
        assertTrue( serializesCorrectly( dlist_dec, "set-d-2d" ) );
        dlist.add( Double.MIN_VALUE );
        assertTrue( serializesCorrectly( dlist, "set-d-3" ) );
        assertTrue( serializesCorrectly( dlist_dec, "set-d-3d" ) );
        dlist.add( Double.MAX_VALUE );
        assertTrue( serializesCorrectly( dlist, "set-d-4" ) );
        assertTrue( serializesCorrectly( dlist_dec, "set-d-4d" ) );
        dlist.add( Double.POSITIVE_INFINITY );
        assertTrue( serializesCorrectly( dlist, "set-d-5" ) );
        assertTrue( serializesCorrectly( dlist_dec, "set-d-5d" ) );
        dlist.add( Double.NEGATIVE_INFINITY );
        assertTrue( serializesCorrectly( dlist, "set-d-6" ) );
        assertTrue( serializesCorrectly( dlist_dec, "set-d-6d" ) );
        // NOTE: trove doesn't deal well with NaN
//        ddmap.add( Double.NaN, Double.NaN );
//        assertTrue( serializesCorrectly( ddmap ) );

        // Float-float
        TFloatHashSet flist = new TFloatHashSet();
        Set<Float> flist_dec = Decorators.wrap( flist );
        assertTrue( serializesCorrectly( flist, "set-f-1" ) );
        assertTrue( serializesCorrectly( flist_dec, "set-f-1d" ) );
        flist.add( 0 );
        flist.add( 1 );
        assertTrue( serializesCorrectly( flist, "set-f-2" ) );
        assertTrue( serializesCorrectly( flist_dec, "set-f-2d" ) );
        flist.add( Float.MIN_VALUE );
        assertTrue( serializesCorrectly( flist, "set-f-3" ) );
        assertTrue( serializesCorrectly( flist_dec, "set-f-3d" ) );
        flist.add( Float.MAX_VALUE );
        assertTrue( serializesCorrectly( flist, "set-f-4" ) );
        assertTrue( serializesCorrectly( flist_dec, "set-f-4d" ) );
        flist.add( Float.POSITIVE_INFINITY );
        assertTrue( serializesCorrectly( flist, "set-f-5" ) );
        assertTrue( serializesCorrectly( flist_dec, "set-f-5d" ) );
        flist.add( Float.NEGATIVE_INFINITY );
        assertTrue( serializesCorrectly( flist, "set-f-6" ) );
        assertTrue( serializesCorrectly( flist_dec, "set-f-6d" ) );
        // NOTE: trove doesn't deal well with NaN
//        ffmap.add( Float.NaN );
//        assertTrue( serializesCorrectly( ffmap ) );
    }


    public void testLinkedList() {
        TLinkedList<LinkedNode> list = new TLinkedList<LinkedNode>();
        list.add( new LinkedNode( 0 ) );
        list.add( new LinkedNode( 1 ) );
        list.add( new LinkedNode( 2 ) );
        list.add( new LinkedNode( 3 ) );

        assertTrue( serializesCorrectly( list, "linkedlist-1" ) );
    }


    // See Tracker #1955103 - Hashing Strategy Not Retained After Serialization
    public void testHashingStrategy() {
        THashMap<String,String> m = new THashMap<String,String>(
            new CaseInsensitiveHashingStrategy() );

        m.put("hello", "world");

        assertTrue( m.containsKey( "hello" ) );
        assertTrue( m.containsKey( "Hello" ) );

        try {
            ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream( bytesOut );
            out.writeObject( m );
            ObjectInputStream in = new ObjectInputStream(
                new ByteArrayInputStream( bytesOut.toByteArray() ) );
            //noinspection unchecked
            m = ( THashMap<String,String> ) in.readObject();
        }
        catch( Exception ex ) {
            ex.printStackTrace();
            fail( ex.toString() );
        }

        assertTrue( m.containsKey( "hello" ) );
        assertTrue( m.containsKey( "Hello" ) );
    }


    /**
     * @param test_name     Name used to refer to the serialized instance. If a test
     *                      output dir is specified (see constructor), a file will be
     *                      created with this name.
     */
    private boolean serializesCorrectly( Object obj, String test_name ) {
        assert obj instanceof Externalizable : obj + " is not Externalizable";

        ObjectOutputStream oout = null;
        ObjectInputStream oin = null;
        try {
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            oout = new ObjectOutputStream( bout );

            oout.writeObject( obj );
            oout.close();

            ByteArrayInputStream bin = new ByteArrayInputStream( bout.toByteArray() );
            oin = new ObjectInputStream( bin );

            Object new_obj = oin.readObject();

            if ( !obj.equals( new_obj ) ) {
                System.err.println( "Non-matching serialized objects (in-VM test):" );
                System.err.println( "  Original: " + obj );
                System.err.println( "  Deserialized: " + new_obj );
                return false;
            }

            if ( obj instanceof THash ) {
                assertEquals( ( ( THash ) obj ).getAutoCompactionFactor(),
                    ( ( THash ) new_obj ).getAutoCompactionFactor(), 0.0 );
            }

            oin.close();

            if ( serialized_object_output_dir != null ) {
                try {
                    File file = new File( serialized_object_output_dir,
                        test_name + ".obj" );
                    ObjectOutputStream test_out = new ObjectOutputStream(
                        new FileOutputStream( file ) );

                    test_out.writeObject( obj );

                    test_out.close();
                }
                catch( IOException ex ) {
                    ex.printStackTrace();
                    fail( "Unable to write to test file: " + test_name );
                }
            }

            // Look for previous versions
            int version = 0;
            for( ; ; version++ ) {
                InputStream stream = SerializationTest.class.getResourceAsStream(
                    "old_serialized_versions/" + version + "/" + test_name + ".obj" );
                if ( stream == null ) break;

                System.out.println( "Testing " + test_name + " against version " +
                    version + "..." );

                oin = new ObjectInputStream( stream );

                new_obj = oin.readObject();

                if ( !obj.equals( new_obj ) ) {
                    System.err.println( "Non-matching serialized objects (version " +
                        version + "):" );
                    System.err.println( "  Original: " + obj );
                    System.err.println( "  Deserialized: " + new_obj );
                    return false;
                }
            }

            return true;
        }
        catch( Exception ex ) {
            ex.printStackTrace();
            return false;
        }
        finally {
            if ( oout != null ) {
                try {
                    oout.close();
                }
                catch( IOException ex ) {
                    // ignore
                }
            }
            if ( oin != null ) {
                try {
                    oin.close();
                }
                catch( IOException ex ) {
                    // ignore
                }
            }
        }
    }


    private static class LinkedNode extends TLinkableAdapter {
        private final int value;

        LinkedNode( int value ) {
            this.value = value;
        }

        public boolean equals( Object o ) {
            if ( this == o ) return true;
            if ( o == null || getClass() != o.getClass() ) return false;

            LinkedNode that = ( LinkedNode ) o;

            if ( value != that.value ) return false;

            return true;
        }

        public int hashCode() {
            return value;
        }
    }

    static class CaseInsensitiveHashingStrategy
        implements TObjectHashingStrategy<String> {
        
        public int computeHashCode(String s) {
            return s.toLowerCase().hashCode();
        }

        public boolean equals(String a, String b) {
            return a.equalsIgnoreCase(b);
        }
    }
}
