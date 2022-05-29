package pl.training.shop.payments.adapters.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.training.shop.commons.web.ExceptionDto;
import pl.training.shop.payments.domain.PaymentNotFoundException;
import pl.training.shop.payments.ports.PaymentService;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static pl.training.shop.payments.domain.PaymentStatus.CONFIRMED;

@RequestMapping("payments")
@Controller
@RequiredArgsConstructor
public class PaymentWebController {

    private static final String PAYMENT_REQUEST_MODEL = "paymentRequest";
    private final PaymentService paymentService;
    private final WebPaymentMapper mapper;

    /*@GetMapping("pay")
    public ModelAndView showPaymentForm() {
        var modelAndView = new ModelAndView("payments/payment-form");
        var paymentRequestViewModel = new PaymentRequestViewModel();
        paymentRequestViewModel.setValue("200 PLN");
        modelAndView.addObject(PAYMENT_REQUEST_MODEL, paymentRequestViewModel);
        return modelAndView;
    }*/

    @GetMapping("pay")
    public String showPaymentForm(Model model) {
        var paymentRequestViewModel = new PaymentRequestViewModel();
        paymentRequestViewModel.setValue("200 PLN");
        model.addAttribute(PAYMENT_REQUEST_MODEL, paymentRequestViewModel);
        return "payments/payment-form";
    }

    @PostMapping("pay")
    public String process(@Valid @ModelAttribute(PAYMENT_REQUEST_MODEL) PaymentRequestViewModel viewModel,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "payments/payment-form";
        }
        var paymentRequest = mapper.toDomain(viewModel);
        var payment = paymentService.process(paymentRequest);

        var paymentViewModel = mapper.toViewModel(payment);
        redirectAttributes.addFlashAttribute("payment", paymentViewModel);
        return "redirect:/payments/payment-summary";
    }

    /*@GetMapping("payment-summary")
    public String showSummary() {
        return "payments/payment-summary";
    }*/

    @GetMapping
    public String showPayments(
            Model model,
            @RequestParam(required = false, defaultValue = "0") int pageNumber,
            @RequestParam(required = false, defaultValue = "5") int pageSize) {
        var page = mapper.toDomain(pageNumber, pageSize);
        var payments = paymentService.getByStatus(CONFIRMED, page);
        model.addAttribute("paymentsPage",mapper.toViewModel(payments));
        //throw new RuntimeException();
        return "payments/payments";
    }

    /*@ExceptionHandler(RuntimeException.class)
    public String onException(RuntimeException exception) {
        return  "redirect:/index.html";
    }*/

}
