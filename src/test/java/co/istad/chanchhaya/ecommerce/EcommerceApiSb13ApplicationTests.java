package co.istad.chanchhaya.ecommerce;

import co.istad.chanchhaya.ecommerce.entity.Category;
import co.istad.chanchhaya.ecommerce.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class EcommerceApiSb13ApplicationTests {

	@Autowired
	private CategoryRepository categoryRepository;

	// Test select data from category table
	@Test
	void test_selectCategories() {
		List<Category> categories = categoryRepository.findAll();

		IO.println("====<Category>====");
		categories.forEach(category -> {
			IO.println("ID: " + category.getId());
			IO.println("Name: " + category.getName());
			IO.println("Icon: " + category.getIcon());
			IO.println("IsDeleted: " + category.getIsDeleted());
			IO.println("============================");
		});
	}

	// Test save data in category table
	@Test
	void test_saveCategory() {
		// 1. Create entity
		Category category = new Category();
		category.setName("Chip M2");
		category.setIcon("default-icon.png");
		category.setDescription("Something about category");
		category.setIsDeleted(false);
		// 2. Start saving the entity
		categoryRepository.save(category);
	}

}
