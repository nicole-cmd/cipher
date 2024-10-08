package edu.grinnell.csc207.main;

import java.io.PrintWriter;
import java.lang.String;
import java.lang.Character;
import java.lang.System;

import edu.grinnell.csc207.util.CipherUtils;

@SuppressWarnings("unused")
public class AllCaesar {
  public static void main(String[] args) {
    char[] inTwo = args[1].toCharArray();

    if (args.length != 2) {
      System.err.println("Error: Incorrect number of parameters.");
    } 
    
    if ((!(args[0].equals("encode"))) && (!(args[0].equals("decode"))) ) {
      System.err.println("Error: Invalid option: " + args[0] + ". Valid options are 'encode' or 'decode'.");

      return;
    }

    for (char index : inTwo) {
      if (!(Character.isAlphabetic(index))) {
        System.err.println("Error: String contains characters other than lowercase letters.");

        return;
      }
    }

    PrintWriter pen = new PrintWriter(System.out, true);
    String str = args[1];
    for (char ch = 'a'; ch <= 'z'; ch++) {
      pen.printf("n = %c: %s\n", ch, CipherUtils.caesarEncrypt(str, ch));
    }
    pen.flush();
    pen.close();
  }
} //class AllCaesar
