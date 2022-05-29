package pl.training.shop.payments.adapters.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.training.shop.payments.ports.PaymentService;

@RequestMapping("payments")
@Controller
@RequiredArgsConstructor
public class PaymentWebController {

    private final PaymentService paymentService;
    private final WebPaymentMapper mapper;

    @GetMapping("pay")
    public String showPaymentForm() {
        return "payments/payment-form";
    }

}
