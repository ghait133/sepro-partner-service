package com.sepro.partnerservice.service;

import com.sepro.partnerservice.dto.CustomerList;
import com.sepro.partnerservice.dto.PartnerDto;
import com.sepro.partnerservice.dto.PartnerList;
import com.sepro.partnerservice.dto.UserDto;
import com.sepro.partnerservice.entity.Adresse;
import com.sepro.partnerservice.entity.Partner;
import com.sepro.partnerservice.error.UserAlreadyExistException;
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

    public CustomerList getAllCustomersForPartner (Long partnerId){

        List<Long> customerIds = new ArrayList<>();
        customerRepository.findByPartnerId(partnerId).forEach(customerPartner -> {
            customerIds.add(customerPartner.getCustomerId());
        });
        CustomerList customers = restTemplate.postForObject("http://customer-service/getcustomersbyidlist",customerIds, CustomerList.class);

        return customers;
    }

}
