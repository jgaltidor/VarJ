/*
 * Copyright (C) 2009 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.common.collect;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collections;

/**
 * GWT emulation of {@link SingletonImmutableMap}.
 *
 * @author Hayward Chan
 */
final class SingletonImmutableMap<K, V> extends ImmutableMap<K, V> {

  // These references are used both by the custom field serializer, and by the
  // GWT compiler to infer the keys and values of the map that needs to be
  // serialized.
  //
  // Although they are non-final, they are package private and the reference is
  // never modified after a map is constructed.
  K singleKey;
  V singleValue;

  SingletonImmutableMap(K key, V value) {
    super(Collections.singletonMap(checkNotNull(key), checkNotNull(value)));
    this.singleKey = key;
    this.singleValue = value;
  }
}
