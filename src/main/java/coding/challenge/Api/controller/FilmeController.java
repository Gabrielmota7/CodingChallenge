package coding.challenge.Api.controller;

import coding.challenge.Api.model.t;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/filme")
public class FilmeController {

    @Value("${api.key}")
    private String apikey;

    @Value("&{url.api}")
    private String urlApi;

    @GetMapping("/{tituloFilme}")
    public t getTitulo(@PathVariable String titulo){

        RestTemplate restTemplate = new RestTemplate();
        StringBuilder stringBuilder = new StringBuilder();
        String urlFinal = stringBuilder.append(urlApi)
                .append(titulo)
                .append(apikey)
                .toString();

        ResponseEntity<t> entity = restTemplate.getForEntity(urlFinal, t.class);


        return null;
    }
}
