package com.casa.agent_service.agent;

import net.bytebuddy.asm.Advice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

/**
 * This class provides custom implementations for the 'getForEntity' method and an advice for method entry.
 * It is used by the Agent class to redefine the 'getForEntity' method of the RestTemplate class in both 'record' and 'replay' modes.
 */
public class CustomRestTemplate {
    private static final String response = "abbreviation: IST\nclient_ip: 14.194.36.190\ndatetime: 2024-05-30T18:27:19.897566+05:30\nday_of_week: 4\nday_of_year: 151\ndst: false\ndst_from: \ndst_offset: 0\ndst_until: \nraw_offset: 19800\ntimezone: Asia/Kolkata\nunixtime: 1717073839\nutc_datetime: 2024-05-30T12:57:19.897566+00:00\nutc_offset: +05:30\nweek_number: 22";

    /**
     * This method is used to provide a static response when the 'getForEntity' method of the RestTemplate class is
     * called in 'REPLAY' mode.
     * It returns a ResponseEntity with a predefined response and a status of OK.
     *
     * @param <T> The type of the response.
     * @param url The URL to send the request to.
     * @param responseType The type of the response.
     * @param uriVariables The URI variables.
     * @return A ResponseEntity with a predefined response and a status of OK.
     * @throws RestClientException If an error occurs during the execution of the method.
     */
    public static <T> ResponseEntity<T> getForEntity(String url, Class<T> responseType, Object... uriVariables) throws RestClientException {
        return new ResponseEntity<>((T) response, HttpStatus.OK);
    }

    /**
     * This advice is executed when a method is entered.
     * It prints the URL to the console.
     *
     * @param url The URL to send the request to.
     * @param responseType The type of the response.
     * @param uriVariables The URI variables.
     */
    @Advice.OnMethodEnter
    public static void enter(@Advice.Argument(0) String url,
                             @Advice.Argument(1) Class<?> responseType,
                             @Advice.Argument(2) Object[] uriVariables) {
        System.out.println("URL is: "+ url);
    }
}
