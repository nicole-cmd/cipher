package edu.grinnell.csc207.util;

import java.lang.String;

/**
 * Implementing Cipher encryption and decryption methods for Caesar and Vignere.
 * 
 * @author Nicole Gorrell
 */
public class CipherUtils {
  // +------------------+----------------------------------------------
  // | Global Variables |
  // +------------------+

  /**
   * Establishing base value (97) to convert between integers and letter values.
   */
  private static int base = (int) 'a';

  /**
   * Setting the maximum number of (lowercase) letters we can de-/encrypt.
   */
  public static int max = 26;

  /**
   * String to reference the input "-encode" action in Cipher.java.
   */
  public static String actionEn = "-encode";

  /**
   * String to reference the input "-decode" action in Cipher.java.
   */
  public static String actionDe = "-decode";

  /**
   * String to reference the input "-vigenere" cipher in Cipher.java.
   */
  public static String vigCall = "-vigenere";

  /**
   * String to reference the input "-caesar" cipher in Cipher.java.
   */
  public static String caesarCall = "-caesar";  

  // +----------------+--------------------------------------------------------
  // | Helper Methods |
  // +----------------+

  /**
   * Convert the letter character into its corresponding integer value,
   * within the specified alphabetical range of 0-25.
   * 
   * @param char letter
   *    The letter we want to rebase.
   * @return int
   *    The letter in its numerical form.
   */
  private static int letter2int(char letter) {
    return (int) letter - base;
  } // letter2int(char)

  /**
   * Convert the number into its corresponding letter, within the
   * specified character range of 'a-z'.
   * 
   * @param int i 
   *    The numerical value we want to convert into a character.
   * @return char
   *    The number in its letter form.
   */
  private static char int2letter(int i) {
    return (char) (i + base);
  } // int2letter(int)

  // +-----------------+--------------------------------------------------------
  // | Primary Methods |
  // +-----------------+

  /**
   * Encrypts an input string message using the Caesar method and a key
   * constant. Message must consist of all lowercase English letters, and
   * the key must be one lowercase English letter in character form.
   * 
   * @param String str
   *    The message we want to encrypt.
   * @param char letter
   *    The key that will determine the resulting encryption.
   * @return String
   *    The string message encrypted via the Caesar method.
   */
  public static String caesarEncrypt(String str, char letter) {

    char[] msgArray = str.toCharArray();
    char[] msgFin = new char[msgArray.length];
    int n = letter2int(letter);

    for (char index : msgArray) {
      int ch = letter2int(msgArray[index]);
      int encode = (ch + n) % max;

      if (encode < 0) {
        int fin = encode + max;
        msgFin[index] = int2letter(fin);
      } else {
        msgFin[index] = int2letter(encode);
      } // if...else
    } // for

    return new String(msgFin);
  } // caesarEncrypt(String, char)

  /**
   * Decrypts an input string message using the Caesar method and a key
   * constant. Message must consist of all lowercase English letters, and
   * the key must be one lowercase English letter in character form.
   * 
   * @param String str
   *    The encrypted message we want to decrypt.
   * @param char letter
   *    The key that will decrypt the input message.
   * @return String
   *    The string message decrypted via the Caesar method.
   */
  public static String caesarDecrypt(String str, char letter) {
    char[] hiddenArray = str.toCharArray();
    char[] msgPlain = new char[hiddenArray.length];
    int n = letter2int(letter);

    for (char index : hiddenArray) {
      int ch = letter2int(hiddenArray[index]);
      int decode = (ch - n) % max;

      if (decode < 0) {
        int fin = decode + max;
        msgPlain[index] = int2letter(fin);
      } else {
        msgPlain[index] = int2letter(decode);
      } // if...else
    } // for

    return new String(msgPlain);
  } // caesarDecrypt(String, char)

  /**
   * Encrypts an input string message using the Vigenere method and a string
   * key. Message and key both must consist of all lowercase English letters.
   * 
   * @param String str
   *    The message we want to encrypt.
   * @param String key
   *    The key that will determine the resulting encryption.
   * @return String
   *    The string message encrypted via the Vigenere method.
   */
  public static String vigenereEncrypt(String str, String key) {
    char[] msgArray = str.toCharArray();
    char[] keyArray = key.toCharArray();
    char[] msgFin = new char[msgArray.length];

    for (char index : msgArray) {
      if (index == keyArray.length - 1) {
        keyArray[index] = 0;
      } // if

      int ch = letter2int(msgArray[index]);
      int n = letter2int(keyArray[index]);
      int encode = (ch + n) % max;

      if (encode < 0) {
        int fin = encode + max;
        msgFin[index] = int2letter(fin);
      } else {
        msgFin[index] = int2letter(encode);
      } // if...else
    } // for
  
    // if (key.length < msgArray.length) {
    // key.concat(indexOf(key.charAt()));
    // }
    // if (key.length() < msgArray.length) {
      //   String keyLong = key.repeat(Math.floorDiv(str.length(), key.length()));
      //   char[] keyArray = keyLong.toCharArray();
      // }
    return new String(msgFin);
  } // vigenerEncrypt(String, String)

  /**
   * Decrypts an input string message using the Vigenere method and a string
   * key. Message and key both must consist of all lowercase English letters.
   * 
   * @param String str
   *    The encrypted message we want to decrypt.
   * @param String key
   *    The key that will decrypt the input message.
   * @return String
   *    The string message decrypted via the Vigenere method.
   */
  public static String vigenereDecrypt(String str, String key) {
    char[] hiddenArray = str.toCharArray();
    char[] keyArray = key.toCharArray();
    char[] msgPlain = new char[hiddenArray.length];

    for (char index : hiddenArray) {
      if (index == keyArray.length - 1) {
        keyArray[index] = 0;
      } // if

      int ch = letter2int(hiddenArray[index]);
      int n = letter2int(keyArray[index]);
      int decode = (ch + n) % max;

      if (decode < 0) {
        int fin = decode - max;
        msgPlain[index] = int2letter(fin);
      } else {
        msgPlain[index] = int2letter(decode);
      } // if...else
    } // for

    return new String(msgPlain);
  } // vignere(String, String)
} // class CipherUtils
