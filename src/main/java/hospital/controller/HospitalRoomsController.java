package hospital.controller;

import java.io.File;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import hospital.entity.HospitalRoom;
import hospital.entity.User;
import hospital.entity.UserDetail;
import hospital.facade.IAuthenticationFacade;
import hospital.misc.MyFileUploader;
import hospital.service.HospitalRoomService;

@Controller
@PropertySource("classpath:file-upload-config.properties")
@RequestMapping("/hospital_rooms")
public class HospitalRoomsController {
	
	@Autowired
	private Environment env;
	
	@Autowired
	private MyFileUploader myFileUploader;
	
	@Autowired
	private HospitalRoomService hospitalRoomService;
	
	@Autowired
    private IAuthenticationFacade authenticationFacade;
	
	@GetMapping
	public String hospitalRoomList() {
		return "hospital_rooms";
	}
	
	@GetMapping("/add")
	public String addHospitalRoom() {
		return "hospital_rooms_add_edit";
	}
	
	@GetMapping("/edit/{id}")
	public String editHospitalRoom(@PathVariable int id, HttpServletRequest request) {
		HospitalRoom hospitalRoom = hospitalRoomService.getHospitalRoom(id);
		if (hospitalRoom.getRoomImage() != null) {
			request.setAttribute("room_image", env.getProperty("file.root.dir") + "/" + hospitalRoom.getRoomImage());
		}
		return "hospital_rooms_add_edit";
	}
	
	@GetMapping("/details/{id}")
	public String viewHospitalRoom() {
		return "hospital_rooms_view";
	}
	
	@PostMapping
	public String addHospitalRoom(HospitalRoom hospitalRoom, 
			HttpServletRequest request,
			RedirectAttributes redirAttr, 
			@RequestParam(required = false) String id,
			@RequestParam MultipartFile file) {
		
		//File upload if there is existing one
		String roomImage = null;
		System.out.println("request.getContextPath() " + request.getContextPath());
		if (file != null && !file.getOriginalFilename().equals("")) {
//			try {
//				File transferFile = new File(env.getProperty("file.root.dir") + "/" + file.getOriginalFilename());
//				System.out.println("Transfer File: " + transferFile);
//				roomImage = file.getOriginalFilename();
//				file.transferTo(transferFile);
//			}catch(Exception e) {
//				e.printStackTrace();
//			}
		}
		
		//Check if its an existing entry since we use one controller for Add and Update
		if(id != null) {
			HospitalRoom existing = hospitalRoomService.getHospitalRoom(Integer.parseInt(id));
			hospitalRoom.setId(Integer.parseInt(id));
			hospitalRoom.setCreatedBy(existing.getCreatedBy());
			hospitalRoom.setCreated(existing.getCreated());
		}else {
			hospitalRoom.setCreated(LocalDateTime.now());
		}
		
//		hospitalRoom.setRoomImage(roomImage);
		
		// Check if there is an authenticated user.
		User user = authenticationFacade.getUser();
		UserDetail userDetail = user.getUserDetail();
		if(user != null && hospitalRoom.getCreatedBy() == null) {
			hospitalRoom.setCreatedBy(userDetail.getFirstName() + " " + userDetail.getLastName());
			hospitalRoom.setCreated(LocalDateTime.now());
		}
		
		if (user != null) {
			hospitalRoom.setUpdatedBy(userDetail.getFirstName() + " " + userDetail.getLastName());
		}
		
		hospitalRoom.setModified(LocalDateTime.now());
		hospitalRoomService.saveHospitalRoom(hospitalRoom);
		
		redirAttr.addFlashAttribute("message", "<b>Saving Hospital Room Successful!</b>");
		redirAttr.addFlashAttribute("status", "success");
		
		return "redirect:/hospital_rooms";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteHospitalRoom(@PathVariable int id, RedirectAttributes redirAttr) {
		HospitalRoom hospitalRoom = hospitalRoomService.getHospitalRoom(id);
		User user = authenticationFacade.getUser();
		
		if (user != null) {
			UserDetail userDetail = user.getUserDetail();
			hospitalRoom.setUpdatedBy(userDetail.getFirstName() + " " + userDetail.getLastName());
			hospitalRoom.setModified(LocalDateTime.now());
		}
		
		hospitalRoom.setDeleted(true);
		hospitalRoom.setDeletedDate(LocalDateTime.now());
		hospitalRoomService.saveHospitalRoom(hospitalRoom);
		
		redirAttr.addFlashAttribute("message", "<b>Hospital Room Deletion Successful!</b>");
		redirAttr.addFlashAttribute("status", "success");
		return "redirect:/hospital_rooms";
	}
	
	@GetMapping("/test")
	public String test() {
		System.out.println("myFileUploader: " + myFileUploader.getRootPath());
		return "hospital_rooms";
	}
}
