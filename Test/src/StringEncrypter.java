import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 
 * Created for SCR #5408 - MINT_08_2012
 * Used to encrypt and decrypt the GUID details.
 *
 */ 

public class StringEncrypter
{

	public static final String DESEDE_ENCRYPTION_SCHEME = "DESede";
	public static final String DES_ENCRYPTION_SCHEME = "DES";
	public static final String DEFAULT_ENCRYPTION_KEY	= "abcdefghijklmnopqrsuvwxyz1234567890";

	private KeySpec				keySpec;
	private SecretKeyFactory	keyFactory;
	private Cipher				cipher;

	private static final String	UNICODE_FORMAT			= "UTF8";

    public StringEncrypter() throws EncryptionException {
        this( DESEDE_ENCRYPTION_SCHEME, DEFAULT_ENCRYPTION_KEY );
    }

    public StringEncrypter( String encryptionScheme ) throws EncryptionException
	{
		this( encryptionScheme, DEFAULT_ENCRYPTION_KEY );
	}

	public StringEncrypter( String encryptionScheme, String encryptionKey )
			throws EncryptionException
	{

		if ( encryptionKey == null )
				throw new IllegalArgumentException( "encryption key was null" );
		if ( encryptionKey.trim().length() < 24 )
				throw new IllegalArgumentException(
						"encryption key was less than 24 characters" );

		try
		{
			byte[] keyAsBytes = encryptionKey.getBytes( UNICODE_FORMAT );

			if ( encryptionScheme.equals( DESEDE_ENCRYPTION_SCHEME) )
			{
				keySpec = new DESedeKeySpec( keyAsBytes );
			}
			else if ( encryptionScheme.equals( DES_ENCRYPTION_SCHEME ) )
			{
				keySpec = new DESKeySpec( keyAsBytes );
			}
			else
			{
				throw new IllegalArgumentException( "Encryption scheme not supported: "
													+ encryptionScheme );
			}

			keyFactory = SecretKeyFactory.getInstance( encryptionScheme );
			cipher = Cipher.getInstance( encryptionScheme );

		}
		catch (InvalidKeyException e)
		{
			throw new EncryptionException( e );
		}
		catch (UnsupportedEncodingException e)
		{
			throw new EncryptionException( e );
		}
		catch (NoSuchAlgorithmException e)
		{
			throw new EncryptionException( e );
		}
		catch (NoSuchPaddingException e)
		{
			throw new EncryptionException( e );
		}

	}

	public String encrypt( String unencryptedString ) throws EncryptionException
	{
		
			
		
		if ( unencryptedString == null || unencryptedString.trim().length() == 0 )
				throw new IllegalArgumentException(
						"unencrypted string was null or empty" );

		try
		{
			SecretKey key = keyFactory.generateSecret( keySpec );
			cipher.init( Cipher.ENCRYPT_MODE, key );
			byte[] cleartext = unencryptedString.getBytes( UNICODE_FORMAT );
			byte[] ciphertext = cipher.doFinal( cleartext );

			BASE64Encoder base64encoder = new BASE64Encoder();
			return base64encoder.encode( ciphertext );
		}
		catch (Exception e)
		{
			throw new EncryptionException( e );
		} 
	}

	public String decrypt( String encryptedString ) throws EncryptionException
	{
		if ( encryptedString == null || encryptedString.trim().length() <= 0 )
				throw new IllegalArgumentException( "encrypted string was null or empty" );

		try
		{
			SecretKey key = keyFactory.generateSecret( keySpec );
			cipher.init( Cipher.DECRYPT_MODE, key );
			BASE64Decoder base64decoder = new BASE64Decoder();
			byte[] cleartext = base64decoder.decodeBuffer( encryptedString );
			byte[] ciphertext = cipher.doFinal( cleartext );

			return bytes2String( ciphertext );
		}
		catch (Exception e)
		{
			throw new EncryptionException( e );
		}
	}

	private static String bytes2String( byte[] bytes )
	{
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < bytes.length; i++)
		{
			stringBuffer.append( (char) bytes[i] );
		}
		return stringBuffer.toString();
	}

	public static class EncryptionException extends Exception
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public EncryptionException( Throwable t )
		{
			super( t );
		}
	}
	private static String pwdEncryptString;
	private static String pwdDecryptString;
	public static String getPwdString() {
		try {
		
		encryptString("dmsintp");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pwdEncryptString;
	}

	
	private static String encryptString(String EncryptString) throws EncryptionException {
		StringEncrypter stringEncrypter;
		try {
			stringEncrypter = new StringEncrypter();
			pwdEncryptString = stringEncrypter.encrypt(EncryptString);
		} catch (EncryptionException e) {
			System.out.println("Error in encryption");
			e.printStackTrace();
		}
		return pwdEncryptString;
		
}private static String passwordEncryptString;
private static String passwordDecryptString;
public static String getpasswordString() {
	try {
	
		
		decryptString("Fox2rrruemp4hRZCe9GIhw==");
	} catch (Exception e) {
		e.printStackTrace();
	}
	return passwordDecryptString;
}

	private static String decryptString(String passwordEncryptString) throws EncryptionException {
		StringEncrypter stringEncrypter;
		try {
			stringEncrypter = new StringEncrypter();
			passwordDecryptString = stringEncrypter.decrypt(passwordEncryptString);
		} catch (EncryptionException e) {
			System.out.println("Error in encryption");
			e.printStackTrace();
		}
		return passwordDecryptString;	}
	
	public static void main(String[] args) throws Exception {
		
		//Encryption*************************
		System.out.println("Encryption*************************");
		System.out.println("Normal Text : dmsintp");
		String text1 = encryptString("dmsintp");
		System.out.println( "Encrypted Text : " + text1);
		
		System.out.println();
		
		//Decryption*************************
		System.out.println("Decryption*************************");
		System.out.println("Normal Text : ndtfAY5EZ/K5YXXpG0X0Ig==");
		String text2  = decryptString("ndtfAY5EZ/K5YXXpG0X0Ig==");
		System.out.println( "decrypted Text : " +text2 );

	
	}
}