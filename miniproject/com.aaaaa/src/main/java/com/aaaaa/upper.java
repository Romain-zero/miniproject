package com.aaaaa;


import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
@WebService(targetNamespace="http://www.com.aaaaa")
public class upper {

    @WebMethod
    public String convertToUpperCase(@WebParam(name="inputString") String input) {
        if (input == null) {
            return "Input is null";
        }
        return input.toUpperCase();
    }
}