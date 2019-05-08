package dao.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.AccountWsDao;
import dto.CommonResponse;
import entity.Account;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

public class AccountWaDaoImpl implements AccountWsDao {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final TypeReference<CommonResponse<Account>> TRASACTION_RESP_ACCOUNT = new TypeReference<CommonResponse<Account>>() {};


    @Override
    public CommonResponse<Account> addAccount(Account account){
        CommonResponse<Account> transactionResponse = null;
        RestTemplate restTemplate = new RestTemplate();
        String postUrl = String.format("http://localhost:8080/api-v1/account");
        ResponseEntity<String> response = restTemplate.postForEntity(URI.create(postUrl), account, String.class);

        if (response.getStatusCode() != HttpStatus.OK){
            System.out.println(String.format("Failed with message %s", response.getStatusCodeValue()));
        } else if (StringUtils.isEmpty(response.getBody())){
            System.out.println("Response is null");
        } else {

            try {
                CommonResponse<Account> transResponse = MAPPER.readValue(response.getBody(), TRASACTION_RESP_ACCOUNT);
                if (!transResponse.getStatus().equalsIgnoreCase("20") && !transResponse.getStatus().equalsIgnoreCase("304")){
                    System.out.println(String.format("Somethink wrong"));
                } else {
                    transactionResponse = transResponse;
                }
            } catch (Exception e){
                e.printStackTrace();
            }

        }
        return transactionResponse;
    }

}
