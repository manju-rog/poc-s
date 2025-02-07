package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PayloadProcessor {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void saveToDatabaseWithStages(String payload) throws Exception {
        // Deserialize JSON into InputPayload
        InputPayload inputPayload = objectMapper.readValue(payload, InputPayload.class);

        // Generate unique code
        String uniqueCode = getUniqueCodeForCustomer(inputPayload.getCustomerId());

        // Map InputPayload to OutputPayload using MapStruct
        OutputPayload outputPayload = PayloadMapper.INSTANCE.toOutputPayload(inputPayload);
        outputPayload.setCode(uniqueCode); // Add the generated code

        // Save to database
        saveToDatabase(outputPayload);
    }

    private String getUniqueCodeForCustomer(String customerId) {
        String query = "SELECT LPAD(SEQ_CODE.NEXTVAL, 5, '0') FROM DUAL";
        return jdbcTemplate.queryForObject(query, String.class);
    }

    private void saveToDatabase(OutputPayload payload) {
        String query = "INSERT INTO CUSTOMER_DATAS (CUSTOMER_ID, CUSTOMER_NAME, CODE, ADDRESS1, ADDRESS2) " +
                       "VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(query, payload.getCustomerId(), payload.getCustomerName(),
                payload.getCode(), payload.getCustomerAddress1(), payload.getCustomerAddress2());
    }
}

