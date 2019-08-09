package com.sepro.partnerservice.resource;


import com.sepro.partnerservice.dto.CustomerList;
import com.sepro.partnerservice.service.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {
    @Autowired
    OAuth2RestTemplate restTemplate;

    @Autowired
    PartnerService partnerService;

    @RequestMapping(value = "/allcustomers/{partnerId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CustomerList registerUserAccount(@PathVariable final Long partnerId) {

        return partnerService.getAllCustomersForPartner(partnerId);
    }
}
