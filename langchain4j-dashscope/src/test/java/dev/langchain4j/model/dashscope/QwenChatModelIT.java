package dev.langchain4j.model.dashscope;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.internal.Utils;
import dev.langchain4j.model.chat.ChatLanguageModel;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;

public class QwenChatModelIT {

    @ParameterizedTest
    @MethodSource("dev.langchain4j.model.dashscope.QwenTestHelper#chatModelNameProvider")
    public void should_send_messages_and_receive_response(String modelName) {
        String apiKey = QwenTestHelper.apiKey();
        if (Utils.isNullOrBlank(apiKey)) {
            return;
        }
        ChatLanguageModel model = QwenChatModel.builder()
                .apiKey(apiKey)
                .modelName(modelName)
                .build();
        AiMessage answer = model.sendMessages(QwenTestHelper.chatMessages());
        assertThat(answer.text()).containsIgnoringCase("rain");
    }
}
