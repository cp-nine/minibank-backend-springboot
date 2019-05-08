package dao.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.CustomerWsDao;
import dto.CommonResponse;
import entity.Customer;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

public class CustomerWsDaoImpl implements CustomerWsDao {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final ParameterizedTypeReference<CommonResponse<Customer>> PARAMETERIZED_TYPE_COMMON_RESP_CUSTOMER = new ParameterizedTypeReference<CommonResponse<Customer>>() {};
    private static final TypeReference<CommonResponse<List<Customer>>> TYPE_REFERENCE_COMMON_CUSTOMERS = new TypeReference<CommonResponse<List<Customer>>>() {};
    private static final TypeReference<CommonResponse> TRASACTION_RESP_CUSTOMER = new TypeReference<CommonResponse>() {};

    @Override
    public List<Customer> getCustomers() {
        List<Customer> list = null;

        RestTemplate restTemplate = new RestTemplate();
        String getUrl = "http://localhost:8080/api-v1/customers";
        ResponseEntity<String> response = restTemplate.getForEntity(getUrl, String.class);

        if (response.getStatusCode() != HttpStatus.OK){
            System.out.println(String.format("Failed with message %s", response.getStatusCodeValue()));
        } else if(StringUtils.isEmpty(response.getBody())){
            System.out.println("Response null");
        } else {
            try {

                CommonResponse<List<Customer>> crsp = MAPPER.readValue(response.getBody(), TYPE_REFERENCE_COMMON_CUSTOMERS);
                if (!crsp.getStatus().equalsIgnoreCase("20")){
                    System.out.println(String.format("Failed with message %s", crsp.getMessage()));
                } else {
                    list = crsp.getData();
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return list;
    }


    @Override
    public Customer getCutomer(String cif){
        Customer customer = null;

        RestTemplate restTemplate = new RestTemplate();
        String getUrl = String.format("http://localhost:8080/api-v1/customer/%s", cif);
        ResponseEntity<CommonResponse<Customer>> response = restTemplate.exchange(getUrl, HttpMethod.GET,
                null, PARAMETERIZED_TYPE_COMMON_RESP_CUSTOMER);

        if (response.getStatusCode() != HttpStatus.OK){
            System.out.println(String.format("Failed with message %s", response.getStatusCodeValue()));
        } else if (StringUtils.isEmpty(response.getBody())){
            System.out.println("Response is null");
        } else {

            CommonResponse<Customer> crsp = response.getBody();
            if (!crsp.getStatus().equalsIgnoreCase("20")){
                String.format("Failed with message %s", crsp.getMessage());
            } else if (StringUtils.isEmpty(crsp.getData())){
                System.out.println("Response is null");
            } else {
                customer = crsp.getData();
            }

        }

        return customer;
    }

    @Override
    public CommonResponse addCustomer(Customer customer){
        CommonResponse transactionResponse = null;
        RestTemplate restTemplate = new RestTemplate();
        String postUrl = String.format("http://localhost:8080/api-v1/customer");
        ResponseEntity<String> response = restTemplate.postForEntity(URI.create(postUrl), customer, String.class);

        if (response.getStatusCode() != HttpStatus.OK){
            System.out.println(String.format("Failed with message %s", response.getStatusCodeValue()));
        } else if (StringUtils.isEmpty(response.getBody())){
            System.out.println("Response is null");
        } else {

            try {
                CommonResponse transResponse = MAPPER.readValue(response.getBody(), TRASACTION_RESP_CUSTOMER);
                if (transResponse.getStatus().equalsIgnoreCase("20")){
                    transactionResponse = transResponse;
                } else {
                    transactionResponse = transResponse;
                    System.out.println(String.format("Somethink wrong"));
                }
            } catch (Exception e){
                e.printStackTrace();
            }

        }
        return transactionResponse;
    }

    @Override
    public CommonResponse updatePassword(Customer customer){
        CommonResponse transactionResponse = null;
        RestTemplate restTemplate = new RestTemplate();
        String postUrl = String.format("http://localhost:8080/api-v1/customer/reset");
        HttpEntity<Customer> entity = new HttpEntity<>(customer);
        ResponseEntity<CommonResponse<Customer>> response = restTemplate.exchange(postUrl, HttpMethod.PUT, entity, PARAMETERIZED_TYPE_COMMON_RESP_CUSTOMER);

        if (response.getStatusCode() != HttpStatus.OK){
            System.out.println(String.format("Failed with message %s", response.getStatusCodeValue()));
        } else if (StringUtils.isEmpty(response.getBody())){
            System.out.println("Response is null");
        } else {

            try {
                CommonResponse transResponse = response.getBody();
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
