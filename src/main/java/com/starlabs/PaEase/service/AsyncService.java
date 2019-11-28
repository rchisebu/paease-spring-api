package com.starlabs.PaEase.service;

import com.google.gson.Gson;
import com.starlabs.PaEase.logger.APILogger;
import com.starlabs.PaEase.model.CustomerProfile;
import com.starlabs.PaEase.model.User;
import com.starlabs.PaEase.request.Request;
import com.starlabs.PaEase.request.ServiceRequest;
import com.starlabs.PaEase.utils.Utils;
import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author francis chulu
 */
@Service
public class AsyncService {

    @Autowired
    private Environment env;
    private final RestTemplate restTemplate;
    private ServiceRequest serviceRequest;

    public AsyncService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Async("processExecutor")
    public void process(Double bal, Long trx_id, Request request, String id_number, String p_charge, APILogger log, Gson gson) {
        log.info("|" + this.getClass().getSimpleName() + "| " + Thread.currentThread().getStackTrace()[1].getMethodName() + "|" + request.getMsisdn() + "| Received request to process in async:" + request.getMsisdn());
        try {
            long start = System.currentTimeMillis();
            serviceRequest = new ServiceRequest();
            serviceRequest.setServiceID(request.getServiceID());
            serviceRequest.setAmount(request.getAmount());
            serviceRequest.setAccount(request.getAccount());
            serviceRequest.setTransactionID(String.valueOf(trx_id));
            serviceRequest.setNrc(id_number);
            serviceRequest.setBalance(bal);
            serviceRequest.setPercentageCharge(p_charge);
            serviceRequest.setMsisdn(request.getMsisdn());

            String url = Utils.getURL(String.valueOf(request.getServiceID()), env.getProperty("service_wrapper_url"));
            log.info("|" + this.getClass().getSimpleName() + "| " + Thread.currentThread().getStackTrace()[1].getMethodName() + "|" + request.getMsisdn() + "| Calling service wrapper on URL:  " + url);
            log.info("|" + this.getClass().getSimpleName() + "| " + Thread.currentThread().getStackTrace()[1].getMethodName() + "|" + request.getMsisdn() + "| Request being sent to service wrapper is:  " + gson.toJson(serviceRequest));
            ServiceResponse results = restTemplate.postForObject(url, gson.toJson(serviceRequest), ServiceResponse.class);
            log.info("|" + this.getClass().getSimpleName() + "| " +Thread.currentThread().getStackTrace()[1].getMethodName() + "|" + request.getMsisdn() + "| Response from service wrappers is: " + gson.toJson(results));
            log.info("|" + this.getClass().getSimpleName() + "| " + Thread.currentThread().getStackTrace()[1].getMethodName() + "|" + request.getMsisdn() + "| Procesing took : " + (System.currentTimeMillis() - start) + " seconds. Going to sleep now....");

        } catch (RestClientException ie) {
            log.error("|" + this.getClass().getSimpleName() + "| " + Thread.currentThread().getStackTrace()[1].getMethodName() + "|" + request.getMsisdn() + "| Error in request: {}" + ie.getMessage());
        }

    }

}
