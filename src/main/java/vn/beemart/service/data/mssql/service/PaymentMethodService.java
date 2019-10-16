package vn.beemart.service.data.mssql.service;

import lombok.val;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.beemart.service.data.mssql.dao.PaymentMethodDao;
import vn.beemart.service.web.rest.model.payment.ListPaymentMethodsResponse;
import vn.beemart.service.web.rest.model.payment.PaymentMethodResponse;

import java.util.stream.Collectors;

@Service
public class PaymentMethodService {
    private final MapperFacade mapperFacade;

    private final PaymentMethodDao paymentMethodDao;

    public PaymentMethodService(MapperFacade mapperFacade, PaymentMethodDao paymentMethodDao) {
        this.mapperFacade = mapperFacade;
        this.paymentMethodDao = paymentMethodDao;
    }

    public ListPaymentMethodsResponse getAllPaymentMethodsResponse() throws Exception {
        val list = paymentMethodDao.getAll().stream().map(value -> mapperFacade.map(value, PaymentMethodResponse.class)).collect(Collectors.toList());
        return new ListPaymentMethodsResponse(list);
    }

    public PaymentMethodResponse getPaymentMethodResponse(int paymentMethodId) throws Exception {
        if(!paymentMethodDao.checkValidPaymentMethod(paymentMethodId)) {
            throw new Exception("Phương thức thanh toán không tồn tại");
        }
        return mapperFacade.map(paymentMethodDao.getById(paymentMethodId), PaymentMethodResponse.class);
    }
}
