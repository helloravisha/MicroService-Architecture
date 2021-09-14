package com.dolby.pastebin.controller;

import com.dolby.pastebin.constants.APIConstants;
import com.dolby.pastebin.data.Bin;
import com.dolby.pastebin.data.PastebinPayload;
import com.dolby.pastebin.service.PastebinService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
/**
 * pastebin controller responsible for executing pastebin operation
 *
 * <p>As per the given requirement , we currently support only creating a bin and getting a value
 * out of the bin.
 *
 * @author ravi shanker katta
 * @since 2021 Sep
 * @version 1.0
 */
@RestController
@RequestMapping(value = APIConstants.PASTEBIN_CONTROLLER_SERVICE_BASE_URI)
public class PastebinController {

  private final Logger log = LoggerFactory.getLogger(this.getClass());

  @Autowired private PastebinService pastebinService;

  @RequestMapping(value = "/", method = RequestMethod.POST)
  public ResponseEntity<String> create(@RequestBody PastebinPayload pastebinPayload) {
    Bin bin =
        Bin.builder()
            .data(pastebinPayload.getData())
            .duration(pastebinPayload.getDuration())
            .build();
    Bin binCreated = pastebinService.createBin(bin);
    log.info(" bin created data  , name ={}", binCreated.getName());
    return ResponseEntity.status(HttpStatus.OK).body(binCreated.getName());
  }

  @RequestMapping(value = "/{binName}", method = RequestMethod.GET)
  public ResponseEntity<String> get(@PathVariable("binName") String binName) {
    String value = pastebinService.getBinValue(binName);
    return ResponseEntity.status(HttpStatus.OK).body(value);
  }
}
