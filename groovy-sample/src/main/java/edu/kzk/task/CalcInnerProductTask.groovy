package edu.kzk.task

import java.util.concurrent.Callable


// Class
// inner product
// 5,000,000ログでuser*userの内積で，1h42m
// 10,000,000ログでuser*userの内積で，7h54m
// 10,000,000ログでuser*userの内積で，4threadsで，5h
public class CalcInnerProductsTask implements Callable<Long> {

	List<Long> uids;
	Map<Long, List<Tuple>> userId2MovieRatingListMap;
	Map<Long, List<Tuple>> movieId2UserIdRatingListMap;

	public CalcInnerProductsTask(uids, userId2MovieRatingListMap, movieId2UserIdRatingListMap) {
		this.uids = uids
		this.userId2MovieRatingListMap = userId2MovieRatingListMap
		this.movieId2UserIdRatingListMap = movieId2UserIdRatingListMap
	}

	@Override
	public Long call() throws Exception {
		def count = 0
		println "size of uids: " + uids.size()
		def stime = System.currentTimeMillis()
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
				println Thread.currentThread().getId() + ":" + count
				def etime = System.currentTimeMillis()
				println Thread.currentThread().getId() + ":" + (etime - stime)
				stime = System.currentTimeMillis() 
			}
			innerProducts = null;
		}
		return Thread.currentThread().getId() 
	}
}
