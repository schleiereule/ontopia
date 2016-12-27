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

package net.ontopia.infoset.core;

import java.io.IOException;

/**
 * INTERNAL: Interface for preloading locators.<p>
 */

public interface PreloaderIF {

  /**
   * INTERNAL: Preloads the resource pointed to by the given locator.
   *
   * @return Returns a locator that references the preloaded resource.
   */
  LocatorIF preload(LocatorIF locator) throws IOException;

  /**
   * INTERNAL: Can be used to figure out if it is necessary to preload
   * the resource referenced by the locator.<p>
   */
  boolean needsPreloading(LocatorIF locator);
  
}





