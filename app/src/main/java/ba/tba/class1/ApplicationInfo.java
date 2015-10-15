package ba.tba.class1;

/**
 * Created by jackblack on 10/12/15.
 */
public class ApplicationInfo {
    private String Publisher;
    private String Post;
    private String Location;
    private int imageFileNameId;


    ApplicationInfo( String Publisher, String Post, String Location, int imageFileNameId){
        this.setPublisher(Publisher);
        this.setPost(Post);
        this.setLocation(Location);
        this.setImageFileNameId(imageFileNameId);
    }

    public String getPublisher() {
        return Publisher;
    }

    public void setPublisher(String publisher) {
        Publisher = publisher;
    }

    public String getPost() {
        return Post;
    }

    public void setPost(String post) {
        Post = post;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public int getImageFileNameId() {
        return imageFileNameId;
    }

    public void setImageFileNameId(int imageFileNameId) {
        this.imageFileNameId = imageFileNameId;
    }
}
