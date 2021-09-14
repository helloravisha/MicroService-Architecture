package com.dolby.pastebin.storage;

import com.dolby.pastebin.data.Bin;

public interface Storage {

    Bin create(Bin bin) throws Exception;

    Bin get(String binName) throws Exception;

    Bin delete(String binName);
}
