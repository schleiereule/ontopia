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

public class StringUtilsTest extends TestCase {
  
  public StringUtilsTest(String name) {
    super(name);
  }

  protected void setUp() {
  }

  protected void tearDown() {
  }

  // --- normalize test cases
  
  public void testNormalizeEmpty() {
    assertNormalizesAs("", "");
  }
  
  public void testNormalizeWord() {
    assertNormalizesAs("abc", "abc");
  }

  public void testNormalizeSpaces() {
    assertNormalizesAs("    ", " ");
  }

  public void testNormalizeWordWithSpace() {
    assertNormalizesAs("abc def", "abc def");
  }

  public void testNormalizeWordWithSpaces() {
    assertNormalizesAs("abc       def", "abc def");
  }

  public void testNormalizeWordWithFunnyChar() {
    assertNormalizesAs2("abc\tdef", "abc def");
  }

  public void testNormalizeWordWithFunnyChars() {
    assertNormalizesAs("abc  \r\t\n     def", "abc def");
  }

  public void testNormalizeWordWithSpacesAtBothEnds() {
    assertNormalizesAs("     abc def     ", " abc def ");
  }

  protected void assertNormalizesAs(String source, String target) {
    String result = StringUtils.normalizeWhitespace(source);
    assertTrue("'" + source + "'should normalize to '" + target +
               "', got '" + result + "'", target.equals(result));
  }

  // --- normalizeIsWhitespace test cases
  
  public void testNormalizeEmpty2() {
    assertNormalizesAs2("", "");
  }
  
  public void testNormalizeWord2() {
    assertNormalizesAs2("abc", "abc");
  }

  public void testNormalizeSpaces2() {
    assertNormalizesAs2("    ", " ");
  }

  public void testNormalizeWordWithSpace2() {
    assertNormalizesAs2("abc def", "abc def");
  }

  public void testNormalizeWordWithSpaces2() {
    assertNormalizesAs2("abc       def", "abc def");
  }

  public void testNormalizeWordWithFunnyChar2() {
    assertNormalizesAs2("abc\u000Bdef", "abc def");
  }

  public void testNormalizeWordWithFunnyChars2() {
    assertNormalizesAs2("abc  \r\t\n\u000B     def", "abc def");
  }

  public void testNormalizeWordWithSpacesAtBothEnds2() {
    assertNormalizesAs2("     abc def     ", " abc def ");
  }

  protected void assertNormalizesAs2(String source, String target) {
    String result = StringUtils.normalizeIsWhitespace(source);
    assertTrue("'" + source + "'should normalize to '" + target +
               "', got '" + result + "'", target.equals(result));
  }
  
  // --- regionEquals test cases

  public void testREEmpty() {
    assertRegionEquals("", new char[]{}, 0, 0);
  }
  
  public void testREUnequal() {
    assertRegionNotEquals("abc", new char[]{}, 0, 0);
  }
  
  public void testRERegion() {
    assertRegionEquals("bcd", new char[]{'a', 'b', 'c', 'd', 'e'}, 1, 3);
  }
  
  public void testRERegionMiss() {
    assertRegionNotEquals("bcd", new char[]{'a', 'b', 'c', 'd', 'e'}, 1, 4);
  }
  
  public void testRERegionMiss2() {
    assertRegionNotEquals("bcd", new char[]{'a', 'b', 'c', 'e', 'd'}, 1, 3);
  }
  
  public void testRERegionWhole() {
    assertRegionEquals("abc", new char[]{'a', 'b', 'c'}, 0, 3);
  }
  
  public void testRERegionEnd() {
    assertRegionEquals("bcd", new char[]{'a', 'b', 'c', 'd'}, 1, 3);
  }
  
  protected void assertRegionEquals(String str, char[] ch, int st, int len) {
    assertTrue("'" + str + "' did not equal " + ch + "[" + st + ":" + (st+len) + "]",
               StringUtils.regionEquals(str, ch, st, len));
  }
  
  protected void assertRegionNotEquals(String str, char[] ch, int st, int len) {
    assertTrue("'" + str + "' equalled " + ch + "[" + st + ":" + (st+len) + "]",
               !StringUtils.regionEquals(str, ch, st, len));
  }


  public void testEscapeEntitiesAmp() {
    assertEscapedEquals("intro & Co", "intro &amp; Co");
  }

  public void testEscapeEntitiesLt() {
    assertEscapedEquals("23 < 42", "23 &lt; 42");
  }
  
  public void testEscapeEntitiesTag() {
    assertEscapedEquals("<boring>", "&lt;boring&gt;");
  }
  
  public void testEscapeEntitiesQuot() {
    assertEscapedEquals("Do know \"So, what?\"", "Do know &quot;So, what?&quot;");
  }
  
  protected void assertEscapedEquals(String to_esc, String expected) {
    String result = StringUtils.escapeHTMLEntities(to_esc);
    assertTrue("'" + result + "' did not equal the escaped string '" + expected +"'",
               result.equals(expected));
  }

  // --- pad test cases
  public void testPadStringZeros() {
    // pad(String,char, int)
    assertPaddedZerosAs("", "00000000", 8);
    assertPaddedZerosAs("1", "00000001", 8);
    assertPaddedZerosAs("12", "00000012", 8);
    assertPaddedZerosAs("123", "00000123", 8);
    assertPaddedZerosAs("1234", "00001234", 8);
    assertPaddedZerosAs("12345", "00012345", 8);
    assertPaddedZerosAs("123456", "00123456", 8);
    assertPaddedZerosAs("1234567", "01234567", 8);
    assertPaddedZerosAs("12345678", "12345678", 8);
    assertPaddedZerosAs("123456789", "23456789", 8);
  }

  public void testPadIntZeros() {
    // pad(int,char, int)
    assertPaddedZerosAs(1, "00000001", 8);
    assertPaddedZerosAs(12, "00000012", 8);
    assertPaddedZerosAs(123, "00000123", 8);
    assertPaddedZerosAs(1234, "00001234", 8);
    assertPaddedZerosAs(12345, "00012345", 8);
    assertPaddedZerosAs(123456, "00123456", 8);
    assertPaddedZerosAs(1234567, "01234567", 8);
    assertPaddedZerosAs(12345678, "12345678", 8);
    assertPaddedZerosAs(123456789, "23456789", 8);
  }

  protected void assertPaddedZerosAs(String num, String correct, int length) {
    String result = StringUtils.pad(num, '0', length);
    assertTrue("String \"" + num + "\" not correctly padded: \"" + result + "\"", correct.equals(result));
  }

  protected void assertPaddedZerosAs(int num, String correct, int length) {
    String result = StringUtils.pad(num, '0', length);
    assertTrue("String \"" + num + "\" not correctly padded: \"" + result + "\"", correct.equals(result));
  }
  
  // --- makeRandomId test cases

  public void testMakeRandomId() {
    String id = StringUtils.makeRandomId(10);
    assertTrue("random id had wrong length", id.length() == 10);
  }

  public void testMakeTwoRandomIds() {
    String id1 = StringUtils.makeRandomId(10);
    String id2 = StringUtils.makeRandomId(10);
    assertTrue("random id1 had wrong length", id1.length() == 10);
    assertTrue("random id2 had wrong length", id2.length() == 10);
    assertTrue("random ids are equal!", !id1.equals(id2));
  }

  // --- normalizeId test cases

  public void testNormalizeIdEmpty() {
    assertTrue("incorrect normalization of empty string",
               StringUtils.normalizeId("") == null);
  }

  public void testNormalizeIdOK() {
    assertEquals("incorrect normalization",
                 StringUtils.normalizeId("abc"), "abc");
  }

  public void testNormalizeIdOK1() {
    assertEquals("incorrect normalization",
                 StringUtils.normalizeId("a"), "a");
  }

  public void testNormalizeIdLowerCase() {
    assertEquals("incorrect normalization",
                 StringUtils.normalizeId("ABCD"), "abcd");
  }

  public void testNormalizeIdStripAccents() {
    String input = "ab\u00C6\u00D8\u00E5\u00E9\u00FF\u00FCab\u00CF";
    assertEquals("incorrect normalization",
                 StringUtils.normalizeId(input), "abeoaeyuabi");
  }

  public void testNormalizeIdKeepSpecials() {
    assertEquals("incorrect normalization",
                 StringUtils.normalizeId("ab._-"), "ab._-");
  }

  public void testNormalizeIdGetRidOfSpaces() {
    String id = StringUtils.normalizeId("  ab   ab  ");
    assertTrue("incorrect normalization, should be 'ab-ab', but was '" + id + "'",
               "ab-ab".equals(id));
  }

  public void testNormalizeIdEarlyDiscard() {
    String id = StringUtils.normalizeId("@@ab");
    assertTrue("incorrect normalization, should be '__ab', but was '" + id + "'",
               "__ab".equals(id));
  }
}
