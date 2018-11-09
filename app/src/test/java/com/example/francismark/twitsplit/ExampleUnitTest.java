package com.example.francismark.twitsplit;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    /**
     * This test will check if the input message with more than 50 characters will be divided into 50 characters per line
     */
    @Test
    public void chunkingMessage() {

        String expectedVal =
                  "1/2 I can't believe Tweeter now supports chunking\n"
                + "2/2 my messages, so I don't have to do it myself.";

        String actualVal = MainActivity.splitMessage("I can't believe Tweeter now supports chunking my messages, so I don't have to do it myself.");

        assertEquals(expectedVal, actualVal);
    }

    /**
     * This test will check if the input message with less than or equal 50 characters will remain as is
     */
    @Test
    public void notMoreThanFiftyChars() {

        String expectedVal = "Hi! My name is LeBron James.";

        String actualVal = MainActivity.splitMessage("Hi! My name is LeBron James.");

        assertEquals(expectedVal, actualVal);
    }

    /**
     * This test will check if the return message will have an error
     */
    @Test
    public void wordWithmoreThanFiftyChars() {

        String expectedVal = "[ERROR] Hello there! ThisShouldReturnAnErrorBecauseThisWordIsMoreThan50Characters end";

        String actualVal = MainActivity.splitMessage("Hello there! ThisShouldReturnAnErrorBecauseThisWordIsMoreThan50Characters end");

        assertEquals(expectedVal, actualVal);
    }
}