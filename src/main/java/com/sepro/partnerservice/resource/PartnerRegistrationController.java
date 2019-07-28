package com.sepro.partnerservice.resource;

import com.sepro.partnerservice.dto.PartnerDto;
import com.sepro.partnerservice.entity.Partner;
import com.sepro.partnerservice.service.IPartnerService;
import com.sepro.partnerservice.util.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;

@RestController
public class PartnerRegistrationController {

    @Autowired
    private IPartnerService partnerService;

    @GetMapping("/")
    public String hello(){
        return "Hello in Partner IPi";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public GenericResponse registerUserAccount(@Valid @RequestBody final PartnerDto accountDto, final HttpServletRequest request) {
        try {
            final Partner registered = partnerService.registerNewPartnerAccount(accountDto);
        } catch (IOException e) {
            return new GenericResponse("error");
        }
        return new GenericResponse("success");
    }
}
