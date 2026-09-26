package net.omaima.bankaccountservice.service;

import net.omaima.bankaccountservice.dto.BankAccountRequestDTO;
import net.omaima.bankaccountservice.dto.BankAccountResponseDTO;
import net.omaima.bankaccountservice.entities.BankAccount;

public interface BankAccountService {
    BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO);
    BankAccountResponseDTO updateAccount(String id, BankAccountRequestDTO bankAccountDTO);

}
