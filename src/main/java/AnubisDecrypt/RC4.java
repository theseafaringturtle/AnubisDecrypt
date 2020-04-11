package AnubisDecrypt;

// RC4 code copied from malicious dex, with some renamed symbols
public class RC4 {
    private int i = 0;
    private int j = 0;
    private int[] sbox;

    public RC4(byte[] bArr) {
        this.sbox = initSBox(bArr);
    }

    public byte[] decrypt(byte[] bArr) {
        return encrypt(bArr);
    }

    public byte[] encrypt(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length];
        for (int i2 = 0; i2 < bArr.length; i2++) {
            this.i = (this.i + 1) % 256;
            int i3 = this.j;
            int[] iArr = this.sbox;
            int i4 = this.i;
            this.j = (i3 + iArr[i4]) % 256;
            swap(i4, this.j, iArr);
            int[] iArr2 = this.sbox;
            bArr2[i2] = (byte) (iArr2[(iArr2[this.i] + iArr2[this.j]) % 256] ^ bArr[i2]);
        }
        return bArr2;
    }

    private int[] initSBox(byte[] bArr) {
        int[] iArr = new int[256];
        for (int i2 = 0; i2 < 256; i2++) {
            iArr[i2] = i2;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < 256; i4++) {
            i3 = (((i3 + iArr[i4]) + bArr[i4 % bArr.length]) + 256) % 256;
            swap(i4, i3, iArr);
        }
        return iArr;
    }

    private void swap(int i2, int i3, int[] iArr) {
        int i4 = iArr[i2];
        iArr[i2] = iArr[i3];
        iArr[i3] = i4;
    }
}
