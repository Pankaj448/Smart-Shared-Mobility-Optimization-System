package tech.codehunt.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import tech.codehunt.model.ContactForm;
import tech.codehunt.model.ServiceForm;
import tech.codehunt.service.AdminCredentialsService;
import tech.codehunt.service.BookingFormService;
import tech.codehunt.service.ContactFormService;
import tech.codehunt.service.DashboardStatsService;
import tech.codehunt.service.RideShareRequestService;
import tech.codehunt.service.ServiceFormService;

@Controller
@RequestMapping("admin")
public class AdminController {
	private final ServiceFormService serviceFormService;
	private final BookingFormService bookingFormService;
	private final AdminCredentialsService adminCredentialsService;
	private final ContactFormService contactFormService;
	private final RideShareRequestService rideShareRequestService;
	private final DashboardStatsService dashboardStatsService;

	public AdminController(ServiceFormService serviceFormService,
						   BookingFormService bookingFormService,
						   AdminCredentialsService adminCredentialsService,
						   ContactFormService contactFormService,
						   RideShareRequestService rideShareRequestService,
						   DashboardStatsService dashboardStatsService) {
		this.serviceFormService = serviceFormService;
		this.bookingFormService = bookingFormService;
		this.adminCredentialsService = adminCredentialsService;
		this.contactFormService = contactFormService;
		this.rideShareRequestService = rideShareRequestService;
		this.dashboardStatsService = dashboardStatsService;
	}

	@GetMapping("dashboard")
	public String adminDashboard(Model model) {
		model.addAttribute("stats", dashboardStatsService.getDashboardStats());
		return "admin/dashboard";
	}
    @GetMapping("readAllContacts")
   	public String readAllContacts(Model model) {
    	model.addAttribute("allcontacts", contactFormService.readAllContactService());
     
    	
   		return "admin/readallcontacts";
   	}
    @GetMapping("deleteContact/{id}")
   	public String deleteContact( @PathVariable int id ,RedirectAttributes redirectAttributes) {
    	  contactFormService.deleteContactService(id);
    	  redirectAttributes.addFlashAttribute("message", "CONTACT DELETED SUCCESSFULLY");
      return "redirect:/admin/readAllContacts";
   	}
    
    @GetMapping("changeCredentials")
   	public String changeCredentialsView( ) {
    	  
      return "admin/changecredentials";
   	}
    
   @PostMapping("changeCredentials")
   	public String  changeCredentials(
   			@RequestParam ("oldusername")String oldusername,
   			@RequestParam ("oldpassword")String oldpassword,
   			@RequestParam ("newusername")String newusername,
   			@RequestParam ("newpassword")String newpassword,
   			RedirectAttributes redirectAttributes
   			
   			) {
    	  //admin admin123
	   String result = adminCredentialsService.checkAdminCredentials(oldusername, oldpassword);
	   System.out.println(result);
	   if(result.equals("SUCCESS")){
	   		//password update
		   result=adminCredentialsService.updateAdminCredentials(newusername, newpassword, oldusername);
		   redirectAttributes.addFlashAttribute("message",result);
		  
   }else {
	   redirectAttributes.addFlashAttribute("message",result);
   }
	   
      return "redirect:/admin/dashboard";
   	}
    
   @GetMapping("readAllBookings")
  	public String readAllBookings(Model model) {
   	model.addAttribute("allBookings", bookingFormService.readAllBookingsService());
    
   	
  		return "admin/readallbookings";
  	}
   
   @GetMapping("deleteBooking/{id}")
  	public String deleteBooking( @PathVariable int id ,RedirectAttributes redirectAttributes) {
   	  bookingFormService.deleteBookingService(id);
   	  redirectAttributes.addFlashAttribute("message", "BOOKING DELETED SUCCESSFULLY");
     return "redirect:/admin/readAllBookings";
  	}

   @GetMapping("readAllRideShares")
   public String readAllRideShares(Model model) {
	   model.addAttribute("allRideShares", rideShareRequestService.readAllRideShareRequests());
	   return "admin/readallrideshares";
   }

   @GetMapping("deleteRideShare/{id}")
   public String deleteRideShare(@PathVariable int id, RedirectAttributes redirectAttributes) {
	   rideShareRequestService.deleteRideShareRequest(id);
	   redirectAttributes.addFlashAttribute("message", "RIDE SHARE REQUEST DELETED SUCCESSFULLY");
	   return "redirect:/admin/readAllRideShares";
   }
   
   @GetMapping("addService")
  	public String addServiceView( ) {
   	  
     return "admin/addservice";
  	}
   @InitBinder
   public void stopBinding(WebDataBinder webDataBinder) {
	   webDataBinder.setDisallowedFields("image");
	   
   }
   
  @PostMapping("addService")
 	public String addService(@Valid @ModelAttribute ServiceForm serviceForm,
							 BindingResult bindingResult,
							 @RequestParam("image") MultipartFile multipartFile,
							 RedirectAttributes redirectAttributes) {
	  if (bindingResult.hasErrors()) {
		  redirectAttributes.addFlashAttribute("msg", "PLEASE FILL SERVICE DETAILS CORRECTLY");
		  return "redirect:/admin/addService";
	  }

  	  if (multipartFile.isEmpty()) {
  		  redirectAttributes.addFlashAttribute("msg", "PLEASE SELECT AN IMAGE");
  		  return "redirect:/admin/addService";
  	  }

  	  serviceForm.setImage(multipartFile.getOriginalFilename());
  	  try {
  		ServiceForm service = serviceFormService.addService(serviceForm, multipartFile);
  		if(service!=null) {
  			redirectAttributes.addFlashAttribute("msg", "SERVICE ADDED SUCCESSFULLY");
  		}
  		else {
  			redirectAttributes.addFlashAttribute("msg", "SOMETHING WENT WRONG");

  		}
	} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msg", "SOMETHING WENT WRONG");
	}
  	  
  	  
  	  
  	  return "redirect:/admin/addService";
    
 	}
}
