import com.ning.*
import com.ning.http.client.AsyncCompletionHandler
import com.ning.http.client.AsyncHttpClient
import com.ning.http.client.HttpResponseBodyPart
import com.ning.http.client.HttpResponseHeaders
import com.ning.http.client.Response
import com.ning.http.client.AsyncHandler.STATE

// future, handlerの併用サンプル

// completion handler classa
class MyCompletionHandler extends AsyncCompletionHandler<String> {
	
	@Override
	public String onCompleted(Response response) throws Exception {
		return response.getStatusCode() + ": " + response.getStatusText()
	}
	
	@Override
	public STATE onHeadersReceived(HttpResponseHeaders headers) throws Exception {
		headers.getHeaders().each {k, v ->
			println k + ": " + v
		}
		return STATE.CONTINUE
	}
	
	// ゼロコピーダウンロードの際に使用する．
	// outputstreamを継承しているクラスなら何でも使用できるので，
	// 単なるダウンロード以外の用途も考えられる．
	@Override
	public STATE onBodyPartReceived( 
			HttpResponseBodyPart content) throws Exception {
		// TODO Auto-generated method stub
		return super.onBodyPartReceived(content);
	}
			
	@Override
	public void onThrowable(Throwable t) {
		super.onThrowable(t);
	}
}


def myCompletionHander = new MyCompletionHandler() 
def ahttpclient = new AsyncHttpClient()
def url = "http://eow.alc.co.jp/search?q=grab"
future = ahttpclient.prepareGet(url).execute(myCompletionHander)
status = future.get()
println status

ahttpclient.close()



