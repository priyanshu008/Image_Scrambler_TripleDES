import java.io.*;
import java.util.*;
import java.nio.file.*;
import java.security.SecureRandom;
import java.awt.image.*;
import javax.imageio.ImageIO;

class DesTables {
    static final int[] IP = {
        58,50,42,34,26,18,10,2,60,52,44,36,28,20,12,4,
        62,54,46,38,30,22,14,6,64,56,48,40,32,24,16,8,
        57,49,41,33,25,17,9,1,59,51,43,35,27,19,11,3,
        61,53,45,37,29,21,13,5,63,55,47,39,31,23,15,7
    };
    static final int[] IP_1 = {
        40,8,48,16,56,24,64,32,39,7,47,15,55,23,63,31,
        38,6,46,14,54,22,62,30,37,5,45,13,53,21,61,29,
        36,4,44,12,52,20,60,28,35,3,43,11,51,19,59,27,
        34,2,42,10,50,18,58,26,33,1,41,9,49,17,57,25
    };
    static final int[] PC1 = {
        57,49,41,33,25,17,9,1,58,50,42,34,26,18,10,2,
        59,51,43,35,27,19,11,3,60,52,44,36,63,55,47,39,
        31,23,15,7,62,54,46,38,30,22,14,6,61,53,45,37,
        29,21,13,5,28,20,12,4
    };
    static final int[] PC2 = {
        14,17,11,24,1,5,3,28,15,6,21,10,23,19,12,4,
        26,8,16,7,27,20,13,2,41,52,31,37,47,55,30,40,
        51,45,33,48,44,49,39,56,34,53,46,42,50,36,29,32
    };
    static final int[] SHIFT_BITS = {
        1,1,2,2,2,2,2,2,1,2,2,2,2,2,2,1
    };
    static final int[] E = {
        32,1,2,3,4,5,4,5,6,7,8,9,8,9,10,11,
        12,13,12,13,14,15,16,17,16,17,18,19,20,21,
        20,21,22,23,24,25,24,25,26,27,28,29,28,29,
        30,31,32,1
    };
    static final int[][][][] S = {
        {
            {
                {14,4,13,1,2,15,11,8,3,10,6,12,5,9,0,7},
                {0,15,7,4,14,2,13,1,10,6,12,11,9,5,3,8},
                {4,1,14,8,13,6,2,11,15,12,9,7,3,10,5,0},
                {15,12,8,2,4,9,1,7,5,11,3,14,10,0,6,13}
            },
            {
                {15,1,8,14,6,11,3,4,9,7,2,13,12,0,5,10},
                {3,13,4,7,15,2,8,14,12,0,1,10,6,9,11,5},
                {0,14,7,11,10,4,13,1,5,8,12,6,9,3,2,15},
                {13,8,10,1,3,15,4,2,11,6,7,12,0,5,14,9}
            },
            {
                {10,0,9,14,6,3,15,5,1,13,12,7,11,4,2,8},
                {13,7,0,9,3,4,6,10,2,8,5,14,12,11,15,1},
                {13,6,4,9,8,15,3,0,11,1,2,12,5,10,14,7},
                {1,10,13,0,6,9,8,7,4,15,14,3,11,5,2,12}
            },
            {
                {7,13,14,3,0,6,9,10,1,2,8,5,11,12,4,15},
                {13,8,11,5,6,15,0,3,4,7,2,12,1,10,14,9},
                {10,6,9,0,12,11,7,13,15,1,3,14,5,2,8,4},
                {3,15,0,6,10,1,13,8,9,4,5,11,12,7,2,14}
            },
            {
                {2,12,4,1,7,10,11,6,8,5,3,15,13,0,14,9},
                {14,11,2,12,4,7,13,1,5,0,15,10,3,9,8,6},
                {4,2,1,11,10,13,7,8,15,9,12,5,6,3,0,14},
                {11,8,12,7,1,14,2,13,6,15,0,9,10,4,5,3}
            },
            {
                {12,1,10,15,9,2,6,8,0,13,3,4,14,7,5,11},
                {10,15,4,2,7,12,9,5,6,1,13,14,0,11,3,8},
                {9,14,15,5,2,8,12,3,7,0,4,10,1,13,11,6},
                {4,3,2,12,9,5,15,10,11,14,1,7,6,0,8,13}
            },
            {
                {4,11,2,14,15,0,8,13,3,12,9,7,5,10,6,1},
                {13,0,11,7,4,9,1,10,14,3,5,12,2,15,8,6},
                {1,4,11,13,12,3,7,14,10,15,6,8,0,5,9,2},
                {6,11,13,8,1,4,10,7,9,5,0,15,14,2,3,12}
            },
            {
                {13,2,8,4,6,15,11,1,10,9,3,14,5,0,12,7},
                {1,15,13,8,10,3,7,4,12,5,6,11,0,14,9,2},
                {7,11,4,1,9,12,14,2,0,6,10,13,15,3,5,8},
                {2,1,14,7,4,10,8,13,15,12,9,0,3,5,6,11}
            }
        }
    };
    static final int[] P = {
        16,7,20,21,29,12,28,17,1,15,23,26,5,18,31,10,
        2,8,24,14,32,27,3,9,19,13,30,6,22,11,4,25
    };
}

class BitUtils {
    public static boolean[] bytesToBits(byte[] bytes, int numBits) {
        boolean[] bits = new boolean[numBits];
        for (int i = 0; i < numBits; i++) {
            bits[i] = ((bytes[i / 8] >> (7 - (i % 8))) & 1) == 1;
        }
        return bits;
    }
    public static long bitsToLong(boolean[] bits) {
        long value = 0;
        for (int i = 0; i < Math.min(bits.length, 64); i++) {
            if (bits[i]) value |= (1L << (bits.length - 1 - i));
        }
        return value;
    }
    public static boolean[] longToBits(long value, int n) {
        boolean[] bits = new boolean[n];
        for (int i = 0; i < n; i++) {
            bits[i] = ((value >> (n - 1 - i)) & 1) == 1;
        }
        return bits;
    }
    public static byte[] bitsToBytes(boolean[] bits) {
        int len = (bits.length + 7) / 8;
        byte[] bytes = new byte[len];
        for (int i = 0; i < bits.length; i++) {
            if (bits[i]) bytes[i / 8] |= (1 << (7 - (i % 8)));
        }
        return bytes;
    }
}

class DesCore {
    public static boolean[] permute(boolean[] input, int[] table, int outSize) {
        boolean[] output = new boolean[outSize];
        for (int i = 0; i < outSize; i++) {
            output[i] = input[table[i] - 1];
        }
        return output;
    }
    public static boolean[] shiftLeft(boolean[] k, int s) {
        boolean[] shifted = new boolean[28];
        for (int i = 0; i < 28; i++) {
            shifted[i] = k[(i + s) % 28];
        }
        return shifted;
    }
    public static boolean[][] generateRoundKeys(boolean[] key) {
        boolean[] pk = permute(key, DesTables.PC1, 56);
        boolean[] l = Arrays.copyOfRange(pk, 0, 28);
        boolean[] r = Arrays.copyOfRange(pk, 28, 56);
        boolean[][] roundKeys = new boolean[16][];
        for (int i = 0; i < 16; i++) {
            l = shiftLeft(l, DesTables.SHIFT_BITS[i]);
            r = shiftLeft(r, DesTables.SHIFT_BITS[i]);
            boolean[] combined = new boolean[56];
            System.arraycopy(l, 0, combined, 0, 28);
            System.arraycopy(r, 0, combined, 28, 28);
            roundKeys[i] = permute(combined, DesTables.PC2, 48);
        }
        return roundKeys;
    }
    public static boolean[] sboxSubstitution(boolean[] input) {
        boolean[] output = new boolean[32];
        for (int i = 0; i < 8; i++) {
            int sb = i * 6;
            int r = (input[sb] ? 2 : 0) + (input[sb + 5] ? 1 : 0);
            int c = (input[sb + 1] ? 8 : 0) + (input[sb + 2] ? 4 : 0) + (input[sb + 3] ? 2 : 0) + (input[sb + 4] ? 1 : 0);
            int val = DesTables.S[0][i][r][c];
            for (int j = 0; j < 4; j++) {
                output[i * 4 + j] = ((val >> (3 - j)) & 1) == 1;
            }
        }
        return output;
    }
    public static boolean[] desProcess(boolean[] block, boolean[][] roundKeys, boolean isEncrypt) {
        boolean[] pb = permute(block, DesTables.IP, 64);
        boolean[] l = Arrays.copyOfRange(pb, 0, 32);
        boolean[] r = Arrays.copyOfRange(pb, 32, 64);
        for (int i = 0; i < 16; i++) {
            boolean[] tr = r.clone();
            boolean[] er = permute(r, DesTables.E, 48);
            boolean[] k = isEncrypt ? roundKeys[i] : roundKeys[15 - i];
            for (int j = 0; j < 48; j++) er[j] ^= k[j];
            boolean[] so = sboxSubstitution(er);
            boolean[] pp = permute(so, DesTables.P, 32);
            for (int j = 0; j < 32; j++) r[j] = l[j] ^ pp[j];
            l = tr;
        }
        boolean[] fb = new boolean[64];
        System.arraycopy(r, 0, fb, 0, 32);
        System.arraycopy(l, 0, fb, 32, 32);
        return permute(fb, DesTables.IP_1, 64);
    }
    public static boolean[] tripleDesProcess(boolean[] block, boolean[][] k1, boolean[][] k2, boolean[][] k3, boolean isEncrypt) {
        if (isEncrypt) {
            return desProcess(desProcess(desProcess(block, k1, true), k2, false), k3, true);
        } else {
            return desProcess(desProcess(desProcess(block, k3, false), k2, true), k1, false);
        }
    }
}

class DesPrng {
    private boolean[] state;
    private boolean[][] k1, k2, k3;

    public DesPrng(byte[] keyBytes) {
        if (keyBytes.length != 24)
            throw new IllegalArgumentException("Key must be 24 bytes.");
        state = BitUtils.bytesToBits(Arrays.copyOfRange(keyBytes, 0, 8), 64);
        k1 = DesCore.generateRoundKeys(BitUtils.bytesToBits(Arrays.copyOfRange(keyBytes, 0, 8), 64));
        k2 = DesCore.generateRoundKeys(BitUtils.bytesToBits(Arrays.copyOfRange(keyBytes, 8, 16), 64));
        k3 = DesCore.generateRoundKeys(BitUtils.bytesToBits(Arrays.copyOfRange(keyBytes, 16, 24), 64));
    }

    public long next() {
        state = DesCore.tripleDesProcess(state, k1, k2, k3, true);
        return BitUtils.bitsToLong(state);
    }
}

public class TripleDESJava {
    private static final int BLOCK_SIZE = 16;

    public static byte[] hexToBytes(String hex) {
        if (hex.length() != 48)
            throw new IllegalArgumentException("Key must be a 48-character hex string.");
        byte[] bytes = new byte[24];
        for (int i = 0; i < 24; i++) {
            bytes[i] = (byte) Integer.parseInt(hex.substring(i * 2, i * 2 + 2), 16);
        }
        return bytes;
    }
    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) sb.append(String.format("%02x", b));
        return sb.toString();
    }
    public static void generateKeyFile(String path) throws IOException {
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[24];
        random.nextBytes(key);
        String hexKey = bytesToHex(key);
        Files.write(Paths.get(path), hexKey.getBytes());
        System.out.println("Successfully generated key file: " + path);
    }
    public static int[] generateShuffleMap(int numBlocks, DesPrng prng) {
        int[] map = new int[numBlocks];
        for (int i = 0; i < numBlocks; i++) map[i] = i;
        for (int i = numBlocks - 1; i > 0; i--) {
            // FIX: The prng.next() method can return a negative long. The modulo operator (%) in Java
            // can return a negative result if the dividend is negative, which would cause an
            // ArrayIndexOutOfBoundsException.
            // To fix this, we ensure the random number is non-negative before the modulo operation.
            // Using `& Long.MAX_VALUE` is a safe way to do this, as it clears the sign bit
            // and correctly handles the edge case of Long.MIN_VALUE.
            int j = (int) ((prng.next() & Long.MAX_VALUE) % (i + 1));
            int tmp = map[i];
            map[i] = map[j];
            map[j] = tmp;
        }
        return map;
    }

    // Fully error-proof block copy: never writes/reads out of bounds
    public static void copyBlock(byte[] src, byte[] dest, int srcX, int srcY, int destX, int destY,
                                 int blockSize, int width, int height, int channels) {
        // Compute actual width/height for source and destination blocks
        int srcBlockW = Math.max(0, Math.min(blockSize, width - srcX));
        int srcBlockH = Math.max(0, Math.min(blockSize, height - srcY));
        int destBlockW = Math.max(0, Math.min(blockSize, width - destX));
        int destBlockH = Math.max(0, Math.min(blockSize, height - destY));
        int blockW = Math.max(0, Math.min(srcBlockW, destBlockW));
        int blockH = Math.max(0, Math.min(srcBlockH, destBlockH));

        // If nothing to copy, skip
        if (blockW <= 0 || blockH <= 0) return;

        for (int y = 0; y < blockH; y++) {
            for (int x = 0; x < blockW; x++) {
                int srcPx = (srcY + y) * width + (srcX + x);
                int destPx = (destY + y) * width + (destX + x);
                for (int c = 0; c < channels; c++) {
                    int srcIdx = srcPx * channels + c;
                    int destIdx = destPx * channels + c;
                    // Bounds check
                    if (srcIdx < 0 || srcIdx >= src.length) continue;
                    if (destIdx < 0 || destIdx >= dest.length) continue;
                    dest[destIdx] = src[srcIdx];
                }
            }
        }
    }

    public static void processImage(boolean isEncrypt, String inputPath, String outputPath, String keyPath) throws Exception {
        String hexKey = new String(Files.readAllBytes(Paths.get(keyPath))).trim();
        byte[] keyBytes = hexToBytes(hexKey);

        BufferedImage img = ImageIO.read(new File(inputPath));
        if (img == null) throw new IOException("Failed to load image: " + inputPath);

        int width = img.getWidth();
        int height = img.getHeight();
        int channels = img.getRaster().getNumBands();
        int blocksX = (int)Math.ceil((double)width / BLOCK_SIZE);
        int blocksY = (int)Math.ceil((double)height / BLOCK_SIZE);
        int numBlocks = blocksX * blocksY;

        System.out.println("Image loaded: " + width + "x" + height + ", " + numBlocks + " blocks.");
        System.out.println(isEncrypt ? "Scrambling..." : "Descrambling...");

        byte[] imgData = new byte[width * height * channels];
        int idx = 0;
        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++)
                for (int c = 0; c < channels; c++)
                    imgData[idx++] = (byte) img.getRaster().getSample(x, y, c);

        DesPrng prng = new DesPrng(keyBytes);
        int[] shuffleMap = generateShuffleMap(numBlocks, prng);

        byte[] outImgData = new byte[width * height * channels];

        for (int i = 0; i < numBlocks; i++) {
            int originalIdx = isEncrypt ? i : shuffleMap[i];
            int newIdx = isEncrypt ? shuffleMap[i] : i;
            if (originalIdx < 0 || originalIdx >= numBlocks || newIdx < 0 || newIdx >= numBlocks) {
                System.err.println("Invalid block index: originalIdx=" + originalIdx + ", newIdx=" + newIdx);
                continue;
            }
            int srcX = (originalIdx % blocksX) * BLOCK_SIZE;
            int srcY = (originalIdx / blocksX) * BLOCK_SIZE;
            int destX = (newIdx % blocksX) * BLOCK_SIZE;
            int destY = (newIdx / blocksX) * BLOCK_SIZE;

            // Only copy if both blocks start inside the image
            if (srcX < width && srcY < height && destX < width && destY < height) {
                copyBlock(imgData, outImgData, srcX, srcY, destX, destY, BLOCK_SIZE, width, height, channels);
            }
        }

        BufferedImage outImg = new BufferedImage(width, height, img.getType());
        idx = 0;
        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++)
                for (int c = 0; c < channels; c++)
                    outImg.getRaster().setSample(x, y, c, outImgData[idx++]);

        ImageIO.write(outImg, "png", new File(outputPath));
        System.out.println("Operation successful! Output saved to: " + outputPath);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("--- 3DES Visual Image Scrambler ---");
            System.out.println("Select an option:");
            System.out.println("1. Generate a new key file");
            System.out.println("2. Scramble (Encrypt) an image");
            System.out.println("3. Descramble (Decrypt) an image");
            System.out.print("Choice: ");
            int choice = Integer.parseInt(scanner.nextLine().trim());
            if (choice == 1) {
                System.out.print("Enter path to save new key file (e.g., secret.key): ");
                String keyPath = scanner.nextLine().trim();
                generateKeyFile(keyPath);
            } else if (choice == 2 || choice == 3) {
                System.out.print("Enter input image path: ");
                String inputPath = scanner.nextLine().trim();
                System.out.print("Enter output image path: ");
                String outputPath = scanner.nextLine().trim();
                System.out.print("Enter key file path: ");
                String keyPath = scanner.nextLine().trim();
                processImage(choice == 2, inputPath, outputPath, keyPath);
            } else {
                throw new Exception("Invalid choice.");
            }
        } catch (Exception e) {
            System.err.println("\nAN ERROR OCCURRED:\n" + e.getMessage());
            e.printStackTrace(); // Also print stack trace for better debugging
        } finally {
            scanner.close();
        }
    }
}
