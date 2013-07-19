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

package net.ontopia.topicmaps.query.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EqualsPredicateTest extends AbstractPredicateTest {
  
  public EqualsPredicateTest(String name) {
    super(name);
  }

  /// setup

  public void tearDown() {    
    closeStore();
  }
  
  /// tests

  public void testEqualsFalse() throws InvalidQueryException, IOException {
    load("instance-of.ltm");
    findNothing("topic1 = topic2?");
  }

  public void testEqualsTrue() throws InvalidQueryException, IOException {
    load("instance-of.ltm");
    List matches = new ArrayList();
    matches.add(new HashMap());
    verifyQuery(matches,"topic1 = topic1?");
  }

  public void testEqualsString() throws InvalidQueryException, IOException {
    load("int-occs.ltm");
    
    List matches = new ArrayList();
    addMatch(matches, "TOPIC", getTopicById("topic2"));

    verifyQuery(matches, 
		"select $TOPIC from occurrence($TOPIC, $O), " + 
		"type($O, description), value($O, $DESC), " +
		"$DESC = \"topic2\"?");
  }

  public void testVariableEqualsTopic() throws InvalidQueryException, IOException {
    load("int-occs.ltm");
    
    List matches = new ArrayList();
    addMatch(matches, "TOPIC", getTopicById("topic4"));

    verifyQuery(matches, "$TOPIC = topic4?");
  }

  public void testVariableEqualsTopic2() throws InvalidQueryException, IOException {
    load("int-occs.ltm");
    
    List matches = new ArrayList();
    addMatch(matches, "TOPIC", getTopicById("topic4"));

    verifyQuery(matches, "topic4 = $TOPIC?");
  }

  public void testEqualsAssocDouble() throws InvalidQueryException, IOException {
    load("family.ltm");

    List matches = new ArrayList();
    addMatch(matches, "GC", getTopicById("trygve"),
                      "C1", getTopicById("petter"),
                      "C2", getTopicById("petter"),
                      "M", getTopicById("may"));
    addMatch(matches, "GC", getTopicById("tine"),
                      "C1", getTopicById("petter"),
                      "C2", getTopicById("petter"),
                      "M", getTopicById("may"));
    addMatch(matches, "GC", getTopicById("julie"),
                      "C1", getTopicById("petter"),
                      "C2", getTopicById("petter"),
                      "M", getTopicById("may"));
    addMatch(matches, "GC", getTopicById("astri"),
                      "C1", getTopicById("kfg"),
                      "C2", getTopicById("kfg"),
                      "M", getTopicById("bjorg"));
    addMatch(matches, "GC", getTopicById("lmg"),
                      "C1", getTopicById("kfg"),
                      "C2", getTopicById("kfg"),
                      "M", getTopicById("bjorg"));
    addMatch(matches, "GC", getTopicById("silje"),
                      "C1", getTopicById("kfg"),
                      "C2", getTopicById("kfg"),
                      "M", getTopicById("bjorg"));
    
    verifyQuery(matches,
                "parenthood(edvin : father, kjellaug : mother, $C1 : child)," +
                "$C1 = $C2, " +
                "parenthood($C2 : father, $M : mother, $GC : child)?");
  }  

  public void testVariableEqualsTopic3() throws InvalidQueryException, IOException {
    load("int-occs.ltm");
    
    List matches = new ArrayList();
    addMatch(matches, "TOPIC", getTopicById("topic4"));

    verifyQuery(matches, "topic($TOPIC), $TOPIC = topic4?");
  }

  public void testVariableEqualsTopic4() throws InvalidQueryException, IOException {
    load("int-occs.ltm");
    
    List matches = new ArrayList();
    addMatch(matches, "TOPIC", getTopicById("topic4"));

    verifyQuery(matches, "$TOPIC = topic4, topic($TOPIC)?");
  }

  public void testUnboundVariable() throws InvalidQueryException, IOException {
    load("int-occs.ltm");

    getParseError("$A = $B?");
  }
  
}
