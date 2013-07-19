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

package net.ontopia.topicmaps.impl.tmapi2;

import junit.framework.TestCase;
import org.tmapi.core.TopicMapSystemFactory;
import org.tmapi.core.TopicMapSystem;

/**
 * INTERNAL.
 */
public class TopicMapSystemFactoryTest 
  extends TestCase {

  public TopicMapSystemFactoryTest(String name) {
    super(name);
  }

  public void testFactory() throws org.tmapi.core.TMAPIException {
    TopicMapSystemFactory tmsf = TopicMapSystemFactory.newInstance();
    assertTrue("TopicMapSystemFactory is not net.ontopia.topicmaps.impl.tmapi2.TopicMapSystemFactory", 
	       tmsf instanceof net.ontopia.topicmaps.impl.tmapi2.TopicMapSystemFactory);

    TopicMapSystem ts = tmsf.newTopicMapSystem();
    assertTrue("TopicMapSystem is not net.ontopia.topicmaps.impl.tmapi2.TopicMapSystem", 
	       ts instanceof net.ontopia.topicmaps.impl.tmapi2.MemoryTopicMapSystemImpl);

    ts.close();
  }

}
