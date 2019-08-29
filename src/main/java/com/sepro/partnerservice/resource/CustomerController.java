package com.sepro.partnerservice.resource;


import com.sepro.partnerservice.dto.CustomerDto;
import com.sepro.partnerservice.dto.CustomerList;
import com.sepro.partnerservice.entity.CustomerPartner;
import com.sepro.partnerservice.model.CustomPrincipal;
import com.sepro.partnerservice.repository.PartnerRepository;
import com.sepro.partnerservice.service.PartnerService;
import com.sepro.partnerservice.util.GenericResponse;
import com.sun.tools.javac.jvm.Gen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    OAuth2RestTemplate restTemplate;

    @Autowired
    PartnerService partnerService;

    @Autowired
    PartnerRepository partnerRepository;

    @RequestMapping(value = "/allcustomers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CustomerList registerUserAccount(CustomPrincipal principal) {

        return partnerService.getAllCustomersForPartner(principal.getEmail());
    }

    @RequestMapping(value = "/addcustomer", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public GenericResponse addCustomer(@RequestBody CustomerDto customer, CustomPrincipal principal) throws IOException {

        partnerService.addCustomer(customer, principal);

        return new GenericResponse("success");

    }


}
