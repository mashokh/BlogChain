package model;

import org.junit.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class CommentsTest{

    private static Comments comment;
    private static Comments commentDT;
    private static int currComment;
    private static LocalDate date = LocalDate.now();
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static String localDate = formatter.format(date);
    private static int category;
    private static Blog blog;
    private static User user;

    @BeforeClass
    public static void setUp(){
        comment = new Comments(1,1, "Creating new Comment", localDate, 0);
        comment.setComment_id(1);
        assertTrue(UserDAO.addUser(new User(1, "test", "123", "black", true)) > 0);
        user = UserDAO.getUserById(UserDAO.getIdByUsername("test"));
        if (!CategoryDao.categoryExists("test")) CategoryDao.suggestCategory("test");
        category = CategoryDao.getCategoryIdByName("test");
        CategoryDao.changeCategoryStatus("test", true);
        BlogsDao.addBlog("Blog Title", "Blog text", user.getId(), localDate, category);
        blog = BlogsDao.getBlogsByCategoryId(category).get(0);
        commentDT = new Comments(blog.getId(), user.getId(), "Comment", localDate, 0);
    }

    @Test
    public void testInsertComment() {
        commentDT.setComment_id(CommentsDao.insertComment(commentDT));
        assertNotNull(CommentsDao.getCommentById(commentDT.getComment_id()));
        assertEquals(CommentsDao.getCommentById(commentDT.getComment_id()).getText(), "Comment");
        currComment = commentDT.getComment_id();
    }

    @Test
    public void testGetCommentsByBlog() {
        ArrayList<Comments> comments = CommentsDao.getCommentsByBlogId(blog.getId());
        assertEquals(comments.get(0).getText(), "Comment");
    }

    @Test
    public void testSimpleCommentLike() {
        assertEquals(CommentsDao.getCommentRate(currComment), 0);
        assertFalse(CommentsDao.userLiked(currComment, user.getId()));
        assertFalse(CommentsDao.userDisliked(currComment, user.getId()));

        CommentsDao.likeComment(currComment, user.getId());
        assertTrue(CommentsDao.userLiked(currComment, user.getId()));
        assertFalse(CommentsDao.userDisliked(currComment, user.getId()));
        assertEquals(CommentsDao.getCommentRate(currComment), 1);

        CommentsDao.removeLike(currComment, user.getId());
        assertFalse(CommentsDao.userLiked(currComment, user.getId()));
        assertFalse(CommentsDao.userDisliked(currComment, user.getId()));
        assertEquals(CommentsDao.getCommentRate(currComment), 0);

        CommentsDao.dislikeComment(currComment, user.getId());
        assertFalse(CommentsDao.userLiked(currComment, user.getId()));
        assertTrue(CommentsDao.userDisliked(currComment, user.getId()));
        assertEquals(CommentsDao.getCommentRate(currComment), -1);

        CommentsDao.removeDislike(currComment, user.getId());
        assertFalse(CommentsDao.userLiked(currComment, user.getId()));
        assertFalse(CommentsDao.userDisliked(currComment, user.getId()));
        assertEquals(CommentsDao.getCommentRate(currComment), 0);
    }

    @Test
    public void testComplexCommentLike() {
        assertEquals(CommentsDao.getCommentRate(currComment), 0);
        assertFalse(CommentsDao.userLiked(currComment, user.getId()));
        assertFalse(CommentsDao.userDisliked(currComment, user.getId()));

        CommentsDao.likeCommentLogic(currComment, user.getId());
        assertTrue(CommentsDao.userLiked(currComment, user.getId()));
        assertFalse(CommentsDao.userDisliked(currComment, user.getId()));
        assertEquals(CommentsDao.getCommentRate(currComment), 1);

        CommentsDao.likeCommentLogic(currComment, user.getId());
        assertFalse(CommentsDao.userLiked(currComment, user.getId()));
        assertFalse(CommentsDao.userDisliked(currComment, user.getId()));
        assertEquals(CommentsDao.getCommentRate(currComment), 0);

        CommentsDao.dislikeCommentLogic(currComment, user.getId());
        assertFalse(CommentsDao.userLiked(currComment, user.getId()));
        assertTrue(CommentsDao.userDisliked(currComment, user.getId()));
        assertEquals(CommentsDao.getCommentRate(currComment), -1);

        CommentsDao.dislikeCommentLogic(currComment, user.getId());
        assertFalse(CommentsDao.userLiked(currComment, user.getId()));
        assertFalse(CommentsDao.userDisliked(currComment, user.getId()));
        assertEquals(CommentsDao.getCommentRate(currComment), 0);

        CommentsDao.likeCommentLogic(currComment, user.getId());
        assertTrue(CommentsDao.userLiked(currComment, user.getId()));
        assertFalse(CommentsDao.userDisliked(currComment, user.getId()));
        assertEquals(CommentsDao.getCommentRate(currComment), 1);

        CommentsDao.dislikeCommentLogic(currComment, user.getId());
        assertFalse(CommentsDao.userLiked(currComment, user.getId()));
        assertTrue(CommentsDao.userDisliked(currComment, user.getId()));
        assertEquals(CommentsDao.getCommentRate(currComment), -1);

        CommentsDao.dislikeCommentLogic(currComment, user.getId());
        assertFalse(CommentsDao.userLiked(currComment, user.getId()));
        assertFalse(CommentsDao.userDisliked(currComment, user.getId()));
        assertEquals(CommentsDao.getCommentRate(currComment), 0);
    }

    @Test
    public void testCommentId() {
        assertEquals(comment.getComment_id(), 1);

        comment.setComment_id(2);
        assertEquals(comment.getComment_id(), 2);

        comment.setComment_id(1);
        assertEquals(comment.getComment_id(), 1);
    }

    @Test
    public void testBlogId() {
        assertEquals(comment.getBlog_id(), 1);

        comment.setBlog_id(2);
        assertEquals(comment.getBlog_id(), 2);

        comment.setBlog_id(1);
        assertEquals(comment.getBlog_id(), 1);
    }

    @Test
    public void testUserId() {
        assertEquals(comment.getUser_id(), 1);

        comment.setUser_id(2);
        assertEquals(comment.getUser_id(), 2);

        comment.setUser_id(1);
        assertEquals(comment.getUser_id(), 1);
    }

    @Test
    public void testCreatedAt() {
        assertEquals(comment.getCreated_at(), localDate);
        comment.setCreated_at("2001-15-08");
        assertEquals(comment.getCreated_at(), "2001-15-08");
        comment.setCreated_at(localDate);
        assertEquals(comment.getCreated_at(), localDate);
    }

    @Test
    public void testText() {
        assertEquals(comment.getText(), "Creating new Comment");
        comment.setText("Change comment");
        assertEquals(comment.getText(), "Change comment");
        comment.setText("This is a good post");
        assertEquals(comment.getText(), "This is a good post");
    }

    @Test
    public void testLikes() {
        assertEquals(comment.getLikes(), 0);
        comment.setLikes(5);
        assertEquals(comment.getLikes(), 5);
        comment.setLikes(-5);
        assertEquals(comment.getLikes(), -5);
    }

    @Test
    public void testRemoveComment() {
        CommentsDao.deleteCommentByCommentId(currComment);
        assertNull(CommentsDao.getCommentById(currComment));
        commentDT.setComment_id(CommentsDao.insertComment(commentDT));
        assertNotNull(CommentsDao.getCommentById(commentDT.getComment_id()));
        assertEquals(CommentsDao.getCommentById(commentDT.getComment_id()).getText(), "Comment");
        currComment = commentDT.getComment_id();
    }

    @AfterClass
    public static void tearDown(){
        UserDAO.deleteUser(user.getId());
        CategoryDao.deleteCategory("test");
    }
}
