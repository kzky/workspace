#!/usr/bin/groovy

import groovy.transform.ToString;

import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.awt.image.ImagingOpException;

import edu.kzk.groovy_sample.serializable.SerializeTest


//public class SerializeTest implements Serializable {
//	
//	private static final long serialVersionUID = 199035831519635924L;
//	
//	private int data1 = 1
//	private int data2 = 2
//	private transient int data3 = 3
//	private transient int data4 = 4
//	
//	public SerializeTest() {		
//	}
//	
//	private void writeObject(ObjectOutputStream stream) throws IOException {
//		println("writeObject: " + this);
//		stream.defaultWriteObject();
//
//		stream.writeInt(data3);
//	}
//
//	private void readObject(ObjectInputStream stream) throws IOException {
//		println("readObject: " + this);
//		stream.defaultReadObject()
//		data3 = stream.readInt()
//	}
//	
//	String toString() {
//		println data1
//		println data2
//		println data3
//		println data4
//	}
//	
//}

println "--- write object ---"
wobj = new SerializeTest()
ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("/home/kzk/serializeTest.txt"))
oos.writeObject(wobj)

println "--- read object ---"
ObjectInputStream ois = new ObjectInputStream(new FileInputStream("/home/kzk/serializeTest.txt"))
robj = (SerializeTest)ois.readObject()




