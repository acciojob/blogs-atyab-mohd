package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog
        Image image = new Image();
        Blog blog = blogRepository2.findById(blogId).get();
        image.setBlog(blog);
        image.setDescription(description);
        image.setDimensions(dimensions);
        blog.getImageList().add(image);
        blogRepository2.save(blog);
        return image;
    }

    public void deleteImage(Integer id){
        deleteImage(id);

    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`

        Image image = imageRepository2.findById(id).get();
        String []imageArr = image.getDimensions().split("X");
        //imageArr = image.getDimensions().split("X");
        int x1 = Integer.parseInt(imageArr[0]);
        int y1 = Integer.parseInt(imageArr[1]);
        int imageArea = x1*y1;
        String []screenArr = screenDimensions.split("X");
        //screenArr = screenDimensions.split("X");
        int x2 = Integer.parseInt(screenArr[0]);
        int y2 = Integer.parseInt(screenArr[1]);
        int screenArea = x2*y2;
        int count = 0;
        count = screenArea/imageArea;
        return count;
    }
}
