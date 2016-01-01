import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import javax.crypto.CipherInputStream;

public class ThreemaDecryptImg
{

    public static void main(String[] args) throws Exception
    {
        File localFile = new File(args[0]);
        File localImg = new File(args[1]);

        byte[] key = cmg_clean.readKey(localFile);
        FileOutputStream fos = new FileOutputStream(args[1] + ".plain.jpg");
        FileInputStream fis = new FileInputStream(localImg);
        CipherInputStream cis = cmg_clean.a(fis, key);
        byte[] d = new byte[8];
        int b;
        while ((b = cis.read(d)) != -1)
        {
            fos.write(d, 0, b);
        }
        fos.flush();
        fos.close();
        fis.close();
    }
}
