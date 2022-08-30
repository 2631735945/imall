package com.elec.mall.controller;

import com.elec.mall.pojo.Order;
import com.elec.mall.pojo.Shipping;
import com.elec.mall.pojo.User;
import com.elec.mall.pojo.vo.CartVO;
import com.elec.mall.pojo.vo.OrderVO;
import com.elec.mall.service.ICartService;
import com.elec.mall.service.IOrderService;
import com.elec.mall.service.IShippingService;
import com.elec.mall.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private IShippingService shippingService;
    @Autowired
    private ICartService cartService;
    @Autowired
    private IOrderService orderService;

    @RequestMapping("/getConfirmOrderPage")
    public String getConfirmOrderPage(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        List<Shipping> shippingList = shippingService.selectByUserId(user.getId());
        model.addAttribute("shippingList", shippingList);

        List<CartVO> cartVOList = cartService.selectByUserIdAndChecked(user.getId());
        model.addAttribute("cartVOList", cartVOList);
        return "confirm_order";
    }

    @RequestMapping("/add")
    @ResponseBody
    public JSONResult add(Order order, HttpSession session) {
        User user = (User) session.getAttribute("user");
        order.setUserId(user.getId());
        orderService.add(order);
        return JSONResult.ok("生成订订单成功");
    }

    @RequestMapping("/getOrderListPage")
    public String getOrderListPage(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<OrderVO> list = orderService.selectByUserId(user.getId());
        model.addAttribute("list", list);
        return "order_list";
    }
}
