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
import java.util.Map;

public class MessageRequests {
    private static String VERSION       = "2016-09-20";
    private static String USER_NAME     = "461b5124-07f2-40ef-ba07-55dce3824fbe";
    private static String PASS_WORD     = "Em5WSdKsdGbQ";
    private static String WORKSPACE_ID  = "2ce8f171-601b-4620-b9a0-842ff8314672";
    private static String URL           = "https://gateway.watsonplatform.net/conversation/api";
    private static boolean LOGGING_ENABLED = Boolean.parseBoolean(System.getenv("LOGGING_ENABLED"));

    private Map<String, Object> context;
    private String stringOutPut;

    public MessageRequests(){
        context = null;
        stringOutPut = null;
    }

    public void requestMessage(String mess) {
        MessageRequest request;
        if(getResContext() != null) {
            request = new MessageRequest.Builder()
                    .inputText(mess).context(getResContext()).build();
        }
        else{
            request = new MessageRequest.Builder()
                    .inputText(mess).build();
        }

        setStringOutPut(responseMessage(request));
    }

    public String responseMessage(MessageRequest request)
    {
        ConversationService service = new ConversationService(VERSION);
        service.setUsernameAndPassword(USER_NAME, PASS_WORD);

        MessageResponse response = null;
        response = service.message(WORKSPACE_ID, request).execute();
        System.out.println(response);
        setResContext(response.getContext());

        /*MessageRequest request2 = new MessageRequest.Builder()
                .inputText(mess).context(response.getContext()).build();
        response = service.message(WORKSPACE_ID, request2).execute();
        System.out.println(response.getTextConcatenated(","));*/

        return response.getTextConcatenated(",");
    }

    public void setResContext(Map<String, Object> context){
        this.context = context;
    }

    public Map<String, Object> getResContext(){
        return this.context;
    }

    public void setStringOutPut(String str){
        this.stringOutPut = str;
    }

    public String getStringOutPut(){
        return this.stringOutPut;
    }

}
