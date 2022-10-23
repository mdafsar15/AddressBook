package com.bridgelabz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.dto.ContactDto;
import com.bridgelabz.dto.LoginDto;
import com.bridgelabz.dto.RegisterDto;
import com.bridgelabz.exception.AuthorizationException;
import com.bridgelabz.model.Contact;
import com.bridgelabz.model.User;
import com.bridgelabz.repository.AddressBookRepository;
import com.bridgelabz.repository.UserRepository;
import com.bridgelabz.util.EmailSenderService;
import com.bridgelabz.util.JwtToken;

@Service
public class AddressBookService implements IAddressBookService {

	@Autowired
	JwtToken jwtToken;

	@Autowired
	EmailSenderService sender;

	@Autowired
	private AddressBookRepository repository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public Contact createAddressBook(ContactDto contactDto) {

		Contact c = null;
		c = new Contact(contactDto);
		return repository.save(c);
	}

	@Override
	public Contact updateAddressBook(int address_id, ContactDto contactDto) {

		Contact c = repository.findById(address_id);
		c.updateAddressBook(contactDto);
		return repository.save(c);
	}

	@Override
	public Contact deleteAddressBookData(int address_id) {

		Contact c = repository.deleteById(address_id);
		return c;

	}

	@Override
	public List<Contact> getAddressBookDataAscByState() {

		return repository.getAddressBookDataByStateName();
	}

	@Override
	public List<Contact> getAddressBookDataAscByCity() {

		return repository.getAddressBookDataByCityName();
	}

	@Override
	public User register(RegisterDto registerDto) {

		User u = null;
		u = new User(registerDto);
		return userRepository.save(u);
	}

	@Override
	public User login(LoginDto loginDto) {

		User u = userRepository.findByUserName(loginDto.getUserName());
		if (u != null) {
			if (loginDto.getPassword().equalsIgnoreCase(u.getPassword())) {

				String token = jwtToken.createToken(u.getUserId());
				System.out.println("Jwt Token : " + token);
			}

		}
		return u;
	}

//	private User authenticate(String token) {
////		User u = userRepository.findByUserName(loginDto.getUserName());
//		User u = userRepository.findByUserId(jwtToken.decodeJWT(token));
//		if (u != null) {
//			return u;
//		}
//		throw new AuthorizationException("Authorization failed", 401);
//	}

	@Override
	public List<Contact> getAll(String token) {

//		authenticate(token);
		jwtToken.decodeJWT(token);
		List<Contact> getAllBook = repository.getAllBook();
		System.out.println("getAllBook : " + getAllBook);
		return getAllBook;
	}

}
