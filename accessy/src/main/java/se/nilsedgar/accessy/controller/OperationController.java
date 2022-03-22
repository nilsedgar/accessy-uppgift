package se.nilsedgar.accessy.controller;

import okhttp3.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.web.bind.annotation.*;
import se.nilsedgar.accessy.model.Operation;
import se.nilsedgar.accessy.repository.OperationRepository;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
public class OperationController {

    @Autowired
    OperationRepository operationRepository;

    OkHttpClient client = new OkHttpClient().newBuilder()
            .build();
    MediaType mediaType = MediaType.parse("application/json");



    @GetMapping("/operation")
    public List<Operation> getOperations(){
        return operationRepository.findAll();
    }

//    @PostMapping("/operation")
//    public String postOperation(@RequestBody Operation operation){
//        operationRepository.save(operation);
//        return "Operation saved";
//    }

    @PutMapping("/operation/{id}/invoke")
    public void performOperation(@PathVariable String id) throws IOException {
        UUID operationId = UUID.fromString(id);
        Operation operation = operationRepository.getById(operationId);
        String lockId = operation.getLock().getId().toString();
        String token = getToken();
        System.out.println("Performing operation: " + operation.getName() + ". On door: " + operation.getLock().getName());
        unlockDoor(token, lockId, operation.getName());
    }


    public String getToken() throws IOException {
        RequestBody body = RequestBody.create("{\r\n  \"username\": \"Hello\",\r\n  \"password\": \"Accessy!\"\r\n}", mediaType);
        Request request = new Request.Builder()
                .url("https://test.api.accessy.app/lasab/login")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();

        Response response = client.newCall(request).execute();

        String token = response.body().string();
        String token2 = token.substring(10, token.length() -2);
        return token2;
    }

    public void unlockDoor(String token, String lockId, String operationName) throws IOException {
        String url = "https://test.api.accessy.app/lasab/lock/";

        MediaType mediaTypePlain = MediaType.parse("text/plain");
        RequestBody bodyTwo = RequestBody.create("", mediaTypePlain);
        System.out.println(url + lockId + "/" + operationName);
        System.out.println(token);
        Request requestTwo = new Request.Builder()
                .url("https://test.api.accessy.app/lasab/lock/" + lockId + "/" + operationName)
                .method("PUT", bodyTwo)
                .addHeader("Authorization", "Bearer " + token)
                .build();

        Response responseTwo = client.newCall(requestTwo).execute();
        System.out.println(responseTwo);
    }



}
