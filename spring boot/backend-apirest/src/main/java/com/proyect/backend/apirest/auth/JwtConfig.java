package com.proyect.backend.apirest.auth;

public class JwtConfig {

    public static final String LLAVE_SECRETA = "clave.secreta.123456789";

    public static final String RSA_PRIVADA = "-----BEGIN RSA PRIVATE KEY-----\r\n"
          + "MIIEpAIBAAKCAQEAlyHzeVdIOy7ujmrM3mJiKwKPA0Pwlm2rlKslsZmjPKAZ65py\r\n"
          + "bZ5EIvLf6dCcCkE77O0lGHoiz+yqxU6b/PTyiA5MH+WvDmdgb0ovf41uDWTxljE7\r\n"
          + "RkID155JnG207ONnfnGwUOr3G9CsHDAjKgbVrKCuIiWXIIi2SYepY7eeRPi3c1Aq\r\n"
          + "N8mngM6WLu5ka/G0wDERSMR7VxyySTo3d8qKogfSIGKBpUG0PS0ZEDHpukPzIzpa\r\n"
          + "FpNkyg9z7mN2mktwsZttUXVAGwZk4qxNaggLSfjHZdAaINJfeVygRKajFI2L+b/B\r\n"
          + "ljI92kgun90WGt7LdiCOLzTG25cFIfNy+Z/++QIDAQABAoIBABps2kh2JXALtbR7\r\n"
          + "IhUkOKvyh5T3lSKITH7zKeUSywL3r3CNkcJu8NT3iIU2b4Ivg83abSkJefrHE9jp\r\n"
          + "9MZXAe0eKttM6r3m/2qWGxX/YaW/56nuM8IGGuvhhlduqzrcTCVEtaKAYvKfGujW\r\n"
          + "1d8sUMIdWjUoQ3Nx1AWpTcncujSuz4LuANKVDpB0g5O4WwSDmNJWebyjVZvK7YsQ\r\n"
          + "FxeXTmhd3AJgxwB7QoO6vxeEXoXANCBfSygR3x98iar9yzP0fEQuKjUL9xUJAEGt\r\n"
          + "yNcFaMoNab5KFGAZM8NT0ri4nlAkApxtkMr4YCLbMScw2qRTExxJWSm2lyFp3I1m\r\n"
          + "6bex5u0CgYEAxovLQMFq3mMEOGSZFAuUaTlznj5aDQvOSeatvTnxT3WPobHg322i\r\n"
          + "jn4Ha4Hb4HewhTzUqUUKRw34bCMsQGarCUjXG6hZohP+qO650DF1uyoizw5N3foC\r\n"
          + "lk5OXBdbu5jKkysmYJsQT0DwWJprvlG+e8QUHqTpowk87AqgDiAXgLMCgYEAwt3K\r\n"
          + "IOBKEprwuW0KbzWRkHh66urY1QkN/hbHI+cRr4fKo7+EkvmqYoNbrdF1g1krFFio\r\n"
          + "21jDG5q4hs9nxpJben7aHVtQeNpTMaZhSVnDk/wKssH80ytmodCQQ8M8DAm0vx0w\r\n"
          + "awtqPvPhxCPqQOesCqIFKj0RXu9CwMA1raqPP6MCgYEApaU51PPJinJHshjT0fJ7\r\n"
          + "RnLFMC/LB3ljCSropqex2N43D+sMtruMVJ925023OQYCgpamwfY0xeU07ltF7xD4\r\n"
          + "/00DEjMZXOybmStaYQTyuU1FL0Q62Mhd4jLAk3p1cQTfdXsZkJ2uNMD0BKgI3gbr\r\n"
          + "Ce0j8BEcT4ZEipWuxTonAZsCgYEAkThIEggUK5lcHFOumwII4OlocPljbh+5VcoT\r\n"
          + "ziW9gFpqIW3wlreIyrGp+PdH5ALnn9oSvKSBgmjja1PVup4Hnlo6ofYTGn1Z0n5z\r\n"
          + "sf47SmVic6UtjRGjAqQzXsx4S2BQWryeAKgZHaESduFtCcGc7K+hucCY2W7lCFA1\r\n"
          + "sJ6S8pECgYBpIY2j3023wKnHx37gK50TLDtObOoo6fM9IKePuENEKt/NnWtsXNKv\r\n"
          + "s8GHIE1T0VW5g5O0T/icOBOEtT7Feie+m8rZPGiVvsmNCVfvQhA8pmYa3m9lSL05\r\n"
          + "zUhLGnA7DUgKI8r3CreNOB3P40NLpjOkNATbQYUOuqUxPNIvT5Bceg==\r\n" 
          + "-----END RSA PRIVATE KEY-----";

    public static final String RSA_PUBLICA = "-----BEGIN PUBLIC KEY-----\r\n"
    + "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlyHzeVdIOy7ujmrM3mJi\r\n"
    + "KwKPA0Pwlm2rlKslsZmjPKAZ65pybZ5EIvLf6dCcCkE77O0lGHoiz+yqxU6b/PTy\r\n"
    + "iA5MH+WvDmdgb0ovf41uDWTxljE7RkID155JnG207ONnfnGwUOr3G9CsHDAjKgbV\r\n"
    + "rKCuIiWXIIi2SYepY7eeRPi3c1AqN8mngM6WLu5ka/G0wDERSMR7VxyySTo3d8qK\r\n"
    + "ogfSIGKBpUG0PS0ZEDHpukPzIzpaFpNkyg9z7mN2mktwsZttUXVAGwZk4qxNaggL\r\n"
    + "SfjHZdAaINJfeVygRKajFI2L+b/BljI92kgun90WGt7LdiCOLzTG25cFIfNy+Z/+\r\n"
    + "+QIDAQAB\r\n"
    + "-----END PUBLIC KEY-----";

}
