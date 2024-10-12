import com.azure.ai.textanalytics.TextAnalyticsClient;
import com.azure.ai.textanalytics.TextAnalyticsClientBuilder;
import com.azure.ai.textanalytics.models.CategorizedEntity;
import com.azure.core.credential.AzureKeyCredential;
public class Test {
    public static void main(String[] args) {
        // Replace with your own API key and endpoint
        String apiKey = "065549c7d1774826bd284fa8a9fdf26b";
        String endpoint = "https://lecturescribedateanalytics.cognitiveservices.azure.com/";
        // Create a Text Analytics client
        TextAnalyticsClient client = new TextAnalyticsClientBuilder()
                .credential(new AzureKeyCredential(apiKey))
                .endpoint(endpoint)
                .buildClient();

        // Sample lecture transcript text
        String document = "The next event will be on January 25, 2024. Another important date is February 14, 2023.";
        for (CategorizedEntity entity : client.recognizeEntities(document)) {
            if ("DateTime".equals(entity.getCategory())) {
                System.out.println("Recognized date: " + entity.getText());
            }
        }
    }
}

