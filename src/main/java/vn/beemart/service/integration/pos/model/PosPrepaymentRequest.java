package vn.beemart.service.integration.pos.model;

import java.math.BigDecimal;

public class PosPrepaymentRequest {
    private int accountId;
    private BigDecimal amount;
    private String note;
    private BigDecimal paidAmount;
    private String paidOn;
    private int paymentMethodId;
    private String reference;
    private BigDecimal returnedAmount;
}
