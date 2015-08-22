package edu.kzk.groovy_sample.serializable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerializeTest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5678987651L;
	public SerializeTest() {
		super();
	}

	private int data1 = 1;
	private int data2 = 2;
	private transient int data3 = 3;
	private transient int data4 = 4;

	private void writeObject(ObjectOutputStream stream) throws IOException {
		System.out.println("writeObject: " + this.toString());

		stream.defaultWriteObject();

		stream.writeInt(data3);
	}

	private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
		stream.defaultReadObject();
		data3 = stream.readInt();
		System.out.println("readObject: " + this.toString());
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("data1: " + data1 + "\t");
		sb.append("data2: " + data2 + "\t");
		sb.append("data3: " + data3 + "\t");
		sb.append("data4: " + data4 + "\t");
		return sb.toString();
	}

}
