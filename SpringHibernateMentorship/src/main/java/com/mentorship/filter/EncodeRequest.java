package com.mentorship.filter;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class EncodeRequest extends HttpServletRequestWrapper {

    private static final Logger LOG = Logger.getLogger(LoggingFilter.class);

    public EncodeRequest(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getRequestURI() {
        String url = super.getRequestURI();
        try {
            url = URLEncoder.encode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            LOG.error("Problem with decoding", e);
        }
        return url;
    }
}
