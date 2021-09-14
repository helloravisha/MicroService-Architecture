package com.dolby.pastebin.error;

import com.dolby.pastebin.constants.APIConstants;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

/**
 * Error constants used with in the application.
 *
 * @author ravi shanker katta
 * @since 2021 Sep
 * @version 1.0
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Error {
  INVALID_INPUT_BASIC(
      "100",
      "Bad Request",
      APIConstants.VALID_INPUT_BASIC_DESCRIPTION,
      HttpStatus.BAD_REQUEST.value()),
  BIN_ALREADY_PRESENT(
      "101", "Bad Request", APIConstants.BIN_ALREADY_PRESENT, HttpStatus.BAD_REQUEST.value()),
  INVALID_INPUT("102", "Bad Request", APIConstants.INVALID_INPUT, HttpStatus.BAD_REQUEST.value()),
  REQUIRED_FIELD_MISSING(
      "103", "Bad Request", APIConstants.REQUIRED_FIELD_MISSING, HttpStatus.BAD_REQUEST.value()),
  METHOD_NOT_ALLOWED(
      "104",
      "Unsupported Method",
      APIConstants.METHOD_NOT_ALLOWED,
      HttpStatus.METHOD_NOT_ALLOWED.value()),
  BIN_NOT_PRESENT(
      "105", "Bad Request", APIConstants.BIN_NOT_PRESENT, HttpStatus.BAD_REQUEST.value()),

  INTERNAL_ERROR(
      "106", "Bad Request", APIConstants.BIN_NOT_PRESENT, HttpStatus.BAD_REQUEST.value()),

  BIN_EXPIRED(
      "106", "Bad Request", APIConstants.BIN_NOT_PRESENT, HttpStatus.BAD_REQUEST.value());

  private String errorCode;
  private String errorMessage;
  private String errorDescription;
  private int httpResponseCode;

  Error(String errorCode, String errorMessage, String errorDescription, int httpResponseCode) {
    this.errorCode = errorCode;
    this.errorMessage = errorMessage;
    this.errorDescription = errorDescription;
    this.httpResponseCode = httpResponseCode;
  }

  public int getHttpResponseCode() {
    return httpResponseCode;
  }

  public void setHttpResponseCode(int httpResponseCode) {
    this.httpResponseCode = httpResponseCode;
  }

  public String getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(String errorCode) {
    this.errorCode = errorCode;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public String getErrorDescription() {
    return errorDescription;
  }

  public void setErrorDescription(String errorDescription) {
    this.errorDescription = errorDescription;
  }
}
