package com.rabo.csp.utility;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpHeaders;

/**
 * HttpUtility : Utility class to provide HTTP based capability
 * @author Parthasarathi Sahoo (partha.sahoo1994@gmail.com)
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HttpUtility {

    /**
     * getHttpHeadersForContentDisposition : THe method prepare the HTTP Headers for the CONTENT_DISPOSITION type response
     * @param fileName String file Name which will be added to the CONTENT_DISPOSITION http header
     * @return HttpHeaders
     */
    public static HttpHeaders getHttpHeadersForContentDisposition(String fileName) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
        headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + fileName + ".csv\"");
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        return headers;
    }
}
