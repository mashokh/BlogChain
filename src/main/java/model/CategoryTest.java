package model;


import org.junit.*;



public class CategoryTest {

    private static Category category;

    @BeforeClass
    public static void setUp() throws Exception {
        category = new Category(1, "test", false);
        if (!CategoryDao.categoryExists("test")) CategoryDao.suggestCategory("test");
    }

    @Test
    public void testId() {
        Assert.assertEquals(category.getId(), 1);

        category.setId(2);
        Assert.assertEquals(category.getId(), 2);

        category.setId(1);
        Assert.assertEquals(category.getId(), 1);
    }

    @Test
    public void testName() {
        Assert.assertEquals(category.getName(), "test");

        category.setName("awesome");
        Assert.assertEquals(category.getName(), "awesome");

        category.setName("test");
        Assert.assertEquals(category.getName(), "test");
    }

    @Test
    public void testApproved() {
        Assert.assertFalse(category.isApproved());

        category.setApproved(true);
        Assert.assertTrue(category.isApproved());

        category.setApproved(false);
        Assert.assertFalse(category.isApproved());

        CategoryDao.changeCategoryStatus("test", true);
    }

    @Test
    public void testDaoGetters() {
        CategoryDao.getCategories(true);
        int id = CategoryDao.getCategoryIdByName("test");
        CategoryDao.getCategoryNameById(id);
    }

    @AfterClass
    public static void tearDown() throws Exception {
        CategoryDao.deleteCategory("test");
    }
}
