import java.io.File;

public class ThreemaDecrypt {

  public static void main(String[] args) throws Exception{
    File localFile = new File(args[0]);
    byte[] key = cmg_clean.readKey(localFile);
    System.out.println("x\"" + a(key) + "\"");
  }

  public static String a(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte != null)
    {
      char[] arrayOfChar1 = new char[16];
      char[] tmp10_9 = arrayOfChar1;
      tmp10_9[0] = 48;
      char[] tmp15_10 = tmp10_9;
      tmp15_10[1] = 49;
      char[] tmp20_15 = tmp15_10;
      tmp20_15[2] = 50;
      char[] tmp25_20 = tmp20_15;
      tmp25_20[3] = 51;
      char[] tmp30_25 = tmp25_20;
      tmp30_25[4] = 52;
      char[] tmp35_30 = tmp30_25;
      tmp35_30[5] = 53;
      char[] tmp40_35 = tmp35_30;
      tmp40_35[6] = 54;
      char[] tmp46_40 = tmp40_35;
      tmp46_40[7] = 55;
      char[] tmp52_46 = tmp46_40;
      tmp52_46[8] = 56;
      char[] tmp58_52 = tmp52_46;
      tmp58_52[9] = 57;
      char[] tmp64_58 = tmp58_52;
      tmp64_58[10] = 97;
      char[] tmp70_64 = tmp64_58;
      tmp70_64[11] = 98;
      char[] tmp76_70 = tmp70_64;
      tmp76_70[12] = 99;
      char[] tmp82_76 = tmp76_70;
      tmp82_76[13] = 100;
      char[] tmp88_82 = tmp82_76;
      tmp88_82[14] = 101;
      char[] tmp94_88 = tmp88_82;
      tmp94_88[15] = 102;
      char[] arrayOfChar2 = new char[paramArrayOfByte.length * 2];
      int i = 0;
      while (i < paramArrayOfByte.length)
      {
        int j = paramArrayOfByte[i] & 0xFF;
        arrayOfChar2[(i * 2)] = arrayOfChar1[(j >>> 4)];
        arrayOfChar2[(i * 2 + 1)] = arrayOfChar1[(j & 0xF)];
        i += 1;
      }
      return new String(arrayOfChar2);
    }
    return null;
  }
} 
