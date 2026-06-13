package tech.codehunt.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import tech.codehunt.dao.AdminDao;
import tech.codehunt.model.Admin;
@Service
public class AdminCredentialsServiceImpl implements AdminCredentialsService {
	private final AdminDao adminDao;
    private final PasswordEncoder passwordEncoder;

    public AdminCredentialsServiceImpl(AdminDao adminDao, PasswordEncoder passwordEncoder) {
		this.adminDao = adminDao;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public String checkAdminCredentials(String oldusername, String oldpassword) {
		
		Optional<Admin> byUsername = adminDao.findByUsername(oldusername);
		if(byUsername.isPresent())
		{
			Admin admin = byUsername.get();//sn pss username
			boolean matches = passwordEncoder.matches(oldpassword, admin.getPassword());
			if(matches) {
				return "SUCCESS";
			}
			else {
				return "Wrong Old Credentials";
			}
			
			
		}else {
			return "Wrong Old Credentials";
		}
	}

	@Override
	public String updateAdminCredentials(String newusername, String newpassword, String oldusername) {
		Optional<Admin> adminOptional = adminDao.findByUsername(oldusername);
		if (adminOptional.isEmpty()) {
			return "FAILED TO UPDATE";
		}

		Admin admin = adminOptional.get();
		admin.setUsername(newusername);
		admin.setPassword(passwordEncoder.encode(newpassword));
		adminDao.save(admin);
		return "CREDENTIALS UPDATED SUCCESSFULLY";
	}

}
