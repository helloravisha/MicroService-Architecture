package com.dolby.pastebin.data;

import lombok.*;

/**
 * responsible for  holding the bin data.
 *
 * @author ravi shanker katta
 * @since 2021 Sep
 * @version 1.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Bin {
    String name;
    String data;
    Long duration;
    Long expiry;
}