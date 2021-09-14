package com.dolby.pastebin.util;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;

public class HashUtil {

  public static String getHashKey(String data) throws Exception {

    MessageDigest md = MessageDigest.getInstance("MD5");
    md.update(data.getBytes());
    byte[] digest = md.digest();
    String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
    return myHash;
  }
}
