package com.ps.skin.constant;

/**
 * 项目公共枚举类
 *
 * @author liuhj
 * @date 2020/05/21 17:28
 */
public class AiSkinEnum {

    /**
     * 后台用户状态 0-正常  1-锁定 2-删除
     */
    public enum status {
        /**
         * 0-正常  1-锁定 2-删除
         */
        NORMAL(0, "正常"), LOCKED(1, "锁定"), deleted(2, "删除");

        private Integer code;
        private String name;

        status(Integer code, String name) {
            this.code = code;
            this.name = name;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }
}
