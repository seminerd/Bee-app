package vn.beemart.service.data.mssql.dao;

import vn.beemart.service.data.mssql.dto.PaymentMethod;

import java.util.List;

public interface PaymentMethodDao {
    boolean checkValidPaymentMethod(int paymentMethodId);

    PaymentMethod getById(int paymentMethodId);

    List<PaymentMethod> getAll();
}
