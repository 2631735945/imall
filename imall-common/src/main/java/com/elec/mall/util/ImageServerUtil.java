package com.elec.mall.util;

public class ImageServerUtil {
    public static final int QINIU = 0;
    public static final int LOCAL = 1;

    public static final int IMG_SERVER = QINIU;

    public static final String IMG_SERVER_LOCAL = "/pic/";
    public static final String IMG_SERVER_QINIU = "http://rh4f7wh1f.hd-bkt.clouddn.com/";

    /**
     * 根据当前的图片服务器，拼接完整的图片的访问路径
     * @param fileName 文件的名字，不包含路径
     * @return
     */
    public static String getImageUrl(String fileName) {
        if (IMG_SERVER == LOCAL) {
            return IMG_SERVER_LOCAL + fileName;
        } else {
            return IMG_SERVER_QINIU + fileName;
        }
    }
}
