AESEncryption Module
=====================
This module has been developed as part of the security aspect for Android mobile app. This module will use PBKDF2 (password derivation function) to derive a key which is used to encrypt/de-crypt data. The initial reason behind this module was, to prevent a hacker reverse engineers the mobile app and try to get the source code. So if someone blindly hardcode the key in the source code the security can be compromised.

Instead we used PBKDF2 to generate a key (with 1000 iterations of hashing) which again derived by users password and 10 bytes long random salt string. Then AESEncryption Engine has been used to encrypt the data.
Several test cases have also been written for functional testing. 
Run test by using;

mvn clean test
