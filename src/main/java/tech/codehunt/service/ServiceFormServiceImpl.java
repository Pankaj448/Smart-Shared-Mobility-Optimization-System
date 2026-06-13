package tech.codehunt.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;

import jakarta.transaction.Transactional;
import tech.codehunt.dao.ServiceFormCurd;
import tech.codehunt.model.ServiceForm;
@Service
public class ServiceFormServiceImpl implements ServiceFormService {
	private final ServiceFormCurd serviceFormCurd;
	private final String uploadDir;

	public ServiceFormServiceImpl(ServiceFormCurd serviceFormCurd,
								  @Value("${app.upload-dir:uploads/myserviceimg}") String uploadDir) {
		this.serviceFormCurd = serviceFormCurd;
		this.uploadDir = uploadDir;
	}


	@Transactional(rollbackOn=Exception.class)
	@Override
	public ServiceForm addService(ServiceForm serviceForm, MultipartFile multipartFile) throws Exception {
		String filename = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
		if (filename.isBlank() || filename.contains("..")) {
			throw new IllegalArgumentException("Invalid image file name");
		}

		serviceForm.setImage(filename);
		ServiceForm savedService = serviceFormCurd.save(serviceForm);

		Path uploadDirectory = Paths.get(uploadDir).toAbsolutePath().normalize();
		Files.createDirectories(uploadDirectory);
		Files.copy(multipartFile.getInputStream(), uploadDirectory.resolve(filename), java.nio.file.StandardCopyOption.REPLACE_EXISTING);

		return savedService;
	}


	@Override
	public List<ServiceForm> readAllServices() {
		
		// TODO Auto-generated method stub
		return serviceFormCurd.findAll();
	}

}
