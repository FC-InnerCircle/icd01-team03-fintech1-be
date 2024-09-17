package incerpay.paygate.presentation.dto.in;

import incerpay.paygate.domain.enumeration.PaymentType;
import incerpay.paygate.presentation.dto.PaymentMethodDetails;

import java.util.UUID;

public record PaymentApproveCommand(
        String customerId,
        UUID paymentId,
        UUID transactionId,
        PaymentType type,
        PaymentApproveDetails paymentApproveDetails,
        PaymentMethodDetails paymentMethodDetails
) {}


