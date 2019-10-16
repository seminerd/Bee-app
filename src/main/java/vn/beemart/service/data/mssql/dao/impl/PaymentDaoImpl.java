package vn.beemart.service.data.mssql.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.beemart.service.data.mssql.dao.PaymentDao;
import vn.beemart.service.data.mssql.repository.PaymentRepository;

@Service
public class PaymentDaoImpl implements PaymentDao {
    private final PaymentRepository paymentRepository;

    public PaymentDaoImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }
}
