package com.sepro.partnerservice.service;

import com.sepro.partnerservice.dto.PartnerDto;
import com.sepro.partnerservice.entity.Partner;
import com.sepro.partnerservice.error.UserAlreadyExistException;

public interface IPartnerService {

    Partner registerNewPartnerAccount(PartnerDto accountDto) throws UserAlreadyExistException;

}
