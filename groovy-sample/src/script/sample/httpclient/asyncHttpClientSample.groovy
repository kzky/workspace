import java.util.concurrent.Future

import com.ning.http.client.AsyncHttpClient
import com.ning.http.client.FluentCaseInsensitiveStringsMap
import com.ning.http.client.Response

// asynchronous http cilent のサンプル
// ハンドラを指定しない場合．

def ahttpclient = new AsyncHttpClient()
def url = "http://eow.alc.co.jp/search?q=grab"
Future<Response> future = ahttpclient.prepareGet(url).execute()

Response response = future.get()

println response.getUri()
FluentCaseInsensitiveStringsMap headerMap = response.getHeaders()
headerMap.each {k, v ->
	println k + ": " + v	
}
println response.getResponseBody()

ahttpclient.close()


 