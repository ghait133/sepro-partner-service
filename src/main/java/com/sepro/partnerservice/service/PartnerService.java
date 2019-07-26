package com.sepro.partnerservice.service;

import com.sepro.partnerservice.dto.PartnerDto;
import com.sepro.partnerservice.entity.Partner;
import com.sepro.partnerservice.error.UserAlreadyExistException;
import com.sepro.partnerservice.repository.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PartnerService implements IPartnerService{

    @Autowired
    private PartnerRepository partnerRepository;

    @Autowired
    private OAuth2RestTemplate restTemplate;

    @Override
    public Partner registerNewPartnerAccount(final PartnerDto accountDto) {
        if (emailExists(accountDto.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email adress: " + accountDto.getEmail());
        }
        final Partner partner = new Partner();
        //user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        partner.setEmail(accountDto.getEmail());
        partner.setCompanyName(accountDto.getName());
        partner.setTelNumbre(accountDto.getTelNumbre());

        OAuth2AccessToken oAuth2AccessToken = restTemplate.getAccessToken();
        return partnerRepository.save(partner);
    }
    private boolean emailExists(final String email) {
        return partnerRepository.findByEmail(email) != null;
    }

}
