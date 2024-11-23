package com.alimert.service.impl;

import com.alimert.dto.DtoAccount;
import com.alimert.dto.DtoAddress;
import com.alimert.dto.DtoCustomer;
import com.alimert.dto.DtoCustomerIU;
import com.alimert.exception.BaseException;
import com.alimert.exception.ErrorMessage;
import com.alimert.exception.MessageType;
import com.alimert.model.Account;
import com.alimert.model.Address;
import com.alimert.model.Customer;
import com.alimert.repository.AccountRepository;
import com.alimert.repository.AddressRepository;
import com.alimert.repository.CustomerRepository;
import com.alimert.service.ICustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AccountRepository accountRepository;


    private Customer createCustomer(DtoCustomerIU dtoCustomerIU) {

        Optional<Address> address = addressRepository.findById(dtoCustomerIU.getAddressId());
        if (address.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoCustomerIU.getAddressId().toString()));
        }

        Optional<Account> account = accountRepository.findById(dtoCustomerIU.getAccountId());
        if (account.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoCustomerIU.getAccountId().toString()));
        }


        Customer customer = new Customer();
        customer.setCreateTime(new Date());
        BeanUtils.copyProperties(dtoCustomerIU, customer);
        customer.setAddress(address.get());
        customer.setAccount(account.get());
        return customer;
    }


    @Override
    public DtoCustomer saveCustomer(DtoCustomerIU dtoCustomerIU) {
        DtoCustomer dtoCustomer = new DtoCustomer();
        DtoAddress dtoAddress = new DtoAddress();
        DtoAccount dtoAccount = new DtoAccount();

        Customer savedCustomer = customerRepository.save(createCustomer(dtoCustomerIU));

        BeanUtils.copyProperties(savedCustomer, dtoCustomer);
        BeanUtils.copyProperties(savedCustomer.getAddress(), dtoAddress);
        BeanUtils.copyProperties(savedCustomer.getAccount(), dtoAccount);

        dtoCustomer.setAddress(dtoAddress);
        dtoCustomer.setAccount(dtoAccount);


        return dtoCustomer;
    }

    @Override
    public List<DtoCustomer> getAllCustomers() {
        List<DtoCustomer> dtoCustomers = new ArrayList<>();
        List<Customer> customerList = customerRepository.findAll();
        if (!customerList.isEmpty() && customerList != null) {
            for (Customer customer : customerList) {
                DtoCustomer dtoCustomer = new DtoCustomer();
                BeanUtils.copyProperties(customer, dtoCustomer);
                dtoCustomers.add(dtoCustomer);
            }
        }
        return dtoCustomers;
    }

    @Override
    public DtoCustomer getCustomerById(Long id) {
        Optional<Customer> optCustomer = customerRepository.findById(id);
        if (optCustomer.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, id.toString()));
        }
        DtoCustomer dtoCustomer = new DtoCustomer();
        Customer customer = optCustomer.get();
        BeanUtils.copyProperties(customer, dtoCustomer);
        return dtoCustomer;
    }

    @Override
    public DtoCustomer updateCustomer(Long id, DtoCustomerIU dtoCustomerIU) {
        DtoCustomer dtoCustomer = new DtoCustomer();
        Optional<Customer> optCustomer = customerRepository.findById(id);
        if (optCustomer.isPresent()) {
            Customer dbCustomer = optCustomer.get();
            dbCustomer.setTckn(dtoCustomerIU.getTckn());
            dbCustomer.setAddress(addressRepository.findById(dtoCustomerIU.getAddressId()).get());
            dbCustomer.setAccount(accountRepository.findById(dtoCustomerIU.getAccountId()).get());
            dbCustomer.setBirthOfDate(dtoCustomerIU.getBirthOfDate());
            dbCustomer.setFirstName(dtoCustomerIU.getFirstName());
            dbCustomer.setLastName(dtoCustomerIU.getLastName());
            Customer updateCustomer = customerRepository.save(dbCustomer);
            BeanUtils.copyProperties(updateCustomer, dtoCustomer);
            return dtoCustomer;
        }
        return null;
    }
}
