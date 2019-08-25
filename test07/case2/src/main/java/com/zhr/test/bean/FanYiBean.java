package com.zhr.test.bean;

import java.util.List;

/**
 * Created by zhr on 2019/7/26/026.
 * Located:zmkj
 * Des:
 */
public class FanYiBean {


    /**
     * status : 0
     * content : {"ph_en":"tri:","ph_am":"tri","ph_en_mp3":"http://res.iciba.com/resource/amp3/oxford/0/02/60/02604d0d01974b6c3b4be972e0778701.mp3","ph_am_mp3":"http://res.iciba.com/resource/amp3/1/0/c0/af/c0af77cf8294ff93a5cdb2963ca9f038.mp3","ph_tts_mp3":"http://res-tts.iciba.com/c/0/a/c0af77cf8294ff93a5cdb2963ca9f038.mp3","word_mean":["n. 树;木料;树状图;宗谱;","vt. 把\u2026赶上树;使处于困境;把鞋型插入（鞋内）;"]}
     */

    private int status;
    private ContentBean content;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ContentBean getContent() {
        return content;
    }

    public void setContent(ContentBean content) {
        this.content = content;
    }

    public static class ContentBean {
        /**
         * ph_en : tri:
         * ph_am : tri
         * ph_en_mp3 : http://res.iciba.com/resource/amp3/oxford/0/02/60/02604d0d01974b6c3b4be972e0778701.mp3
         * ph_am_mp3 : http://res.iciba.com/resource/amp3/1/0/c0/af/c0af77cf8294ff93a5cdb2963ca9f038.mp3
         * ph_tts_mp3 : http://res-tts.iciba.com/c/0/a/c0af77cf8294ff93a5cdb2963ca9f038.mp3
         * word_mean : ["n. 树;木料;树状图;宗谱;","vt. 把\u2026赶上树;使处于困境;把鞋型插入（鞋内）;"]
         */

        private String ph_en;
        private String ph_am;
        private String ph_en_mp3;
        private String ph_am_mp3;
        private String ph_tts_mp3;
        private List<String> word_mean;

        public String getPh_en() {
            return ph_en;
        }

        public void setPh_en(String ph_en) {
            this.ph_en = ph_en;
        }

        public String getPh_am() {
            return ph_am;
        }

        public void setPh_am(String ph_am) {
            this.ph_am = ph_am;
        }

        public String getPh_en_mp3() {
            return ph_en_mp3;
        }

        public void setPh_en_mp3(String ph_en_mp3) {
            this.ph_en_mp3 = ph_en_mp3;
        }

        public String getPh_am_mp3() {
            return ph_am_mp3;
        }

        public void setPh_am_mp3(String ph_am_mp3) {
            this.ph_am_mp3 = ph_am_mp3;
        }

        public String getPh_tts_mp3() {
            return ph_tts_mp3;
        }

        public void setPh_tts_mp3(String ph_tts_mp3) {
            this.ph_tts_mp3 = ph_tts_mp3;
        }

        public List<String> getWord_mean() {
            return word_mean;
        }

        public void setWord_mean(List<String> word_mean) {
            this.word_mean = word_mean;
        }
    }
}
