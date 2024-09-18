package com.jwtexample.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private Jaxb2Marshaller marshaller;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/submit")
    public String submitPaymentRequest(@RequestBody PaymentRequest paymentRequest) {
        // Convert the PaymentRequest object to XML
        String xmlRequest = convertToXml(paymentRequest);

        // Set the HTTP headers for the request
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);

        // Create an HttpEntity with the XML data and headers
        HttpEntity<String> requestEntity = new HttpEntity<>(xmlRequest, headers);

        // Make an HTTP POST request to the payment gateway API
        String apiEndpoint = "https://64f1919e0e1e60602d23f102.mockapi.io/api/check/testing";
        String response = restTemplate.postForObject(apiEndpoint, requestEntity, String.class);

        // Process the response from the payment gateway API
        // ...

        return response;
    }

    private String convertToXml(Object obj) {
        // Use the marshaller to convert the object to XML
        StringWriter writer = new StringWriter();
        marshaller.marshal(obj, new StreamResult(writer));
        return writer.toString();
    }
}

