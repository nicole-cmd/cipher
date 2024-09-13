package edu.grinnell.csc207.main;

import java.io.PrintWriter;
import java.lang.String;
import edu.grinnell.csc207.main.CipherUtils;

@SuppressWarnings("unused")
public class Cipher {
  public static void main(String[] args) {
    /** will store command line inputs depending on how they match desired properties
     *  in below for loop. will be referenced when calling en-/decoding methods. */
    String storeAct = "";
    String storeMethod = "";
    String message = "";
    String result = "";
    char keyChar = '\0';
    String keyString = "";

    //checks for the correct amount of input parameters
    if (args.length != 4) {
      System.err.println("Error: Expected four parameters, received " + args.length);
    }

    //reads through inputs and assigns to respective variables 
    for(String a : args) {
      if ((a.equals(CipherUtils.actionEn) || (a.equals(CipherUtils.actionDe)))) {
        storeAct = a;
      } else if (a.equals(CipherUtils.vigCall) || (a.equals(CipherUtils.caesarCall))) {
        storeMethod = a;
      } else if ((storeMethod.startsWith("-c")) && a.length() == 1) {
        keyChar = a.charAt(0);
      } else if ((storeMethod.startsWith("-v")) && a.length() >= 1) {
        keyString = a;
      } else if (a.charAt(0) > CipherUtils.max ){
        System.err.println("Error: Strings must only contain lowercase letters");  
      } else {
        message = a;
      }
    } //for

    String tempChar = String.valueOf(keyChar); //like to assess if there is only one char input

    if (storeAct != CipherUtils.actionEn || storeAct != CipherUtils.actionDe) {
      System.err.println("Error: No valid action specified. Legal values are 'encode' and 'decode'");
    } else if ((tempChar.length() != 1) && storeMethod.equals(CipherUtils.caesarCall)) {
      System.err.println("Error: Caesar cipher requires a one-character key");
    } else if (keyString.length() < 1 || (tempChar.length() != 1)) {
      System.err.println("Error: Empty keys are not permitted");
    }

//i need to do somethin about the error msgs
    if ((storeMethod.equals(CipherUtils.caesarCall)) && (storeAct.equals(CipherUtils.actionEn)) &&
        (keyChar != '\0')) { 
          result = CipherUtils.caesarEncrypt(message, keyChar);
        } else if ((storeMethod.equals(CipherUtils.caesarCall)) && keyString != "") {
          System.err.println("Error: Entered a multi-character key for a Caesar method. Must use one character.");
        } else if ((storeMethod.equals(CipherUtils.caesarCall)) && (storeAct.equals(CipherUtils.actionDe)) &&
                  (keyChar != '\0')) {
                  result = CipherUtils.caesarDecrypt(message, keyChar);
                  } else if ((storeMethod.equals(CipherUtils.vigCall)) && (storeAct.equals(CipherUtils.actionEn))) {
                            result = CipherUtils.vigenereEncrypt(message, keyString);
                            } else if ((storeMethod.equals(CipherUtils.vigCall)) && (storeAct.equals(CipherUtils.actionDe))) {
                                        result = CipherUtils.vigenereDecrypt(message, keyString);
                            }

    PrintWriter pen = new PrintWriter(System.out, true);
    
    pen.println(result);

    pen.flush();
    pen.close();
    }
} //class Cipher
