package com.starlabs.PaEase.utils;

import java.util.ArrayList;

/**
 *
 * @author chulu
 */
public class Utils {

    public static final int status_ok = 100;
    public static final String status_ok_desc = "Request OK";
    public static final int status_error = 500;
    public static final String status_error_desc = "Error occured while processing";
    public static final int status_missing_mandatory_parm = 101;
    public static final String status_missing_mandatory_parm_desc = "Mandatory parameter[{params}] is missing";
    public static final int status_invalid_pin = 102;
    public static final String status_invalid_pin_desc = "Invalid pin supplied!";
    public static final int status_invalid_method = 103;
    public static final String status_invalid_method_desc = "Requested method: {method} is not allowed!";
    public static final int status_invalid_msisdn = 104;
    public static final String status_invalid_msisdn_desc = "Invalid msisdn {msisdn} supplied!";
    public static final String status_valid_pin_desc = "Supplied pin is valid!";
    public static final int status_no_record = 105;
    public static final String status_no_record_desc = "No Customer Record found for the provided mobile number!";
    public static final int profile_active_status = 1;
    public static final int profile_inactive_status = 0;
    public static final int profile_blocked_status = 2;
    public static final int profile_blockedLow_balance_status = 3;
    public static final int profile_otp_status = 4;

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
