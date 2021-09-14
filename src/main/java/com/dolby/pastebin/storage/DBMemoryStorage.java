package com.dolby.pastebin.storage;

import com.dolby.pastebin.data.Bin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * DB storage  responsible for dealing all the  pastebin operations
 * in DB , current we dont support DB based storage. added this
 * class to demonsrate  extensibility of the application.
 *
 * @author ravi shanker katta
 * @since 2021 Sep
 * @version 1.0
 */
@Component("db")
public class DBMemoryStorage implements Storage {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public Bin create(Bin bin) {
        log.error(" not supported ");
        return null;
    }

    @Override
    public Bin get(String binName) {
        log.error(" not supported ");

        return null;
    }

    @Override
    public Bin delete(String binName) {
        log.error(" not supported ");
        return null;
    }
}

