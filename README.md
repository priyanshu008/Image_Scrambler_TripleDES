
# 🔐 Triple DES Image Scrambler (Java)

A powerful **Triple DES (3DES) based visual encryption tool** written in Java.

This project scrambles (encrypts) and descrambles (decrypts) images by **shuffling image blocks** using a secure **Triple DES-based pseudorandom number generator (PRNG)**.


## 🚀 Features

✅ Implements **Triple DES (3DES)** algorithm manually (no external crypto libs).

✅ Uses **custom PRNG** based on 3DES for secure shuffling.

✅ Performs **block-wise image scrambling** (default 16×16).

✅ Works on **any PNG/JPG image**.

✅ Supports **key generation** and **bidirectional encryption/decryption**.

✅ Built with strong **bit-level manipulation** and DES tables.

✅ Fully **error-proof** block copying (handles edge cases safely).

## 🧠 How It Works

The program:

1. Generates a **24-byte (192-bit)** Triple DES key.

2. Divides the image into **blocks** (default: 16×16).

3. Uses a **Triple DES-based PRNG** to shuffle the block order.

4. Rearranges pixels to create a visually encrypted image.

5. The same key can **reproduce** the shuffle order for decryption.

## 🗂️ Project Structure

```

TripleDESJava/
│
├── TripleDESJava.java          \# Main program (3DES implementation + image shuffler)
├── secret.key                  \# Auto-generated 24-byte key file (hex format)
├── input.png                   \# Original image (your input)
├── scrambled.png               \# Encrypted (scrambled) image output
└── descrambled.png             \# Decrypted (descrambled) image output

```

## ⚙️ Usage Guide
```
You can manually set the input and output image path

Just make sure to give path without "

Input and output file path example - E:\priyanshu008\image_encrypt\input.jpg
similarly for output and key files

```
### 🧩 Compile and Run

```

# Compile

javac TripleDESJava.java

# Run

java TripleDESJava

```

### 🪄 Options Menu

When you run the program, you’ll see:

```

\--- 3DES Visual Image Scrambler ---
Select an option:

1.  Generate a new key file
2.  Scramble (Encrypt) an image
3.  Descramble (Decrypt) an image
    Choice:

<!-- end list -->

```

### 🗝️ 1. Generate a New Key File

This option creates a **24-byte Triple DES key** saved as a `.key` file.

```

Enter path to save new key file (e.g., secret.key): secret.key

```

Example key file content (48 hex chars):

```

a3b1e0f9d72c4b91e58c0a23f58ef0012a4cbb45d8e913f2

```

### 🌀 2. Scramble (Encrypt) an Image

```

Enter input image path: input.png
Enter output image path: scrambled.png
Enter key file path: secret.key

```

✅ The image will be **scrambled visually** using the 3DES-based shuffle map.

### 🔓 3. Descramble (Decrypt) an Image

```

Enter input image path: scrambled.png
Enter output image path: descrambled.png
Enter key file path: secret.key

```

✅ The tool will **reverse the shuffle** to restore the original image.

## 🧩 Key Details

* **Key Size:** 24 bytes (192 bits)

* **Block Size:** 16×16 pixels (can be changed via `BLOCK_SIZE` constant)

* **PRNG Algorithm:** Triple DES-based feedback generator

* **Image Format:** PNG (preferred), JPG supported

* **Error Handling:** Safe array indexing & block boundary checks

## 💡 Example Workflow

```

# Step 1: Generate a key

java TripleDESJava

# Choose option 1 → Save as secret.key

# Step 2: Scramble image

java TripleDESJava

# Choose option 2 → input.png → scrambled.png → secret.key

# Step 3: Descramble image

java TripleDESJava

# Choose option 3 → scrambled.png → descrambled.png → secret.key

```

## 🧠 Technical Breakdown

### 🔹 3DES Core

* Implements **Initial Permutation (IP)**, **Expansion (E)**, **S-box substitution**, **Permutation (P)**, and **Key Schedule (PC1, PC2, Rotations)** manually.

* Performs **Encrypt-Decrypt-Encrypt (EDE)** sequence for 3DES.

### 🔹 PRNG

* Uses **Triple DES encryption feedback** as a random number generator.

* Ensures randomness and repeatability based on the same key.

### 🔹 Image Processing

* Converts images to **byte arrays** (per channel).

* Shuffles blocks with precise index handling and bounds safety.

* Supports both **RGB and grayscale** formats.

## 🧰 Requirements

* **Java 8 or higher**

* **javax.imageio** (standard in Java SE)

* Works on **Windows, macOS, and Linux**

## 🧑‍💻 Author

**Priyanshu Joshi**
B.Tech Cyber Security Student | Java & Cryptography Enthusiast
📚 Exploring advanced encryption systems and secure coding techniques.

## 🪪 License

This project is open-source and available under the **MIT License**.
Feel free to use, modify, and share — but please give credit. 🙌

## 🧷 Future Enhancements

* \[ \] Add GUI (JavaFX/Swing) for drag-and-drop image encryption.

* \[ \] Implement CBC/CTR modes for pixel-level cryptography.

* \[ \] Add batch image processing support.

* \[ \] Include performance benchmarks and randomness testing.

> ⚡ *“Cryptography is not about secrecy — it’s about trust in structure.”*
> — Priyanshu Joshi
