package project.laundry.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Controller
@RequestMapping("/stock-quantity")
public class StockQuantityController {

    @GetMapping
    public String GET_MainPage() {

        return "stock_quantity/main";
    }
}