

class ObjectTest implements Serializable {
	int a = 10
	String b = "hoofoo"
	public double multiply(a , b) {
		return a * b
	}
	private void writeObject(ObjectOutputStream stream) throws IOException {
		stream.defaultWriteObject();
	}

	private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
		stream.defaultReadObject();
	}
}

ByteArrayOutputStream baos = new ByteArrayOutputStream()
ObjectOutputStream oos = new ObjectOutputStream(baos)
ot = new ObjectTest()
oos.writeObject(ot)

byte[] bytearray = baos.toByteArray()
println bytearray

str = "bytes"
