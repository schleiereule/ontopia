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

package net.ontopia.utils;

import junit.framework.TestCase;

public class SoftHashMapIndexTest extends TestCase {
  protected LookupIndexIF index;
  
  public SoftHashMapIndexTest(String name) {
    super(name);
  }
  
  public void setUp() {
    index = new SoftHashMapIndex();
  }
  
  protected void tearDown() {
  }
  
  // --- Test cases
  
  public void testWithStrings() {
    Object A = "A";
    Object a = "a";
    Object B = "B";
    Object b = "b";
    
    Object r = null;
    
    assertTrue("{} does contain key A", index.get(A) == null);
    assertTrue("{} does contain key B", index.get(B) == null);
    assertTrue("{} does contain key null", index.get(null) == null);
    
    r = index.put(A, a);
    
    assertTrue("index.put return value is not null", r == null);
    assertTrue("{A:a} does not contain key A", a.equals(index.get(A)));
    assertTrue("{A:a} does contain key B", index.get(B) == null);
    assertTrue("{A:a} with 1 element does contain key null", index.get(null) == null);
    
    r = index.put(B, b);
    
    assertTrue("index.put return value is not null", r == null);
    assertTrue("{A:a,B:b} does not contain key A", a.equals(index.get(A)));
    assertTrue("{A:a,B:b} does not contain key B", b.equals(index.get(B)));
    assertTrue("{A:a,B:b} does contain key null", index.get(null) == null);
    
    r = index.remove(A);
    
    assertTrue("index.remove return value is not null", r == a);
    assertTrue("{B:b} does contain key A", index.get(A) == null);
    assertTrue("{B:b} does not contain key B", b.equals(index.get(B)));
    assertTrue("{B:b} does contain key null", index.get(null) == null);
    
    r = index.remove(B);
    
    assertTrue("index.remove return value is not null", r == b);
    assertTrue("{} does contain key A", index.get(A) == null);
    assertTrue("{} does contain key B", index.get(A) == null);
    assertTrue("{} does contain key null", index.get(null) == null);
    
    // NOTE: the current implementation does not support null keys, so
    // we're ignoring those tests.
    
    //! // null keys
    //! r = index.put(null, c);
    //! assertTrue("index.put return value is not null", r == null);
    //! assertTrue("{null:c} does contain key null", index.get(null) == c);
    //! 
    //! r = index.remove(null);
    //! assertTrue("index.remove return value is not null", r == c);
    //! assertTrue("{} does contain key null", index.get(null) == c);
  }
  
  public void testWithDummyObjects() {
    Object A = new DummyObject("A", 11);
    Object a = "a";
    Object B = new DummyObject("B", 22);
    Object b = "b";
    Object C = new DummyObject("C", 11);
    Object c = "c";
    
    Object r = null;
    
    assertTrue("{} does contain key A", index.get(A) == null);
    assertTrue("{} does contain key B", index.get(B) == null);
    assertTrue("{} does contain key null", index.get(null) == null);
    
    r = index.put(A, a);
    
    assertTrue("index.put return value is not null", r == null);
    assertTrue("{A:a} does not contain key A", a.equals(index.get(A)));
    assertTrue("{A:a} does contain key B", index.get(B) == null);
    assertTrue("{A:a} with 1 element does contain key null", index.get(null) == null);
    
    r = index.put(B, b);
    
    assertTrue("index.put return value is not null", r == null);
    assertTrue("{A:a,B:b} does not contain key A", a.equals(index.get(A)));
    assertTrue("{A:a,B:b} does not contain key B", b.equals(index.get(B)));
    assertTrue("{A:a,B:b} does contain key null", index.get(null) == null);
    
    r = index.put(C, c);
    
    assertTrue("index.put return value is not null", r == null);
    assertTrue("{A:a,B:b,C:c} does not contain key A", a.equals(index.get(A)));
    assertTrue("{A:a,B:b,C:c} does not contain key B", b.equals(index.get(B)));
    assertTrue("{A:a,B:b,C:c} does not contain key C", c.equals(index.get(C)));
    assertTrue("{A:a,B:b,C:c} does contain key null", index.get(null) == null);
    
    r = index.remove(A);
    
    assertTrue("index.remove return value is not null", r == a);
    assertTrue("{B:b,C:c} does contain key A", index.get(A) == null);
    assertTrue("{B:b,C:c} does not contain key B", b.equals(index.get(B)));
    assertTrue("{B:b,C:c} does not contain key C", c.equals(index.get(C)));
    assertTrue("{B:b,C:c} does contain key null", index.get(null) == null);
    
    r = index.remove(B);
    
    assertTrue("index.remove return value is not null", r == b);
    assertTrue("{C:c} does contain key A", index.get(A) == null);
    assertTrue("{C:c} does contain key B", index.get(B) == null);
    assertTrue("{C:c} does not contain key C", c.equals(index.get(C)));
    assertTrue("{C:c} does contain key null", index.get(null) == null);
    
    r = index.remove(C);
    
    assertTrue("index.remove return value is not null", r == c);
    assertTrue("{C:c} does contain key A", index.get(A) == null);
    assertTrue("{C:c} does contain key B", index.get(B) == null);
    assertTrue("{C:c} does contain key C", index.get(C) == null);
    assertTrue("{C:c} does contain key null", index.get(null) == null);
  }
  
  public void testBig() {
    // add integers 0-999
    for (int i=0; i < 1000; i++) {
      Integer x = new Integer(i);
      index.put(x, x);
    }
    
    // verify integers
    for (int i=0; i < 1000; i++) {
      Object x = new Integer(i);
      Object y = index.get(x);
      assertTrue("index.get return value is not " + x, x.equals(y));	
    }
    
    // remove some of the integers
    int nonprimes = 0;
    for (int i=0; i < 1000; i++) {
      Integer x = new Integer(i);
      if (!isprime(x)) {
        Object r = index.remove(x);
        assertTrue("index.remove return value is not " + x, x.equals(r));	
        assertTrue("index contains removed key " + x, index.get(x) == null);
        nonprimes++;
      }
    }
    
    // verify integers (and check primes)
    int primes = 0;
    for (int i=0; i < 1000; i++) {
      Integer x = new Integer(i);
      Object y = index.get(x);
      if (!isprime(x)) {
        if (y != null)
          fail("Found prime number " + x);
      } else
        primes++;
    }
    assertTrue("1000 - primes > nonprimes (" + primes + "/" + nonprimes + ")",
        (1000-primes) == nonprimes);	
    
    // add integers 1000-1499
    for (int i=1000; i < 1500; i++) {
      Integer x = new Integer(i);
      index.put(x, x);
    }
    
    // remove some of the integers
    for (int i=1000; i < 1500; i++) {
      Integer x = new Integer(i);
      if (!isprime(x)) {
        Object r = index.remove(x);
        assertTrue("index.remove return value is not " + x, x.equals(r));	
        assertTrue("index contains removed key " + x, index.get(x) == null);
        nonprimes++;
      }
    }
    
    // verify integers (and check primes)
    primes = 0;
    for (int i=0; i < 1500; i++) {
      Integer x = new Integer(i);
      Object y = index.get(x);
      if (!isprime(x)) {
        if (y != null)
          fail("Found prime number " + x);
      } else
        primes++;
    }
    assertTrue("1500 - primes > nonprimes (" + primes + "/" + nonprimes + ")",
        (1500-primes) == nonprimes);	
    
  }
  
  //
  // --- Internal test object
  
  private class DummyObject {
    private int hashCode;
    private String value;
    
    public DummyObject(String value, int hashCode) {
      this.value = value;
      this.hashCode = hashCode;
    }
    
    public int hashCode() {
      return hashCode;
    }
    
    public String toString() {
      return "<DummyObject " + value + ":" + hashCode +">";
    }
    
    public boolean equals(Object other) {
      return ((DummyObject) other).value.equals(value);
    }
  }
  
  private static boolean isprime(Integer integer) {
    int i = integer.intValue();
    int root = ((int) Math.sqrt(i)) + 1;
    
    if (i != 2 && (i % 2 == 0))
      return false;
    
    for (int n = 3; n < root; n += 2)
      if (i % n == 0)
        return false;
    
    return true;
  }
}
