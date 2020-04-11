package AnubisDecrypt;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {
        if(args.length != 3){
            System.out.println("Usage: java Main [Plaintext file], [Corresponding encrypted file],"
                    + " [Directory with encrypted files]");
            return;
        }
        
        String plain = args[0];
        String cipher = args[1];
        String dirName = args[2];
        File cryptedDir = new File(dirName);
        if(!cryptedDir.isDirectory()){
            System.err.println(dirName + " is not a directory");
            return;
        }
        String[] fileNames = cryptedDir.list();
        File outDir = new File(cryptedDir + "_decrypted");
        outDir.mkdir();
        for(String name : fileNames) {
            if(!name.endsWith(".AnubisCrypt")){
                continue;
            }
            byte[] dec = Decrypt.decrypt(plain, cipher, cryptedDir + File.separator + name);
            String fileName = outDir.getPath() + File.separator + name.replace(".AnubisCrypt", "");
            try {
                File outFile = new File(fileName);
                Files.write(Paths.get(fileName), dec);
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
    
}
