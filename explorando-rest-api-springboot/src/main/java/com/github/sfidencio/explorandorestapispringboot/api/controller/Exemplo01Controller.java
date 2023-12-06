package com.github.sfidencio.explorandorestapispringboot.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Exemplo01Controller {
    /*
     * O método exemplo01() é mapeado para a URL /exemplo01 e par a URL /exemplo01-1
     * curl  --location http://localhost:8080/exemplo01 -X GET --header 'Content-Type: application/json'
     * curl  --location http://localhost:8080/exemplo01-1 -X GET --header 'Content-Type: application/json'
     */
    @RequestMapping(value = {"/exemplo01", "/exemplo01-1"}, method = RequestMethod.GET)
    @ResponseBody
    public String exemplo01() {
        return "Hello World!";
    }
}
