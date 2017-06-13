package com.example.demo.controller;

import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Hello Demo.
 *
 * @author gstripe@gmail.com
 * @date 2017-06-06 11:10
 */
@RestController
public class HelloController {
    @GetMapping("/say/{word}")
    public String sayWord(@PathVariable("word") String word) {
        return word;
    }

    @PostMapping("/say")
    public String say(@RequestParam("word") String word) {
        return word;
    }

}
