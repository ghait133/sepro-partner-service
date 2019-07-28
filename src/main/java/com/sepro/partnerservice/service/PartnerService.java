package com.sepro.partnerservice.service;

import com.sepro.partnerservice.dto.PartnerDto;
import com.sepro.partnerservice.dto.UserDto;
import com.sepro.partnerservice.entity.Adresse;
import com.sepro.partnerservice.entity.Partner;
import com.sepro.partnerservice.error.UserAlreadyExistException;
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
import org.springframework.web.reactive.function.client.WebClient;

import javax.transaction.Transactional;
import java.io.IOException;

@Service
@Transactional
public class PartnerService implements IPartnerService{

    @Autowired
    private PartnerRepository partnerRepository;

    @LoadBalanced
    @Autowired
    private OAuth2RestTemplate restTemplate;
    @Autowired
    RestTemplate rt;




    @Override
    public Partner registerNewPartnerAccount(final PartnerDto accountDto) throws IOException {
        if (emailExists(accountDto.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email adress: " + accountDto.getEmail());
        }
        final Partner partner = new Partner();
        //user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        partner.setEmail(accountDto.getEmail());
        partner.setCompanyName(accountDto.getName());
        partner.setTelNumbre(accountDto.getTelNumbre());

        final UserDto user = new UserDto(accountDto.getPassword(),accountDto.getMatchingPassword(),accountDto.getEmail(),"partner_role");

        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{" +
                "\"email\":\"" + accountDto.getEmail() + "\","+
                "\"password\":\"" + accountDto.getPassword()+ "\","+
                "\"matchingPassword\":\"" + accountDto.getMatchingPassword() + "\","+
                "\"role\":\" role_partner\""+
                "}");
        Request request = new Request.Builder()
                .url("http://localhost:8073/registration")
                .post(body)
                .addHeader("Content-Type", "application/json")
                .build();

        Response response = client.newCall(request).execute();
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

}
