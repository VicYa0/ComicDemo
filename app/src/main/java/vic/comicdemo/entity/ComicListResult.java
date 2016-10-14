package vic.comicdemo.entity;

import java.util.List;

/**
 * Created by Vic Yao on 2016/10/14.
 */

public class ComicListResult {

    /**
     * total : 15767
     * limit : 20
     * bookList :
     */

    private int total;
    private int limit;
    /**
     * name : 好漫画
     * type : 少年漫画
     * area : 国漫
     * des :
     * finish : false
     * lastUpdate : 20150406
     * coverImg : http://imgs.juheapi.com/comic_xin/5559b86938f275fd560ad617.jpg
     */

    private List<BookListBean> bookList;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public List<BookListBean> getBookList() {
        return bookList;
    }

    public void setBookList(List<BookListBean> bookList) {
        this.bookList = bookList;
    }

    public static class BookListBean {
        private String name;
        private String type;
        private String area;
        private String des;
        private boolean finish;
        private int lastUpdate;
        private String coverImg;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public boolean isFinish() {
            return finish;
        }

        public void setFinish(boolean finish) {
            this.finish = finish;
        }

        public int getLastUpdate() {
            return lastUpdate;
        }

        public void setLastUpdate(int lastUpdate) {
            this.lastUpdate = lastUpdate;
        }

        public String getCoverImg() {
            return coverImg;
        }

        public void setCoverImg(String coverImg) {
            this.coverImg = coverImg;
        }
    }
}
