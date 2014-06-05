///////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2007-2008, Rob Eden All Rights Reserved.
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

import java.util.Arrays;
import java.util.List;


/**
 *
 */
public class P2OHashMapTest extends TestCase {
    public P2OHashMapTest( String name ) {
        super( name );
    }


    public void testGetValues() {
        TIntObjectHashMap<String> map = new TIntObjectHashMap<String>();

        map.put( 1, "one" );
        map.put( 2, "two" );
        map.put( 3, "three" );
        map.put( 4, "four" );

        // Exact size
        String[] template = new String[ map.size() ];
        String[] values = map.getValues( template );
        assertSame( template, values );

        List<String> list = Arrays.asList( values );
        assertTrue( list.contains( "one" ) );
        assertTrue( list.contains( "two" ) );
        assertTrue( list.contains( "three" ) );
        assertTrue( list.contains( "four" ) );

        // Zero length
        template = new String[ 0 ];
        values = map.getValues( template );
        assertNotSame( template, values );

        list = Arrays.asList( values );
        assertTrue( list.contains( "one" ) );
        assertTrue( list.contains( "two" ) );
        assertTrue( list.contains( "three" ) );
        assertTrue( list.contains( "four" ) );

        // Longer than needed
        template = new String[ 10 ];
        values = map.getValues( template );
        assertSame( template, values );

        list = Arrays.asList( values );
        assertTrue( list.contains( "one" ) );
        assertTrue( list.contains( "two" ) );
        assertTrue( list.contains( "three" ) );
        assertTrue( list.contains( "four" ) );
    }


    public void testPutIfAbsent() {
        TIntObjectHashMap<String> map = new TIntObjectHashMap<String>();

        map.put( 1, "One" );
        map.put( 2, "Two" );
        map.put( 3, "Three" );

        assertEquals( "One", map.putIfAbsent( 1, "Two" ) );
        assertEquals( "One", map.get( 1 ) );
        assertNull( map.putIfAbsent( 9, "Nine" ) );
        assertEquals( "Nine", map.get( 9 ) );
    }


    public void testBug2037709() {
        TIntObjectHashMap<String> m = new TIntObjectHashMap<String>();
        for (int i = 0; i < 10; i++) {
            m.put(i, String.valueOf(i));
        }

        int sz = m.size();
        assertEquals(10, sz);

        int[] keys = new int[sz];
        m.keys(keys);

        boolean[] seen = new boolean[sz];
        Arrays.fill( seen, false );
        for (int i = 0; i < 10; i++) {
            seen[ keys[ i ] ] = true;
        }

        for (int i = 0; i < 10; i++) {
            if (!seen[ i ]) {
                TestCase.fail("Missing key for: " + i);
            }
        }
    }

  public void testPutAll() throws Exception {
    TIntObjectHashMap<String> t = new TIntObjectHashMap<String>();
    TIntObjectHashMap<String> m = new TIntObjectHashMap<String>();
    m.put(1, "two");
    m.put(2, "four");
    m.put(3, "six");

    t.put(4, "five");
    assertEquals(1, t.size());

    t.putAll(m);
    assertEquals(4, t.size());
    assertEquals("four", t.get(2));
}

  public void testToString() {
    TIntObjectHashMap<String> m = new TIntObjectHashMap<String>();
    m.put(11, "One");
    m.put(22, "Two");

    String to_string = m.toString();
    assertTrue(to_string, to_string.equals("{11=One,22=Two}")
        || to_string.equals("{22=Two,11=One}"));
  }
}
