import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.core.task.TaskExecutor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProxyManager {

    public static ProxyManager instance;
    public TaskExecutor taskExecutor;
    Random rand = new Random();
    public List<String> checkingProxies = new ArrayList<>();
    public List<String> proxies = new ArrayList<>();
    public CloseableHttpClient client;

    private ProxyManager(){
    }

    public static ProxyManager GetProxyManager(){
        if(instance == null){
            instance = new ProxyManager();
        }
        return instance;
    }

    public void SetupVariables(TaskExecutor executor, CloseableHttpClient client){
        this.taskExecutor = executor;
        this.client = client;
    }

    public void SetupProxyChecker(){
    }

    public void LoadProxies(){
    }

    public HttpHost GetProxy(){
        int indexRand = rand.nextInt(proxies.size() - 1);

        HttpHost proxy = new HttpHost(proxies.get(indexRand).split(":")[0],Integer.parseInt(proxies.get(indexRand).split(":")[1]));
        return proxy;
    }

}