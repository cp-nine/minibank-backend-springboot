package dao.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.TrxWsDao;
import dto.CommonResponse;
import entity.TrxEntity;
import entity.Vtrx;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

public class TrxWsDaoImpl implements TrxWsDao {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final ParameterizedTypeReference<CommonResponse<Vtrx>> PARAMETERIZED_TYPE_COMMON_RESP_TRX = new ParameterizedTypeReference<CommonResponse<Vtrx>>() {};
    private static final TypeReference<CommonResponse<List<Vtrx>>> TYPE_REFERENCE_COMMON_TRXS = new TypeReference<CommonResponse<List<Vtrx>>>() {};
    private static final TypeReference<CommonResponse<TrxEntity>> TRASACTION_RESP_TRX = new TypeReference<CommonResponse<TrxEntity>>() {};

    @Override
    public List<Vtrx> getTransactionReport(String cif) {
        List<Vtrx> list = null;

        RestTemplate restTemplate = new RestTemplate();
        String getUrl = String.format("http://localhost:8080/api-v1/trx/%s", cif);
        ResponseEntity<String> response = restTemplate.getForEntity(getUrl, String.class);

        if (response.getStatusCode() != HttpStatus.OK){
            System.out.println(String.format("Failed with message %s", response.getStatusCodeValue()));
        } else if(StringUtils.isEmpty(response.getBody())){
            System.out.println("Response null");
        } else {
            try {

                CommonResponse<List<Vtrx>> crsp = MAPPER.readValue(response.getBody(), TYPE_REFERENCE_COMMON_TRXS);
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
    public CommonResponse<TrxEntity> topUp(TrxEntity trxEntity){
        CommonResponse<TrxEntity> transactionResponse = null;
        RestTemplate restTemplate = new RestTemplate();
        String postUrl = "http://localhost:8080/api-v1/trx/topup";
        ResponseEntity<String> response = restTemplate.postForEntity(URI.create(postUrl), trxEntity, String.class);

        if (response.getStatusCode() != HttpStatus.OK){
            System.out.println(String.format("Failed with message %s", response.getStatusCodeValue()));
        } else if (StringUtils.isEmpty(response.getBody())){
            System.out.println("Response is null");
        } else {

            try {
                CommonResponse<TrxEntity> transResponse = MAPPER.readValue(response.getBody(), TRASACTION_RESP_TRX);
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

    @Override
    public CommonResponse<TrxEntity> cashWithDrawal(TrxEntity trxEntity){
        CommonResponse<TrxEntity> transactionResponse = null;
        RestTemplate restTemplate = new RestTemplate();
        String postUrl = String.format("http://localhost:8080/api-v1/trx/cash");
        ResponseEntity<String> response = restTemplate.postForEntity(URI.create(postUrl), trxEntity, String.class);

        if (response.getStatusCode() != HttpStatus.OK){
            System.out.println(String.format("Failed with message %s", response.getStatusCodeValue()));
        } else if (StringUtils.isEmpty(response.getBody())){
            System.out.println("Response is null");
        } else {

            try {
                CommonResponse<TrxEntity> transResponse = MAPPER.readValue(response.getBody(), TRASACTION_RESP_TRX);
                if (!transResponse.getStatus().equalsIgnoreCase("20") && !transResponse.getStatus().equalsIgnoreCase("304")){
                    transactionResponse = transResponse;
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

    @Override
    public CommonResponse<TrxEntity> transfer(TrxEntity trxEntity){
        CommonResponse<TrxEntity> transactionResponse = null;
        RestTemplate restTemplate = new RestTemplate();
        String postUrl = String.format("http://localhost:8080/api-v1/trx/transfer");
        ResponseEntity<String> response = restTemplate.postForEntity(URI.create(postUrl), trxEntity, String.class);

        if (response.getStatusCode() != HttpStatus.OK){
            System.out.println(String.format("Failed with message %s", response.getStatusCodeValue()));
        } else if (StringUtils.isEmpty(response.getBody())){
            System.out.println("Response is null");
        } else {

            try {
                CommonResponse<TrxEntity> transResponse = MAPPER.readValue(response.getBody(), TRASACTION_RESP_TRX);
                if (!transResponse.getStatus().equalsIgnoreCase("20") && !transResponse.getStatus().equalsIgnoreCase("304")){
                    transactionResponse = transResponse;
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
