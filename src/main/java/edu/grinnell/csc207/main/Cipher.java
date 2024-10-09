package edu.grinnell.csc207.main;

import java.io.PrintWriter;
import java.lang.String;
import edu.grinnell.csc207.util.CipherUtils;

/**
 * The main program through which we can process encrypt/decrypt input from the command line.
 * 
 * @author Nicole Gorrell
 */
public class Cipher {
  public static void main(String[] args) {
    // Following variables store command line inputs as they are read in to be referenced in method calls.
    String storeAct = "";
    String storeMethod = "";
    String message = "";
    String result = "";
    char keyChar = '\0'; // could i just convert to string if char.length > 1...
    String keyString = "";

    // Check for the correct amount of input parameters
    if(args.length != 4) {
      System.err.println("Error: Expected four parameters, received " + args.length);
      return; // End the program
    } // if

    // Read through command line arguments and stores commands in corresponding variables 
    for(String a : args) {
      if((a.equals(CipherUtils.actionEn) || (a.equals(CipherUtils.actionDe)))) {
        storeAct = a;
      } else if(a.equals(CipherUtils.vigCall) || (a.equals(CipherUtils.caesarCall))) {
        storeMethod = a;
      } else if((storeMethod.startsWith("-c")) && a.length() == 1) {
        keyChar = a.charAt(0);
      } else if((storeMethod.startsWith("-v")) && a.length() >= 1) {
        keyString = a;
      } else if(a.charAt(0) > CipherUtils.max){
        System.err.println("Error: Strings must only contain lowercase letters");
        return; // End program  
      } else {
        message = a;
      } // if/else
    } // for

    String tempChar = String.valueOf(keyChar); // Used below to ensure the char type key has only one char

    // Checks the validity of command line inputs
    if(storeAct != CipherUtils.actionEn || storeAct != CipherUtils.actionDe) {
      System.err.println("Error: No valid action specified. Legal values are 'encode' and 'decode'");
      return; // End program 
    } else if((tempChar.length() != 1) && storeMethod.equals(CipherUtils.caesarCall)) {
      System.err.println("Error: Caesar cipher requires a one-character key");
      return; // End program
    } else if(keyString.length() == 0 || (tempChar.length() == 0)) {
      System.err.println("Error: Empty keys are not permitted");
      return; // End program
    } // if/else

    // Run encryption/decryption methods depending on command-line input
    if((storeMethod.equals(CipherUtils.caesarCall)) && (storeAct.equals(CipherUtils.actionEn))) { 
      result = CipherUtils.caesarEncrypt(message, keyChar);
    } else if((storeMethod.equals(CipherUtils.caesarCall)) && (storeAct.equals(CipherUtils.actionDe))) {
      result = CipherUtils.caesarDecrypt(message, keyChar);
    } else if((storeMethod.equals(CipherUtils.vigCall)) && (storeAct.equals(CipherUtils.actionEn))) {
      result = CipherUtils.vigenereEncrypt(message, keyString);
    } else if ((storeMethod.equals(CipherUtils.vigCall)) && (storeAct.equals(CipherUtils.actionDe))) {
      result = CipherUtils.vigenereDecrypt(message, keyString);
    } // if/else

    // Prepare to print resulting message
    PrintWriter pen = new PrintWriter(System.out, true);
    
    pen.println(result);

    pen.flush();
    pen.close();
  } // main(String[])
} // class Cipher
