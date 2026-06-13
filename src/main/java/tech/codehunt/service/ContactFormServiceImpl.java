package tech.codehunt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import tech.codehunt.dao.ContactFormCurd;
import tech.codehunt.model.ContactForm;
@Service
public class ContactFormServiceImpl implements ContactFormService {
    private final ContactFormCurd contactFormCurd;

	public ContactFormServiceImpl(ContactFormCurd contactFormCurd) {
		this.contactFormCurd = contactFormCurd;
	}

	@Override
	public ContactForm saveContactFormService(ContactForm contactForm) {
		
		 return contactFormCurd.save(contactForm);
	}

	@Override
	public List<ContactForm> readAllContactService() {
		
		return contactFormCurd.findAll();
	}

	@Override
	public void  deleteContactService(int id) {
		 contactFormCurd.deleteById(id);
	}
               
}
