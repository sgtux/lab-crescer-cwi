package br.com.cwi.shop.controllers;

import br.com.cwi.shop.helpers.StringHelper;
import org.owasp.html.HtmlPolicyBuilder;
import org.owasp.html.PolicyFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ViewController {

    @GetMapping(value = "/hello", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> hello(@RequestParam(required = false) String nome) {

        StringBuilder sb = new StringBuilder();
        sb.append("<html><body style='font-size:30px'><div id='div'>");

        if(StringHelper.isNullOrEmpty(nome))
            sb.append("Informe o parâmetro 'nome' na url.");
        else {

            PolicyFactory policy = new HtmlPolicyBuilder()
                    .allowElements("b")
                    .toFactory();

            sb.append("Olá ").append(policy.sanitize(nome));
        }

        sb.append("</div></body></html>");

        return ResponseEntity.ok(sb.toString());
    }
}
