import java.security.MessageDigest;

// digets MD5/SHA1のサンプル

src = "plain text."
MessageDigest md = MessageDigest.getInstance("MD5")
md.update(src.getBytes())
println md.digest()

md = MessageDigest.getInstance("SHA-1")
md.update(src.getBytes())
println md.digest()





