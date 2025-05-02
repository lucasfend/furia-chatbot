package lucasfend.backend.service;
import com.fasterxml.jackson.databind.ObjectMapper;
import lucasfend.backend.DTO.ChatRequest;
import lucasfend.backend.DTO.ChatResponse;
import lucasfend.backend.DTO.Content;
import lucasfend.backend.DTO.Message;
import lucasfend.backend.config.OpenAIConfig;

import lucasfend.backend.repository.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChatGPTService {

    @Autowired
    private OpenAIConfig openAIConfig;

    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    private final RestTemplate restTemplate = new RestTemplate();

    public List<Content> findRelevantContentByTitle(String title) {
        return contentRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<Content> findRelevantContent(String prompt) {
        // Aqui você pode melhorar a busca, talvez utilizando regex, palavras-chave ou alguma lógica de matching
        List<Content> allContent = contentRepository.findAll();

        // Filtra o conteúdo baseado em palavras-chave presentes no prompt
        return allContent.stream()
                .filter(content -> content.getTitle().toLowerCase().contains(prompt.toLowerCase())
                        || content.getContent().toLowerCase().contains(prompt.toLowerCase()))
                .collect(Collectors.toList());
    }



    public String sendMessage(ChatRequest r) {
        //pre-mongodb
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(openAIConfig.getApiKey());

        HttpEntity<ChatRequest> entity = new HttpEntity<>(r, headers);

        try {
            ResponseEntity<ChatResponse> response = restTemplate.exchange(
                    openAIConfig.getApiUrl(),
                    HttpMethod.POST,
                    entity,
                    ChatResponse.class
            );

            if (response.getStatusCode().is2xxSuccessful() &&
                    response.getBody() != null &&
                    !response.getBody().getChoices().isEmpty()) {
                return response.getBody().getChoices().get(0).getMessage().getContent();
            }

            return "Desculpe, não consegui processar sua pergunta no momento.";
        } catch (Exception e) {
            return "Erro ao conectar com o serviço de chat: " + e.getMessage();
        }
    }
}
