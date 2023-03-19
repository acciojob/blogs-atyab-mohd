package com.driver.services;

import com.driver.models.Blog;
import com.driver.models.Image;
import com.driver.models.User;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.ImageRepository;
import com.driver.repositories.UserRepository;
import org.apache.tomcat.jni.Time;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.attribute.standard.DateTimeAtCreation;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class BlogService {
    @Autowired
    BlogRepository blogRepository1;

    @Autowired
    UserRepository userRepository1;

    public Blog createAndReturnBlog(Integer userId, String title, String content) {
        //create a blog at the current time
        //List<Blog> blogs = blogRepository1.findById(userId).get();

        User user = userRepository1.findById(userId).get();
        Blog blog = new Blog();
        blog.setUser(user);
        blog.setTitle(title);
        blog.setContent(content);
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        blog.setPubDate(date);
        user.getBlogs().add(blog);
        userRepository1.save(user);
        return blog;
    }

    public void deleteBlog(int blogId){
        //delete blog and corresponding images
        blogRepository1.deleteById(blogId);
    }
}
