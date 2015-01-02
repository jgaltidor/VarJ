///////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2002, Eric D. Friedman All Rights Reserved.
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

package gnu.trove.decorator;

import gnu.trove.TDoubleShortHashMap;
import gnu.trove.TDoubleShortIterator;

import java.io.*;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Map;
import java.util.Set;


//////////////////////////////////////////////////
// THIS IS A GENERATED CLASS. DO NOT HAND EDIT! //
//////////////////////////////////////////////////


/**
 * Wrapper class to make a TDoubleShortHashMap conform to the <tt>java.util.Map</tt> API.
 * This class simply decorates an underlying TDoubleShortHashMap and translates the Object-based
 * APIs into their Trove primitive analogs.
 * <p/>
 * <p/>
 * Note that wrapping and unwrapping primitive values is extremely inefficient.  If
 * possible, users of this class should override the appropriate methods in this class
 * and use a table of canonical values.
 * </p>
 * <p/>
 * Created: Mon Sep 23 22:07:40 PDT 2002
 *
 * @author Eric D. Friedman
 * @author Rob Eden
 */
public class TDoubleShortHashMapDecorator extends AbstractMap<Double, Short>
	implements Map<Double, Short>, Externalizable, Cloneable {

    /** the wrapped primitive map */
    protected TDoubleShortHashMap _map;


    /**
     * FOR EXTERNALIZATION ONLY!!
     */
    public TDoubleShortHashMapDecorator() {}

    /**
     * Creates a wrapper that decorates the specified primitive map.
     */
    public TDoubleShortHashMapDecorator(TDoubleShortHashMap map) {
        super();
        this._map = map;
    }


    /**
     * Returns a reference to the map wrapped by this decorator.
     */
    public TDoubleShortHashMap getMap() {
        return _map;
    }


    /**
     * Clones the underlying trove collection and returns the clone wrapped in a new
     * decorator instance.  This is a shallow clone except where primitives are
     * concerned.
     *
     * @return a copy of the receiver
     */
    public TDoubleShortHashMapDecorator clone() {
        try {
            TDoubleShortHashMapDecorator copy = (TDoubleShortHashMapDecorator) super.clone();
            copy._map = (TDoubleShortHashMap)_map.clone();
            return copy;
        } catch (CloneNotSupportedException e) {
            // assert(false);
            throw new InternalError(); // we are cloneable, so this does not happen
        }
    }

    /**
     * Inserts a key/value pair into the map.
     *
     * @param key   an <code>Object</code> value
     * @param value an <code>Object</code> value
     * @return the previous value associated with <tt>key</tt>,
     *         or Short(0) if none was found.
     */
    public Short put(Double key, Short value) {
        return wrapValue(_map.put(unwrapKey(key), unwrapValue(value)));
    }

    /**
     * Retrieves the value for <tt>key</tt>
     *
     * @param key an <code>Object</code> value
     * @return the value of <tt>key</tt> or null if no such mapping exists.
     */
    public Short get(Double key) {
        double k = unwrapKey(key);
        short v = _map.get(k);
	// 0 may be a false positive since primitive maps
	// cannot return null, so we have to do an extra
	// check here.
        if (v == 0) {
            return _map.containsKey(k) ? wrapValue(v) : null;
        } else {
            return wrapValue(v);
        }
    }


    /**
     * Empties the map.
     */
    public void clear() {
        this._map.clear();
    }

    /**
     * Deletes a key/value pair from the map.
     *
     * @param key an <code>Object</code> value
     * @return the removed value, or Short(0) if it was not found in the map
     */
    public Short remove(Double key) {
        return wrapValue(_map.remove(unwrapKey(key)));
    }

    /**
     * Returns a Set view on the entries of the map.
     *
     * @return a <code>Set</code> value
     */
    public Set<Map.Entry<Double,Short>> entrySet() {
        return new AbstractSet<Map.Entry<Double,Short>>() {
            public int size() {
                return _map.size();
            }

            public boolean isEmpty() {
                return TDoubleShortHashMapDecorator.this.isEmpty();
            }

            public boolean contains(Object o) {
                if (o instanceof Map.Entry) {
                    Object k = ((Map.Entry) o).getKey();
                    Object v = ((Map.Entry) o).getValue();
                    return TDoubleShortHashMapDecorator.this.containsKey(k)
                            && TDoubleShortHashMapDecorator.this.get(k).equals(v);
                } else {
                    return false;
                }
            }

            public Iterator<Map.Entry<Double,Short>> iterator() {
                return new Iterator<Map.Entry<Double,Short>>() {
                    private final TDoubleShortIterator it = _map.iterator();

                    public Map.Entry<Double,Short> next() {
                        it.advance();
                        final Double key = wrapKey(it.key());
                        final Short v = wrapValue(it.value());
                        return new Map.Entry<Double,Short>() {
                            private Short val = v;

                            public boolean equals(Object o) {
                                return o instanceof Map.Entry
                                        && ((Map.Entry) o).getKey().equals(key)
                                        && ((Map.Entry) o).getValue().equals(val);
                            }

                            public Double getKey() {
                                return key;
                            }

                            public Short getValue() {
                                return val;
                            }

                            public int hashCode() {
                                return key.hashCode() + val.hashCode();
                            }

                            public Short setValue(Short value) {
                                val = value;
                                return put(key, value);
                            }
                        };
                    }

                    public boolean hasNext() {
                        return it.hasNext();
                    }

                    public void remove() {
                        it.remove();
                    }
                };
            }

            public boolean add(Short o) {
                throw new UnsupportedOperationException();
            }

            public boolean remove(Object o) {
                throw new UnsupportedOperationException();
            }

            public boolean addAll(Collection<? extends Map.Entry<Double, Short>> c) {
                throw new UnsupportedOperationException();
            }

            public boolean retainAll(Collection<?> c) {
                throw new UnsupportedOperationException();
            }

            public boolean removeAll(Collection<?> c) {
                throw new UnsupportedOperationException();
            }

            public void clear() {
                TDoubleShortHashMapDecorator.this.clear();
            }
        };
    }

    /**
     * Checks for the presence of <tt>val</tt> in the values of the map.
     *
     * @param val an <code>Object</code> value
     * @return a <code>boolean</code> value
     */
    public boolean containsValue(Object val) {
        return _map.containsValue(unwrapValue(val));
    }

    /**
     * Checks for the present of <tt>key</tt> in the keys of the map.
     *
     * @param key an <code>Object</code> value
     * @return a <code>boolean</code> value
     */
    public boolean containsKey(Object key) {
        return _map.containsKey(unwrapKey(key));
    }

    /**
     * Returns the number of entries in the map.
     *
     * @return the map's size.
     */
    public int size() {
        return this._map.size();
    }

    /**
     * Indicates whether map has any entries.
     *
     * @return true if the map is empty
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Copies the key/value mappings in <tt>map</tt> into this map.
     * Note that this will be a <b>deep</b> copy, as storage is by
     * primitive value.
     *
     * @param map a <code>Map</code> value
     */
    public void putAll(Map<? extends Double, ? extends Short> map) {
        Iterator<? extends Entry<? extends Double,? extends Short>> it = map.entrySet().iterator();
        for (int i = map.size(); i-- > 0;) {
            Entry<? extends Double,? extends Short> e = it.next();
            this.put(e.getKey(), e.getValue());
        }
    }

    /**
     * Wraps a key
     *
     * @param k key in the underlying map
     * @return an Object representation of the key
     */
    protected Double wrapKey(double k) {
        return Double.valueOf(k);
    }

    /**
     * Unwraps a key
     *
     * @param key wrapped key
     * @return an unwrapped representation of the key
     */
    protected double unwrapKey(Object key) {
        return ((Double)key).doubleValue();
    }

    /**
     * Wraps a value
     *
     * @param k value in the underlying map
     * @return an Object representation of the value
     */
    protected Short wrapValue(short k) {
        return Short.valueOf(k);
    }

    /**
     * Unwraps a value
     *
     * @param value wrapped value
     * @return an unwrapped representation of the value
     */
    protected short unwrapValue(Object value) {
        return ((Short)value).shortValue();
    }


    // Implements Externalizable
    public void readExternal(ObjectInput in)
        throws IOException, ClassNotFoundException {

        // VERSION
        in.readByte();

        // MAP
        _map = (TDoubleShortHashMap) in.readObject();
    }


    // Implements Externalizable
    public void writeExternal(ObjectOutput out) throws IOException {
        // VERSION
        out.writeByte(0);

        // MAP
        out.writeObject(_map);
    }

} // TDoubleShortHashMapDecorator