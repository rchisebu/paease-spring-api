package com.starlabs.PaEase.utils;

import com.starlabs.PaEase.model.Transactions;
import com.starlabs.PaEase.request.Request;
import java.util.ArrayList;

/**
 *
 * @author francis chulu
 */
public class Utils {
    
    public static final int status_transaction_pending = 99;
    public static final String status_transaction_pending_desc = "Transaction is pending processing";
    public static final int status_ok = 100;
    public static final String status_ok_desc = "Request OK";
    public static final int status_failed = 101;
    public static final String status_failed_desc = "Transaction failed";
    public static final int status_error = 500;
    public static final String status_error_desc = "Error occured while processing";
    public static final int status_missing_mandatory_parm = 102;
    public static final String status_missing_mandatory_parm_desc = "Mandatory parameter[{params}] is missing";
    public static final int status_invalid_pin = 103;
    public static final String status_invalid_pin_desc = "Invalid pin supplied!";
    public static final int status_invalid_method = 104;
    public static final String status_invalid_method_desc = "Requested method: {method} is not allowed!";
    public static final int status_invalid_msisdn = 105;
    public static final String status_invalid_msisdn_desc = "Invalid msisdn {msisdn} supplied!";
    public static final String status_valid_pin_desc = "Supplied pin is valid!";
    public static final int status_no_record = 106;
    public static final String status_no_record_desc = "No Customer Record found for the provided mobile number!";
    public static final int status_invalid_serviceid = 107;
    public static final String status_invalid_serviceid_desc = "ServiceID {serviceid} is invalid!";
    public static final int profile_active_status = 1;
    public static final int profile_inactive_status = 0;
    public static final int profile_blocked_status = 2;
    public static final int profile_blockedLow_balance_status = 3;
    public static final int profile_otp_status = 4;
    
    public static Transactions requestToTransction(Request request) {
        Transactions transactions = new Transactions();
        transactions.setServiceID(Long.valueOf(request.getServiceID()));
        transactions.setAmount(request.getAmount());
        transactions.setMsisdn(request.getMsisdn());
        transactions.setB_msisdn(request.getB_msisdn());
        transactions.setAccount(request.getAccount());
        transactions.setExternal_transactionID(request.getExternal_transactionID());
        transactions.setSource_application(request.getExtra_data());
        transactions.setReceipt_number(request.getReceipt_number());
        transactions.setStatus_code(status_transaction_pending);
        transactions.setNarration(status_transaction_pending_desc);
        
        return transactions;
    }

    /**
     * Converts Arraylist to comma separated string
     *
     * @param list
     * @return
     */
    public String ArrayListToString(ArrayList list) {
        String str = String.join(",", list);
        return str;
    }
}
