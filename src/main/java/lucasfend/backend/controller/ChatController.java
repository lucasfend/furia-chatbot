package lucasfend.backend.controller;

import lucasfend.backend.DTO.*;
import lucasfend.backend.service.ChatGPTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/")
public class ChatController {

    @Autowired
    private ChatGPTService chatGPTService;



    @PostMapping("")
    public Map<String, String> c(@RequestBody Map<String, String> payload) {
        String prompt = payload.get("prompt");


        // Buscar conteúdo relevante do MongoDB, você pode personalizar a consulta
        List<Content> relevantContent = chatGPTService.findRelevantContent(prompt);

        StringBuilder contextBuilder = new StringBuilder("Você é um assistente. Use somente o conteudo abaixo para responder a pergunta:\n\n");

        for (Content c : relevantContent) {
            contextBuilder.append("Titulo: ").append(c.getTitle()).append("\n");
            contextBuilder.append("Conteudo: ").append(c.getContent()).append("\n\n");
        }

        String context = contextBuilder.toString();

        // Monta o request
        ChatRequest request = new ChatRequest();
        request.getMessages().add(new Message("system", context));
        request.getMessages().add(new Message("user", prompt));

        String response = chatGPTService.sendMessage(request);
        return Map.of("response", response);

    }
}
