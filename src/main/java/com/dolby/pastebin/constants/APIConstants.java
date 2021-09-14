package com.dolby.pastebin.constants;

/**
 * used to define API constants.
 *
 * @author ravi shanker katta
 * @since 2021 Sep
 * @version 1.0
 */
public interface APIConstants {
  String PASTEBIN_CONTROLLER_SERVICE_BASE_URI = "api/v1/bin";
  String CONVERT = "/convert";
  String ERROR = "/error";

  //Error Messages
  String VALID_INPUT_BASIC_DESCRIPTION = "Please provide valid input";
  String BIN_ALREADY_PRESENT = "Please provide unique bin name, bin already present";
  String BIN_NOT_PRESENT = " bin not present, please provide valid bin name";
  String INVALID_INPUT = "Please provide valid data";
  String REQUIRED_FIELD_MISSING = " mandatory parameters missing , please check the input ";
  String METHOD_NOT_ALLOWED = "Please use only HTTP GET request as request method";
  String INTERNAL_ERROR = " internal server error , please contact support ";
  String BIN_EXPIRED = " bin expired, try again. ";


}
