package com.starlabs.PaEase.config;

/**
 *
 * @author Francis chulu
 */
import com.google.gson.Gson;
import com.starlabs.PaEase.logger.APILogger;
import com.starlabs.PaEase.request.Request;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SpringSecurityConfig {

    private final Map<String, ArrayList<String>> methodFieldsMap;
    private final ArrayList<String> methodList;

    public SpringSecurityConfig() {
        this.methodFieldsMap = new HashMap<>();
        this.methodList = new ArrayList<>();
        this.prepareMethodFieldsMap();
        this.prepareMethodList();
    }

    public String encodePin(String pin) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPin = passwordEncoder.encode(pin);
        return hashedPin;
    }

    public boolean verifyPin(String pin, String pinHash) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(pin, pinHash);
    }

    public static boolean validateMsisdn(String msisdn, APILogger log) {
        log.info("| " + Thread.currentThread().getStackTrace()[1].getMethodName() + " |" + msisdn + "|  Validiting msisdn ");
        boolean response = false;
        if (msisdn.length() == 12 && msisdn.matches("[0-9]+")) {
            log.info("| " + Thread.currentThread().getStackTrace()[1].getMethodName() + " |" + msisdn + "| msisdn is valid");
            response = true;
        } else {
            log.info("| " + Thread.currentThread().getStackTrace()[1].getMethodName() + " |" + msisdn + "| msisdn is invalid");
        }
        return response;
    }

    /**
     * Checks if all methods mandatory fields are set - for a called method.
     *
     * @param request
     * @param log
     * @param gson
     * @return boolean
     */
    public ArrayList validateMethodMandatoryFields(Request request, APILogger log, Gson gson) {
        boolean response = true;
        int counter = 0;
        String methodName = request.getMethod();
        ArrayList<String> fields = new ArrayList<>();
        ArrayList<String> methodFieldList = this.methodFieldsMap.get(methodName);
        log.info("| " + Thread.currentThread().getStackTrace()[1].getMethodName() + " |" + request.getMsisdn() + "| Method Field list is " + gson.toJson(methodFieldList));

        if (methodFieldList != null) {
            while (response == true && counter < methodFieldList.size()) {
                String filedName = methodFieldList.get(counter);
                counter++;
                switch (filedName) {
                    case "msisdn":
                        if (request.getMsisdn() == null || request.getMsisdn().equals("")) {
                            fields.add("msisdn");
                        }
                        break;
                    case "b_msisdn":
                        if (request.getB_msisdn() == null || request.getB_msisdn().equals("")) {
                            fields.add("b_msisdn");
                        }
                        break;
                    case "pin":
                        if (request.getPin() < 0) {
                            fields.add("pin");
                        }
                        break;
                    case "extra_data":
                        if (request.getExtra_data() == null || request.getExtra_data().equals("")) {
                            fields.add("extra_data");
                        }
                        break;
                    case "serviceID":
                        if (request.getServiceID() < 0) {
                            fields.add("serviceID");
                        }
                        break;
                    case "amount":
                        if (request.getAmount() < 0) {
                            fields.add("amount");
                        }
                        break;
                    case "account":
                        if (request.getAccount() == null || request.getAccount().equals("")) {
                            fields.add("account");
                        }
                        break;
                    case "transactionID":
                        if (request.getTransactionID() == null || request.getTransactionID().equals("")) {
                            fields.add("transactionID");
                        }
                        break;
                    case "external_transactionID":
                        if (request.getExternal_transactionID() == null || request.getExternal_transactionID().equals("")) {
                            fields.add("external_transactionID");
                        }
                        break;
                    case "receipt_number":
                        if (request.getReceipt_number() == null || request.getReceipt_number().equals("")) {
                            fields.add("receipt_number");
                        }
                        break;
                    case "hash":
                        if (request.getHash() == null || request.getHash().equals("")) {
                            fields.add("hash");
                        }
                        break;
                    case "status_desc":
                        if (request.getStatus_desc() == null || request.getStatus_desc().equals("")) {
                            fields.add("status_desc");
                        }
                        break;
                    case "status_code":
                        if (request.getStatus_code() < 0) {
                            fields.add("status_code");
                        }
                        break;

                }
            }
            log.info("| " + Thread.currentThread().getStackTrace()[1].getMethodName() + " |" + request.getMsisdn() + "| Missing fields are{ " + gson.toJson(fields) + "}");
        }

        return fields;
    }

    private void prepareMethodList() {
        this.methodList.add("verifyPin");
        this.methodList.add("customerProfile");
        this.methodList.add("changePin");
        this.methodList.add("pinAttempts");
        this.methodList.add("blockProfile");
    }

    /**
     * Returns a Map of method as a key plus a map of fields
     */
    private void prepareMethodFieldsMap() {
        this.methodFieldsMap.put("verifyPin", this.prepareMethodFields(new String[]{"method", "pin", "msisdn"}));
        this.methodFieldsMap.put("changePin", this.prepareMethodFields(new String[]{"method", "pin", "msisdn"}));
        this.methodFieldsMap.put("customerProfile", this.prepareMethodFields(new String[]{"method", "msisdn"}));
        this.methodFieldsMap.put("blockProfile", this.prepareMethodFields(new String[]{"method", "msisdn"}));
        this.methodFieldsMap.put("pinAttempts", this.prepareMethodFields(new String[]{"method", "msisdn", "extra_data"}));
    }

    /**
     * Adds received methods mandatory fields to an ArrayList
     *
     * @param fields
     * @return methodFields
     */
    private ArrayList<String> prepareMethodFields(String[] fields) {
        ArrayList<String> methodFields = new ArrayList<>();
        methodFields.addAll(Arrays.asList(fields));
        return methodFields;
    }

    /**
     * Checks if received method exists
     *
     * @param method
     * @return boolean
     */
    public boolean checkMethod(String method) {
        boolean response = false;
        if (this.methodList.contains(method)) {
            response = true;
        }
        return response;
    }
}
