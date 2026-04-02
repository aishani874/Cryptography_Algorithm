# Cryptography Algorithm
### Route Cipher
Route Cipher is a Transposition Cipher, which encrypts a message by changing the positions of the alphabets based on a spiral matrix path/route along with Caeser Cipher. 
Th plain text message is filled into a matrix column-wise, and based on the spiral path specified (eg., spiral inwards/outwards starting from top right/centre, etc), the routed text is formed.

### Polynomial Rolling Hash
A Polynomial Rolling Hash Function converts a string of any length into a single numeric value, window by window of the string.  
**Mathematical Logic**: It uses only multiplications and additions. The function is represented as :  
     <img width="500" height="182" alt="image" src="https://github.com/user-attachments/assets/3ca8b1c4-ac63-4765-a705-48d5fdb9c954" />    
     - String s of length n : input to the function  
     - p and m : The choice of p and m affects the performance and the security of the hash function. If the string s consists of only lower-case letters, then p = 31 is a good choice but if the string only consists of upper-case letters then p = 53.
        A very large prime (like 10^9 + 7). This keeps the hash value within a manageable range, preventing integer overflow, while minimizing the chance that two different strings produce the same number, i.e., collision.

### Route Cipher+Polynomial Rolling Hash Function
Combining Route Cipher and Polynomial Rolling Hash will create a multi-layered encryption and decryption system which will:
- Convert the message into a fixed-length hexadecimal representation
- Shuffle the hexadecimal representation using a complex Spiral Route
- Shift the shuffled characters using an x+c logic.
This ensures that even if an attacker is able to find the spiral path, they will only be able to find the hash value and not the original string.

## Working of Final Algorithm - Encryption and Decryption
**ENCRYPTION**
1. The input text is hashed into a hexadecimal string using Polynomial Rolling Hash Function.
2. The hashed string is padded with extra letters to fit a perefctly geometrical matrix, and the string is then filled into a matrix, column-wise.
3. Spiral Routing is done in the matrix starting from the centre, spiralling outwards, clockwise.
4. The routed text obtained is then ciphered using **x+c logic**, where c = no. of words present in the original plain text.

**DECRYPTION**
1. The cipher text is reverse ciphered by **subtracting c** from theASCII value of each alphabet.
2. The reverse spiral logic is used to fill in the matrix with the subtracted cipher text.
3. The matrix is then read column-wise to obtain the hashed string.

The hash string obtained is then compared with the padded hashed value from the encryption process to check for successful ciphering and deciphering.

## OUTPUT

<img width="580" height="500" alt="image" src="https://github.com/user-attachments/assets/57a146f2-6e2d-43c9-9c79-0c418539a384" />   

<img width="580" height="500" alt="image" src="https://github.com/user-attachments/assets/c3a1d1e5-10fd-4337-999e-cac71ec1c719" />
