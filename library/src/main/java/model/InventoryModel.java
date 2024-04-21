package model;

import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component
public class InventoryModel {
	private int inventoryid;
	private String isbn;
	private LocalDateTime store_time;
	private String status;
	
	public int getinventoryid() {
		return inventoryid;
	}
	public void setinventoryid(int inventoryid) {
		this.inventoryid = inventoryid;
	}
	
	public String getisbn() {
		return isbn;
	}
	public void setisbn(String isbn) {
		this.isbn = isbn;
	}
	
	public LocalDateTime getstoretime() {
		return store_time;
	}
	public void setstoretime(LocalDateTime store_time) {
		this.store_time = store_time;
	}
	
	public String getstatus() {
		return status;
	}
	public void setstatus(String status) {
		this.status = status;
	}

}