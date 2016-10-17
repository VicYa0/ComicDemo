package vic.comicdemo.entity;

import java.util.List;

/**
 * Created by Vic Yao on 2016/10/14.
 */

public class ChapterResult {

    /**
     * total : 535
     * comicName : 火影忍者
     * chapterList : [{"name":"第33卷","id":139877},{"name":"第34卷","id":139879},{"name":"第35卷","id":139880},{"name":"第36卷","id":139882},{"name":"第37卷","id":139883},{"name":"第38卷","id":139885},{"name":"第39卷","id":139887},{"name":"第40卷","id":139888},{"name":"第41卷","id":139889},{"name":"第42卷","id":139890},{"name":"第43卷","id":139891},{"name":"第44卷","id":139892},{"name":"第45卷","id":139893},{"name":"第46卷","id":139894},{"name":"第47卷","id":139895},{"name":"第48卷","id":139896},{"name":"第49卷","id":139897},{"name":"第50卷","id":139898},{"name":"第51卷","id":139899},{"name":"第52卷","id":139900}]
     * limit : 20
     * skip : 30
     */

    private int total;
    private String comicName;
    /**
     * name : 第33卷
     * id : 139877
     */

    private List<ChapterListBean> chapterList;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getComicName() {
        return comicName;
    }

    public void setComicName(String comicName) {
        this.comicName = comicName;
    }

    public List<ChapterListBean> getChapterList() {
        return chapterList;
    }

    public void setChapterList(List<ChapterListBean> chapterList) {
        this.chapterList = chapterList;
    }

    public static class ChapterListBean {
        private String name;
        private int id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
