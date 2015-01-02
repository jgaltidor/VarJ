/*
 * Copyright(c) 2008, MQSoftware, Inc.
 * All rights reserved.
 */
package gnu.trove.benchmark;

import gnu.trove.THashMap;
import gnu.trove.THashSet;


/**
 *
 */
public class Test {
    public static void main(String[] args) {
        final int KEYS = 50000 * 10;
        final int ELEMENTS = 50000;

        final THashMap map = new THashMap();
//        map.setAutoCompactionFactor(0);
        final THashSet keys = new THashSet(KEYS);
//        keys.setAutoCompactionFactor(0);
        while (keys.size() != KEYS) {
            final Integer k = (int) (Math.random() * 100000000);
            keys.add(k);
        }
        final Object[] keysA = keys.toArray();

        int addKey = 0;
        int removeKey = 0;

        for (int i = 1; i < ELEMENTS; ++i) {
            map.put(keysA[ addKey++ ], null);
        }

        System.out.println("Map size: " + map.size());
        final long start = System.currentTimeMillis();

        for (int i = 1; i < 1000000; ++i) {
            map.put(keysA[ addKey++ ], null);
            if (addKey >= KEYS) {
                addKey = 0;
            }
            map.remove(keysA[ removeKey++ ]);
            if (removeKey >= KEYS) {
                removeKey = 0;
            }
        }
        System.out.println("Map size: " + map.size() + ", total time " +
            (System.currentTimeMillis() - start) + "ms"
        );
    }
}
