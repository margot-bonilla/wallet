package org.mbonilla.wallet.controller;

import org.mbonilla.wallet.model.Card;
import org.mbonilla.wallet.model.User;
import org.mbonilla.wallet.service.CardService;
import org.mbonilla.wallet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class DashboardController {

    @Autowired
    UserService userService;

    @Autowired
    CardService cardService;

    @GetMapping({"/", "/dashboard"})
    public ModelAndView card(@RequestParam(value = "searchTerm", required = false) Long cardNumber, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("dashboard");

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User owner = userService.findByUsername(((UserDetails) principal).getUsername());

        List<Card> cardList = request.isUserInRole("ROLE_ADMIN")
                ? cardService.findAllByNumber(cardNumber)
                : cardService.findByNumberAndUser(cardNumber, owner.getId());

        mav.addObject("searchTerm", cardNumber);
        mav.addObject("cardList", cardList);

        return mav;
    }
}
