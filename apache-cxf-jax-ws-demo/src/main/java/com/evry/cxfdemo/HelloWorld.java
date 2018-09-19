package com.evry.cxfdemo;

import javax.jws.WebService;

@WebService
public interface HelloWorld {
    String sayHi(String text);
}

