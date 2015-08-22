import java.security.Key

import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec


def encrypt(byte[] src, Key skey) {
	Cipher cipher = Cipher.getInstance("AES")
	cipher.init(Cipher.ENCRYPT_MODE, skey)
	return cipher.doFinal(src)
}

def decrypt(byte[] src, Key skey) {
	Cipher cipher = Cipher.getInstance("AES")
	cipher.init(Cipher.DECRYPT_MODE, skey)
	return cipher.doFinal(src)
}

def makeKey(int keybits) {
	byte[] key = new byte[keybits / 8]
	// keyはユーザが決める
	for(int i in 0..<key.size()) {
		key[i] = (byte) (i + 1)
	}
	return new SecretKeySpec(key, "AES")
}

def src = "the plain text"
def skey = makeKey(128)
def esrc = encrypt(src.getBytes(), skey)
//dsrc = decrypt(esrc,"not shared key length".length())
def dsrc = decrypt(esrc, skey)
println "--- plain text ---"
println src
println "--- plain text as byte"
println src.getBytes()
println "--- shared key ---"
println skey
println "--- cipher text as byte ---"
println esrc
println "--- decrypted text as byte"
println dsrc
println "--- decrypted plain text as text ---"
println new String(dsrc)

