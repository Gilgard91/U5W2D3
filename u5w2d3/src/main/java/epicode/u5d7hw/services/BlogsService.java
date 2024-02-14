package epicode.u5d7hw.services;

import epicode.u5d7hw.entities.Blogpost;
import epicode.u5d7hw.exceptions.NotFoundException;
import epicode.u5d7hw.repositories.BlogPostsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class BlogsService {

    @Autowired
    BlogPostsDAO blogPostsDAO;

//    private final List<Blogpost> blogs = new ArrayList<>();

    public Blogpost save(Blogpost blogpost) {
//        Random rndm = new Random();
//        blogpost.setId(rndm.nextInt());
//        blogpost.setCover("https://picsum.photos/200/300");
//        this.blogs.add(blogpost);
//        return blogpost;

        return blogPostsDAO.save(blogpost);
    }

//    public List<Blogpost> getBlogs() {
//        return this.blogs;
//    }

    public Page<Blogpost> getBlogPosts(int pageNumber, int size, String orderBy){
        Pageable pageable = PageRequest.of(pageNumber, size, Sort.by(orderBy));
        return blogPostsDAO.findAll(pageable);
    }

    public Blogpost findById(Long id) {
        return blogPostsDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void findByIdAndDelete(Long id) {
        Blogpost found = this.findById(id);
        blogPostsDAO.delete(found);
        }


//    public Blogpost findByIdAndUpdate(int id, Blogpost body) {
//        Blogpost found = null;
//
//        for (Blogpost currentBlog : blogs) {
//            if (currentBlog.getId() == id) {
//                found = currentBlog;
//                found.setCover(body.getCover());
//                found.setCategory(body.getCategory());
//                found.setContent(body.getCover());
//                found.setReadingTime(body.getReadingTime());
//                found.setId(id);
//            }
//        }
//        if (found == null)
//            throw new NotFoundException(id);
//        return found;
//
//    }

    public Blogpost findByIdAndUpdate(Long id, Blogpost body){
        Blogpost found = this.findById(id);
        found.setCategory(body.getCategory());
        found.setTitle(body.getTitle());
        found.setCover(body.getCover());
        found.setContent(body.getContent());
        found.setReadingTime(body.getReadingTime());
        return blogPostsDAO.save(found);
    }
}
