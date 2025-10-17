package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/payment-info")
@CrossOrigin(origins = "*")
public class PaymentInfoController {

    @Value("${payments.bank.accountName:PrintIt101}")
    private String accountName;

    @Value("${payments.bank.accountNumber:000000000}")
    private String accountNumber;

    @Value("${payments.bank.branchCode:000000}")
    private String branchCode;

    @Value("${payments.crypto.walletAddress:replace_with_wallet}")
    private String cryptoAddress;

    @GetMapping
    public ResponseEntity<Map<String, String>> getPaymentInfo() {
        // do not return secrets like private keys — this is just public banking instructions
        return ResponseEntity.ok(Map.of(
                "accountName", accountName,
                "accountNumber", accountNumber,
                "branchCode", branchCode,
                "cryptoAddress", cryptoAddress
        ));
    }
}
