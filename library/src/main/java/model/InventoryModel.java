package model;

import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component
public class InventoryModel {
	private int inventoryId;
	private String isbn;
	private LocalDateTime storeTime;
	private String status;
	
	public int getInventoryId() {
		return inventoryId;
	}
	public void setInventoryId(int inventoryId) {
		this.inventoryId = inventoryId;
	}
	
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	public LocalDateTime getStoreTime() {
		return storeTime;
	}
	public void setStoreTime(LocalDateTime storeTime) {
		this.storeTime = storeTime;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
