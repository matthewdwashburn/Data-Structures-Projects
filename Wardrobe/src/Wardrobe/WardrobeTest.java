package Wardrobe;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import Wardrobe.Apparel.Condition;
import Wardrobe.Pants;
import Wardrobe.Shirt;
import Wardrobe.Shirt.Size;

public class WardrobeTest {

	@Test
	void testPantsConstructor() {
		Pants pants = new Pants("Black",44.99,Apparel.Condition.NEW,30,40);
		assertEquals("Black", pants.getColor());
		assertEquals(44.99, pants.getPrice());
		assertEquals(Apparel.Condition.NEW, pants.getCondition());
		assertEquals(30, pants.getwaistMeasurement());
		assertEquals(40, pants.getinseamMeasurement());
	}
	
	@Test
	void testIllegalPantsConstructor() {
		assertThrows(IllegalArgumentException.class, () -> new Pants("", 30.99, Apparel.Condition.NEW,30,40));
		assertThrows(IllegalArgumentException.class, () -> new Pants(null, 30.99, Apparel.Condition.NEW,30,40));
		assertThrows(IllegalArgumentException.class, () -> new Pants("Black", -30.99, Apparel.Condition.NEW,30,40));
		assertThrows(IllegalArgumentException.class, () -> new Pants("Black", 30.99, Apparel.Condition.NEW,-30,40));
		assertThrows(IllegalArgumentException.class, () -> new Pants("Black", 30.99, null,0,40));
		assertThrows(IllegalArgumentException.class, () -> new Pants("Black", 30.99, Apparel.Condition.NEW,30,-40));
		assertThrows(IllegalArgumentException.class, () -> new Pants("Black", 30.99, Apparel.Condition.NEW,30,0));
	}
	
	@Test
	void testShirtConstructor() {
		Shirt shirt = new Shirt("Black",39.99,Apparel.Condition.NEW,Shirt.Size.M,"It's Just Water Weight");
		assertEquals("Black", shirt.getColor());
		assertEquals(39.99, shirt.getPrice());
		assertEquals(Apparel.Condition.NEW, shirt.getCondition());
		assertEquals(Shirt.Size.M, shirt.getSize());
		assertEquals("It's Just Water Weight", shirt.getText());
	}
	
	@Test
	void testIllegalShirtConstructor() {
		assertThrows(IllegalArgumentException.class, () -> new Shirt("", 30.99, Apparel.Condition.NEW, Shirt.Size.M, "YOLO"));
		assertThrows(IllegalArgumentException.class, () -> new Shirt(null, 30.99, Apparel.Condition.NEW, Shirt.Size.M, "YOLO"));
		assertThrows(IllegalArgumentException.class, () -> new Shirt("Blue", -30.99, Apparel.Condition.NEW, Shirt.Size.M, "YOLO"));
		assertThrows(IllegalArgumentException.class, () -> new Shirt("Blue", 30.99, null, Shirt.Size.M, "YOLO"));
		assertThrows(IllegalArgumentException.class, () -> new Shirt("Blue", 30.99, Apparel.Condition.NEW, null, "YOLO"));
	}
	
	@Test
	void testPantsSetters() {
		Pants pants = new Pants("Black",44.99,Apparel.Condition.NEW,30,40);
		pants.setColor("Green");
		pants.setPrice(49.99);
		pants.setCondition(Apparel.Condition.GOOD);
		pants.setwaistMeasurement(40);
		pants.setinseamMeasurement(50);
		
		assertEquals("Green", pants.getColor());
		assertEquals(49.99, pants.getPrice());
		assertEquals(Apparel.Condition.GOOD, pants.getCondition());
		assertEquals(40, pants.getwaistMeasurement());
		assertEquals(50, pants.getinseamMeasurement());
	}

	 @Test
	    void testIllegalPantsSetters() {
	        Pants pants = new Pants("Blue", 29.99, Apparel.Condition.GOOD, 32, 34);
	        assertThrows(IllegalArgumentException.class, () -> pants.setColor(""));
	        assertThrows(IllegalArgumentException.class, () -> pants.setColor(null));
	        assertThrows(IllegalArgumentException.class, () -> pants.setPrice(-40));
	        assertThrows(IllegalArgumentException.class, () -> pants.setCondition(null));
	        assertThrows(IllegalArgumentException.class, () -> pants.setwaistMeasurement(-36));
	        assertThrows(IllegalArgumentException.class, () -> pants.setwaistMeasurement(0));
	        assertThrows(IllegalArgumentException.class, () -> pants.setinseamMeasurement(-36));
	        assertThrows(IllegalArgumentException.class, () -> pants.setinseamMeasurement(0));
	        
	        assertEquals("Blue", pants.getColor());
			assertEquals(29.99, pants.getPrice());
			assertEquals(Apparel.Condition.GOOD, pants.getCondition());
			assertEquals(32, pants.getwaistMeasurement());
			assertEquals(34, pants.getinseamMeasurement());
	 }
	 @Test
		void testShirtSetters() {
			Shirt shirt = new Shirt("Black",39.99,Apparel.Condition.NEW,Shirt.Size.M,"It's Just Water Weight");
			shirt.setColor("Blue");
			shirt.setPrice(49.99);
			shirt.setCondition(Apparel.Condition.GOOD);
			shirt.setSize(Shirt.Size.L);
			shirt.setText("YOLO");
			
			assertEquals("Blue", shirt.getColor());
			assertEquals(49.99, shirt.getPrice());
			assertEquals(Apparel.Condition.GOOD, shirt.getCondition());
			assertEquals(Shirt.Size.L, shirt.getSize());
			assertEquals("YOLO", shirt.getText());
		}
	 @Test
	    void testIllegalShirtSetters() {
	        Shirt shirt = new Shirt("Black",39.99,Apparel.Condition.NEW,Shirt.Size.M,"It's Just Water Weight");
	        assertThrows(IllegalArgumentException.class, () -> shirt.setColor(""));
	        assertThrows(IllegalArgumentException.class, () -> shirt.setColor(null));
	        assertThrows(IllegalArgumentException.class, () -> shirt.setPrice(-40));
	        assertThrows(IllegalArgumentException.class, () -> shirt.setCondition(null));
	        assertThrows(IllegalArgumentException.class, () -> shirt.setSize(null));
	        
	        assertEquals("Black", shirt.getColor());
			assertEquals(39.99, shirt.getPrice());
			assertEquals(Apparel.Condition.NEW, shirt.getCondition());
			assertEquals(Shirt.Size.M, shirt.getSize());
			assertEquals("It's Just Water Weight", shirt.getText());
	        
	 }
}
