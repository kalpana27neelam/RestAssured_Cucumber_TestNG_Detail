package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.testng.annotations.Test;

/*
 Post.java is a plain java class matching the shape of a JSONPlaceholder post  -- this is what JSON gets
 converted into (deserialization) or converted from (serialization)
 */

/*
ignoreUnknown = true makes deserialization resilient if the API ever
adds a field , this class doesn't know about - it just gets skipped
instead of throwing an error
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Post {

    private Integer id;
    private Integer userId;
    private String title;
    private String body;

    public Post()
    {
        //Jackson requires a no-arg constructor to build the object
        //before filling in fields via the setters below.
    }

    public Post(Integer userId,String title,String body)
    {
        this.userId=userId;
        this.title=title;
        this.body=body;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id=id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title=title;
    }

    public String getBody()
    {
        return body;
    }

    public void setBody(String body)
    {
        this.body=body;
    }

    @Override
    public String toString()
    {
        return "Post { id =" +id+ ",userid ="+userId+ ", title ='"+title+" ', body ='"+body+"'}";
    }

}
