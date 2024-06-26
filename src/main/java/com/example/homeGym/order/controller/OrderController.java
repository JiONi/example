package com.example.homeGym.order.controller;


import com.example.homeGym.order.dto.ProgramOrderDto;
import com.example.homeGym.order.service.OrderService;
import com.example.homeGym.toss.dto.PaymentCancelDto;
import com.example.homeGym.toss.entity.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService service;
  
    @GetMapping("/schedule")
    public String selectSchedulePage(){
        return "order/select-schedule";

    }

    @GetMapping
    public List<ProgramOrderDto> readAll() {
        return service.readAll();
    }

    @GetMapping("{id}")
    public ProgramOrderDto readOne(
            @PathVariable("id")
            Long id
    ) {
        return service.readOne(id);
    }
   


    @GetMapping("/payment")
    public ModelAndView readTossPayment() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("orderId", "abcddksdkf2203");
        mav.addObject("amount", "15000");
        mav.addObject("orderName", "김건강씨의 헬스 프로젝트");
        mav.addObject("userName", "이티피");
        mav.addObject("userEmail", "tp@naver.com");
        mav.addObject("userPhone", "01011111111");
        mav.setViewName("order/payment");

        return mav;
    }

    @GetMapping("/success")
    public String success() {
        return "order/success";
    }

    @GetMapping("/fail")
    public ModelAndView fail(@RequestParam(value = "message") final String message, @RequestParam(value = "code")final String code) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("message", message);
        mav.addObject("code", code);
        mav.setViewName("order/fail");
        return mav;
    }

    @PostMapping("{id}/cancel")
    public Object cancelPayment(
            @PathVariable("id")
            Long id,
            @RequestBody
            PaymentCancelDto dto
    ) {
        return service.cancelPayment(id, dto);
    }


}
