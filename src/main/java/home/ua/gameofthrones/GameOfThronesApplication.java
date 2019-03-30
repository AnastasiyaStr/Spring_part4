package home.ua.gameofthrones;


import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class GameOfThronesApplication {
    private static final Logger log = LoggerFactory.getLogger(GameOfThronesApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(GameOfThronesApplication.class, args);

//        CloseableHttpClient httpClient = HttpClients.custom()
//                .setSSLHostnameVerifier(new NoopHostnameVerifier())
//                .build();
//        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
//        requestFactory.setHttpClient(httpClient);
//        RestTemplate restTemplate = new RestTemplate(requestFactory);
//        Object result = restTemplate.getForObject("https://anapioficeandfire.com/api/characters/583", Character.class);
//        log.info(result.toString());

  /*  RestTemplate restTemplate = new RestTemplate();
    Character quote = restTemplate.getForObject("https://anapioficeandfire.com/api/characters/583", Character.class);
        log.info(quote.toString());*/


}



}
