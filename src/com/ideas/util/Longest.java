package com.ideas.util;

import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.*;
import java.util.*;
import java.util.regex.*;

public class Longest {
  public static void main(String args[]) {

    try {
      // Map File from filename to byte buffer
      FileInputStream input =
          new FileInputStream("c:/comErr.log");
      FileChannel channel = input.getChannel();
      int fileLength = (int) channel.size();
      MappedByteBuffer buffer = channel.map(
          FileChannel.MapMode.READ_ONLY, 0, fileLength);

      // Convert to character buffer
      Charset charset = Charset.forName("ISO-8859-1");
      CharsetDecoder decoder = charset.newDecoder();
      CharBuffer charBuffer = decoder.decode(buffer);

      // Create line pattern
      Pattern linePattern =
          Pattern.compile(".*$", Pattern.MULTILINE);

      // Create word pattern
      Pattern wordBreakPattern =
          Pattern.compile("[\\p{Punct}\\s}]");

      // Match line pattern to buffer
      Matcher lineMatcher =
          linePattern.matcher(charBuffer);

      // Holder for longest word
      String longest = "";

      // For each line
      while (lineMatcher.find()) {

        // Get line
        String line = lineMatcher.group();

        // Get array of words on line
        String words[] = wordBreakPattern.split(line);

        // Look for longest word
        for (int i = 0, n = words.length; i < n; i++) {
          if (words[i].length() > longest.length()) {
            longest = words[i];
          }
        }
      }
      // Report
      System.out.println("Longest word: " + longest);


      // Close
      input.close();
    }
    catch (IOException e) {
      System.err.println("Error processing");
    }
  }
}
