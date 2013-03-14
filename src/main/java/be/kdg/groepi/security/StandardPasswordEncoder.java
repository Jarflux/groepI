package be.kdg.groepi.security;

import be.kdg.groepi.utils.CompareUtil;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import org.springframework.security.authentication.encoding.PasswordEncoder;

/**
 * Created with IntelliJ IDEA.
 * User: Vincent
 * Date: 27/02/13
 * Time: 9:42
 * To change this template use File | Settings | File Templates.
 */
public class StandardPasswordEncoder implements PasswordEncoder {

    @Override
    public String encodePassword(String rawPass, Object o) {
        try {
            return CompareUtil.getHashedPassword(rawPass);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
    }

    @Override
    public boolean isPasswordValid(String encPass, String rawPass, Object salt) {
        String tempencode = null;
        try {
            tempencode = CompareUtil.getHashedPassword(rawPass);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return tempencode.equals(encPass);
    }
}
