package com.demo.liquibase.service;

import com.demo.liquibase.model.Product;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.net.URI;
import java.nio.charset.StandardCharsets;

@Service
public class ProductSynchronizer {

    private static final URI URL = URI.create("http://localhost:8081/v1/products");
    private static final String URL_WITH_SKU = URL + "/{SKU}";

    @Value("${file.name}")
    private String fileName;

    private final RestTemplate restTemplate = new RestTemplate();

    @Scheduled(fixedDelay = 6000L)
    private void runSynchronizer() throws IOException {
        ClassPathResource resource = new ClassPathResource(fileName);
        if (!resource.exists()) {
            return;
        }
        File file = resource.getFile();

        try (BufferedReader fileReader = new BufferedReader(
                new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT
                             .withFirstRecordAsHeader()
                             .withIgnoreHeaderCase()
                             .withTrim())) {

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                Product product = new Product(
                        Integer.parseInt(csvRecord.get("sku")),
                        csvRecord.get("title"),
                        csvRecord.get("description"),
                        Double.parseDouble(csvRecord.get("price")),
                        Integer.parseInt(csvRecord.get("quantity"))
                );
                ResponseEntity<Product> productResponse = restTemplate.getForEntity(URL_WITH_SKU,
                        Product.class, product.getSKU());
                if (productResponse.getStatusCode() == HttpStatus.OK) {
                    Product productBody = productResponse.getBody();
                    if (productBody == null) {
                        ResponseEntity<Product> response = restTemplate.postForEntity(URL.toString(),
                                product, Product.class);
                        if (response.getStatusCode() == HttpStatus.OK) {
                            System.out.println("Product Added");
                        } else {
                            System.out.println("Error while adding product");
                        }
                    } else {
                        restTemplate.put(URL_WITH_SKU, productBody, product.getSKU());
                    }
                } else {
                    System.out.println("Error while executing get request with error code " + productResponse.getStatusCode());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }
}
