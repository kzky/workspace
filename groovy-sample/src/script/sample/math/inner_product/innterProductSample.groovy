
import java.text.BreakDictionary;

import edu.kzk.dataformat.Tuple


// format
// user_id::movie_id::rating::timestamp

// input file
def fin = "/home/kzk/dataset/movie_lens/ml-10M100K/ratings_head_half.dat"
def fpin = new File(fin)

// create movie_id_[user_id, rating]s_map
def movieId2UserIdRatingListMap = new HashMap<Long, List<Tuple>>();

fpin.each {
	l0 = it.split("::")
	def tuple = new Tuple()
	tuple.setId(Long.parseLong(l0[0]))
	tuple.setRating(Float.parseFloat(l0[2]))

	if (movieId2UserIdRatingListMap.get(l0[1]) != null){
		movieId2UserIdRatingListMap.get(l0[1]).add(tuple)
	} else {
		movieId2UserIdRatingListMap.put(l0[1], new ArrayList<Tuple>())
	}
}

//printData(movieId2UserIdRatingListMap)

// inner product for all (スパース形式の通常計算 for movie_vec * movie_vec)
def stime = System.currentTimeMillis()
c = 0
movieId2UserIdRatingListMap.keySet().each { k1 ->
	def mVec1 = movieId2UserIdRatingListMap.get(k1)
	movieId2UserIdRatingListMap.keySet().each { k2 ->
		def innerProduct = 0.0;
		mVec1.each {e1 ->
			def mVec = movieId2UserIdRatingListMap.get(k2)
			for(e2 in mVec) {
				if (e1.getId() == e2.getId()) {
					innerProduct += e1.getRating() * e2.getRating()
					break
				} 
			}
		}
		c += 1
		if (c % 10000 == 0) println c
		//println "${c}: innter prod of ${k1} and ${k2} = ${innerProduct}"
	}
}
def etime = System.currentTimeMillis()
println etime - stime // 遅い，user同士のマッピングなら早いはず．

// マップを２重で持って，あるベクトルと他すべてのベクトルの内積を一度に計算する高速化
// mapを2重でもつ，user2movies, movie2user_rating_list: user*userの内積
// mapを2重でもつ，movie2users, user2movie_rating_list: movie*movieの内積


// print function
def printData(movieId2UserIdRatingListMap) {
	movieId2UserIdRatingListMap.keySet().each { k ->
		movieId2UserIdRatingListMap.get(k).each { v ->
			v.each { elm ->
				println "${k}, ${elm.getId()}, ${elm.getRating()}"
			}
		}
	}
}