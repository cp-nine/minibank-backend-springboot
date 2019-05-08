package dao.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.WalletWsDao;
import dto.CommonResponse;
import entity.Vwallet;
import entity.Wallet;
import entity.WalletAccount;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

public class WalletWsDaoImpl implements WalletWsDao {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final TypeReference<CommonResponse<Wallet>> TRASACTION_RESP_WALLET = new TypeReference<CommonResponse<Wallet>>() {};
    private static final TypeReference<CommonResponse<WalletAccount>> TRASACTION_RESP_WALLET_ACCOUNT = new TypeReference<CommonResponse<WalletAccount>>() {};
    private static final TypeReference<CommonResponse<List<Vwallet>>> TRASACTION_RESP_VWALLET = new TypeReference<CommonResponse<List<Vwallet>>>() {};
    private static final TypeReference<CommonResponse<List<WalletAccount>>> TRASACTION_RESP_WALLET_ACCOUNTS = new TypeReference<CommonResponse<List<WalletAccount>>>() {};

    @Override
    public List<Vwallet> getWalletCustomer(String cif) {
        List<Vwallet> list = null;

        RestTemplate restTemplate = new RestTemplate();
        String getUrl = String.format("http://localhost:8080/api-v1/wallet/list-account/%s", cif);
        ResponseEntity<String> response = restTemplate.getForEntity(getUrl, String.class);

        if (response.getStatusCode() != HttpStatus.OK){
            System.out.println(String.format("Failed with message %s", response.getStatusCodeValue()));
        } else if(StringUtils.isEmpty(response.getBody())){
            System.out.println("Response null");
        } else {
            try {

                CommonResponse<List<Vwallet>> crsp = MAPPER.readValue(response.getBody(), TRASACTION_RESP_VWALLET);
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
    public Wallet getWalletProfile(Integer wid) {
        Wallet list = null;

        RestTemplate restTemplate = new RestTemplate();
        String getUrl = String.format("http://localhost:8080/api-v1/wallet/%s", wid);
        ResponseEntity<String> response = restTemplate.getForEntity(getUrl, String.class);

        if (response.getStatusCode() != HttpStatus.OK){
            System.out.println(String.format("Failed with message %s", response.getStatusCodeValue()));
        } else if(StringUtils.isEmpty(response.getBody())){
            System.out.println("Response null");
        } else {
            try {

                CommonResponse<Wallet> crsp = MAPPER.readValue(response.getBody(), TRASACTION_RESP_WALLET);
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
    public CommonResponse<Wallet> unregWallet(Integer wid) {
        CommonResponse<Wallet> transactionResponse = null;
        RestTemplate restTemplate = new RestTemplate();
        String postUrl = String.format("http://localhost:8080/api-v1/wallet/unreg/%s", wid);
        ResponseEntity<String> response = restTemplate.postForEntity(URI.create(postUrl), null, String.class);

        if (response.getStatusCode() != HttpStatus.OK){
            System.out.println(String.format("Failed with message %s", response.getStatusCodeValue()));
        } else if (StringUtils.isEmpty(response.getBody())){
            System.out.println("Response is null");
        } else {

            try {
                CommonResponse<Wallet> transResponse = MAPPER.readValue(response.getBody(), TRASACTION_RESP_WALLET);
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
    public List<WalletAccount> getWalletAccount(Integer wid) {
        List<WalletAccount> list = null;

        RestTemplate restTemplate = new RestTemplate();
        String getUrl = String.format("http://localhost:8080/api-v1/wallet/account/%s", wid);
        ResponseEntity<String> response = restTemplate.getForEntity(getUrl, String.class);

        if (response.getStatusCode() != HttpStatus.OK){
            System.out.println(String.format("Failed with message %s", response.getStatusCodeValue()));
        } else if(StringUtils.isEmpty(response.getBody())){
            System.out.println("Response null");
        } else {
            try {

                CommonResponse<List<WalletAccount>> crsp = MAPPER.readValue(response.getBody(), TRASACTION_RESP_WALLET_ACCOUNTS);
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
    public CommonResponse<Wallet> addWallet(Wallet wallet,Long acn){
        CommonResponse<Wallet> transactionResponse = null;
        RestTemplate restTemplate = new RestTemplate();
        String postUrl = String.format("http://localhost:8080/api-v1/wallet/%s", acn);
        ResponseEntity<String> response = restTemplate.postForEntity(URI.create(postUrl), wallet, String.class);

        if (response.getStatusCode() != HttpStatus.OK){
            System.out.println(String.format("Failed with message %s", response.getStatusCodeValue()));
        } else if (StringUtils.isEmpty(response.getBody())){
            System.out.println("Response is null");
        } else {

            try {
                CommonResponse<Wallet> transResponse = MAPPER.readValue(response.getBody(), TRASACTION_RESP_WALLET);
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
    public CommonResponse<WalletAccount> addWalletAccount(WalletAccount walletAccount){
        CommonResponse<WalletAccount> transactionResponse = null;
        RestTemplate restTemplate = new RestTemplate();
        String postUrl = String.format("http://localhost:8080/api-v1/wallet/account");
        ResponseEntity<String> response = restTemplate.postForEntity(URI.create(postUrl), walletAccount, String.class);

        if (response.getStatusCode() != HttpStatus.OK){
            System.out.println(String.format("Failed with message %s", response.getStatusCodeValue()));
        } else if (StringUtils.isEmpty(response.getBody())){
            System.out.println("Response is null");
        } else {

            try {
                CommonResponse<WalletAccount> transResponse = MAPPER.readValue(response.getBody(), TRASACTION_RESP_WALLET_ACCOUNT);
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
