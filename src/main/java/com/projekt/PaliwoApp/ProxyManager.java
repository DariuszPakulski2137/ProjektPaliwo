import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.core.task.TaskExecutor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


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
    }

    public void SetupVariables(TaskExecutor executor, CloseableHttpClient client){
    }

    public void SetupProxyChecker(){
    }

    public void LoadProxies(){
    }

    public HttpHost GetProxy(){
    }

}
`