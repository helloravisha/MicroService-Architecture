package com.dolby.pastebin.storage;

import com.dolby.pastebin.data.Bin;
import com.dolby.pastebin.util.HashUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

/**
 * InMemoryStorage responsible for dealing all the pastebin operations in memmory.
 *
 * @author ravi shanker katta
 * @since 2021 Sep
 * @version 1.0
 */
@Component("inmemory")
public class InMemoryStorage implements Storage {

  private final Logger log = LoggerFactory.getLogger(this.getClass());
  private ConcurrentHashMap<String, Bin> binStorage = new ConcurrentHashMap<String, Bin>();

  public InMemoryStorage() {}

  @Override
  public Bin create(Bin bin) throws Exception {
    String binContent = bin.getData();
    String hashKey = HashUtil.getHashKey(binContent);
    if (binStorage.containsKey(hashKey)) {
      Bin currentBin = binStorage.get(hashKey);
      log.warn(" bin with the content already present ,  name={} ", currentBin.getName());
      return currentBin;
    }
    bin.setName(hashKey);
    binStorage.put(hashKey, bin);
    return bin;
  }

  @Override
  public Bin get(String binName) throws Exception {
    return binStorage.get(binName);
  }

  @Override
  public Bin delete(String binName) {
    Bin currentBin = binStorage.get(binName);
    if (currentBin == null) {
      log.warn(" bin with the name not present ,  name={} ", binName);
      return null;
    }
    return binStorage.remove(binName);
  }
}
