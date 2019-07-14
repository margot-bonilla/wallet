package org.mbonilla.wallet.controller;

import org.mbonilla.wallet.model.Card;
import org.mbonilla.wallet.model.User;
import org.mbonilla.wallet.service.CardService;
import org.mbonilla.wallet.service.UserService;
import org.mbonilla.wallet.validator.CardValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CardController {

    private static final Logger logger = LoggerFactory.getLogger(CardController.class);
    @Autowired
    UserService userService;
    @Autowired
    CardService cardService;
    @Autowired
    CardValidator cardValidator;

    @GetMapping("/card")
    public String addCard(Model model) {
        model.addAttribute("cardForm", new Card());

        return "card";
    }

    @PostMapping(value = "/card")
    public String addCard(@ModelAttribute("cardForm") Card cardForm, BindingResult bindingResult) {
        cardValidator.validate(cardForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "card";
        }
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User owner = userService.findByUsername(((UserDetails) principal).getUsername());
        cardForm.setOwner(owner);

        cardService.save(cardForm);

        return "redirect:/dashboard";
    }

    @PostMapping(value = "/card/{id}")
    public String deleteCard(@PathVariable Long id) {
        cardService.delete(id);

        return "redirect:/dashboard";
    }
}
