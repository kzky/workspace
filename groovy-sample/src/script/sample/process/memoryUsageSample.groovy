import java.lang.management.ManagementFactory

import javax.sound.midi.SysexMessage;

// メモリの使用量を取得するサンプル

println "--- runtime version ---"
runtime = Runtime.getRuntime()
println "total memory: " + runtime.totalMemory()
println "free memory: " + runtime.freeMemory()
println "max memory: " + runtime.maxMemory()

println "--- management factory version ---"
mbean = ManagementFactory.getMemoryMXBean()
usage = mbean.getHeapMemoryUsage()
printf("初期サイズ：%10d\n", usage.getInit());
printf("使用サイズ：%10d\n", usage.getUsed());
printf("保証サイズ：%10d\n", usage.getCommitted());	//JavaVMが使用できる最大サイズ
printf("最大サイズ：%10d\n", usage.getMax());	//理論上の最大限使用可能なサイズ


