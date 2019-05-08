package dao.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.AuthWsDao;
import dto.CommonResponse;
import entity.Account;
import entity.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

public class AuthWsDaoImpl implements AuthWsDao {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final TypeReference<CommonResponse<Customer>> TYPE_REFERENCE_COMMON_CUSTOMER = new TypeReference<CommonResponse<Customer>>() {};
    private static final TypeReference<CommonResponse<Account>> TYPE_REFERENCE_COMMON_ACCOUNT = new TypeReference<CommonResponse<Account>>() {};

    @Override
    public Customer loginCustomer(Customer customer) {
        Customer currentCustomer = null;
        RestTemplate restTemplate = new RestTemplate();
        String postUrl = String.format("http://localhost:8080/api-v1/customer/login");
        ResponseEntity<String> response = restTemplate.postForEntity(URI.create(postUrl), customer, String.class);

        if (response.getStatusCode() != HttpStatus.OK){
            System.out.println(String.format("Failed with message %s", response.getStatusCodeValue()));
        } else if (StringUtils.isEmpty(response.getBody())){
            System.out.println("Response is null");
        } else {

            try {
                CommonResponse<Customer> authResponse = MAPPER.readValue(response.getBody(), TYPE_REFERENCE_COMMON_CUSTOMER);
                if (!authResponse.getStatus().equalsIgnoreCase("20") && !authResponse.getStatus().equalsIgnoreCase("34")){
                    System.out.println(String.format("User not authorized"));
                } else {
                    currentCustomer = authResponse.getData();
                }
            } catch (Exception e){
                e.printStackTrace();
            }

        }

        return currentCustomer;
    }

    @Override
    public Account loginAccount(Account account) {
        return null;
    }
}
