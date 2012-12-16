/**
 * 
 */
package encryption_util.rfc2898derivebytes;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import junit.framework.Assert;

import org.junit.Test;

/**
 * @author NThusitha
 * 
 *         Test class for testing Rfc2898DeriveBytes key generator.
 */

public class Rfc2898KeyGeneratorTest {

	@Test
	public void test() {
		final String password = "nuwan";

		final String saltString = "hack-me-if-you-can";
		
		//final String saltString = "hack-me";

		final String encoding = "UTF-8";

		final int keyLength = 100;
		
		
		try {
			
			byte[] numberOfBytes = saltString.getBytes(encoding);
			
			if(numberOfBytes.length <= 8)
			{
				Assert.fail("saltString must be greater than 8 bytes");
			}
			
			System.out.print("bytes consumed by saltString: " + numberOfBytes.length);

			byte[] salt = saltString.getBytes(encoding);

			final int iterations = 1000;

			Rfc2898DeriveBytes rfc2898DeriveBytes = new Rfc2898DeriveBytes(
					password, salt, iterations);

			byte[] secureKey = rfc2898DeriveBytes.getBytes(keyLength);

			System.out.println("Rfc2989KeyGenerator key: "
					+ new String(secureKey));
			
			Assert.assertEquals(keyLength, secureKey.length); //checks whether the returned key is with correct given length.
		
			
			
		} catch (UnsupportedEncodingException e) {
			Assert.fail(e.getMessage());
		} catch (InvalidKeyException e) {
			Assert.fail(e.getMessage());
		} catch (NoSuchAlgorithmException e) {
			Assert.fail(e.getMessage());
		}

	}

}
