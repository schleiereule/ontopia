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

public class ComparisonPredicatesTest extends AbstractPredicateTest {
  
  public ComparisonPredicatesTest(String name) {
    super(name);
  }

  /// setup

  public void tearDown() {    
    closeStore();
  }
  
  /// tests

  public void testLTFalse() throws InvalidQueryException, IOException {
    load("instance-of.ltm");
    assertFindNothing("\"b\" < \"a\"?");
  }

  public void testLTFalse2() throws InvalidQueryException, IOException {
    load("instance-of.ltm");
    assertFindNothing("\"a\" < \"a\"?");
  }
  
  public void testLTEFalse() throws InvalidQueryException, IOException {
    load("instance-of.ltm");
    assertFindNothing("\"b\" <= \"a\"?");
  }
  
  public void testGTFalse() throws InvalidQueryException, IOException {
    load("instance-of.ltm");
    assertFindNothing("\"a\" > \"b\"?");
  }
  
  public void testGTFalse2() throws InvalidQueryException, IOException {
    load("instance-of.ltm");
    assertFindNothing("\"a\" > \"a\"?");
  }
  
  public void testGTEFalse() throws InvalidQueryException, IOException {
    load("instance-of.ltm");
    assertFindNothing("\"a\" >= \"b\"?");
  }
  
  public void testLTTrue() throws InvalidQueryException, IOException {
    load("instance-of.ltm");
    List matches = new ArrayList();
    matches.add(new HashMap());
    assertQueryMatches(matches,"\"a\" < \"b\"?");
  }
  
  public void testLTETrue() throws InvalidQueryException, IOException {
    load("instance-of.ltm");
    List matches = new ArrayList();
    matches.add(new HashMap());
    assertQueryMatches(matches,"\"a\" <= \"b\"?");
  }
  
  public void testLTETrue2() throws InvalidQueryException, IOException {
    load("instance-of.ltm");
    List matches = new ArrayList();
    matches.add(new HashMap());
    assertQueryMatches(matches,"\"a\" <= \"a\"?");
  }
  
  public void testGTTrue() throws InvalidQueryException, IOException {
    load("instance-of.ltm");
    List matches = new ArrayList();
    matches.add(new HashMap());
    assertQueryMatches(matches,"\"b\" > \"a\"?");
  }
  
  public void testGTETrue() throws InvalidQueryException, IOException {
    load("instance-of.ltm");
    List matches = new ArrayList();
    matches.add(new HashMap());
    assertQueryMatches(matches,"\"b\" >= \"a\"?");
  }
  
  public void testGTETrue2() throws InvalidQueryException, IOException {
    load("instance-of.ltm");
    List matches = new ArrayList();
    matches.add(new HashMap());
    assertQueryMatches(matches,"\"a\" >= \"a\"?");
  }

  public void testLTVariable() throws InvalidQueryException, IOException {
    load("instance-of.ltm");
    List matches = new ArrayList();

    addMatch(matches, "T", getTopicById("topic1"));
    addMatch(matches, "T", getTopicById("topic2"));
    
    assertQueryMatches(matches, "select $T from " +
                "topic-name($T, $N), value($N, $V), $V < \"Topic3\"?");
  } 

  public void testLTVariable2() throws InvalidQueryException, IOException {
    load("instance-of.ltm");
    List matches = new ArrayList();

    addMatch(matches, "T", getTopicById("topic1"));
    addMatch(matches, "T", getTopicById("topic2"));
    
    assertQueryMatches(matches, "select $T from " +
                "topic-name($T, $N), $V < \"Topic3\", value($N, $V)?");
  } 

  public void testUnboundVariable() throws InvalidQueryException, IOException {
    load("instance-of.ltm");
    
    assertGetParseError("topic-name($T, $N), $V < \"Topic3\"?"); // V never bound...
  }

  public void testTypeClash() throws InvalidQueryException, IOException {
    load("int-occs.ltm");
    assertFindNothing("description($TOPIC, $VALUE), $VALUE < 1?");
  }

  public void testTypeClash2() throws InvalidQueryException, IOException {
    load("int-occs.ltm");
    assertFindNothing("description($TOPIC, $VALUE), $VALUE <= 1?");
  }

  public void testTypeClash3() throws InvalidQueryException, IOException {
    load("int-occs.ltm");
    assertFindNothing("description($TOPIC, $VALUE), 1 > $VALUE?");
  }

  public void testTypeClash4() throws InvalidQueryException, IOException {
    load("int-occs.ltm");
    assertFindNothing("description($TOPIC, $VALUE), 1 >= $VALUE?");
  }
}
