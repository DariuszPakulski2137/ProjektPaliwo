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
        taskExecutor.execute(() -> {
            while(true){
                if(checkingProxies.size() > 0) {
                    for (String proxy :
                            checkingProxies) {
                        HttpHost proxyHost = new HttpHost(proxy.split(":")[0],
                                Integer.parseInt(proxy.split(":")[1]));
                        RequestConfig.Builder reqconfigconbuilder = RequestConfig.custom();
                        reqconfigconbuilder.setConnectionRequestTimeout(500);
                        reqconfigconbuilder.setConnectTimeout(500);
                        reqconfigconbuilder = reqconfigconbuilder.setProxy(proxyHost);
                        RequestConfig config = reqconfigconbuilder.build();

                        HttpGet post = new HttpGet("http://ocr.asprise.com/api/v1/receipt");
                        post.setConfig(config);
                        try {
                            CloseableHttpResponse resp = client.execute(post);
                            System.out.println("PROXY OK");
                            proxies.add(proxy);
                            resp.close();
                        } catch (IOException e) {
                            System.out.println ("PROXY DEAD: " + e.getMessage());
                            proxies.remove(proxy);
                        }
                    }
                }
            }
        });
    }

    public void LoadProxies(){
        HttpGet getProxies = new HttpGet("https://free-proxy-list.net/anonymous-proxy.html");
        HttpResponse proxiesResponse = null;
        try {
            proxiesResponse = client.execute(getProxies);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpEntity proxiesRaw = proxiesResponse.getEntity();
        String proxiesRawStr = null;
        try {
            proxiesRawStr = EntityUtils.toString(proxiesRaw);
        } catch (IOException e) {
            e.printStackTrace();
        }
        proxiesRawStr = proxiesRawStr.split("UTC.")[1].split("</textarea>")[0];
        String regex = ".+:.+";
        Pattern p = Pattern.compile(regex);
        Matcher matcher = p.matcher(proxiesRawStr);

        while (matcher.find()) {
            this.checkingProxies.add(matcher.group());
        }
    }

    public HttpHost GetProxy(){
        int indexRand = rand.nextInt(proxies.size() - 1);

        HttpHost proxy = new HttpHost(proxies.get(indexRand).split(":")[0],Integer.parseInt(proxies.get(indexRand).split(":")[1]));
        return proxy;
    }

}