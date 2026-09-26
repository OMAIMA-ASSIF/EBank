package net.omaima.bankaccountservice.web;

import net.omaima.bankaccountservice.dto.BankAccountRequestDTO;
import net.omaima.bankaccountservice.dto.BankAccountResponseDTO;
import net.omaima.bankaccountservice.mappers.AccountMapper;
import net.omaima.bankaccountservice.repositories.BankAccountRepository;
import net.omaima.bankaccountservice.service.BankAccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class AccountRestController {

    private final BankAccountRepository bankAccountRepository;
    private final BankAccountService accountService;
    private final AccountMapper accountMapper;

    public AccountRestController(BankAccountRepository bankAccountRepository,
                                 BankAccountService accountService,
                                 AccountMapper accountMapper) {
        this.bankAccountRepository = bankAccountRepository;
        this.accountService = accountService;
        this.accountMapper = accountMapper;
    }

    @GetMapping("/bankAccounts")
    public List<BankAccountResponseDTO> getAllAccounts() {
        return bankAccountRepository.findAll().stream()
                .map(accountMapper::fromBankAccount)
                .collect(Collectors.toList());
    }

    @GetMapping("/bankAccounts/{id}")
    public BankAccountResponseDTO getAccountById(@PathVariable String id) {
        return bankAccountRepository.findById(id)
                .map(accountMapper::fromBankAccount)
                .orElseThrow(() -> new RuntimeException(String.format("Account %s not found", id)));
    }

    @PostMapping("/bankAccounts")
    public BankAccountResponseDTO createAccount(@RequestBody BankAccountRequestDTO bankAccountRequestDTO) {
        return accountService.addAccount(bankAccountRequestDTO);
    }

    @PutMapping("/bankAccounts/{id}")
    public BankAccountResponseDTO updateAccount(@PathVariable String id, @RequestBody BankAccountRequestDTO bankAccountRequestDTO) {
        return accountService.updateAccount(id, bankAccountRequestDTO);
    }

    @DeleteMapping("/bankAccounts/{id}")
    public void deleteAccount(@PathVariable String id) {
        bankAccountRepository.deleteById(id);
    }
}