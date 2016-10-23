package com.example.mine.myapplication; /**
 * Created by mine on 2016/10/12.
 */

import com.ibm.watson.developer_cloud.conversation.v1.ConversationService;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;
import com.ibm.watson.developer_cloud.retrieve_and_rank.v1.util.Messages;
import com.ibm.watson.developer_cloud.util.GsonSingleton;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class MessageRequests {
    private static String VERSION       = "2016-09-20";
    private static String USER_NAME     = "461b5124-07f2-40ef-ba07-55dce3824fbe";
    private static String PASS_WORD     = "Em5WSdKsdGbQ";
    private static String WORKSPACE_ID  = "2ce8f171-601b-4620-b9a0-842ff8314672";
    private static String URL           = "https://gateway.watsonplatform.net/conversation/api";
    private static boolean LOGGING_ENABLED = Boolean.parseBoolean(System.getenv("LOGGING_ENABLED"));

    private MessageResponse res;

    public void requestMessage() {
        ConversationService service = new ConversationService(VERSION);
        service.setUsernameAndPassword(USER_NAME, PASS_WORD);

        String mess = "おなかへった";
        MessageRequest request = new MessageRequest.Builder()
                .inputText(mess).build();

        MessageResponse response = null;
        response = service.message(WORKSPACE_ID, request).execute();
        System.out.println(response);

    }

    public MessageResponse getResponse() { return res; }


}
