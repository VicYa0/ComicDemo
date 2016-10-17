package vic.comicdemo.entity;

import java.util.List;

/**
 * Created by Vic Yao on 2016/10/14.
 */

public class ComicContent {

    /**
     * comicName : 火影忍者
     * chapterId : 139833

     */

    private String comicName;
    private int chapterId;
    /**
     * imageUrl : http://imgs.juheapi.com/comic_xin/u/DTsMjM1d8=/139833/0-MTM5ODMzMA==.jpg
     * id : 1
     */

    private List<ImageListBean> imageList;

    public String getComicName() {
        return comicName;
    }

    public void setComicName(String comicName) {
        this.comicName = comicName;
    }

    public int getChapterId() {
        return chapterId;
    }

    public void setChapterId(int chapterId) {
        this.chapterId = chapterId;
    }

    public List<ImageListBean> getImageList() {
        return imageList;
    }

    public void setImageList(List<ImageListBean> imageList) {
        this.imageList = imageList;
    }

    public static class ImageListBean {
        private String imageUrl;
        private int id;

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}

