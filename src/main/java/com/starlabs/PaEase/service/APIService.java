package com.starlabs.PaEase.service;

import com.google.gson.Gson;
import com.starlabs.PaEase.request.Request;
import com.starlabs.PaEase.config.ApiSecurityConfig;
import com.starlabs.PaEase.logger.APILogger;
import com.starlabs.PaEase.model.CustomerProfile;
import com.starlabs.PaEase.model.Services;
import com.starlabs.PaEase.model.Statement;
import com.starlabs.PaEase.model.Transactions;
import com.starlabs.PaEase.model.User;
import com.starlabs.PaEase.repository.CustomerProfileRepository;
import com.starlabs.PaEase.repository.ServicesRepository;
import com.starlabs.PaEase.repository.TransactionsRepository;
import com.starlabs.PaEase.utils.Utils;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author francis chulu
 */
@Service
public class APIService {
    
    private ArrayList response;
    private final APILogger log;
    private final ApiSecurityConfig config;
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
    @Autowired
    TransactionsRepository transactionsRepository;
    @Autowired
    ServicesRepository servicesRepository;
    @Autowired
    AsyncService asyncService;
    private final RestTemplate restTemplate;
    
    public APIService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
        this.utils = new Utils();
        this.config = new ApiSecurityConfig();
        this.log = new APILogger(getClass());
        this.resp = new Response();
        this.gson = new Gson();
        
    }
    
    public Response handleRequest(Request request) {
        this.initializeRequestParams(request);
        this.response = new ArrayList();
        //<editor-fold defaultstate="collapsed" desc="Method = verifyPin">
        if (request.getMethod().equals("verifyPin")) {
            if (ApiSecurityConfig.validateMsisdn(String.valueOf(request.getMsisdn()), this.log)) {
                this.resp = this.verifyPin(request);
            } else {
                this.resp.setData(response);
                this.resp.setStatus(Utils.status_invalid_msisdn);
                this.resp.setStatus_desc(Utils.status_invalid_msisdn_desc.replace("{msisdn}", String.valueOf(request.getMsisdn())));
            }
        } //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Method = changePin">
        if (request.getMethod().equals("changePin")) {
            if (ApiSecurityConfig.validateMsisdn(String.valueOf(request.getMsisdn()), this.log)) {
                this.resp = this.changePin(request);
            } else {
                this.resp.setData(response);
                this.resp.setStatus(Utils.status_invalid_msisdn);
                this.resp.setStatus_desc(Utils.status_invalid_msisdn_desc.replace("{msisdn}", String.valueOf(request.getMsisdn())));
            }
        } //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Method = customerProfile">
        if (request.getMethod().equals("customerProfile")) {
            if (ApiSecurityConfig.validateMsisdn(String.valueOf(request.getMsisdn()), this.log)) {
                this.resp = this.customerProfile(request);
            } else {
                this.resp.setData(response);
                this.resp.setStatus(Utils.status_invalid_msisdn);
                this.resp.setStatus_desc(Utils.status_invalid_msisdn_desc.replace("{msisdn}", String.valueOf(request.getMsisdn())));
            }
        } //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Method = transactions">
        if (request.getMethod().equals("transactions")) {
            if (ApiSecurityConfig.validateMsisdn(String.valueOf(request.getMsisdn()), this.log)) {
                this.resp = this.transactions(request);
            } else {
                this.resp.setData(response);
                this.resp.setStatus(Utils.status_invalid_msisdn);
                this.resp.setStatus_desc(Utils.status_invalid_msisdn_desc.replace("{msisdn}", String.valueOf(request.getMsisdn())));
            }
        } //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Method = statement">
        if (request.getMethod().equals("statement")) {
            if (ApiSecurityConfig.validateMsisdn(String.valueOf(request.getMsisdn()), this.log)) {
                this.resp = this.statement(request);
            } else {
                this.resp.setData(response);
                this.resp.setStatus(Utils.status_invalid_msisdn);
                this.resp.setStatus_desc(Utils.status_invalid_msisdn_desc.replace("{msisdn}", String.valueOf(request.getMsisdn())));
            }
        } //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Method = pinAttempts">
        if (request.getMethod().equals("pinAttempts")) {
            if (ApiSecurityConfig.validateMsisdn(String.valueOf(request.getMsisdn()), this.log)) {
                this.resp = this.pinAttempts(request);
            } else {
                this.resp.setData(response);
                this.resp.setStatus(Utils.status_invalid_msisdn);
                this.resp.setStatus_desc(Utils.status_invalid_msisdn_desc.replace("{msisdn}", String.valueOf(request.getMsisdn())));
            }
        } //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Method = blockProfile">
        if (request.getMethod().equals("blockProfile")) {
            if (ApiSecurityConfig.validateMsisdn(String.valueOf(request.getMsisdn()), this.log)) {
                this.resp = this.blockProfile(request);
            } else {
                this.resp.setData(response);
                this.resp.setStatus(Utils.status_invalid_msisdn);
                this.resp.setStatus_desc(Utils.status_invalid_msisdn_desc.replace("{msisdn}", String.valueOf(request.getMsisdn())));
            }
        } //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Method = postPayment">
        if (request.getMethod().equals("postPayment")) {
            if (ApiSecurityConfig.validateMsisdn(String.valueOf(request.getMsisdn()), this.log)) {
                this.resp = this.postPayment(request);
            } else {
                this.resp.setData(response);
                this.resp.setStatus(Utils.status_invalid_msisdn);
                this.resp.setStatus_desc(Utils.status_invalid_msisdn_desc.replace("{msisdn}", String.valueOf(request.getMsisdn())));
            }
        } //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Method = postPayment">
        if (request.getMethod().equals("postPaymentAcknowledgement")) {
            this.resp = this.postPaymentAcknowledgement(request);
            
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
        log.info("|" + this.getClass().getSimpleName() + "| " + Thread.currentThread().getStackTrace()[1].getMethodName() + "|" + request.getMsisdn() + "| Verifying customers pin to authenticate them");
        try {
            this.customerProfile = customerProfileRepository.findByMsisdn(request.getMsisdn());
            if (this.customerProfile != null && this.config.verifyPin(String.valueOf(request.getPin()), this.customerProfile.getPIN())) {
                log.info("|" + this.getClass().getSimpleName() + "| " + Thread.currentThread().getStackTrace()[1].getMethodName() + "|" + request.getMsisdn() + "| Supplied Pin is valid");
                this.resp.setData(response);
                this.resp.setStatus(Utils.status_ok);
                this.resp.setStatus_desc(Utils.status_valid_pin_desc);
            } else {
                log.info("|" + this.getClass().getSimpleName() + "| " + Thread.currentThread().getStackTrace()[1].getMethodName() + "|" + request.getMsisdn() + "| Supplied Pin is invalid");
                this.resp.setData(response);
                this.resp.setStatus(Utils.status_invalid_pin);
                this.resp.setStatus_desc(Utils.status_invalid_pin_desc);
            }
            
        } catch (Exception ex) {
            log.info("|" + this.getClass().getSimpleName() + "| " + Thread.currentThread().getStackTrace()[1].getMethodName() + "|" + request.getMsisdn() + "| Error occured while processing request. Error is : " + ex.getMessage());
        }
        log.info("|" + this.getClass().getSimpleName() + "| " + Thread.currentThread().getStackTrace()[1].getMethodName() + " | Response is : " + gson.toJson(this.resp));
        return this.resp;
    }
    
    public Response changePin(Request request) {
        log.info("|" + this.getClass().getSimpleName() + "| " + Thread.currentThread().getStackTrace()[1].getMethodName() + "|" + request.getMsisdn() + "| Chaning customers pin");
        try {
            this.customerProfile = customerProfileRepository.findByMsisdn(request.getMsisdn());
            if (this.customerProfile != null) {
                String hash = this.config.encodePin(String.valueOf(request.getPin()));
                if (!hash.isEmpty()) {
                    log.info("|" + this.getClass().getSimpleName() + "| " + Thread.currentThread().getStackTrace()[1].getMethodName() + "|" + request.getMsisdn() + "| Customer pin was successfully changed");
                    this.customerProfile.setPIN(hash);
                    customerProfileRepository.save(this.customerProfile);
                    this.resp.setData(response);
                    this.resp.setStatus(Utils.status_ok);
                    this.resp.setStatus_desc("Pin was successfully changed");
                }
            } else {
                log.info("|" + this.getClass().getSimpleName() + "| " + Thread.currentThread().getStackTrace()[1].getMethodName() + "|" + request.getMsisdn() + "| No customer record was found for this msisdn");
                this.resp.setData(response);
                this.resp.setStatus(Utils.status_no_record);
                this.resp.setStatus_desc(Utils.status_no_record_desc);
            }
            
        } catch (Exception ex) {
            log.info("|" + this.getClass().getSimpleName() + "| " + Thread.currentThread().getStackTrace()[1].getMethodName() + " | Error occured while processing request. Error is : " + ex.getMessage());
        }
        // log.info("|"+this.getClass().getSimpleName()+"| " + Thread.currentThread().getStackTrace()[1].getMethodName() + " | Response is : " + gson.toJson(this.resp));
        return this.resp;
    }
    
    public Response pinAttempts(Request request) {
        log.info("|" + this.getClass().getSimpleName() + "| " + Thread.currentThread().getStackTrace()[1].getMethodName() + "|" + request.getMsisdn() + "| Updating customers wrong pin attempts to: " + request.getExtra_data());
        try {
            this.customerProfile = customerProfileRepository.findByMsisdn(request.getMsisdn());
            if (this.customerProfile != null) {
                this.customerProfile.setWrong_pin_attempts(Integer.parseInt(request.getExtra_data()));
                customerProfileRepository.save(this.customerProfile);
                this.resp.setData(response);
                this.resp.setStatus(Utils.status_ok);
                this.resp.setStatus_desc(Utils.status_ok_desc);
                log.info("|" + this.getClass().getSimpleName() + "| " + Thread.currentThread().getStackTrace()[1].getMethodName() + "|" + request.getMsisdn() + "| Wrong pin attempts was successfully updated");
                
            } else {
                this.resp.setData(response);
                this.resp.setStatus(Utils.status_no_record);
                this.resp.setStatus_desc(Utils.status_no_record_desc);
                log.info("|" + this.getClass().getSimpleName() + "| " + Thread.currentThread().getStackTrace()[1].getMethodName() + "|" + request.getMsisdn() + "| Wrong pin attempt update was not successful");
            }
            
        } catch (Exception ex) {
            log.info("|" + this.getClass().getSimpleName() + "| " + Thread.currentThread().getStackTrace()[1].getMethodName() + " | Error occured while processing request. Error is : " + ex.getMessage());
        }
        log.info("|" + this.getClass().getSimpleName() + "| " + Thread.currentThread().getStackTrace()[1].getMethodName() + " | Response is : " + gson.toJson(this.resp));
        return this.resp;
    }
    
    public Response blockProfile(Request request) {
        log.info("|" + this.getClass().getSimpleName() + "| " + Thread.currentThread().getStackTrace()[1].getMethodName() + "|" + request.getMsisdn() + "| Block customer profile request received ");
        try {
            this.customerProfile = customerProfileRepository.findByMsisdn(request.getMsisdn());
            if (this.customerProfile != null) {
                this.customerProfile.setStatus(Utils.profile_blocked_status);
                customerProfileRepository.save(this.customerProfile);
                this.resp.setData(response);
                this.resp.setStatus(Utils.status_ok);
                this.resp.setStatus_desc(Utils.status_ok_desc);
                log.info("|" + this.getClass().getSimpleName() + "| " + Thread.currentThread().getStackTrace()[1].getMethodName() + "|" + request.getMsisdn() + "| Customer profile was successfully blocked");
                
            } else {
                this.resp.setData(response);
                this.resp.setStatus(Utils.status_no_record);
                this.resp.setStatus_desc(Utils.status_no_record_desc);
                log.info("|" + this.getClass().getSimpleName() + "| " + Thread.currentThread().getStackTrace()[1].getMethodName() + "|" + request.getMsisdn() + "| Customer record for this msisdn was not found");
            }
            
        } catch (Exception ex) {
            log.info("|" + this.getClass().getSimpleName() + "| " + Thread.currentThread().getStackTrace()[1].getMethodName() + " | Error occured while processing request. Error is : " + ex.getMessage());
        }
        log.info("|" + this.getClass().getSimpleName() + "| " + Thread.currentThread().getStackTrace()[1].getMethodName() + " | Response is : " + gson.toJson(this.resp));
        return this.resp;
    }
    
    public Response customerProfile(Request request) {
        log.info("|" + this.getClass().getSimpleName() + "| " + Thread.currentThread().getStackTrace()[1].getMethodName() + "|" + request.getMsisdn() + "| Getting customers profile details");
        try {
            this.customerProfile = customerProfileRepository.findByMsisdn(msisdn);
            if (this.customerProfile != null) {
                response.add(this.customerProfile);
                this.resp.setData(response);
                this.resp.setStatus(Utils.status_ok);
                this.resp.setStatus_desc("Request OK");
                log.info("|" + this.getClass().getSimpleName() + "| " + Thread.currentThread().getStackTrace()[1].getMethodName() + "|" + request.getMsisdn() + "| Customer details were found");
            } else {
                this.response = new ArrayList();
                log.info("|" + this.getClass().getSimpleName() + "| " + Thread.currentThread().getStackTrace()[1].getMethodName() + "|" + request.getMsisdn() + "| No customer record was found!");
                this.resp.setData(response);
                this.resp.setStatus(Utils.status_no_record);
                this.resp.setStatus_desc(Utils.status_no_record_desc);
            }
            log.info("|" + this.getClass().getSimpleName() + "| " + Thread.currentThread().getStackTrace()[1].getMethodName() + " | Response is : " + this.gson.toJson(this.resp));
        } catch (Exception ex) {
            log.info("|" + this.getClass().getSimpleName() + "| " + Thread.currentThread().getStackTrace()[1].getMethodName() + " | Error occured while processing request. Error is : " + ex.getMessage());
        }
        return this.resp;
    }
    
    public Response transactions(Request request) {
        log.info("|" + this.getClass().getSimpleName() + "| " + Thread.currentThread().getStackTrace()[1].getMethodName() + "|" + request.getMsisdn() + "| Getting customers transactions");
        try {
            List<Transactions> list = transactionsRepository.findByMsisdnAndStatusCode(request.getMsisdn(), Utils.status_ok);
            if (!list.isEmpty()) {
                this.response.add(list);
                this.resp.setData(this.response);
                this.resp.setStatus(Utils.status_ok);
                this.resp.setStatus_desc("Request OK");
                log.info("|" + this.getClass().getSimpleName() + "| " + Thread.currentThread().getStackTrace()[1].getMethodName() + "|" + request.getMsisdn() + "| Found " + list.size() + " Customer transactions");
            } else {
                this.response = new ArrayList();
                log.info("|" + this.getClass().getSimpleName() + "| " + Thread.currentThread().getStackTrace()[1].getMethodName() + "|" + request.getMsisdn() + "| No customer transactions were found!");
                this.resp.setData(this.response);
                this.resp.setStatus(Utils.status_no_record);
                this.resp.setStatus_desc(Utils.status_no_record_desc);
            }
            //  log.info("|"+this.getClass().getSimpleName()+"| " + Thread.currentThread().getStackTrace()[1].getMethodName() + " | Response is : " + this.gson.toJson(this.resp));
        } catch (Exception ex) {
            log.info("|" + this.getClass().getSimpleName() + "| " + Thread.currentThread().getStackTrace()[1].getMethodName() + " | Error occured while processing request. Error is : " + ex.getMessage());
        }
        return this.resp;
    }
    
    public Response statement(Request request) {
        log.info("|" + this.getClass().getSimpleName() + "| " + Thread.currentThread().getStackTrace()[1].getMethodName() + "|" + request.getMsisdn() + "| Getting customers service statements");
        try {
            List<Statement> list = transactionsRepository.statement(request.getMsisdn(), Utils.status_ok);
            if (!list.isEmpty()) {
                this.response.add(list);
                this.resp.setData(this.response);
                this.resp.setStatus(Utils.status_ok);
                this.resp.setStatus_desc(Utils.status_ok_desc);
                log.info("|" + this.getClass().getSimpleName() + "| " + Thread.currentThread().getStackTrace()[1].getMethodName() + "|" + request.getMsisdn() + "| Found " + list.size() + " Customer service records");
            } else {
                this.response = new ArrayList();
                log.info("|" + this.getClass().getSimpleName() + "| " + Thread.currentThread().getStackTrace()[1].getMethodName() + "|" + request.getMsisdn() + "| No customer service records were found!");
                this.resp.setData(this.response);
                this.resp.setStatus(Utils.status_no_record);
                this.resp.setStatus_desc(Utils.status_no_record_desc);
            }
            //log.info("|"+this.getClass().getSimpleName()+"| " + Thread.currentThread().getStackTrace()[1].getMethodName() + " | Response is : " + this.gson.toJson(this.resp));
        } catch (Exception ex) {
            log.info("|" + this.getClass().getSimpleName() + "| " + Thread.currentThread().getStackTrace()[1].getMethodName() + " | Error occured while processing request. Error is : " + ex.getMessage());
        }
        return this.resp;
    }
    
    public Response postPayment(Request request) {
        Optional<Services> services = servicesRepository.findById(Long.valueOf(request.getServiceID()));
        if (services != null) {
            log.info("|" + this.getClass().getSimpleName() + "| " + Thread.currentThread().getStackTrace()[1].getMethodName() + "|" + request.getMsisdn() + "| Processing transaction for service:" + services.get().getService_name() + "(" + request.getServiceID() + ")");
            Transactions transaction = Utils.requestToTransction(request);
            Transactions result = this.transactionsRepository.save(transaction);
            Long trx_id = result.getTransactionID();
            if (trx_id != null && trx_id > 0) {
                log.info("|" + this.getClass().getSimpleName() + "| " + Thread.currentThread().getStackTrace()[1].getMethodName() + "|" + request.getMsisdn() + "| Transaction was saved successfully. Transaction id is:" + trx_id);
                //We get the customer and deduct their balance
                log.info("|" + this.getClass().getSimpleName() + "| " + Thread.currentThread().getStackTrace()[1].getMethodName() + "|" + request.getMsisdn() + "| Getting customers profile details so that we reduce the balance");
                this.customerProfile = customerProfileRepository.findByMsisdn(request.getMsisdn());
                if (this.customerProfile != null) {
                    log.info("|" + this.getClass().getSimpleName() + "| " + Thread.currentThread().getStackTrace()[1].getMethodName() + "|" + request.getMsisdn() + "| Found customers profile details. Reducing the balance");
                    //We dont want to insert negative balances
                    Double bal = 0.0;
                    Double loan_amt = 0.0;
                    if (this.customerProfile.getBalance() >= request.getAmount()) {
                        //We update the loan before and loan after amounts
                        Transactions trx = transactionsRepository.findByTransactionID(trx_id);
                        trx.setUpdate_status(1);
                        trx.setLoan_bal_before(this.customerProfile.getBalance());
                        //Customer has enough balance
                        //Reduce the balance
                        bal = this.customerProfile.getBalance() - request.getAmount();
                        trx.setLoan_bal_after(bal);
                        //Increment the loan
                        loan_amt = this.customerProfile.getLoan_amount() + request.getAmount();
                        this.customerProfile.setBalance(bal);
                        this.customerProfile.setLoan_amount(loan_amt);
                        if (this.customerProfile.getMin_amount() >= bal) {
                            this.customerProfile.setStatus(Utils.profile_blockedLow_balance_status);
                        }
                        customerProfileRepository.save(this.customerProfile);
                        transactionsRepository.save(trx);

                        //Asychronosely invoke the microservice for the said service 
                        asyncService.process(bal, trx_id, request, this.customerProfile.getCustomer().getId_number(), this.customerProfile.getPercentage_charge(), log, gson);
                        this.response.add(trx_id);
                        this.resp.setData(this.response);
                        this.resp.setStatus(Utils.status_transaction_pending);
                        this.resp.setStatus_desc(Utils.status_transaction_pending_desc);
                    } else {
                        //Customer has low balance than the requesting amount
                        this.resp.setData(this.response);
                        this.resp.setStatus(Utils.status_low_balance);
                        this.resp.setStatus_desc(Utils.status_low_balance_desc);
                    }
                    
                }
                
            } else {
                log.info("|" + this.getClass().getSimpleName() + "| " + Thread.currentThread().getStackTrace()[1].getMethodName() + "|" + request.getMsisdn() + "| Transaction could not be saved!");
            }
            
        } else {
            log.info("|" + this.getClass().getSimpleName() + "| " + Thread.currentThread().getStackTrace()[1].getMethodName() + "|" + request.getMsisdn() + "| ServiceID " + request.getServiceID() + " does not exists");
            this.resp.setData(this.response);
            this.resp.setStatus(Utils.status_invalid_serviceid);
            this.resp.setStatus_desc(Utils.status_invalid_serviceid_desc.replace("{serviceid}", String.valueOf(request.getServiceID())));
        }
        log.info("|" + this.getClass().getSimpleName() + "| " + Thread.currentThread().getStackTrace()[1].getMethodName() + " | Response is : " + this.gson.toJson(this.resp));
        return this.resp;
    }
    
    public Response postPaymentAcknowledgement(Request request) {
        log.info("|" + this.getClass().getSimpleName() + "| " + Thread.currentThread().getStackTrace()[1].getMethodName() + "|" + request.getMsisdn() + "|" + request.getTransactionID() + "| Processing PostAck for transactionID:" + request.getTransactionID());
        //We check if transaction ID exist and has not been updated already
        Transactions transactions = transactionsRepository.findByStatuscodeAndTransactionIDAndMsisdnAndAccountAndServiceID(Utils.status_transaction_pending,Long.valueOf(request.getTransactionID()), request.getMsisdn(), request.getAccount(), Long.valueOf(request.getServiceID()));
        if (transactions == null) {
            //TransactionID does not exist
            log.info("|" + this.getClass().getSimpleName() + "| " + Thread.currentThread().getStackTrace()[1].getMethodName() + "|" + request.getMsisdn() + "|" + request.getTransactionID() + "| TransactionID " + request.getTransactionID() + " does not exist");
            this.resp.setData(this.response);
            this.resp.setStatus(Utils.status_invalid_transactionID);
            this.resp.setStatus_desc(Utils.status_invalid_transactionid_desc.replace("{trxid}", String.valueOf(request.getServiceID())));
            log.info("|" + this.getClass().getSimpleName() + "| " + Thread.currentThread().getStackTrace()[1].getMethodName() + " | Response is : " + this.gson.toJson(this.resp));
            return this.resp;
        }
        
        if (transactions.getUpdate_status() == 1) {
            //Transaction already updated
            log.info("|" + this.getClass().getSimpleName() + "| " + Thread.currentThread().getStackTrace()[1].getMethodName() + "|" + request.getMsisdn() + "|" + request.getTransactionID() + "| Transaction with TransactionID " + request.getTransactionID() + " has already been updated");
            this.resp.setData(this.response);
            this.resp.setStatus(Utils.status_transactionID_already_updated);
            this.resp.setStatus_desc(Utils.status_transactionID_already_updated_desc.replace("{trxid}", String.valueOf(request.getTransactionID())));
            log.info("|" + this.getClass().getSimpleName() + "| " + Thread.currentThread().getStackTrace()[1].getMethodName() + " | Response is : " + this.gson.toJson(this.resp));
            return this.resp;
        }
        
        this.customerProfile = customerProfileRepository.findByMsisdn(request.getMsisdn());
        if (this.customerProfile == null) {
            this.resp.setData(response);
            this.resp.setStatus(Utils.status_no_record);
            this.resp.setStatus_desc(Utils.status_no_record_desc);
            log.info("|" + this.getClass().getSimpleName() + "| " + Thread.currentThread().getStackTrace()[1].getMethodName() + "|" + request.getMsisdn() + "| Customer record for this msisdn was not found");
            log.info("|" + this.getClass().getSimpleName() + "| " + Thread.currentThread().getStackTrace()[1].getMethodName() + " | Response is : " + this.gson.toJson(this.resp));
            return this.resp;
        }

        //We update the transaction with the status 
        transactions.setUpdate_status(1);
        if (request.getStatus_code() == Utils.status_ok) {
            //Transaction succeeded
            log.info("|" + this.getClass().getSimpleName() + "| " + Thread.currentThread().getStackTrace()[1].getMethodName() + "|" + request.getMsisdn() + "| Transaction was successful. Status code is:" + request.getStatus_code()+". Status update was successful");
            transactions.setStatuscode(Utils.status_ok);
            transactions.setNarration("Transaction succeeded:" + request.getStatus_desc());
            transactions.setExternal_transactionID(request.getExternal_transactionID());
            transactions.setReceipt_number(request.getReceipt_number());
        } else {
            //Transaction failed, we roll back the transaction
            Double bal = 0.0;
            Double loan_amt = 0.0;
            transactions.setStatuscode(request.getStatus_code());
            transactions.setNarration("Transaction failed:" + request.getStatus_desc());
            //Roll back balance
            bal = this.customerProfile.getBalance() + request.getAmount();
            transactions.setLoan_bal_before(bal);
            transactions.setLoan_bal_after(0);
            //Increment the loan
            loan_amt = this.customerProfile.getLoan_amount() - request.getAmount();
            log.info("|" + this.getClass().getSimpleName() + "| " + Thread.currentThread().getStackTrace()[1].getMethodName() + "|" + request.getMsisdn() + "| Transaction failed. Status code is:" + request.getStatus_code()+". Status update was successful");
            log.info("|" + this.getClass().getSimpleName() + "| " + Thread.currentThread().getStackTrace()[1].getMethodName() + "|" + request.getMsisdn() + "| Customer balance and loan amount rolled back to. Balance:" + bal + ", loan amount:" + loan_amt);
            this.customerProfile.setBalance(bal);
            this.customerProfile.setLoan_amount(loan_amt);
            //Just in case we blocked the customer in postPayment
            this.customerProfile.setStatus(Utils.profile_active_status);
        }
        
        transactionsRepository.save(transactions);
        customerProfileRepository.save(this.customerProfile);
        this.resp.setData(response);
        this.resp.setStatus(Utils.status_ok);
        this.resp.setStatus_desc(Utils.status_ok_desc);
        log.info("|" + this.getClass().getSimpleName() + "| " + Thread.currentThread().getStackTrace()[1].getMethodName() + " | Response is : " + this.gson.toJson(this.resp));
        
        return this.resp;
    }
    
}
