package org.example.dailyquiz2.Controller;

import org.example.dailyquiz2.Domain.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private List<Product> products = new ArrayList<>();
    private Long nextId = 1L;

    @PostMapping("/save")
    public String savePost(@ModelAttribute Product product) {
        product.setId(nextId++);
        product.setName("Test Product" + product.getId());
        product.setPrice(10000 * nextId.intValue());
        products.add(product);
        return "redirect:/products/list";
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("products", products);
        return "list";
    }
}
