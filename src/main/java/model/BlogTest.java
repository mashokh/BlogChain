package model;

import junit.framework.TestCase;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BlogTest extends TestCase {

    private Blog blog;
    LocalDate date = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String localDate = formatter.format(date);
    int created_by;
    int id;
    int category_id;


    @Override
    protected void setUp() throws Exception {
        created_by = 2;
        id = 1;
        category_id = 1;
        blog = new Blog(id, "cs", "counter strike", created_by, localDate, category_id);
    }


    public void testBlogId() {
        assertEquals(blog.getId(), 1);

        blog.setId(2);
        assertEquals(blog.getId(), 2);

        blog.setId(1);
        assertEquals(blog.getId(), 1);

    }

    public void testTitle() {
        assertEquals(blog.getTitle(), "cs");
        blog.setTitle("lol");
        assertEquals(blog.getTitle(), "lol");
        blog.setTitle("cs");
        assertEquals(blog.getTitle(), "cs");
    }

    public void testText() {
        assertEquals(blog.getText(), "counter strike");
        blog.setText("lol");
        assertEquals(blog.getText(), "lol");
        blog.setText("counter strike");
        assertEquals(blog.getText(), "counter strike");
    }

    public void testCreatedBy() {
        assertEquals(blog.getCreated_by(), 2);
        blog.setCreated_by(1);
        assertEquals(blog.getCreated_by(), 1);
        blog.setCreated_by(2);
        assertEquals(blog.getCreated_by(), 2);
    }

    public void testCreatedAt() {
        assertEquals(blog.getCreated_at(), localDate);
        blog.setCreated_at("2020-15-08");
        assertEquals(blog.getCreated_at(), "2020-15-08");
        blog.setCreated_at(localDate);
        assertEquals(blog.getCreated_at(), localDate);
    }

    public void testCategoryId() {
        assertEquals(blog.getCategory_id(), 1);
        blog.setCategory_id(2);
        assertEquals(blog.getCategory_id(), 2);
        blog.setCategory_id(1);
        assertEquals(blog.getCategory_id(), 1);
    }

    public void testTruncatedText(){
        assertEquals(blog.getTruncatedText(), "counter strike");
        blog.setText("1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31");
        assertEquals("1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 ...", blog.getTruncatedText());
        blog.setText("counter strike");
    }

    public void testAddBlog(){
        BlogsDao.addBlog(blog.getTitle(), blog.getText(), blog.getCreated_by(), blog.getCreated_at(), blog.getCategory_id());
    }

    public void testGetsInDao(){
        assertNotNull(BlogsDao.getBlogsByCategoryId(category_id));
        assertNotNull(BlogsDao.getBlogsByUserId(created_by));
    }

}
