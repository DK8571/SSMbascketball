package com.baoshi.programmer.controller.admin;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.baoshi.programmer.config.admin.AlipayConfig;
import com.baoshi.programmer.page.admin.Page;
import com.baoshi.programmer.service.admin.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin/recharge")
@ResponseBody
public class RechargeController {
    @Autowired
    private MemberService memberService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ModelAndView list(ModelAndView mv){
        mv.setViewName("recharge/list");
        return mv;
    }

    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public Map<String,Object> list(Page page,
                                   HttpServletRequest request
                                   ){
        Map<String,Object> ret = new HashMap<>();
        Map<String, Object> queryMap = new HashMap<String, Object>();
        Long userid = (Long) request.getSession().getAttribute("adminid");
        queryMap.put("offset", page.getOffset());
        queryMap.put("pageSize", page.getRows());
        queryMap.put("userid",userid);
        ret.put("rows", memberService.findList(queryMap));
        ret.put("total", memberService.getTotal(queryMap));
        return ret;
    }
    /**
     * 代码从alipay.trade.page.pay.jsp考过来即可，用于显示支付宝官方支付页面
     *
     * @param request
     * @param response
     * @throws Exception
     */
    //充值接入支付宝沙箱
    @RequestMapping("/showPay")
    public void showPay(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.getSession().setAttribute("t",0);
        response.setContentType("text/html;charset=UTF-8");
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id,
                                                            AlipayConfig.merchant_private_key, "json",
                                                            AlipayConfig.charset,
                                                            AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = new String(request.getParameter("WIDout_trade_no")
                                                .getBytes("ISO-8859-1"), "UTF-8");
        //付款金额，必填
        String total_amount = new String(request.getParameter("WIDtotal_amount")
                                                .getBytes("ISO-8859-1"), "UTF-8");
        //订单名称，必填
        String subject = new String(request.getParameter("WIDsubject")
                                            .getBytes("ISO-8859-1"), "UTF-8");
        //商品描述，可空
        String body = new String(request.getParameter("WIDbody")
                                        .getBytes("ISO-8859-1"), "UTF-8");
        alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\","
                + "\"total_amount\":\"" + total_amount + "\","
                + "\"subject\":\"" + subject + "\","
                + "\"body\":\"" + body + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节
        //构建支付宝官方支付页面
        String top = "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n" +
                "<html>\n" +
                "<head>\n" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n" +
                "<title>付款</title>\n" +
                "</head>";
        String result = alipayClient.pageExecute(alipayRequest).getBody();
        String bottom = "<body>\n" +
                "</body>\n" +
                "</html>";
        //输出
        response.getWriter().println(top + result + bottom);
    }
}
