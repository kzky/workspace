
import java.util.concurrent.Callable
import java.util.concurrent.CompletionService
import java.util.concurrent.ExecutorCompletionService
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future

import edu.kzk.dataformat.Tuple
import edu.kzk.task.CalcInnerProductsTask


// format
// user_id::movie_id::rating::timestamp

// input file
def fin = "/home/kzk/dataset/movie_lens/ml-10M100K/ratings.dat"
def fpin = new File(fin)

// create movie_id_[user_id, rating]s_map
def movieId2UserIdRatingListMap = new HashMap<Long, List<Tuple>>();
def userId2MovieRatingListMap = new HashMap<Long, List<Tuple>>();
fpin.each {
	l0 = it.split("::")

	// user2movie_rating_list
	def userIdRatingTuple = new Tuple()
	userIdRatingTuple.setId(Long.parseLong(l0[0]))
	userIdRatingTuple.setRating(Float.parseFloat(l0[2]))
	def userIdRatingList = movieId2UserIdRatingListMap.get(Long.parseLong(l0[1]))
	if(userIdRatingList != null) {
		userIdRatingList.add(userIdRatingTuple)
	} else {
		def list = new ArrayList<Tuple>()
		list.add(userIdRatingTuple)
		movieId2UserIdRatingListMap.put(Long.parseLong(l0[1]), list)
	}

	// movie2user_rating_list
	def movieIdRatingTuple = new Tuple()
	movieIdRatingTuple.setId(Long.parseLong(l0[1]))
	movieIdRatingTuple.setRating(Float.parseFloat(l0[2]))
	def movieIdRatingList = userId2MovieRatingListMap.get(Long.parseLong(l0[0]))
	if(movieIdRatingList != null) {
		movieIdRatingList.add(movieIdRatingTuple)
	} else {
		def list = new ArrayList<Tuple>()
		list.add(movieIdRatingTuple)
		userId2MovieRatingListMap.put(Long.parseLong(l0[0]), list)
	}
}

// print
//printMovieId2UserIdRatingListMap(movieId2UserIdRatingListMap)
//printUserId2MovieRatingListMap(userId2MovieRatingListMap)


// inner product for all (スパース形式の通常計算 for movie_vec * movie_vec)
// Add Tasks to FutureList
List<Future<Long>> futures = new ArrayList<Future<Long>>()
List<Long> uids =  userId2MovieRatingListMap.keySet().toList()
int nThreads = 4
int sliceSize = uids.size()/nThreads

// Service and Submit task
ExecutorService executor = Executors.newFixedThreadPool(nThreads)
CompletionService<Long> service = new ExecutorCompletionService<Long>(executor)
def stime = System.currentTimeMillis()
for(i in 0..<(nThreads-1)) {
	Callable task = new CalcInnerProductsTask(uids.subList(i*sliceSize, (i+1)*sliceSize), userId2MovieRatingListMap, movieId2UserIdRatingListMap)
	Future<Long> future = service.submit(task)
//	futures.add(future)
}
Callable task = new CalcInnerProductsTask(uids.subList((nThreads-1)*sliceSize, uids.size() - 1), userId2MovieRatingListMap, movieId2UserIdRatingListMap)
Future<Long> future = service.submit(task)
//futures.add(future)

// Take
for(i in 0..<nThreads) {
	Future<Long> f = service.take()
	Long taskResult = f.get() 
}
def etime = System.currentTimeMillis()

// total time
println etime - stime
executor.shutdown()

// function
def calcAllInnerProducts(uids, userId2MovieRatingListMap, movieId2UserIdRatingListMap) {
	def count = 0


	for (uid in uids) {
		// map version
		// user/usersの内積．ArrayListのほうが早いかも
		def innerProducts = new HashMap<Long, Float>()
		for (movieIdRating in userId2MovieRatingListMap.get(uid)) {
			def t0 = movieIdRating
			def m0 = t0.getId()
			def v0 = t0.getRating()

			for (userIdRating in movieId2UserIdRatingListMap.get(m0)) {
				def v = innerProducts.get(userIdRating.getId())
				if (v != null) {
					v += v0 * userIdRating.getRating();
				} else {
					innerProducts.put(userIdRating.getId(), v0 * userIdRating.getRating())
				}
			}
		}

		//		println "uid: " + uid + "'s inner products"
		//		println innerProducts
		count += 1
		if (count % 1000 == 0) {
			println count
		}
		innerProducts = null;
	}
}

// print
def printMovieId2UserIdRatingListMap(movieId2UserIdRatingListMap) {
	movieId2UserIdRatingListMap.each {k, v ->
		v.each {
			println k + ": " + it
		}
	}
}
def printUserId2MovieRatingListMap(userId2MovieRatingListMap) {
	userId2MovieRatingListMap.each {k, v ->
		v.each {
			println k + ": " + it
		}
	}
}
