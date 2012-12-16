/**
 * 
 */
package encryption_util.rfc2898derivebytes;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.InvalidParameterException;
import java.security.NoSuchAlgorithmException;

import javax.naming.NoPermissionException;

import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.encoders.Base64Encoder;

/**
 * Encrypt/Decrypt Data using derived-keys.
 * 
 * @since 8/12/2012
 * 
 * @author NThusitha
 * 
 * 
 */
public final class EncryptData {

	private PaddedBufferedBlockCipher cipher = null;
	private String plainTextPassword;
	private final int minPasswordLength = 8;
	private final String charset = "UTF-8";

	private final int inputDataOffset = 0; // the offset at which the input data
											// starts.
	private final int outputDataOffset = 0; // the offset from which the output
											// will be copied.

	// private final String salt = "hack-me-if-you-can";

	private final int iterations = 1000;

	private final int securityKeyLength = 32; // Length of the security key for
												// encryption in bytes (256
												// bits) This depends on the
												// cipher engine used.

	public EncryptData(final String plainTextPassword) {

		if (plainTextPassword.isEmpty()
				|| plainTextPassword.length() < minPasswordLength) {
			throw new InvalidParameterException(
					"Minimum acceptable password lenght is "
							+ minPasswordLength);
		} else {
			this.plainTextPassword = plainTextPassword;
			cipher = new PaddedBufferedBlockCipher(new CBCBlockCipher(
					new AESEngine()));
		}

	}

	/**
	 * Encrypt given data.
	 * 
	 * @param data
	 *            - Plain text to be encrypted.
	 * @param forEncryption
	 *            - true for encryption
	 * @param salt
	 *            TODO
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws DataLengthException
	 * @throws IllegalStateException
	 * @throws InvalidCipherTextException
	 * @throws IOException
	 * @throws NoPermissionException
	 */
	public byte[] getEncryptedData(final String data,
			final boolean forEncryption, final String salt)
			throws InvalidKeyException, NoSuchAlgorithmException,
			DataLengthException, IllegalStateException,
			InvalidCipherTextException, IOException, NoPermissionException {

		byte[] out = null;

		if (forEncryption) {
			byte[] inputData = data.getBytes(charset); // convert data into
														// bytes
			int inputLength; // the number of bytes to be copied out of the
								// input
								// array.

			// if input data is too large truncate them.
			if (inputData.length > 200) {

				inputLength = 200;

			} else {
				inputLength = inputData.length;
			}

			Rfc2898DeriveBytes rfc2989DeriveBytes = new Rfc2898DeriveBytes(
					this.plainTextPassword, salt.getBytes(this.charset),
					iterations); // initialise the key with Rfc2989 Derived-Keys

			// System.out.print("Key (in string format)during encryption :" +
			// rfc2989DeriveBytes.getBytes(securityKeyLength).toString() +
			// "\r");
			System.out
					.print("Key (in string format)during encryption :"
							+ new String(rfc2989DeriveBytes
									.getBytes(securityKeyLength)));
			cipher.init(
					true,
					new KeyParameter(rfc2989DeriveBytes
							.getBytes(securityKeyLength)));

			int minOutputLength = cipher.getOutputSize(inputData.length); // generate
																			// how
																			// big
																			// output
																			// will
																			// be
																			// for
																			// update
																			// and
																			// doFinal

			byte[] encryptedData = new byte[minOutputLength];

			int processedLength = cipher.processBytes(inputData,
					inputDataOffset, inputLength, encryptedData,
					outputDataOffset);
			// TODO: Write a test case for this (check the number of output
			// bytes
			// copied out) and debug and see what is happening after this line
			// of
			// the code.

			cipher.doFinal(encryptedData, processedLength); // handle last
															// block.
															// offset is set to
															// 'outputLength'
															// meaning doFinal
															// will
															// outputs the final
															// block after
															// 'outputLenght'
															// number
															// of bytes in the
															// buffer

			// Need more encoding?
			// Hex encoding
			// String encryptedString = new String(Hex.encode(encryptedData));
			// Base64Encoding

			out = encryptedData;

			System.out.print("Encrypted Data in String format :"
					+ new String(out) + "\r");
		} else {
			throw new NoPermissionException(
					"Can't invoke getEncryptedData() when 'forEncryption' = false");
		}

		return out;

	}

	/**
	 * @param data - Encrypted + encoded data string
	 * @param forDecryption - true for decryption
	 * @param salt - random salt string which was used for encryption
	 * @return
	 * @throws IOException
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws DataLengthException
	 * @throws IllegalStateException
	 * @throws InvalidCipherTextException
	 * @throws NoPermissionException
	 */
	public byte[] getDecryptedData(final String data,
			final boolean forDecryption, String salt) throws IOException,
			InvalidKeyException, NoSuchAlgorithmException, DataLengthException,
			IllegalStateException, InvalidCipherTextException,
			NoPermissionException {
		byte[] out = null;

		if (forDecryption) {
			ByteArrayOutputStream os = new ByteArrayOutputStream();

			Base64Encoder base64Encoder = new Base64Encoder();
			base64Encoder.decode(data, os);

			byte[] encryptedData = os.toByteArray();

			Rfc2898DeriveBytes rfc2989DeriveBytes = new Rfc2898DeriveBytes(
					this.plainTextPassword, salt.getBytes(this.charset),
					iterations); // initialise the key with Rfc2989 Derived-Keys

			// System.out.print("Key (in string format) during decryption :" +
			// rfc2989DeriveBytes.getBytes(securityKeyLength).toString() +
			// "\r");
			System.out
					.print("Key (in string format) during decryption :"
							+ new String(rfc2989DeriveBytes
									.getBytes(securityKeyLength)) + "\r");
			cipher.init(
					false,
					new KeyParameter(rfc2989DeriveBytes
							.getBytes(securityKeyLength))); // false for
															// decryption

			int minOutputLength = cipher.getOutputSize(encryptedData.length);

			byte[] decryptedData = new byte[minOutputLength];

			int processedLength = cipher.processBytes(encryptedData,
					inputDataOffset, encryptedData.length, decryptedData,
					outputDataOffset);

			cipher.doFinal(decryptedData, processedLength);
			out = decryptedData;
			System.out.print("Decrypted data in string format: "
					+ new String(out) + "\r");

		} else {
			throw new NoPermissionException(
					"Can't invoke getDecryptedData() when 'forDecryption' = false");
		}
		return out;

	}
}
