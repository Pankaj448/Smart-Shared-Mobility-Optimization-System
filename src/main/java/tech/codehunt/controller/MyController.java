package tech.codehunt.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.ui.Model;

import tech.codehunt.model.BookingForm;
import tech.codehunt.model.ContactForm;
import tech.codehunt.model.RideShareRequest;
import tech.codehunt.model.ServiceForm;
import tech.codehunt.service.BookingFormService;
import tech.codehunt.service.ContactFormService;
import tech.codehunt.service.RideShareRequestService;
import tech.codehunt.service.ShortestPathService;
import tech.codehunt.service.ServiceFormService;

@Controller
public class MyController {
	private final ServiceFormService serviceFormService;
	private final ContactFormService contactFormService;
	private final BookingFormService bookingFormService;
	private final RideShareRequestService rideShareRequestService;
	private final ShortestPathService shortestPathService;

	public MyController(ServiceFormService serviceFormService,
						ContactFormService contactFormService,
						BookingFormService bookingFormService,
						RideShareRequestService rideShareRequestService,
						ShortestPathService shortestPathService) {
		this.serviceFormService = serviceFormService;
		this.contactFormService = contactFormService;
		this.bookingFormService = bookingFormService;
		this.rideShareRequestService = rideShareRequestService;
		this.shortestPathService = shortestPathService;
	}
    

	@GetMapping(path = {"/", "home", "welcome", "index"})
    public String welcomeView(HttpServletRequest req, Model m) {
        m.addAttribute("mycurrentpage", req.getRequestURI());
        
        m.addAttribute("bookingForm",new BookingForm());
        return "index";
    }

    

	@GetMapping("about")
    public String aboutView(HttpServletRequest req, Model m) {
        m.addAttribute("mycurrentpage", req.getRequestURI());
        return "about";
    }

    @GetMapping("cars")
    public String carsView(HttpServletRequest req, Model m) {
        m.addAttribute("mycurrentpage", req.getRequestURI());
        return "cars";
    }

    @GetMapping("services")
    public String serviceView(HttpServletRequest req, Model m) {
        m.addAttribute("mycurrentpage", req.getRequestURI());
        
        // data collect 
        List<ServiceForm> allServices = serviceFormService.readAllServices();
        m.addAttribute("allservices",allServices);
        
        return "services";
    }

    @GetMapping("contacts")
    public String contactView(HttpServletRequest req, Model m) {
        m.addAttribute("mycurrentpage", req.getRequestURI());
        m.addAttribute("contactForm", new ContactForm());
        return "contacts";
    }

    @GetMapping("ride-sharing")
    public String rideSharingView(HttpServletRequest req, Model m) {
        m.addAttribute("mycurrentpage", req.getRequestURI());
        m.addAttribute("rideShareRequest", new RideShareRequest());
        return "ride-sharing";
    }

    @GetMapping("shortest-path")
    public String shortestPathView(HttpServletRequest req, Model m) {
        m.addAttribute("mycurrentpage", req.getRequestURI());
        m.addAttribute("locations", shortestPathService.getLocations());
        return "shortest-path";
    }

    @PostMapping("shortest-path")
    public String calculateShortestPath(@RequestParam("source") String source,
                                        @RequestParam("destination") String destination,
                                        HttpServletRequest req,
                                        Model m) {
        m.addAttribute("mycurrentpage", req.getRequestURI());
        m.addAttribute("locations", shortestPathService.getLocations());

        try {
            m.addAttribute("pathResult", shortestPathService.findShortestPath(source, destination));
        } catch (IllegalArgumentException e) {
            m.addAttribute("errorMessage", e.getMessage());
        }

        m.addAttribute("selectedSource", source);
        m.addAttribute("selectedDestination", destination);
        return "shortest-path";
    }

    @GetMapping("/login")
    public String adminLoginView() {
    	return "admin/adminlogin";
    }

    @PostMapping("contactform")
    public String contactForm(@Valid @ModelAttribute ContactForm contactForm,
                              BindingResult bindingResult,
                              Model m,RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            m.addAttribute("bindingResult", bindingResult);
            m.addAttribute("contactForm", contactForm);
            return "contacts";
        }
       ContactForm  saveContactFormService = contactFormService.saveContactFormService(contactForm);
       if(saveContactFormService!=null) {
    	      redirectAttributes.addFlashAttribute("message", "Message sent successfully");
       }
       else {
    	   redirectAttributes.addFlashAttribute("message", "Something went wrong");
       }
        return "redirect:/contacts";
    }
    
    @PostMapping("bookingform")
    public String bookingForm(@Valid @ModelAttribute BookingForm bookingform,
                              BindingResult bindingResult,
                              Model m,RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            m.addAttribute("bindingResult", bindingResult);
            m.addAttribute("bookingForm", bookingform);
            return "index";
        }
       
        
        
        //DAO
        BookingForm saveBookingFormService = bookingFormService.saveBookingFormService(bookingform);        
        if(saveBookingFormService!=null) {
  	      redirectAttributes.addFlashAttribute("message", "Booking confirmed. Driver: "
                  + saveBookingFormService.getAssignedDriverName()
                  + " | Vehicle: " + saveBookingFormService.getAssignedVehicleNumber());
     }
     else {
  	   redirectAttributes.addFlashAttribute("message", "Something went wrong");
     }
        
        
        
        return "redirect:/index";
        
    }

    @PostMapping("rideshareform")
    public String rideShareForm(@Valid @ModelAttribute RideShareRequest rideShareRequest,
                                BindingResult bindingResult,
                                HttpServletRequest req,
                                Model m,
                                RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            m.addAttribute("mycurrentpage", req.getRequestURI());
            m.addAttribute("rideShareRequest", rideShareRequest);
            return "ride-sharing";
        }

        RideShareRequest savedRideShareRequest = rideShareRequestService.saveRideShareRequest(rideShareRequest);
        if (savedRideShareRequest != null) {
            redirectAttributes.addFlashAttribute("message", "Ride sharing request confirmed. Driver: "
                    + savedRideShareRequest.getAssignedDriverName()
                    + " | Vehicle: " + savedRideShareRequest.getAssignedVehicleNumber());
        } else {
            redirectAttributes.addFlashAttribute("message", "Something went wrong");
        }

        return "redirect:/ride-sharing";
    }
}
