
object breeze_sample {
	import breeze.linalg._;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(104); 
	
	// Dense vector
	val x = DenseVector.zeros[Double](5);System.out.println("""x  : breeze.linalg.DenseVector[Double] = """ + $show(x ));$skip(28); 
	// Set element
	x(1) = 2.0;$skip(36); val res$0 = 
	
	// Set element
	x(2 to 4) := 5.0;System.out.println("""res0: breeze.linalg.DenseVector[Double] = """ + $show(res$0));$skip(61); 
	
	// Dense matrix
  val z = DenseMatrix.zeros[Double](5, 5);System.out.println("""z  : breeze.linalg.DenseMatrix[Double] = """ + $show(z ));$skip(51); val res$1 = 
  // Set all elements at 3rd row
  z(3, ::) := 5.0;System.out.println("""res1: breeze.linalg.Transpose[breeze.linalg.DenseVector[Double]] = """ + $show(res$1));$skip(16); val res$2 = 
  
 	z :+= 10.0;System.out.println("""res2: breeze.linalg.DenseMatrix[Double] = """ + $show(res$2));$skip(11); val res$3 = 
 	z := 5.0;System.out.println("""res3: breeze.linalg.DenseMatrix[Double] = """ + $show(res$3));$skip(168); 
                                                  
  // Sparse matrix (Compressed Sparse Column martix)
  val builder = new CSCMatrix.Builder[Double](rows=10, cols=10);System.out.println("""builder  : breeze.linalg.CSCMatrix.Builder[Double] = """ + $show(builder ));$skip(23); 
	builder.add(3,4, 1.0);$skip(33); 
	val myMatrix = builder.result();System.out.println("""myMatrix  : breeze.linalg.CSCMatrix[Double] = """ + $show(myMatrix ));$skip(28); 
	myMatrix.update(4, 3, 5.0);$skip(10); val res$4 = 
	myMatrix;System.out.println("""res4: breeze.linalg.CSCMatrix[Double] = """ + $show(res$4));$skip(57); 
                 
	val eMulMatirx = myMatrix :* myMatrix;System.out.println("""eMulMatirx  : breeze.linalg.CSCMatrix[Double] = """ + $show(eMulMatirx ));$skip(39); 
	
	val mulMatrix = myMatrix * myMatrix;System.out.println("""mulMatrix  : breeze.linalg.CSCMatrix[Double] = """ + $show(mulMatrix ));$skip(38); 
	val f: Unit = (i: Int, j: Int) => {};System.out.println("""f  : Unit = """ + $show(f ));$skip(80); 
	
	mulMatrix.foreachPair{(k, v) => println(s"matrix(${k._1}, ${k._2}) = ${v}")};$skip(62); 


  // Counter2
  val m = Counter2((4,"b", -1), (5,"b", -2) );System.out.println("""m  : breeze.linalg.Counter2[Int,String,Int] = """ + $show(m ));$skip(11); val res$5 = 

	m(5, ::);System.out.println("""res5: breeze.linalg.Counter[String,Int] = """ + $show(res$5));$skip(12); val res$6 = 
	m(::, "b")
  
  
  // Distribution
  import breeze.stats.{distributions => bd};System.out.println("""res6: breeze.linalg.Counter[Int,Int] = """ + $show(res$6));$skip(110); 
  
  val gaussian = bd.Gaussian(0.0, 1.0);System.out.println("""gaussian  : breeze.stats.distributions.Gaussian = """ + $show(gaussian ));$skip(23); val res$7 = 
	
	gaussian.sample(10);System.out.println("""res7: IndexedSeq[Double] = """ + $show(res$7))}
  
}
