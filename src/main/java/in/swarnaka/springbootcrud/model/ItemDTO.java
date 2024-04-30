package in.swarnaka.springbootcrud.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "items")

public class ItemDTO {
	
	@Id
	private String id;
	
	@NotNull(message = "Item Code can not be Null")
	private String code;
	
	@NotNull(message = "Item Name can not be Null")
	private String name;
	
	@NotNull(message = "Item Category can not be Null")
	private String category;
	
	@NotNull(message = "Status can not be Null")
	private String status = "ACTIVE";
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
