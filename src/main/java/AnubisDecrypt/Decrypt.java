
package AnubisDecrypt;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Decrypt {
    
    public static byte[] decrypt(String plain1name, String cipher1name, String cipher2name) {
        try {
            System.out.println("Decrypting " + cipher2name);
            byte[] plain = Files.readAllBytes(Paths.get(plain1name));
            byte[] cipher1 = Files.readAllBytes(Paths.get(cipher1name));
            byte[] cipher2 = Files.readAllBytes(Paths.get(cipher2name));
            
            byte[] decrypted;
            if(cipher2.length < cipher1.length) {
                decrypted = new byte[cipher2.length];
            }
            // can't decrypt past size of known file, so truncate it and warn the user.
            else {
                System.err.println("File " + cipher2name + 
                        "can't be fully decrypted since it's longer than " + plain1name);
                decrypted = new byte[cipher1.length];
            }
            for(int i = 0; i < cipher2.length; i++)
            {
                decrypted[i] = (byte) (plain[i] ^ cipher1[i] ^ cipher2[i]);
            }
            return decrypted;
            
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }
}
