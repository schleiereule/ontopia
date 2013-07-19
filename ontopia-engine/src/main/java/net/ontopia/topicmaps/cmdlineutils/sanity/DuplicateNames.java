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

package net.ontopia.topicmaps.cmdlineutils.sanity;

import java.util.*;
import net.ontopia.topicmaps.core.*;
import net.ontopia.utils.*;
import net.ontopia.topicmaps.utils.*;

public class DuplicateNames {
  private TopicMapIF tm;
  //  protected StringifierIF ts = TopicStringifiers.getTopicTopicNameStringifier();

  public DuplicateNames(TopicMapIF tm) {
    this.tm = tm;
  }


  /**
   * Returns a collection of all the topics with the same basename and scope.
   */
  public Collection getDuplicatedNames() {

    Collection retur = new ArrayList();
    Collection topics = tm.getTopics();
    Iterator it = topics.iterator();
    //check all the topics.
    while (it.hasNext()) {
      TopicIF topic = (TopicIF)it.next();

      Collection basenames = topic.getTopicNames();
      String basename, scopename;
      Iterator itbasenames = basenames.iterator();
      while (itbasenames.hasNext()) {
        TopicNameIF b = (TopicNameIF)itbasenames.next();
        basename = b.toString();
        Collection scopes = b.getScope();
        Iterator itscope = scopes.iterator();
        while (itscope.hasNext()) {
          TopicIF t = (TopicIF)itscope.next();
          scopename = t.getObjectId();
          if (basename.equals(scopename)) {
            retur.add(topic);
          }
        }
      }
    }
    return retur;
  }
}





