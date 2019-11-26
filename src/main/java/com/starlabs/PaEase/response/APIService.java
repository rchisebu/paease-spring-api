/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.starlabs.PaEase.response;

import com.google.gson.Gson;
import com.starlabs.PaEase.request.Request;
import com.starlabs.PaEase.config.SpringSecurityConfig;
import com.starlabs.PaEase.logger.APILogger;
import com.starlabs.PaEase.model.CustomerProfile;
import com.starlabs.PaEase.repository.CustomerProfileRepository;
import com.starlabs.PaEase.utils.Utils;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author francis chulu
 */
@Service
public class APIService {

    private ArrayList response;
    private final APILogger log;
    private final SpringSecurityConfig config;
    private Response resp;
    private final Gson gson;
    private Long msisdn;
    private Long b_msisdn;
    private int pin;
    private String extra_data;
    private int serviceID;
    private double amount;
    private Long account;
    private String transactionID;
    private int status_code;
    private String status_desc;
    private String external_transactionID;
    private String receipt_number;
    private String method;
    private String hash;
    private final Utils utils;
    @Autowired
    CustomerProfileRepository customerProfileRepository;
    private CustomerProfile customerProfile;

    public APIService() {
        this.utils = new Utils();
        this.config = new SpringSecurityConfig();
        this.log = new APILogger(getClass());
        this.resp = new Response();
        this.gson = new Gson();
        this.response = new ArrayList();
    }

    public Response handleRequest(Request request) {
        this.initializeRequestParams(request);
        //<editor-fold defaultstate="collapsed" desc="Method = verifyPin">
        if (request.getMethod().equals("verifyPin")) {
            if (SpringSecurityConfig.validateMsisdn(String.valueOf(request.getMsisdn()), this.log)) {
                this.resp = this.verifyPin(request);
            } else {
                this.resp.setData(response);
                this.resp.setStatus(Utils.status_invalid_msisdn);
                this.resp.setStatus_desc(Utils.status_invalid_msisdn_desc.replace("{msisdn}", String.valueOf(request.getMsisdn())));
            }
        } //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Method = changePin">
        if (request.getMethod().equals("changePin")) {
            if (SpringSecurityConfig.validateMsisdn(String.valueOf(request.getMsisdn()), this.log)) {
                this.resp = this.changePin(request);
            } else {
                this.resp.setData(response);
                this.resp.setStatus(Utils.status_invalid_msisdn);
                this.resp.setStatus_desc(Utils.status_invalid_msisdn_desc.replace("{msisdn}", String.valueOf(request.getMsisdn())));
            }
        } //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Method = customerProfile">
        if (request.getMethod().equals("customerProfile")) {
            if (SpringSecurityConfig.validateMsisdn(String.valueOf(request.getMsisdn()), this.log)) {
                this.resp = this.customerProfile(request);
            } else {
                this.resp.setData(response);
                this.resp.setStatus(Utils.status_invalid_msisdn);
                this.resp.setStatus_desc(Utils.status_invalid_msisdn_desc.replace("{msisdn}", String.valueOf(request.getMsisdn())));
            }
        } //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Method = pinAttempts">
        if (request.getMethod().equals("pinAttempts")) {
            if (SpringSecurityConfig.validateMsisdn(String.valueOf(request.getMsisdn()), this.log)) {
                this.resp = this.pinAttempts(request);
            } else {
                this.resp.setData(response);
                this.resp.setStatus(Utils.status_invalid_msisdn);
                this.resp.setStatus_desc(Utils.status_invalid_msisdn_desc.replace("{msisdn}", String.valueOf(request.getMsisdn())));
            }
        } //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Method = blockProfile">
        if (request.getMethod().equals("blockProfile")) {
            if (SpringSecurityConfig.validateMsisdn(String.valueOf(request.getMsisdn()), this.log)) {
                this.resp = this.blockProfile(request);
            } else {
                this.resp.setData(response);
                this.resp.setStatus(Utils.status_invalid_msisdn);
                this.resp.setStatus_desc(Utils.status_invalid_msisdn_desc.replace("{msisdn}", String.valueOf(request.getMsisdn())));
            }
        } //</editor-fold>
        return this.resp;
    }

    private void initializeRequestParams(Request request) {
        this.msisdn = request.getMsisdn();
        this.b_msisdn = request.getB_msisdn();
        this.pin = request.getPin();
        this.extra_data = request.getExtra_data();
        this.serviceID = request.getServiceID();
        this.amount = request.getAmount();
        this.account = request.getAccount();
        this.transactionID = request.getTransactionID();
        this.status_code = request.getStatus_code();
        this.status_desc = request.getStatus_desc();
        this.external_transactionID = request.getExternal_transactionID();
        this.receipt_number = request.getReceipt_number();
        this.method = request.getMethod();
        this.hash = request.getHash();
        this.resp.setData(this.response);
        this.resp.setStatus(Utils.status_error);
        this.resp.setStatus_desc(Utils.status_error_desc);
    }

    /**
     * Verifies customer pin
     *
     * @param request
     * @return
     */
    public Response verifyPin(Request request) {
        log.info("| " + Thread.currentThread().getStackTrace()[1].getMethodName() + "|" + request.getMsisdn() + "| Verifying customers pin to authenticate them");
        try {
            this.customerProfile = customerProfileRepository.findByMsisdn(request.getMsisdn());
            if (this.customerProfile != null && this.config.verifyPin(String.valueOf(request.getPin()), this.customerProfile.getPIN())) {
                log.info("| " + Thread.currentThread().getStackTrace()[1].getMethodName() + "|" + request.getMsisdn() + "| Supplied Pin is valid");
                this.resp.setData(response);
                this.resp.setStatus(Utils.status_ok);
                this.resp.setStatus_desc(Utils.status_valid_pin_desc);
            } else {
                log.info("| " + Thread.currentThread().getStackTrace()[1].getMethodName() + "|" + request.getMsisdn() + "| Supplied Pin is invalid");
                this.resp.setData(response);
                this.resp.setStatus(Utils.status_invalid_pin);
                this.resp.setStatus_desc(Utils.status_invalid_pin_desc);
            }

        } catch (Exception ex) {
            log.info("| " + Thread.currentThread().getStackTrace()[1].getMethodName() + "|" + request.getMsisdn() + "| Error occured while processing request. Error is : " + ex.getMessage());
        }
        log.info("| " + Thread.currentThread().getStackTrace()[1].getMethodName() + " | Response is : " + gson.toJson(this.resp));
        return this.resp;
    }

    public Response changePin(Request request) {
        log.info("| " + Thread.currentThread().getStackTrace()[1].getMethodName() + "|" + request.getMsisdn() + "| Chaning customers pin");
        try {
            this.customerProfile = customerProfileRepository.findByMsisdn(request.getMsisdn());
            if (this.customerProfile != null) {
                String hash = this.config.encodePin(String.valueOf(request.getPin()));
                if (!hash.isEmpty()) {
                    log.info("| " + Thread.currentThread().getStackTrace()[1].getMethodName() + "|" + request.getMsisdn() + "| Customer pin was successfully changed");
                    this.customerProfile.setPIN(hash);
                    customerProfileRepository.save(this.customerProfile);
                    this.resp.setData(response);
                    this.resp.setStatus(Utils.status_ok);
                    this.resp.setStatus_desc("Pin was successfully changed");
                }
            } else {
                log.info("| " + Thread.currentThread().getStackTrace()[1].getMethodName() + "|" + request.getMsisdn() + "| No customer record was found for this msisdn");
                this.resp.setData(response);
                this.resp.setStatus(Utils.status_no_record);
                this.resp.setStatus_desc(Utils.status_no_record_desc);
            }

        } catch (Exception ex) {
            log.info("| " + Thread.currentThread().getStackTrace()[1].getMethodName() + " | Error occured while processing request. Error is : " + ex.getMessage());
        }
        // log.info("| " + Thread.currentThread().getStackTrace()[1].getMethodName() + " | Response is : " + gson.toJson(this.resp));
        return this.resp;
    }

    public Response pinAttempts(Request request) {
        log.info("| " + Thread.currentThread().getStackTrace()[1].getMethodName() + "|" + request.getMsisdn() + "| Updating customers wrong pin attempts to: " + request.getExtra_data());
        try {
            this.customerProfile = customerProfileRepository.findByMsisdn(request.getMsisdn());
            if (this.customerProfile != null) {
                this.customerProfile.setWrong_pin_attempts(Integer.parseInt(request.getExtra_data()));
                customerProfileRepository.save(this.customerProfile);
                this.resp.setData(response);
                this.resp.setStatus(Utils.status_ok);
                this.resp.setStatus_desc(Utils.status_ok_desc);
                log.info("| " + Thread.currentThread().getStackTrace()[1].getMethodName() + "|" + request.getMsisdn() + "| Wrong pin attempts was successfully updated");

            } else {
                this.resp.setData(response);
                this.resp.setStatus(Utils.status_no_record);
                this.resp.setStatus_desc(Utils.status_no_record_desc);
                log.info("| " + Thread.currentThread().getStackTrace()[1].getMethodName() + "|" + request.getMsisdn() + "| Wrong pin attempt update was not successful");
            }

        } catch (Exception ex) {
            log.info("| " + Thread.currentThread().getStackTrace()[1].getMethodName() + " | Error occured while processing request. Error is : " + ex.getMessage());
        }
        log.info("| " + Thread.currentThread().getStackTrace()[1].getMethodName() + " | Response is : " + gson.toJson(this.resp));
        return this.resp;
    }

    public Response blockProfile(Request request) {
        log.info("| " + Thread.currentThread().getStackTrace()[1].getMethodName() + "|" + request.getMsisdn() + "| Block customer profile request received ");
        try {
            this.customerProfile = customerProfileRepository.findByMsisdn(request.getMsisdn());
            if (this.customerProfile != null) {
                this.customerProfile.setStatus(Utils.profile_blocked_status);
                customerProfileRepository.save(this.customerProfile);
                this.resp.setData(response);
                this.resp.setStatus(Utils.status_ok);
                this.resp.setStatus_desc(Utils.status_ok_desc);
                log.info("| " + Thread.currentThread().getStackTrace()[1].getMethodName() + "|" + request.getMsisdn() + "| Customer profile was successfully blocked");

            } else {
                this.resp.setData(response);
                this.resp.setStatus(Utils.status_no_record);
                this.resp.setStatus_desc(Utils.status_no_record_desc);
                log.info("| " + Thread.currentThread().getStackTrace()[1].getMethodName() + "|" + request.getMsisdn() + "| Customer record for this msisdn was not found");
            }

        } catch (Exception ex) {
            log.info("| " + Thread.currentThread().getStackTrace()[1].getMethodName() + " | Error occured while processing request. Error is : " + ex.getMessage());
        }
        log.info("| " + Thread.currentThread().getStackTrace()[1].getMethodName() + " | Response is : " + gson.toJson(this.resp));
        return this.resp;
    }

    public Response customerProfile(Request request) {
        log.info("| " + Thread.currentThread().getStackTrace()[1].getMethodName() + "|" + request.getMsisdn() + "| Getting customers profile details");
        try {
            this.customerProfile = customerProfileRepository.findByMsisdn(msisdn);
            if (this.customerProfile != null) {
                response.add(this.customerProfile);
                this.resp.setData(response);
                this.resp.setStatus(Utils.status_ok);
                this.resp.setStatus_desc("Request OK");
                log.info("| " + Thread.currentThread().getStackTrace()[1].getMethodName() + "|" + request.getMsisdn() + "| Customer details were found");
            } else {
                log.info("| " + Thread.currentThread().getStackTrace()[1].getMethodName() + "|" + request.getMsisdn() + "| No customer record was found!");
                this.resp.setData(response);
                this.resp.setStatus(Utils.status_no_record);
                this.resp.setStatus_desc(Utils.status_no_record_desc);
            }
            log.info("| " + Thread.currentThread().getStackTrace()[1].getMethodName() + " | Response is : " + this.gson.toJson(this.resp));
        } catch (Exception ex) {
            log.info("| " + Thread.currentThread().getStackTrace()[1].getMethodName() + " | Error occured while processing request. Error is : " + ex.getMessage());
        }
        return this.resp;
    }
}
