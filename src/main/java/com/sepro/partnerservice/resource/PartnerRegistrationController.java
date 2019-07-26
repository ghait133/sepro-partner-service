package com.sepro.partnerservice.resource;

import com.sepro.partnerservice.entity.Partner;
import com.sepro.partnerservice.util.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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


        final Partner registered = partnerService.registerNewPartnerAccount(accountDto);
        //eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, request.getLocale(), getAppUrl(request)));
        return new GenericResponse("success");
    }
}
