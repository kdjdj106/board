package com.example.board.controller;

import com.example.board.model.PrincipalUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(Model model, @AuthenticationPrincipal PrincipalUser principalUser) {
        System.out.println("///");
        String view = "index";

        if (principalUser != null) {

            String userName = principalUser.providerUser().getUsername();
            if (userName == null) userName = principalUser.providerUser().getUniqueId();
            model.addAttribute("user", userName);
            model.addAttribute("provider", principalUser.providerUser().getProvider());
            if(!principalUser.providerUser().isCertificated()) view = "selfcert";

        }
        System.out.println(principalUser.providerUser().getUniqueId());
        System.out.println(principalUser.providerUser().getEmail() + " " + principalUser.providerUser().getProvider());

        return view;
    }
}