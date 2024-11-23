package com.dolby.pastebin.service;

import com.dolby.pastebin.data.Bin;
import com.dolby.pastebin.error.Error;
import com.dolby.pastebin.exception.ServiceException;
import com.dolby.pastebin.storage.Storage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * PastebinService is responsible for handling pastebin operations , currently this service only
 * supports operations of creating bins and getting the data present with the bin.
 *
 * @author ravi shanker katta
 * @since 2021 Aug
 * @version 1.0
 */
@Service
public class PastebinService {

  private final Logger log = LoggerFactory.getLogger(this.getClass());

  @Autowired
  @Qualifier("inmemory")
  private Storage storage;

  /**
   * responsible for creating the bin.
   *
   * @param bin
   * @return
   * @throws ServiceException
   */
  public Bin createBin(Bin bin) throws ServiceException {

    if (bin == null) {
      log.error(" pastebin,   payload not present");
      throw new ServiceException(Error.INVALID_INPUT);
    }

    if (bin.getData() == null) {
      log.error(" pastebin configuration is missing, please check for valid data. ");
      throw new ServiceException(Error.REQUIRED_FIELD_MISSING);
    }

    // set expiry time
    Long durationInSeconds = bin.getDuration();
    Long expiryTime = null;
    if (durationInSeconds == null) {
      expiryTime = System.currentTimeMillis() + (10 * 1000);
      log.warn(
          " duration not present, going with default expiry time of 10 seconds, bucketName={}",
          bin.getName());
    } else {
      expiryTime = System.currentTimeMillis() + (durationInSeconds * 1000);
    }
    bin.setExpiry(expiryTime);

    // create bin
    Bin binCreated = null;
    try {
      binCreated = storage.create(bin);
    } catch (Exception e) {
      log.error(" exception creating bin , ex={}", e);
      throw new ServiceException(Error.INTERNAL_ERROR);
    }
    return binCreated;
  }

  /**
   * responsible for getting the value of the bin.
   *
   * @param binName
   * @return
   * @throws ServiceException
   */
  public String getBinValue(String binName) throws ServiceException {
    Bin bin = null;
    try {
      bin = storage.get(binName);
    } catch (Exception exception) {
      log.error(" exception getting  bin value , ex={}", exception);
      throw new ServiceException(Error.INTERNAL_ERROR);
    }
    if (bin == null) {
      log.error(" bin not present , name={}", binName);
      throw new ServiceException(Error.BIN_NOT_PRESENT);
    }

    Long currentTime = System.currentTimeMillis();
    Long expiryTime = bin.getExpiry();

    // not expired
    if (currentTime <= expiryTime) {
      return bin.getData();
    }

    // expired  ? delete the bin and communicate the same
    storage.delete(binName);
    log.warn(" bin expired  , name={}", binName);
    throw new ServiceException(Error.BIN_EXPIRED);
  }
}
