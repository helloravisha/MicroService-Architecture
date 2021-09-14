package com.dolby.pastebin.service;

import com.dolby.pastebin.data.Bin;
import com.dolby.pastebin.exception.ServiceException;
import com.dolby.pastebin.storage.Storage;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Demonstrates mocking capabilities and pastebin validation.
 *
 * <p>This test is added to validate roman number conversion and also demonstrate mocking
 * capability. today as per the given requirement , the layer is next to service is not having much
 * functionality , however in a traditional application , we may have a DAO class or a work flow
 * associated classes, in those cases mocking comes handy and helps in validating only the layer
 * under test and mocking all the other classes.
 *
 * @author ravi shanker katta
 * @since 2021 Sep *
 * @version 1.0
 */
@RunWith(MockitoJUnitRunner.class)
public class PastebinServiceMockTest {

  @InjectMocks
  private PastebinService pastebinService;

  @Mock
  private Storage storage;

  @Test
  public void createBinTest() throws Exception{

    String bucketName = "FC9DBE42BB08FEE3D521CB7DF88B0933";
    Bin bin = Bin.builder().name(bucketName).data("sample test").build();
    Mockito.when(storage.create(Mockito.any(Bin.class))).thenReturn(bin);
    Bin updatedBin =
        pastebinService.createBin(Bin.builder().name("test").data("sample test").build());
    Assert.assertEquals(bucketName, updatedBin.getName());
  }

  @Test (expected = ServiceException.class)
  public void createBinValidationTest() {
    pastebinService.createBin(Bin.builder().build());
  }
}
