package com.dolby.pastebin.data;

import lombok.*;


/**
 * request payload for creating the bin.
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
public class PastebinPayload {
    String data;
    Long duration;
}
