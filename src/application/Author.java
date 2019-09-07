package application;

/**
 *
 * @author Gurmeet Singh, gsdhillon@gmail.com
 */
public class Author{
    private final String authorName = "Gurmeet Singh";
    private final String authorEMailID = "gsdhillon@gmail.com";

    public Author() {
    }

    @Override
    public String toString() {
        return authorName + ", " + authorEMailID;
    }
    
}
