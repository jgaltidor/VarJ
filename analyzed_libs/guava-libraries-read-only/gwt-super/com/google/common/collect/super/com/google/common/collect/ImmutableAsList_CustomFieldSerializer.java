/*
 * Copyright (C) 2010 Google Inc.
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

import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;
import com.google.gwt.user.client.rpc.core.java.util.Collection_CustomFieldSerializerBase;

import java.util.ArrayList;
import java.util.List;

/**
 * This class implements the client-side GWT serialization of
 * {@link ImmutableAsList}.
 *
 * @author Hayward Chan
 */
public class ImmutableAsList_CustomFieldSerializer {

  public static void deserialize(SerializationStreamReader reader,
      RegularImmutableList<?> instance) {
  }

  public static ImmutableAsList<Object> instantiate(
      SerializationStreamReader reader) throws SerializationException {
    List<Object> elements = new ArrayList<Object>();
    Collection_CustomFieldSerializerBase.deserialize(reader, elements);
    return new ImmutableAsList<Object>(elements);
  }

  public static void serialize(SerializationStreamWriter writer,
      ImmutableAsList<?> instance) throws SerializationException {
    Collection_CustomFieldSerializerBase.serialize(writer, instance);
  }
}
