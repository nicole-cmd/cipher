package edu.grinnell.csc207.util;

import java.lang.String;

/**
 * Implementing Cipher encryption and decryption methods for Caesar and Vignere.
 * 
 * @author Nicole Gorrell
 */
public class CipherUtils {
  // +-----------+----------------------------------------------
  // | Constants |
  // +-----------+

  /**
   * Establishing base value (97) to convert between integers and letter values.
   */
  private static final int base = (int) 'a';

  /**
   * Setting the maximum number of (lowercase) letters we can de-/encrypt.
   */
  public static final int max = 26;

  /**
   * String to reference the input "-encode" action in Cipher.java.
   */
  public static final String actionEn = "-encode";

  /**
   * String to reference the input "-decode" action in Cipher.java.
   */
  public static final String actionDe = "-decode";

  /**
   * String to reference the input "-vigenere" cipher in Cipher.java.
   */
  public static final String vigCall = "-vigenere";

  /**
   * String to reference the input "-caesar" cipher in Cipher.java.
   */
  public static final String caesarCall = "-caesar";

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
  public static int letter2int(char letter) {
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

  /**
   * Modified modulo that wraps negative numerators around to the
   * beginning of the alphabet.
   * 
   * @param int num
   *    The numerical value we want to convert into a character. The result of 
   * @return char
   *    The number in its letter form.
   */
  private static int mod(int num) {
    int result = num % max;

    if (result < 0) {
      result += max;
      return result;
    } // if

    return result;
  } // mod(int, int)

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

    for(int i = 0; i < msgArray.length; i++) {
      int ch = letter2int(msgArray[i]);
      int encode = mod((ch + n));

      msgFin[i] = int2letter(encode);
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
   * @param char   letter
   *    The key that will decrypt the input message.
   * @return String
   *    The string message decrypted via the Caesar method.
   */
  public static String caesarDecrypt(String str, char letter) {
    char[] hiddenArray = str.toCharArray();
    char[] msgPlain = new char[hiddenArray.length];
    int n = letter2int(letter);

    for (int i = 0; i < hiddenArray.length; i++) {
      int ch = letter2int(hiddenArray[i]);
      int decode = mod((ch - n));

      msgPlain[i] = int2letter(decode);
    } // for

    return new String(msgPlain);
  } // caesarDecrypt(String, char)

  /**
   * Encrypts an input string message using the Vigenere method and a string
   * key. Message and key both must consist of all lowercase English letters.
   * 
   * @param String str
   *               The message we want to encrypt.
   * @param String key
   *               The key that will determine the resulting encryption.
   * @return String
   *         The string message encrypted via the Vigenere method.
   */
  public static String vigenereEncrypt(String str, String key) {
    char[] msgArray = str.toCharArray();
    char[] keyArray = key.toCharArray();
    char[] msgFin = new char[msgArray.length];
    int i = 0;

    do {
      for (int k = 0; k < keyArray.length; k++) {
        if (msgArray[i] == keyArray.length) {
          keyArray[k] = 0;
        } // if

        int ch = letter2int(msgArray[i]);
        int n = letter2int(keyArray[k]);
        int encode = mod(ch + n);

        msgFin[i] = int2letter(encode);
        i++;
      } // for
    } while(i < msgArray.length); // do/while

    return new String(msgFin);
  } // vigenerEncrypt(String, String)

  /**
   * Decrypts an input string message using the Vigenere method and a string
   * key. Message and key both must consist of all lowercase English letters.
   * 
   * @param String str
   *               The encrypted message we want to decrypt.
   * @param String key
   *               The key that will decrypt the input message.
   * @return String
   *         The string message decrypted via the Vigenere method.
   */
  public static String vigenereDecrypt(String str, String key) {
    char[] hiddenArray = str.toCharArray();
    char[] keyArray = key.toCharArray();
    char[] msgPlain = new char[hiddenArray.length];

    for (int i = 0; i < hiddenArray.length; i++) {
      if (i == keyArray.length - 1) {
        keyArray[i] = 0;
      } // if

      int ch = letter2int(hiddenArray[i]);
      int n = letter2int(keyArray[i]);
      int decode = mod((ch - n));

      msgPlain[i] = int2letter(decode);
    } // for

    return new String(msgPlain);
  } // vignere(String, String)
} // class CipherUtils
