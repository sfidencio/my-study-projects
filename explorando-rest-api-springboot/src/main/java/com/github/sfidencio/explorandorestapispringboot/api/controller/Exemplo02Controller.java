package com.github.sfidencio.explorandorestapispringboot.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Exemplo02Controller {
    /*
     * O método exemplo02() é mapeado para a URL /exemplo02
     * curl  --location http://localhost:8080/exemplo02 -X GET --header 'Content-Type: application/json'
     */
    @RequestMapping(value = "/exemplo02", method = RequestMethod.GET)
    @ResponseBody
    public String exemplo02() {
        return "Hello World!";
    }
}
