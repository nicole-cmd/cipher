package edu.grinnell.csc207.main;

import java.io.PrintWriter;
import java.lang.String;
import java.lang.Character;
import java.lang.System;

import edu.grinnell.csc207.util.CipherUtils;

//@SuppressWarnings("unused")
/**
 * Generate all 26 possible encoded/decoded (via the Caesar method) versions of a message.
 * 
 * @author Nicole Gorrell
 */
public class AllCaesar {
  public static void main(String[] args) {
    char[] inTwo = args[1].toCharArray();

    // Checks for correct amount of command line inputs.  
    if (args.length != 2) {
      System.err.println("Error: Incorrect number of parameters.");
      return; // End program.
    } // if 
    
    // Ensures that the first command argument is a valid input
    if ((!(args[0].equals("encode"))) && (!(args[0].equals("decode"))) ) {
      System.err.println("Error: Invalid option: " + args[0] + ". Valid options are 'encode' or 'decode'.");
      return; // End program
    } // if

    // Ensures the input message to process is in a valid format
    for (char index : inTwo) {
      if (!(Character.isAlphabetic(index))) { // doesn't necessarily check for lowercase...
        System.err.println("Error: String contains characters other than lowercase letters.");
        return; // End program
      } // if
    } // for

    PrintWriter pen = new PrintWriter(System.out, true);
    String str = args[1];
    for (char ch = 'a'; ch <= 'z'; ch++) {
      pen.printf("n = %c: %s\n", ch, CipherUtils.caesarEncrypt(str, ch));
    }
    pen.flush();
    pen.close();
  } // main(String[])
} // class AllCaesar
