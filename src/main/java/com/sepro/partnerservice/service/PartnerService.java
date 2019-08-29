package com.sepro.partnerservice.service;

import com.sepro.partnerservice.dto.*;
import com.sepro.partnerservice.entity.Adresse;
import com.sepro.partnerservice.entity.CustomerPartner;
import com.sepro.partnerservice.entity.Partner;
import com.sepro.partnerservice.error.UserAlreadyExistException;
import com.sepro.partnerservice.model.CustomPrincipal;
import com.sepro.partnerservice.repository.CustomerRepository;
import com.sepro.partnerservice.repository.PartnerRepository;
import com.sepro.partnerservice.util.GenericResponse;
import com.squareup.okhttp.*;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PartnerService implements IPartnerService{

    @Autowired
    private PartnerRepository partnerRepository;

    @LoadBalanced
    @Autowired
    private OAuth2RestTemplate restTemplate;


    @Autowired
    CustomerRepository customerRepository;



    @Override
    public Partner registerNewPartnerAccount(final PartnerDto partnerForm) throws IOException {
        if (emailExists(partnerForm.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email adress: " + partnerForm.getEmail());
        }
        final Partner partner = new Partner();
        //user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        partner.setEmail(partnerForm.getEmail());
        partner.setCompanyLongName(partnerForm.getLongName());
        partner.setCompanyShortName(partnerForm.getShortName());
        partner.setTelNumber(partnerForm.getTelNumbre());
        partner.setSector_id(partnerForm.getSector_id());

        final UserDto user = new UserDto(
                partnerForm.getPassword(),
                partnerForm.getConfirmPassword(),
                partner.getEmail(),
                "partner_role");

        /*OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{" +
                "\"email\":\"" + accountDto.getEmail() + "\","+
                "\"password\":\"" + accountDto.getPassword()+ "\","+
                "\"matchingPassword\":\"" + accountDto.getConfirmPassword() + "\","+
                "\"role\":\" role_partner\""+
                "}");
        Request request = new Request.Builder()
                .url("http://localhost:8073/registration")
                .post(body)
                .addHeader("Content-Type", "application/json")
                .build();

        client.newCall(request).execute();*/
        //restTemplate.postForObject("http://user-service/registration", user,String.class);
            //OAuth2AccessToken oAuth2AccessToken = restTemplate.getAccessToken();
        return partnerRepository.save(partner);
    }
    private boolean emailExists(final String email) {
        return partnerRepository.findByEmail(email) != null;
    }

    public void deletePartner(Partner partner){

        partnerRepository.delete(partner);
    }

    public PartnerList getPartnersByIds (List<Long> partnerIds){
        List<Partner> partners = new ArrayList<>();
        partnerRepository.findAllById(partnerIds).forEach(partner -> {
            partners.add(partner);
        });

        PartnerList partnerList = new PartnerList(partners);

        return partnerList;
    }

    public CustomerList getAllCustomersForPartner (String partnerEmail){
        Long partnerId = partnerRepository.findByEmail(partnerEmail).getId();

        List<Long> customerIds = new ArrayList<>();
        customerRepository.findByPartnerId(partnerId).forEach(customerPartner -> {
            customerIds.add(customerPartner.getCustomerId());
        });
        CustomerList customers = restTemplate.postForObject("http://customer-service/getcustomersbyidlist",customerIds, CustomerList.class);

        return customers;
    }

    public Partner getPartnerByEmail(String partnerEmail){
        return partnerRepository.findByEmail(partnerEmail);
    }

    public void addCustomer(CustomerDto customer, CustomPrincipal principal) throws IOException {

        Partner partner = partnerRepository.findByEmail(principal.getEmail());
        Boolean flag = relationExists(partner,customer);
        if (!flag) {
            throw new UserAlreadyExistException("Der Kunde " + customer.getFirstName() + " " + customer.getLastName() + " existiert schon bei der Liste des Partners");
        }

        CustomerPartner customerPartner = new CustomerPartner();
        customerPartner.setCustomerId(customer.getId());
        customerPartner.setPartnerId(partner.getId());

        customerRepository.save(customerPartner);
    }

    private boolean relationExists( Partner partner,CustomerDto customer){
        return customerRepository.findByCustomerIdAndPartnerId(customer.getId(), partner.getId() ).isEmpty();
    }

}
