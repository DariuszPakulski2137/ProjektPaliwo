package com.projekt.PaliwoApp;
import org.springframework.beans.factory.annotation.Autowired;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
@RestController
public class ApiController {

    HttpClientBuilder clientBuilder = HttpClients.custom().setSSLSocketFactory(
            new SSLConnectionSocketFactory(
                    SSLContexts.custom().loadTrustMaterial(null, new TrustSelfSignedStrategy()).build()));
    CloseableHttpClient client = clientBuilder.build();
    ProxyManager proxyManager = ProxyManager.GetProxyManager();

    @Autowired
    private TaskExecutor taskExecutor;

    public ApiController() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {}

    @PostConstruct
    public void init(){
    }

    public void resizeFile(File input,
                           File output)
            throws IOException {

        BufferedImage bufferedImageInput = ImageIO.read(input);

        BufferedImage bufferedImageOutput = new BufferedImage(bufferedImageInput.getWidth(), bufferedImageInput.getHeight(), BufferedImage.TYPE_INT_RGB);

        Graphics2D g2d = bufferedImageOutput.createGraphics();
        g2d.drawImage(bufferedImageInput, 0, 0, bufferedImageInput.getWidth(), bufferedImageOutput.getHeight(), null);
        g2d.dispose();

        // writes to the output file
        ImageIO.write(bufferedImageOutput, "jpg", output);
    }
}