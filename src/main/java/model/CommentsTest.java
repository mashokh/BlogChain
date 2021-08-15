package model;

import junit.framework.TestCase;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CommentsTest extends TestCase {

    private Comments comment;
    LocalDate date = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String localDate = formatter.format(date);

    @Override
    public void setUp() {
        comment = new Comments(1, 1, "Creating new Comment", localDate, 0);
        comment.setComment_id(1);
    }

    public void testCommentId() {
        assertEquals(comment.getComment_id(), 1);

        comment.setComment_id(2);
        assertEquals(comment.getComment_id(), 2);

        comment.setComment_id(1);
        assertEquals(comment.getComment_id(), 1);
    }

    public void testBlogId() {
        assertEquals(comment.getBlog_id(), 1);

        comment.setBlog_id(2);
        assertEquals(comment.getBlog_id(), 2);

        comment.setBlog_id(1);
        assertEquals(comment.getBlog_id(), 1);
    }

    public void testUserId() {
        assertEquals(comment.getUser_id(), 1);

        comment.setUser_id(2);
        assertEquals(comment.getUser_id(), 2);

        comment.setUser_id(1);
        assertEquals(comment.getUser_id(), 1);
    }

    public void testCreatedAt() {
        assertEquals(comment.getCreated_at(), localDate);
        comment.setCreated_at("2001-15-08");
        assertEquals(comment.getCreated_at(), "2001-15-08");
        comment.setCreated_at(localDate);
        assertEquals(comment.getCreated_at(), localDate);
    }

    public void testText() {
        assertEquals(comment.getText(), "Creating new Comment");
        comment.setText("Change comment");
        assertEquals(comment.getText(), "Change comment");
        comment.setText("This is a good post");
        assertEquals(comment.getText(), "This is a good post");
    }

    public void testLikes() {
        assertEquals(comment.getLikes(), 0);
        comment.setLikes(5);
        assertEquals(comment.getLikes(), 5);
        comment.setLikes(-5);
        assertEquals(comment.getLikes(), -5);
    }
}
