package com.example.test.mynewapptwo.model;

import java.util.List;

/**
 * Created by 11942 on 2017/11/15.
 */

public class TupianBean {

    /**
     * showapi_res_code : 0
     * showapi_res_error :
     * showapi_res_body : {"newslist":[{"title":"秋色","picUrl":"http://image.hnol.net/c/2017-11/15/06/201711150650031711-239867.gif","description":"华声美女","ctime":"2017-11-15 09:00","url":"http://bbs.voc.com.cn/mm/meinv-8109325-0-1.html"},{"title":"雨中红裙【转载】","picUrl":"http://image.hnol.net/c/2017-11/14/10/20171114101505841-3356886.jpg","description":"华声美女","ctime":"2017-11-14 11:00","url":"http://bbs.voc.com.cn/mm/meinv-8107756-0-1.html"},{"title":"[贴图]克拉女神-小西《微笑の天使》","picUrl":"http://image.hnol.net/c/2017-11/13/11/201711131141247541-5794010.jpg","description":"华声美女","ctime":"2017-11-13 12:00","url":"http://bbs.voc.com.cn/mm/meinv-8106078-0-1.html"},{"title":"青春年华-117","picUrl":"http://image.hnol.net/c/2017-11/13/07/201711130712215181-239867.jpg","description":"华声美女","ctime":"2017-11-13 09:01","url":"http://bbs.voc.com.cn/mm/meinv-8105645-0-1.html"},{"title":"倩倩","picUrl":"http://image.hnol.net/c/2017-11/13/07/201711130716221411-239867.gif","description":"华声美女","ctime":"2017-11-13 09:01","url":"http://bbs.voc.com.cn/mm/meinv-8105652-0-1.html"},{"title":"玫瑰","picUrl":"http://image.hnol.net/c/2017-11/11/17/20171111172516111-239867.gif","description":"华声美女","ctime":"2017-11-11 19:00","url":"http://bbs.voc.com.cn/mm/meinv-8104690-0-1.html"},{"title":"青春年华-116","picUrl":"http://image.hnol.net/c/2017-11/11/17/20171111171600551-239867.jpg","description":"华声美女","ctime":"2017-11-11 18:00","url":"http://bbs.voc.com.cn/mm/meinv-8104681-0-1.html"},{"title":"文静旗袍【转载】","picUrl":"http://image.hnol.net/c/2017-11/10/09/201711100929019571-3356886.jpg","description":"华声美女","ctime":"2017-11-11 07:00","url":"http://bbs.voc.com.cn/mm/meinv-8102730-0-1.html"},{"title":"韩国车模李美来","picUrl":"http://image.hnol.net/c/2017-11/09/13/201711091312457481-5058976.jpg","description":"华声美女","ctime":"2017-11-10 12:00","url":"http://bbs.voc.com.cn/mm/meinv-8101304-0-1.html"},{"title":"咖啡屋女孩【转载】","picUrl":"http://image.hnol.net/c/2017-11/10/09/2017111009435221-3356886.jpg","description":"华声美女","ctime":"2017-11-10 10:00","url":"http://bbs.voc.com.cn/mm/meinv-8102762-0-1.html"}],"code":200,"msg":"success"}
     */

    private int showapi_res_code;
    private String showapi_res_error;
    private ShowapiResBodyBean showapi_res_body;

    public int getShowapi_res_code() {
        return showapi_res_code;
    }

    public void setShowapi_res_code(int showapi_res_code) {
        this.showapi_res_code = showapi_res_code;
    }

    public String getShowapi_res_error() {
        return showapi_res_error;
    }

    public void setShowapi_res_error(String showapi_res_error) {
        this.showapi_res_error = showapi_res_error;
    }

    public ShowapiResBodyBean getShowapi_res_body() {
        return showapi_res_body;
    }

    public void setShowapi_res_body(ShowapiResBodyBean showapi_res_body) {
        this.showapi_res_body = showapi_res_body;
    }

    public static class ShowapiResBodyBean {
        /**
         * newslist : [{"title":"秋色","picUrl":"http://image.hnol.net/c/2017-11/15/06/201711150650031711-239867.gif","description":"华声美女","ctime":"2017-11-15 09:00","url":"http://bbs.voc.com.cn/mm/meinv-8109325-0-1.html"},{"title":"雨中红裙【转载】","picUrl":"http://image.hnol.net/c/2017-11/14/10/20171114101505841-3356886.jpg","description":"华声美女","ctime":"2017-11-14 11:00","url":"http://bbs.voc.com.cn/mm/meinv-8107756-0-1.html"},{"title":"[贴图]克拉女神-小西《微笑の天使》","picUrl":"http://image.hnol.net/c/2017-11/13/11/201711131141247541-5794010.jpg","description":"华声美女","ctime":"2017-11-13 12:00","url":"http://bbs.voc.com.cn/mm/meinv-8106078-0-1.html"},{"title":"青春年华-117","picUrl":"http://image.hnol.net/c/2017-11/13/07/201711130712215181-239867.jpg","description":"华声美女","ctime":"2017-11-13 09:01","url":"http://bbs.voc.com.cn/mm/meinv-8105645-0-1.html"},{"title":"倩倩","picUrl":"http://image.hnol.net/c/2017-11/13/07/201711130716221411-239867.gif","description":"华声美女","ctime":"2017-11-13 09:01","url":"http://bbs.voc.com.cn/mm/meinv-8105652-0-1.html"},{"title":"玫瑰","picUrl":"http://image.hnol.net/c/2017-11/11/17/20171111172516111-239867.gif","description":"华声美女","ctime":"2017-11-11 19:00","url":"http://bbs.voc.com.cn/mm/meinv-8104690-0-1.html"},{"title":"青春年华-116","picUrl":"http://image.hnol.net/c/2017-11/11/17/20171111171600551-239867.jpg","description":"华声美女","ctime":"2017-11-11 18:00","url":"http://bbs.voc.com.cn/mm/meinv-8104681-0-1.html"},{"title":"文静旗袍【转载】","picUrl":"http://image.hnol.net/c/2017-11/10/09/201711100929019571-3356886.jpg","description":"华声美女","ctime":"2017-11-11 07:00","url":"http://bbs.voc.com.cn/mm/meinv-8102730-0-1.html"},{"title":"韩国车模李美来","picUrl":"http://image.hnol.net/c/2017-11/09/13/201711091312457481-5058976.jpg","description":"华声美女","ctime":"2017-11-10 12:00","url":"http://bbs.voc.com.cn/mm/meinv-8101304-0-1.html"},{"title":"咖啡屋女孩【转载】","picUrl":"http://image.hnol.net/c/2017-11/10/09/2017111009435221-3356886.jpg","description":"华声美女","ctime":"2017-11-10 10:00","url":"http://bbs.voc.com.cn/mm/meinv-8102762-0-1.html"}]
         * code : 200
         * msg : success
         */

        private int code;
        private String msg;
        private List<NewslistBean> newslist;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public List<NewslistBean> getNewslist() {
            return newslist;
        }

        public void setNewslist(List<NewslistBean> newslist) {
            this.newslist = newslist;
        }

        public static class NewslistBean {
            /**
             * title : 秋色
             * picUrl : http://image.hnol.net/c/2017-11/15/06/201711150650031711-239867.gif
             * description : 华声美女
             * ctime : 2017-11-15 09:00
             * url : http://bbs.voc.com.cn/mm/meinv-8109325-0-1.html
             */

            private String title;
            private String picUrl;
            private String description;
            private String ctime;
            private String url;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getPicUrl() {
                return picUrl;
            }

            public void setPicUrl(String picUrl) {
                this.picUrl = picUrl;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getCtime() {
                return ctime;
            }

            public void setCtime(String ctime) {
                this.ctime = ctime;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
