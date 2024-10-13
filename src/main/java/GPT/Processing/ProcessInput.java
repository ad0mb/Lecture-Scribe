package GPT.Processing;

import com.azure.ai.openai.OpenAIClient;
import com.azure.ai.openai.models.*;

import java.util.ArrayList;
import java.util.List;

public class ProcessInput {

    private OpenAIClient service;
    private final String GPT_TYPE = "gpt-35-turbo-16k";

    public ProcessInput(OpenAIClient service) {
        this.service = service;
    }

    public String transcriptInjection(String prompt) {
        List<ChatRequestMessage> injectedPrompt = new ArrayList<>();
        injectedPrompt.add(new ChatRequestUserMessage(prompt));
        injectedPrompt.add(new ChatRequestSystemMessage(contextInstruction));

        ChatCompletions completions = service.getChatCompletions(GPT_TYPE, new ChatCompletionsOptions(injectedPrompt));


        return completions.getChoices().getFirst().getMessage().getContent();
    }

    private static final String contextInstruction =
            "You are an AI model that listens to college lectures and seeks important dates (i.e. exams, homework assignment due dates, project due dates, quizzes, etc.) an overall summary, and lecture key points.\n" +
            "\n" +
            "Format in JSON:\n" +
            "{\n" +
            "  \"results\": {\n" +
            "    \"events\": [\n" +
            "      {\n" +
            "        \"name\": \"<event_name>\",\n" +
            "        \"date\": \"<event_date>\",\n" +
            "        \"location\": \"<event_location or 'N/A'>\"\n" +
            "      }\n" +
            "    ],\n" +
            "    \"keyPoints\": [\"<key_point1>\", \"<key_point2>\"],\n" +
            "    \"summary\": \"<lecture_summary>\"\n" +
            "  }\n" +
            "}\n" +
            "For the event_name field, decide the name based off context from the event description and for the key points include as many as you determine the lecture covers\n";
}
