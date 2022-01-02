package com.pas.orlikrent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pas.orlikrent.dto.accounts.*;
import com.pas.orlikrent.exceptions.ETagException;
import com.pas.orlikrent.model.Users.Client;
import com.pas.orlikrent.security.EntityIdentitySignerVerifier;
import com.pas.orlikrent.security.PropertiesReader;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import javax.validation.constraints.AssertTrue;
import javax.ws.rs.core.MediaType;
import java.util.*;

import static io.restassured.RestAssured.given;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AccountEndpointT {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String accountBaseUri;
    public AccountEndpointT() {
        Properties securityProperties = PropertiesReader.getSecurityProperties();
        accountBaseUri = securityProperties.getProperty("app.baseurl") + "/OrlikRentPAS";
    }

    @Test
    public void getAllAccounts_SUCCESS() throws JsonProcessingException {
        Response response = given().baseUri(accountBaseUri).get("/Account");
        String accountString = response.getBody().asString();
        List<AccountDTO> accountDTOList = Arrays.asList(objectMapper.readValue(accountString,AccountDTO[].class));
        assertThat(response.getStatusCode()).isEqualTo(200);
        assertTrue(accountDTOList.size()>0);
    }
    @Test
    public void getAllClients_SUCESS() throws JsonProcessingException {
        Response response = given().baseUri(accountBaseUri).get("/Account/clients");
        String accountString = response.getBody().asString();
        List<ClientDTO> clientsDTOList = Arrays.asList(objectMapper.readValue(accountString,ClientDTO[].class));
        assertThat(response.getStatusCode()).isEqualTo(200);
        assertTrue(clientsDTOList.size()>0);
        for (ClientDTO client :clientsDTOList
             ) {
            assertEquals("USER", client.getRole());
        }
    }
    @Test
    public void getAllManagers_SUCESS() throws JsonProcessingException {
        Response response = given().baseUri(accountBaseUri).get("/Account/managers");
        String accountString = response.getBody().asString();
        List<ManagerDTO> accountDTOList = Arrays.asList(objectMapper.readValue(accountString,ManagerDTO[].class));
        assertThat(response.getStatusCode()).isEqualTo(200);
        assertTrue(accountDTOList.size()>0);
        for (ManagerDTO ac :accountDTOList
        ) {
            assertEquals("MANAGER", ac.getRole());
        }
    }
    @Test
    public void getAllAdmins_SUCESS() throws JsonProcessingException {
        Response response = given().baseUri(accountBaseUri).get("/Account/admins");
        String accountString = response.getBody().asString();
        List<AccountDTO> accountDTOList = Arrays.asList(objectMapper.readValue(accountString,AccountDTO[].class));
        assertThat(response.getStatusCode()).isEqualTo(200);
        assertTrue(accountDTOList.size()>0);
        for (AccountDTO ac :accountDTOList
        ) {
            assertEquals("ADMINISTRATOR", ac.getRole());
        }
    }
    @Test
    public void createAndGetClient_SUCESS() throws JsonProcessingException {
        ClientForRegistrationDTO newClient = new ClientForRegistrationDTO("test","abcABC123*","test@gmail.com","Test","Test");
        given().baseUri(accountBaseUri).contentType(MediaType.APPLICATION_JSON).body(newClient).when().post("/Account/client").then().statusCode(201);
        Response response = given().baseUri(accountBaseUri).get("/Account/clients");
        String accountString = response.getBody().asString();
        List<ClientDTO> clientsDTOList = Arrays.asList(objectMapper.readValue(accountString,ClientDTO[].class));
        Optional<ClientDTO> login = clientsDTOList.stream().filter(clientDTO -> clientDTO.getLogin().equals(newClient.getLogin())).findAny();
        Response res = given().baseUri(accountBaseUri).get("/Account/client/"+ login.get().getId());
        String resString = res.getBody().asString();
        ClientDTO getClient = objectMapper.readValue(resString,ClientDTO.class);
        assertEquals(getClient.getLogin(), newClient.getLogin());


    }
    @Test
    public void createAndGetManager_SUCESS() throws JsonProcessingException {
        ManagerForRegistrationDTO newManager = new ManagerForRegistrationDTO("testManager","abcABC123*","testManager@gmail.com",3670.0,40);
        given().baseUri(accountBaseUri).contentType(MediaType.APPLICATION_JSON).body(newManager).when().post("/Account/manager").then().statusCode(201);
        Response response = given().baseUri(accountBaseUri).get("/Account/managers");
        String accountString = response.getBody().asString();
        List<ManagerDTO> accountDTOList = Arrays.asList(objectMapper.readValue(accountString,ManagerDTO[].class));
        Optional<ManagerDTO> login = accountDTOList.stream().filter(managerDTO ->  managerDTO.getLogin().equals(newManager.getLogin())).findAny();
        Response res = given().baseUri(accountBaseUri).get("/Account/manager/"+ login.get().getId());
        String resString = res.getBody().asString();
        ManagerDTO getManager = objectMapper.readValue(resString,ManagerDTO.class);
        assertEquals(getManager.getLogin(), newManager.getLogin());
    }
    @Test
    public void createAndGetAdmin_SUCESS() throws JsonProcessingException {
        AdminForRegistrationDTO newAdmin = new AdminForRegistrationDTO("testAdmin","abcABC123*","testAdmin@gmail.com");
        given().baseUri(accountBaseUri).contentType(MediaType.APPLICATION_JSON).body(newAdmin).when().post("/Account/admin").then().statusCode(201);
        Response response = given().baseUri(accountBaseUri).get("/Account/admins");
        String accountString = response.getBody().asString();
        List<AccountDTO> accountDTOList = Arrays.asList(objectMapper.readValue(accountString,AccountDTO[].class));
        Optional<AccountDTO> login = accountDTOList.stream().filter(adminDTO ->  adminDTO.getLogin().equals(newAdmin.getLogin())).findAny();
        Response res = given().baseUri(accountBaseUri).get("/Account/admin/"+ login.get().getId());
        String resString = res.getBody().asString();
        AccountDTO getAdmin = objectMapper.readValue(resString,AccountDTO.class);
        assertEquals(getAdmin.getLogin(), newAdmin.getLogin());
    }
    @Test
    public void updateClient_SUCESS() throws JsonProcessingException, ETagException {
        Response response = given().baseUri(accountBaseUri).get("/Account/clients");
        String accountString = response.getBody().asString();
        List<ClientDTO> clientsDTOList = Arrays.asList(objectMapper.readValue(accountString,ClientDTO[].class));
        Optional<ClientDTO> login = clientsDTOList.stream().filter(clientDTO -> clientDTO.getLogin().equals("jkowalski")).findAny();
        ClientDTO updatedClient = new ClientDTO(login.get().getId(),login.get().getLogin(),login.get().getEmail(),login.get().getActive(),login.get().getRole()
                                                  ,"Jeff","Bezos");
        String etag = EntityIdentitySignerVerifier.calculateEntitySignature(updatedClient);
        Response res = given().baseUri(accountBaseUri).header("If-Match",etag).contentType(MediaType.APPLICATION_JSON).body(updatedClient).when().put("/Account/UpdateClient/"+login.get().getId());
        assertThat(res.getStatusCode()).isEqualTo(204);
    }
    @Test
    public void updateManager_SUCESS() throws JsonProcessingException, ETagException {
        Response response = given().baseUri(accountBaseUri).get("/Account/managers");
        String accountString = response.getBody().asString();
        List<ManagerDTO> clientsDTOList = Arrays.asList(objectMapper.readValue(accountString,ManagerDTO[].class));
        Optional<ManagerDTO> login = clientsDTOList.stream().filter(clientDTO -> clientDTO.getLogin().equals("mklyz")).findAny();
        ManagerDTO updatedClient = new ManagerDTO(login.get().getId(),login.get().getLogin(),login.get().getEmail(),login.get().getActive(),login.get().getRole()
                ,9999.9,99999);
        String etag = EntityIdentitySignerVerifier.calculateEntitySignature(updatedClient);
        Response res = given().baseUri(accountBaseUri).header("If-Match",etag).contentType(MediaType.APPLICATION_JSON).body(updatedClient).when().put("/Account/UpdateManager/"+login.get().getId());
        assertThat(res.getStatusCode()).isEqualTo(204);
    }
    @Test
    public void updateAdmin_SUCESS() throws JsonProcessingException, ETagException {
        Response response = given().baseUri(accountBaseUri).get("/Account/admins");
        String accountString = response.getBody().asString();
        List<AccountDTO> clientsDTOList = Arrays.asList(objectMapper.readValue(accountString,AccountDTO[].class));
        Optional<AccountDTO> login = clientsDTOList.stream().filter(clientDTO -> clientDTO.getLogin().equals("dbednarek")).findAny();
        AccountDTO updatedClient = new AccountDTO(login.get().getId(),login.get().getLogin(),"zmieniony@gmail.com",login.get().getActive(),login.get().getRole());
        String etag = EntityIdentitySignerVerifier.calculateEntitySignature(updatedClient);
        Response res = given().baseUri(accountBaseUri).header("If-Match",etag).contentType(MediaType.APPLICATION_JSON).body(updatedClient).when().put("/Account/UpdateAdmin/"+login.get().getId());
        assertThat(res.getStatusCode()).isEqualTo(204);
    }
    @Test
    public void activateClient_SUCESS(){
        given().baseUri(accountBaseUri).contentType(MediaType.APPLICATION_JSON).when().post("/Account/AccountActivation/tnowak").then().statusCode(201);
    }
}
