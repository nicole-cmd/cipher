package edu.grinnell.csc207.main;

import java.lang.String;

/**
 * Implementing Cipher inputs and procedures.
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

  // +---------+--------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Convert the letter character into its corresponding integer value,
   * within the specified alphabetical range of 0-25.
   * 
   * @return the letter in its numerical form.
   */
  private static int letter2int(char letter) {
    return (int) letter - base;
  } // letter2int(char)

  /**
   * Convert the number into its corresponding letter, within the
   * specified character range of 'a-z'.
   * 
   * @return the number in its letter form.
   */
  private static char int2letter(int i) {
    return (char) (i + base); // STUB
  } // int2letter(int)

  /**
   * Encrypts an input string message using the Caesar method and a key
   * constant. Message must consist of all lowercase English letters, and
   * the key must be one lowercase English letter in character form.
   * 
   * @return the string message encrypted via the Caesar method.
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
      }
    } // for

    return new String(msgFin);
  } // caesarEncrypt(String, char)

  /**
   * Decrypts an input string message using the Caesar method and a key
   * constant. Message must consist of all lowercase English letters, and
   * the key must be one lowercase English letter in character form.
   * 
   * @return the string message decrypted via the Caesar method.
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
      }
    } // for

    return new String(msgPlain);
  } // caesarDecrypt(String, char)

  /**
   * Encrypts an input string message using the Vigenere method and a string
   * key. Message and key both must consist of all lowercase English letters.
   * 
   * @return the string message encrypted via the Vigenere method.
   */
  public static String vigenereEncrypt(String str, String key) {
    char[] msgArray = str.toCharArray();
    char[] keyArray = key.toCharArray();
    char[] msgFin = new char[msgArray.length];

    for (char index : msgArray) {
      if (index == keyArray.length - 1) {
        keyArray[index] = 0;
      }

      int ch = letter2int(msgArray[index]);
      int n = letter2int(keyArray[index]);
      int encode = (ch + n) % max;

      if (encode < 0) {
        int fin = encode + max;
        msgFin[index] = int2letter(fin);
      } else {
        msgFin[index] = int2letter(encode);
      }
    } // for
  
    // if (key.length < msgArray.length) {
    // key.concat(indexOf(key.charAt()));
    // }
    // if (key.length() < msgArray.length) {
      //   String keyLong = key.repeat(Math.floorDiv(str.length(), key.length()));
      //   char[] keyArray = keyLong.toCharArray();
      // }
    return new String(msgFin);
  } //vigenerEncrypt(String, String)

  /**
   * Decrypts an input string message using the Vigenere method and a string
   * key. Message and key both must consist of all lowercase English letters.
   * 
   * @return the string message decrypted via the Vigenere method.
   */
  public static String vigenereDecrypt(String str, String key) {
    char[] hiddenArray = str.toCharArray();
    char[] keyArray = key.toCharArray();
    char[] msgPlain = new char[hiddenArray.length];

    for (char index : hiddenArray) {
      if (index == keyArray.length - 1) {
        keyArray[index] = 0;
      }

      int ch = letter2int(hiddenArray[index]);
      int n = letter2int(keyArray[index]);
      int decode = (ch + n) % max;

      if (decode < 0) {
        int fin = decode - max;
        msgPlain[index] = int2letter(fin);
      } else {
        msgPlain[index] = int2letter(decode);
      }
    } // for

    return new String(msgPlain);
  } //vignere(String, String)
} // class CipherUtils
