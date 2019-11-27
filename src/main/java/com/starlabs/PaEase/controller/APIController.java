package com.starlabs.PaEase.controller;

import com.google.gson.Gson;
import com.starlabs.PaEase.request.Request;
import com.starlabs.PaEase.config.SpringSecurityConfig;
import com.starlabs.PaEase.logger.APILogger;
import com.starlabs.PaEase.model.Clients;
import com.starlabs.PaEase.model.CustomerProfile;
import com.starlabs.PaEase.model.Statement;
import com.starlabs.PaEase.model.Transactions;
import com.starlabs.PaEase.repository.ClientRepository;
import com.starlabs.PaEase.repository.CustomerProfileRepository;
import com.starlabs.PaEase.repository.TransactionsRepository;
import com.starlabs.PaEase.service.APIService;
import com.starlabs.PaEase.service.Response;
import com.starlabs.PaEase.utils.Utils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author chulu
 */
@RestController
@RequestMapping("/api")
public class APIController {

    private APILogger log;
    private SpringSecurityConfig config;
    private Response resp;
    private Gson gson;
    private ArrayList response;
    private ArrayList validationResp;
    private Utils utils;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    TransactionsRepository transactionsRepository;
    @Autowired
    CustomerProfileRepository customerProfileRepository;
    @Autowired
    APIService processor;

    public APIController() {
        this.validationResp = new ArrayList();
        this.utils = new Utils();
        this.config = new SpringSecurityConfig();
        this.log = new APILogger(getClass());
        this.resp = new Response();
        this.gson = new Gson();
        this.response = new ArrayList();
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/clients")
    public List<Clients> getAllclients() {
        return clientRepository.findAll();
    }

    @GetMapping("/statement/{msisdn}")
    public List<Statement> getStatement(@PathVariable(value = "msisdn") String msisdn) {
        return transactionsRepository.statement(Long.valueOf(msisdn), 100);
    }

   /* @PostMapping("/clients")
    public Clients createClient(@Valid @RequestBody Clients client) {
        return clientRepository.save(client);
    }

    @GetMapping("/customerProfiles")
    public List<CustomerProfile> getCustomerprofiles() {
        return customerProfileRepository.findAll();
    }*/

    @PostMapping("/v1/json")
    public ResponseEntity<Response> JsonRequest(@Valid @RequestBody Request request) {
        log.info("| " + Thread.currentThread().getStackTrace()[1].getMethodName() + "|" + request.getMsisdn() + " | Request is : " + gson.toJson(request));

        if (this.config.checkMethod(request.getMethod())) {
            //Lets validate mandatory fields first before proceeding
            this.validationResp = this.config.validateMethodMandatoryFields(request, log, gson);
            if (this.validationResp.isEmpty()) {
                this.resp = this.processor.handleRequest(request);
            } else {
                //Missing mandatory fields
                this.resp.setData(response);
                this.resp.setStatus(Utils.status_missing_mandatory_parm);
                this.resp.setStatus_desc(Utils.status_missing_mandatory_parm_desc.replace("{params}", this.utils.ArrayListToString(this.validationResp)));
                log.info("| " + Thread.currentThread().getStackTrace()[1].getMethodName() + "|" + request.getMsisdn() + " | Mandatory parameter(s) {" + this.utils.ArrayListToString(this.validationResp) + "} are missing!");
            }
        } else {
            //Method being requested is not allowed
            log.info("| " + Thread.currentThread().getStackTrace()[1].getMethodName() + "|" + request.getMsisdn() + " | Requested method : " + request.getMethod() + " is not allowed!");
            this.resp.setData(response);
            this.resp.setStatus(Utils.status_invalid_method);
            this.resp.setStatus_desc(Utils.status_invalid_method_desc.replace("{method}", request.getMethod()));
        }
        return ResponseEntity.accepted().body(this.resp);
    }

    @GetMapping("/encode/{pin}")
    public ResponseEntity<String> encodePin(@PathVariable(value = "pin") String pin) {
        String response = "";
        if (!pin.isEmpty()) {
            response = this.config.encodePin(pin);
        }
        return new ResponseEntity<String>(response, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/decode")
    public ResponseEntity<Response> decodePin(@RequestBody Request request) {
        log.info(Thread.currentThread().getStackTrace()[1].getMethodName() + "| Response status code is: " + request.toString());
        this.resp.setStatus(101);
        if (request.getPin() > 0 && !request.getHash().isEmpty()) {
            this.resp.setStatus(100);
        }
        return ResponseEntity.ok(this.resp);
    }
}
