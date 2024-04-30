package in.swarnaka.springbootcrud.exception;

public class InventoryCollectionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InventoryCollectionException(String message) {
		super(message);
	}
	
	public static String NotFoundException(String id) {
		return "Inventory item with " +id+ " not found!";
		
	}
	
	public static String ItemAllreadyExists() {
		return "Inventory item with given name already exists";
	}
	
	
	

}
