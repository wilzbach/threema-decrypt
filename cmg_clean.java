import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.io.File;
import java.io.InputStream;
import java.io.FileInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;



public class cmg_clean
{
    private static final byte[] a = { -107, 13, 38, 122, -120, -22, 119, 16, -100, 80, -25, 63, 71, -32, 105, 114, -38, -60, 57, 124, -103, -22, 126, 103, -81, -3, -35, 50, -38, 53, -9, 12 };

    // reads the key file and applies some weird byte shifting
    // verifies with array.equals
    public static byte[] readKey(File paramFile) throws Exception
    {
        byte[] c;
        boolean d;
        boolean e;
        byte[] f;
        byte[] g;
        byte[] h;
        boolean i;


        DataInputStream localDataInputStream = new DataInputStream(new FileInputStream(paramFile));
        e = localDataInputStream.readBoolean();
        f = new byte[32];
        localDataInputStream.readFully(f);
        int k = 0;
        while (k < 32)
        {
            byte[] arrayOfByte = f;
            arrayOfByte[k] = ((byte)(arrayOfByte[k] ^ a[k]));
            k += 1;
        }
        g = new byte[8];
        localDataInputStream.readFully(g);
        h = new byte[4];
        localDataInputStream.readFully(h);
        if (e)
        {
            d = true;
            c = null;
        }
        localDataInputStream.close();
        d = false;
        c = f;
        if (!Arrays.equals(c(c), h))
        {
            throw new IOException("Corrupt key");
        }
        return f;
    }

    private static byte[] c(byte[] paramArrayOfByte) throws Exception
    {
        try
        {
            Object localObject = MessageDigest.getInstance("SHA-1");
            ((MessageDigest)localObject).update(paramArrayOfByte);
            paramArrayOfByte = ((MessageDigest)localObject).digest();
            localObject = new byte[4];
            System.arraycopy(paramArrayOfByte, 0, localObject, 0, 4);
            return (byte[])localObject;
        }
        catch (NoSuchAlgorithmException paramArrayOfByte2)
        {
            throw new RuntimeException(paramArrayOfByte2);
        }

    }
  public static Cipher a(byte[] paramArrayOfByte, byte[] key) throws RuntimeException
  {
    try
    {
      Cipher localCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
      IvParameterSpec spec = new IvParameterSpec(paramArrayOfByte);
      localCipher.init(2, new SecretKeySpec(key, "AES"), spec);
      return localCipher;
    }
    catch (Exception paramArrayOfByte2)
    {
      throw new RuntimeException(paramArrayOfByte2);
    }
  }
  
  public static CipherInputStream a(InputStream paramInputStream, byte[] key) throws IOException
  {
    byte[] arrayOfByte = new byte[16];
    if (paramInputStream.read(arrayOfByte) != 16) {
      throw new IOException("Bad encrypted file (invalid IV)");
    }
    return new CipherInputStream(paramInputStream, a(arrayOfByte, key));
  }
 
}
