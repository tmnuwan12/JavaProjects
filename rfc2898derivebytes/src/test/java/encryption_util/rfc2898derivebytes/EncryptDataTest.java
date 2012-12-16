/**
 * 
 */
package encryption_util.rfc2898derivebytes;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Random;

import javax.annotation.Resource;
import javax.naming.NoPermissionException;

import junit.framework.Assert;

import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.util.encoders.Base64Encoder;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author NThusitha
 * 
 */
public class EncryptDataTest extends BaseTest {

	@Resource
	private SecurityTokenDao tokenDao;

	/**
	 * Try to store security data; username, base64encoded password, salt in the
	 * database
	 */
	@Test
	public void doStoreSecurityData() {
		final String password = "thusitha";

		final int start_index = 0;
		final int end_index = 9;

		String random = "salt" + Integer.toString(new Random().nextInt());

		String salt = random.substring(start_index, end_index); // 10 characters
																// in the salt
																// string
																// (10bytes)

		try {
			Base64Encoder base64Encoder = new Base64Encoder();
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			// store basic security token data in the security database
			os.reset(); // clean up the output stream, flush previous data.
						// Must do this
			base64Encoder.encode(password.getBytes(), 0,
					password.getBytes().length, os);
			HashMap<String, String> params = new HashMap<String, String>();
			params.put("username", "test_user1");
			params.put("password", os.toString()); // string representation
													// of base64 encoded
													// string
			params.put("salt", salt);
			tokenDao.doInsert(params); // only storing username, password,
										// salt. We don't store actual
										// encrypted data in this stage as
										// we don't have db backend
										// implemented for that.
		} catch (IOException e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	@Ignore
	@Test
	public void doEncryptData() {

		final String password = "thusitha";
		final String data = "0123456789@#$@# ";

		final int start_index = 0;
		final int end_index = 9;

		String random = "salt" + Integer.toString(new Random().nextInt());

		String salt = random.substring(start_index, end_index); // 10 characters
																// in the salt
																// string
																// (10bytes)

		EncryptData encryptData = new EncryptData(password);
		try {
			boolean forEncryption = true;
			byte[] out = encryptData
					.getEncryptedData(data, forEncryption, salt);

			Base64Encoder base64Encoder = new Base64Encoder();
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			base64Encoder.encode(out, 0, out.length, os);

			if (os != null) {

				// we have to have proper table structure to store encrypted
				// data
				System.out.print("Test Method: doEncryptData()" + "\r");
				System.out
						.print("encrypted + base64encoded data (in string form)"
								+ os.toString());

			}
		} catch (InvalidKeyException e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		} catch (DataLengthException e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		} catch (IllegalStateException e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		} catch (InvalidCipherTextException e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		} catch (NoPermissionException e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}

	}

	/**
	 * Test for decoding encrypted data
	 * 
	 */
	@Ignore
	@Test
	public void doDecryptData() {
		final String password = "thusitha";
		final String data = "account No: 1234";
		final String charset = "UTF-8";

		EncryptData encryptData = new EncryptData(password); // for encryption

		byte[] encryptedData;
		try {
			boolean forEncryption = true;
			encryptedData = encryptData.getEncryptedData(data, forEncryption,
					null);
			Base64Encoder base64Encoder = new Base64Encoder();
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			base64Encoder.encode(encryptedData, 0, encryptedData.length, os);

			// Ready to be stored the encrypted data
			if (os != null) {

				// Now perform the decryption
				EncryptData decryptData = new EncryptData(password); // for
																		// decryption
				boolean forDecryption = true;
				byte[] decryptedData = decryptData.getDecryptedData(
						os.toString(), forDecryption, null);

				/*
				 * Note: byte[].toString() and new String(decryptedData) is
				 * different.
				 * 
				 * new String(decryptedData) will do an ecoding operation to
				 * retrieve original values but byte[].toString() will only show
				 * the string represantation of bytes.
				 */
				Assert.assertEquals(data,
						new String(decryptedData, charset).trim()); // checks
																	// encrypted
																	// string
																	// and
																	// decrypted
																	// string
																	// are same.
																	// do the
																	// trim
																	// operation
																	// to remote
																	// any
																	// unwanted
																	// whitespaces
			}
		} catch (InvalidKeyException e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		} catch (DataLengthException e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		} catch (NoPermissionException e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		} catch (IllegalStateException e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		} catch (InvalidCipherTextException e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}

	}
}
