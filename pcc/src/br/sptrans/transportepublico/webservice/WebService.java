package br.sptrans.transportepublico.webservice;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;
import br.sptrans.transportepublico.pcc.R;
 
public class WebService{
 
    DefaultHttpClient httpClient;
    HttpContext localContext;
    private String ret;
 
    HttpResponse response = null;
    HttpPost httpPost = null;
    HttpGet httpGet = null;
    String webServiceUrl;
    String TAG_LOG = "WebService";
    Activity context;
    
    public WebService(Activity context,String serviceName){
        HttpParams myParams = new BasicHttpParams();
        this.context = context;
        HttpConnectionParams.setConnectionTimeout(myParams, 30000);
        HttpConnectionParams.setSoTimeout(myParams, 30000);
        httpClient = new DefaultHttpClient(myParams);
        localContext = new BasicHttpContext();
        webServiceUrl = context.getResources().getText(R.string.webservice_url_base).toString() + serviceName; 
    }
 
    //Use this method to do a HttpGet/WebGet on the web service
    public String webGet() {
    	String getUrl = webServiceUrl;
 
        httpGet = new HttpGet(getUrl);
 
        try 
        {
            response = httpClient.execute(httpGet);
        }        
        catch (ConnectTimeoutException e) 
        {
        	Log.e(TAG_LOG, e.getMessage());
        	context.runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					Toast.makeText(context,R.string.webservice_conexao_lenta, Toast.LENGTH_LONG).show();
				}
			});        	
        }
        catch (Exception e) {
        	Log.e(TAG_LOG, e.getMessage());
		}
 
        try 
        {
            ret = EntityUtils.toString(response.getEntity());
        } 
        catch (Exception e) 
        {
        	Log.e(TAG_LOG, e.getMessage());
        }
 
        return ret;
    }
 
}