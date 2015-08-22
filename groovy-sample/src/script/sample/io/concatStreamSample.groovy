import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

import com.javamex.classmexer.MemoryUtil;

// Concat Stream Smaple

rt = Runtime.getRuntime()
println rt.totalMemory()
byte[] b = new byte[1147483647];

println MemoryUtil.deepMemoryUsageOf(b);

println "--- concat output steam ---"
baos = new ByteArrayOutputStream()
ZipOutputStream zos = new ZipOutputStream(baos)
def zipEntry = new ZipEntry("")
zipEntry.setSize(b.size());
zos.putNextEntry(zipEntry)
zos.write(b)
zos.closeEntry()
zos.close()
println MemoryUtil.deepMemoryUsageOf(baos.toByteArray());