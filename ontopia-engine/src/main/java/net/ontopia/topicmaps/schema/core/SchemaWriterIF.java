/*
 * #!
 * Ontopia Engine
 * #-
 * Copyright (C) 2001 - 2013 The Ontopia Project
 * #-
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * !#
 */

package net.ontopia.topicmaps.schema.core;

/**
 * PUBLIC: Schema writers can write object structures representing
 * schemas out to an implicitly specified location in some schema
 * language syntax.
 */
public interface SchemaWriterIF {

  /**
   * PUBLIC: Writes the schema.
   * @exception java.io.IOException Thrown if there are problems writing
   *                                to the specified location.
   */
  public void write(SchemaIF schema) throws java.io.IOException;
  
}





