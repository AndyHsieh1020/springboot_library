package controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import model.InventoryModel;
import service.InventoryService;

@RestController
public class InventoryController {
	
	@Autowired
	InventoryModel inventorymodel;
	@Autowired
	InventoryService inventoryservice;
	
	@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST})
	@PostMapping("/addInventory")
	public String addBook(@RequestBody InventoryModel bookmodel) {
		bookmodel.setisbn(bookmodel.getisbn());
		bookmodel.setstoretime(bookmodel.getstoretime());
		inventoryservice.addBook(bookmodel);
		return "Inventory added";
	}
	@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST})
	@PostMapping("/editInventory")
	public String editBook(@RequestBody InventoryModel bookmodel) {
		System.out.println(bookmodel.toString());
		System.out.println(bookmodel.getisbn()+","+bookmodel.getstoretime()+','+bookmodel.getstatus()+','+bookmodel.getinventoryid());
		bookmodel.setisbn(bookmodel.getisbn());
		bookmodel.setstoretime(bookmodel.getstoretime());
		bookmodel.setstatus(bookmodel.getstatus());
		bookmodel.setinventoryid(bookmodel.getinventoryid());
		inventoryservice.editBook(bookmodel);
		return "Inventory edited";
	}
	@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST})
	@PostMapping("/deleteInventory")
	public String deleteBook(@RequestBody InventoryModel bookmodel) {
		bookmodel.setinventoryid(bookmodel.getinventoryid());
		inventoryservice.deleteBook(bookmodel);
		return "Inventory deleted";
	}
	@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST})
	@GetMapping("/listInventory")
	public List<Map<String,Object>> listBook() {
		return inventoryservice.listAllbooks();
	}
}
