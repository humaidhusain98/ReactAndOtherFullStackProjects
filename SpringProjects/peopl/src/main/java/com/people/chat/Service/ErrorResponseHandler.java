package com.people.chat.Service;

import com.amazonaws.SdkBaseException;
import com.amazonaws.http.HttpResponse;
import com.amazonaws.http.HttpResponseHandler;

public class ErrorResponseHandler implements HttpResponseHandler<SdkBaseException> {

    @Override
    public SdkBaseException handle(final HttpResponse response) throws Exception {
        // TODO
        return null;
    }

    @Override
    public boolean needsConnectionLeftOpen() {
        return false;
    }
}