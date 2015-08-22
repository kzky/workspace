
object breeze_sample {
	import breeze.linalg._
	
	// Dense vector
	val x = DenseVector.zeros[Double](5)      //> x  : breeze.linalg.DenseVector[Double] = DenseVector(0.0, 0.0, 0.0, 0.0, 0.0
                                                  //| )
	// Set element
	x(1) = 2.0
	
	// Set element
	x(2 to 4) := 5.0                          //> res0: breeze.linalg.DenseVector[Double] = DenseVector(5.0, 5.0, 5.0)
	
	// Dense matrix
  val z = DenseMatrix.zeros[Double](5, 5)         //> z  : breeze.linalg.DenseMatrix[Double] = 0.0  0.0  0.0  0.0  0.0  
                                                  //| 0.0  0.0  0.0  0.0  0.0  
                                                  //| 0.0  0.0  0.0  0.0  0.0  
                                                  //| 0.0  0.0  0.0  0.0  0.0  
                                                  //| 0.0  0.0  0.0  0.0  0.0  
  // Set all elements at 3rd row
  z(3, ::) := 5.0                                 //> res1: breeze.linalg.Transpose[breeze.linalg.DenseVector[Double]] = Transpose
                                                  //| (DenseVector(5.0, 5.0, 5.0, 5.0, 5.0))
  
 	z :+= 10.0                                //> res2: breeze.linalg.DenseMatrix[Double] = 10.0  10.0  10.0  10.0  10.0  
                                                  //| 10.0  10.0  10.0  10.0  10.0  
                                                  //| 10.0  10.0  10.0  10.0  10.0  
                                                  //| 15.0  15.0  15.0  15.0  15.0  
                                                  //| 10.0  10.0  10.0  10.0  10.0  
 	z := 5.0                                  //> res3: breeze.linalg.DenseMatrix[Double] = 5.0  5.0  5.0  5.0  5.0  
                                                  //| 5.0  5.0  5.0  5.0  5.0  
                                                  //| 5.0  5.0  5.0  5.0  5.0  
                                                  //| 5.0  5.0  5.0  5.0  5.0  
                                                  //| 5.0  5.0  5.0  5.0  5.0  
                                                  
  // Sparse matrix (Compressed Sparse Column martix)
  val builder = new CSCMatrix.Builder[Double](rows=10, cols=10)
                                                  //> builder  : breeze.linalg.CSCMatrix.Builder[Double] = breeze.linalg.CSCMatrix
                                                  //| $Builder$mcD$sp@15aab8c6
	builder.add(3,4, 1.0)
	val myMatrix = builder.result()           //> myMatrix  : breeze.linalg.CSCMatrix[Double] = 10 x 10 CSCMatrix
                                                  //| (3,4) 1.0
	myMatrix.update(4, 3, 5.0)
	myMatrix                                  //> res4: breeze.linalg.CSCMatrix[Double] = 10 x 10 CSCMatrix
                                                  //| (4,3) 5.0
                                                  //| (3,4) 1.0
                 
	val eMulMatirx = myMatrix :* myMatrix     //> eMulMatirx  : breeze.linalg.CSCMatrix[Double] = 10 x 10 CSCMatrix
                                                  //| (4,3) 25.0
                                                  //| (3,4) 1.0
	
	val mulMatrix = myMatrix * myMatrix       //> mulMatrix  : breeze.linalg.CSCMatrix[Double] = 10 x 10 CSCMatrix
                                                  //| (3,3) 5.0
                                                  //| (4,4) 5.0
	val f: Unit = (i: Int, j: Int) => {}      //> f  : Unit = ()
	
	mulMatrix.foreachPair{(k, v) => println(s"matrix(${k._1}, ${k._2}) = ${v}")}
                                                  //> matrix(0, 0) = 0.0
                                                  //| matrix(0, 1) = 0.0
                                                  //| matrix(0, 2) = 0.0
                                                  //| matrix(0, 3) = 0.0
                                                  //| matrix(0, 4) = 0.0
                                                  //| matrix(0, 5) = 0.0
                                                  //| matrix(0, 6) = 0.0
                                                  //| matrix(0, 7) = 0.0
                                                  //| matrix(0, 8) = 0.0
                                                  //| matrix(0, 9) = 0.0
                                                  //| matrix(1, 0) = 0.0
                                                  //| matrix(1, 1) = 0.0
                                                  //| matrix(1, 2) = 0.0
                                                  //| matrix(1, 3) = 0.0
                                                  //| matrix(1, 4) = 0.0
                                                  //| matrix(1, 5) = 0.0
                                                  //| matrix(1, 6) = 0.0
                                                  //| matrix(1, 7) = 0.0
                                                  //| matrix(1, 8) = 0.0
                                                  //| matrix(1, 9) = 0.0
                                                  //| matrix(2, 0) = 0.0
                                                  //| matrix(2, 1) = 0.0
                                                  //| matrix(2, 2) = 0.0
                                                  //| matrix(2, 3) = 0.0
                                                  //| matrix(2, 4) = 0.0
                                                  //| matrix(2, 5) = 0.0
                                                  //| matrix(2, 6) = 0.0
                                                  //| matrix(2, 7) = 0.0
                                                  //| matrix(2, 8) = 0.0
                                                  //| matrix(2, 9) = 0.0
                                                  //| matrix(3, 0) = 0.0
                                                  //| matrix(3, 1) = 0.0
                                                  //| matrix(3, 2) = 0.0
                                                  //| matrix(3, 3) = 5.0
                                                  //| matrix(3, 4) = 0.0
                                                  //| matrix(3, 5) = 0.0
                                                  //| matrix(3, 6) = 0.0
                                                  //| matrix(3, 7) = 0.0
                                                  //| matrix(3, 8) = 0.0
                                                  //| matrix(3, 9) = 0.0
                                                  //| matrix(4, 0) = 0.0
                                                  //| matrix(4, 1) = 0.0
                                                  //| matrix(4, 2) = 0.0
                                                  //| matrix(
                                                  //| Output exceeds cutoff limit.


  // Counter2
  val m = Counter2((4,"b", -1), (5,"b", -2) )     //> m  : breeze.linalg.Counter2[Int,String,Int] = Counter2(5 -> Counter(b -> -2)
                                                  //| ,
                                                  //| 4 -> Counter(b -> -1))

	m(5, ::)                                  //> res5: breeze.linalg.Counter[String,Int] = Counter(b -> -2)
	m(::, "b")                                //> res6: breeze.linalg.Counter[Int,Int] = Counter(5 -> -2, 4 -> -1)
  
  
  // Distribution
  import breeze.stats.{distributions => bd}
  
  val gaussian = bd.Gaussian(0.0, 1.0)            //> gaussian  : breeze.stats.distributions.Gaussian = Gaussian(0.0, 1.0)
	
	gaussian.sample(10)                       //> res7: IndexedSeq[Double] = Vector(0.07803468180231134, -1.1689767987375894,
                                                  //|  0.3401365457955729, -1.6694721158525099, 1.5359924462550958, 0.32701178538
                                                  //| 36685, -0.603649867648051, 0.8573111021834523, 0.32669487183144524, 1.03203
                                                  //| 46473094395)
  
}