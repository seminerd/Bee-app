package vn.beemart.service.data.mssql.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.beemart.service.data.mssql.dao.PaymentMethodDao;
import vn.beemart.service.data.mssql.dto.PaymentMethod;
import vn.beemart.service.data.mssql.repository.PaymentMethodRepository;

import java.util.List;

@Service
public class PaymentMethodDaoImpl implements PaymentMethodDao {
    private final PaymentMethodRepository paymentMethodRepository;

    public PaymentMethodDaoImpl(PaymentMethodRepository paymentMethodRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
    }

    @Override
    public boolean checkValidPaymentMethod(int paymentMethodId) {
        return paymentMethodRepository.existsById(paymentMethodId);
    }

    @Override
    public PaymentMethod getById(int paymentMethodId) {
        return paymentMethodRepository.getOne(paymentMethodId);
    }

    @Override
    public List<PaymentMethod> getAll() {
        return paymentMethodRepository.findAll();
    }
}
