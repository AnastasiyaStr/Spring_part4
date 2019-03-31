package home.ua.gameofthrones.service;

import home.ua.gameofthrones.domain.Character;
import home.ua.gameofthrones.entity.CharacterEntity;
import home.ua.gameofthrones.repository.CharacterRepository;
import home.ua.gameofthrones.utils.ObjectMapperUtils;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class CharacterService {
  @Autowired
    CharacterRepository characterRepository;
    @Autowired
    private ObjectMapperUtils modelMapper;
    public int save(String character){
        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLHostnameVerifier(new NoopHostnameVerifier())
                .build();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        Object[] result = restTemplate.getForObject("https://anapioficeandfire.com/api/characters/?name="+character, Character[].class);
        Character characterDTO  = (Character) result[0];
        String[] allegiances = characterDTO.getAllegiances();
        characterRepository.save(modelMapper.map(characterDTO, CharacterEntity.class));
      return 1;

    }





}
