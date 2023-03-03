package com.convenience.conveniencestorerecommendation.controller;

import com.convenience.conveniencestorerecommendation.domain.Direction;
import com.convenience.conveniencestorerecommendation.service.DirectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.util.UriComponentsBuilder;

import static com.convenience.conveniencestorerecommendation.constant.BaseURL.KAKAO_DIRECTION_URL;

@RequiredArgsConstructor
@Controller
public class ShortenURLController {
    private final DirectionService directionService;

    @GetMapping("/dir/{encodedId}")
    public String searchDirection(@PathVariable("encodedId") String encodedId) {
        String result = directionService.findDirectionURLById(encodedId);

        return "redirect:" + result;
    }
}
