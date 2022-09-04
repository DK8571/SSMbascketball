package test;

import org.junit.Test;

import java.security.NoSuchAlgorithmException;

import static com.baoshi.programmer.util.MD5.getMd5;

public class test {
    @Test
    public  void testMD5() throws NoSuchAlgorithmException {
        System.out.println(getMd5("1234567"));
    }
}