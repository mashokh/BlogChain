package model;

import junit.framework.TestCase;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BlogTest extends TestCase {

    private Blog blog;
    LocalDate date = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String localDate = formatter.format(date);

    @Override
    protected void setUp() throws Exception {

        blog = new Blog(1, "cs", "counter strike", 2, localDate, 1);
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
        blog.setText("cs cs cs cs cs cs cs cs cs cs cs cs");
        assertEquals("cs cs cs cs cs cs cs cs cs cs ...", blog.getTruncatedText());
        blog.setText("counter strike");
    }
}
