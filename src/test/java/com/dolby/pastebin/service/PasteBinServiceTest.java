package com.dolby.pastebin.service;

import com.dolby.pastebin.data.Bin;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PasteBinServiceTest {

    @Autowired
    private PastebinService pastebinService;

    @Test
    public void createBinTest() {
        String bucketName = "FC9DBE42BB08FEE3D521CB7DF88B0933";
        Bin updatedBin =
                pastebinService.createBin(Bin.builder().data("sample test").build());
        Assert.assertEquals(bucketName, updatedBin.getName());
    }
}
