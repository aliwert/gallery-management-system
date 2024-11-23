package com.alimert.service.impl;

import com.alimert.controller.RootEntity;
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
        if (!customerList.isEmpty()) {
            for (Customer customer : customerList) {
                DtoCustomer dtoCustomer = new DtoCustomer();
                Customer customer1 = customerList.get(customerList.indexOf(customer));
                BeanUtils.copyProperties(customer, dtoCustomer);

                DtoAccount dtoAccount = new DtoAccount();
                dtoAccount.setId(customer.getAccount().getId());
                dtoAccount.setCurrencyType(customer1.getAccount().getCurrencyType());
                dtoAccount.setIban(customer1.getAccount().getIban());
                dtoAccount.setCreateTime(customer1.getAccount().getCreateTime());
                dtoAccount.setAmount(customer1.getAccount().getAmount());
                dtoAccount.setCurrencyType(customer1.getAccount().getCurrencyType());
                dtoAccount.setIban(customer1.getAccount().getIban());
                dtoAccount.setCreateTime(customer1.getAccount().getCreateTime());
                dtoCustomer.setAccount(dtoAccount);


                DtoAddress dtoAddress = new DtoAddress();
                dtoAddress.setCity(customer1.getAddress().getCity());
                dtoAddress.setDistrict(customer1.getAddress().getDistrict());
                dtoAddress.setId(customer1.getAddress().getId());
                dtoAddress.setStreet(customer1.getAddress().getStreet());
                dtoAddress.setNeighborhood(customer1.getAddress().getNeighborhood());
                dtoAddress.setCreateTime(customer1.getCreateTime());

                dtoCustomer.setAccount(dtoAccount);
                dtoCustomer.setAddress(dtoAddress);

                dtoCustomers.add(dtoCustomer);
            }
        }
        return dtoCustomers;
    }

    @Override
    public DtoCustomer getCustomerById(Long id) {
        DtoCustomer dtoCustomer = new DtoCustomer();
        Optional<Customer> optCustomer = customerRepository.findById(id);
        if (optCustomer.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, id.toString()));
        }
        Customer customer = optCustomer.get();
        BeanUtils.copyProperties(customer, dtoCustomer);

        DtoAccount dtoAccount = new DtoAccount();
        dtoAccount.setId(customer.getAccount().getId());
        dtoAccount.setAccountNo(customer.getAccount().getAccountNo());
        dtoAccount.setIban(customer.getAccount().getIban());
        dtoAccount.setAmount(customer.getAccount().getAmount());
        dtoAccount.setCurrencyType(customer.getAccount().getCurrencyType());
        dtoAccount.setCreateTime(customer.getCreateTime());

        DtoAddress dtoAddress = new DtoAddress();
        dtoAddress.setCity(customer.getAddress().getCity());
        dtoAddress.setDistrict(customer.getAddress().getDistrict());
        dtoAddress.setId(customer.getAddress().getId());
        dtoAddress.setStreet(customer.getAddress().getStreet());
        dtoAddress.setNeighborhood(customer.getAddress().getNeighborhood());
        dtoAddress.setCreateTime(customer.getCreateTime());

        dtoCustomer.setAccount(dtoAccount);
        dtoCustomer.setAddress(dtoAddress);


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

    @Override
    public RootEntity<Void> deleteCustomer(Long id) {
        Optional<Customer> optCustomer = customerRepository.findById(id);
        if (optCustomer.isPresent()) {
            accountRepository.deleteById(id);
            customerRepository.deleteById(id);
        } else {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, id.toString()));
        }
        return null;
    }
}
