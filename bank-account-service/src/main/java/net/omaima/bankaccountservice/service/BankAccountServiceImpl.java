package net.omaima.bankaccountservice.service;

import net.omaima.bankaccountservice.entities.BankAccount;
import net.omaima.bankaccountservice.mappers.AccountMapper;
import net.omaima.bankaccountservice.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import net.omaima.bankaccountservice.dto.BankAccountRequestDTO;
import net.omaima.bankaccountservice.dto.BankAccountResponseDTO;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;


@Service
@Transactional
public class BankAccountServiceImpl implements BankAccountService{
    @Autowired
    private BankAccountRepository bankAccountRepository;
    private AccountMapper accountMapper;

    @Override
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO) {
        BankAccount bankAccount= BankAccount.builder()
                .id(UUID.randomUUID().toString())
                .createAt(new Date())
                .balance(bankAccountDTO.getBalance())
                .type(bankAccountDTO.getType())
                .currency(bankAccountDTO.getCurrency())
                .build();
        BankAccount saveBankAccount = bankAccountRepository.save(bankAccount);
        //mapping
        BankAccountResponseDTO bankAccountResponseDTO = accountMapper.fromBankAccount(saveBankAccount);
        return bankAccountResponseDTO;
    }

    @Override
    public BankAccountResponseDTO updateAccount(String id, BankAccountRequestDTO requestDTO) {
        BankAccount existingAccount = bankAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Account %s not found", id)));

        if (requestDTO.getBalance() != null) existingAccount.setBalance(requestDTO.getBalance());
        if (requestDTO.getType() != null) existingAccount.setType(requestDTO.getType());
        if (requestDTO.getCurrency() != null) existingAccount.setCurrency(requestDTO.getCurrency());

        BankAccount updatedAccount = bankAccountRepository.save(existingAccount);
        return accountMapper.fromBankAccount(updatedAccount);
    }
}
