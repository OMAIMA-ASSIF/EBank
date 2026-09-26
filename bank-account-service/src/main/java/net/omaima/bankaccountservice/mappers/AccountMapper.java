package net.omaima.bankaccountservice.mappers;

import net.omaima.bankaccountservice.dto.BankAccountRequestDTO;
import net.omaima.bankaccountservice.dto.BankAccountResponseDTO;
import net.omaima.bankaccountservice.entities.BankAccount;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;


@Component
public class AccountMapper {
    public BankAccountResponseDTO fromBankAccount(BankAccount bankAccount){
        BankAccountResponseDTO bankAccountResponseDTO = new BankAccountResponseDTO();
        BeanUtils.copyProperties(bankAccount, bankAccountResponseDTO);
        return bankAccountResponseDTO;
    }

    public BankAccount toBankAccount(BankAccountRequestDTO requestDTO) {
        BankAccount bankAccount = new BankAccount();
        BeanUtils.copyProperties(requestDTO, bankAccount);
        return bankAccount;
    }
}
