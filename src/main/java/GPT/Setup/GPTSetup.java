package GPT.Setup;

import com.azure.ai.openai.OpenAIClient;
import com.azure.ai.openai.OpenAIClientBuilder;
import com.azure.core.credential.AzureKeyCredential;
import com.azure.core.credential.TokenCredential;
import com.azure.identity.DefaultAzureCredentialBuilder;

public class GPTSetup {

    private final String ENDPOINT_LINK;
    private final String API_KEY = "136a237a1b794cd998f375c2e1655505";


    public GPTSetup(String endpointLink) {
        ENDPOINT_LINK = System.getenv(endpointLink);
    }

    public OpenAIClient getService() {
        //TokenCredential credential = new DefaultAzureCredentialBuilder().build();
        return new OpenAIClientBuilder().endpoint(ENDPOINT_LINK).credential(new AzureKeyCredential(API_KEY)).buildClient();
    }

}
