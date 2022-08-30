package com.elec.mall.controller;

import com.elec.mall.pojo.Cart;
import com.elec.mall.pojo.User;
import com.elec.mall.pojo.vo.CartVO;
import com.elec.mall.service.ICartService;
import com.elec.mall.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private ICartService cartService;

    @RequestMapping("/add")
    @ResponseBody
    public JSONResult add(Cart cart, HttpSession session) {
        User user = (User) session.getAttribute("user");
        cart.setUserId(user.getId());
        cart.setChecked(1);
        cartService.add(cart);
        return JSONResult.ok("插入成功");
    }

    @RequestMapping("/getCartListPage")
    public String getCartListPage(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        List<CartVO> list = cartService.selectByUserId(user.getId());
        model.addAttribute("list", list);
        return "cart_list";
    }

    @RequestMapping("/updateChecked")
    @ResponseBody
    public JSONResult updateChecked(Integer id, Integer checked) {
        cartService.updateChecked(id, checked);
        return JSONResult.ok("更新成功");
    }
}