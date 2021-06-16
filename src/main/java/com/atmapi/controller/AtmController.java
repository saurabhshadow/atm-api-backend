package com.atmapi.controller;



import java.util.List;
import java.util.Optional;
import com.atmapi.model.TransactionDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atmapi.model.Customer;
import com.atmapi.services.AtmServiceImp;





@RestController
public class AtmController {
	
	 @Autowired
	 private AtmServiceImp atmServiceImp;

	

	@RequestMapping(method=RequestMethod.PUT, value="/withdraw")
	@CrossOrigin(origins="http://localhost:3000")
	public ResponseEntity<Object> withdraw(@RequestBody Customer c) {

		return atmServiceImp.withdraw(c,"Cash Withdraw");
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/deposit")
	@CrossOrigin(origins="http://localhost:3000")
	public ResponseEntity<Object> deposit(@RequestBody Customer c) {
		
		return atmServiceImp.deposit(c,"Cash Deposit");
	}

	@RequestMapping(method=RequestMethod.GET, value="/checkBalance")
	@CrossOrigin(origins="http://localhost:3000")
	public ResponseEntity<Object> checkBalance(@RequestParam(value = "accountNumber", defaultValue = "0") String accountNumber) {
		Optional<Customer> c=atmServiceImp.checkBalance(accountNumber);
		return new ResponseEntity<>(c,HttpStatus.OK);

	}
	
	@RequestMapping(method=RequestMethod.GET, value="/loginUser")
	@CrossOrigin(origins="http://localhost:3000")
	public ResponseEntity<Object> details(@RequestParam(value = "accountNumber", defaultValue = "0") String accountNumber) {
		var c=atmServiceImp.loginUser(accountNumber);
		if(c.isPresent()){
			return new ResponseEntity<>(c,HttpStatus.OK);
		}
		return new ResponseEntity<>(c,HttpStatus.FORBIDDEN);

	}
	@RequestMapping(method=RequestMethod.PUT, value="/transferMoney")
	@CrossOrigin(origins="http://localhost:3000")
	public ResponseEntity<Object> transferMoney(@RequestParam(value = "accountNumber") String accountNumber,@RequestBody Customer c1) {
		return atmServiceImp.transferMoney(accountNumber,c1);

	}

	@RequestMapping(method=RequestMethod.GET, value="/transactionDetails")
	@CrossOrigin(origins="http://localhost:3000")
	public List<TransactionDetails> transferMoney(@RequestParam(value = "accountNumber") String accountNumber) {
		return atmServiceImp.getTransactionDetails(accountNumber);


	}



	//For Testing
	@RequestMapping(method=RequestMethod.POST, value="/addData")
	public ResponseEntity<Object> addData(@RequestBody Customer c1) {
		atmServiceImp.addData(c1);
		return new ResponseEntity<>(HttpStatus.OK);

	}
	

}