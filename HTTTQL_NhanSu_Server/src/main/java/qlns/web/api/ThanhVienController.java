package qlns.web.api;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import qlns.ThanhVien;
import qlns.data.ThanhVienRepo;

@RestController
@RequestMapping(path = "/", produces = "application/json")
@CrossOrigin(origins = "*")
public class ThanhVienController {
	private ThanhVienRepo tvRepo;
	
	@Autowired
	EntityLinks entityLinks;
	public ThanhVienController(ThanhVienRepo tvRepo) {
		this.tvRepo = tvRepo;
	}
	
	@PostMapping(path = "/check-login", consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public ThanhVien checkLogin(@RequestBody ThanhVien tv) {
		tv = tvRepo.checkLogin(tv.getUsername(), tv.getPassword());
		return tv;
	}
	
	@GetMapping("/manage-staff/search/{name}")
	public Iterable<ThanhVien> searchStaffByName(@PathVariable("name") String name){
		return tvRepo.searchStaff(name);
	}
	
	@PostMapping(path = "/manage-staff/save", consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public ThanhVien addMember(@RequestBody ThanhVien tv) {
		return tvRepo.save(tv);
	}
	
	@GetMapping("/manage-department/{idPb}/view-staff")
	public List<ThanhVien> viewStaff(@PathVariable("idPb") Long idPb){
		return tvRepo.searchStaffByPb(idPb);
	}
	
	@DeleteMapping("/manage-staff/delete/{id}")
	public void deleteTv(@PathVariable("id") Long id) {
		tvRepo.deleteById(id);
	}
}
