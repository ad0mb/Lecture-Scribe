package GPT.Setup;

import com.azure.ai.openai.OpenAIClient;
import com.azure.ai.openai.OpenAIClientBuilder;
import com.azure.core.credential.AzureKeyCredential;

public class GPTSetup {

    private final String ENDPOINT_LINK;
    private final String API_KEY = "--";


    public GPTSetup(String endpointLink) {
        ENDPOINT_LINK = System.getenv(endpointLink);
    }

    public OpenAIClient getService() {
        OpenAIClient service = new OpenAIClientBuilder().endpoint("https://lecturescribegpt.openai.azure.com/").credential(new AzureKeyCredential(API_KEY)).buildClient();
        return service;
    }
}
