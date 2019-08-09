package com.sepro.partnerservice.resource;

import com.sepro.partnerservice.dto.PartnerList;
import com.sepro.partnerservice.entity.Partner;
import com.sepro.partnerservice.service.PartnerService;
import com.sepro.partnerservice.util.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class PartnerInfosController {

    @Autowired
    PartnerService partnerService;

    @RequestMapping(value = "/getpartnersbyidlist", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('role_partner','role_customer')")
    public PartnerList changeUserPassword(@RequestBody List<Long> partnerIds) {

        return partnerService.getPartnersByIds(partnerIds);
    }
}
