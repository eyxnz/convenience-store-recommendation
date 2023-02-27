package com.convenience.conveniencestorerecommendation.controller;

import com.convenience.conveniencestorerecommendation.dto.InputDto;
import com.convenience.conveniencestorerecommendation.service.ConvenienceStoreRecommendationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RequiredArgsConstructor
@Controller
public class MainController {
    private final ConvenienceStoreRecommendationService convenienceStoreRecommendationService;

    @GetMapping("/")
    public String main() {
        return "main";
    }

    @PostMapping("/search")
    public ModelAndView postDirection(@ModelAttribute InputDto inputDto) {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("output");
        modelAndView.addObject(
                "outputFormList",
                convenienceStoreRecommendationService.recommendConvenienceStoreList(inputDto.getAddress())
        );

        return modelAndView;
    }
}
