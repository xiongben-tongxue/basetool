package com.gws.resp;

import lombok.Data;

/**
 * @author:wangdong
 * @description:
 */
@Data
public class FaceIdentityResp {

    private String platformtoken;

    private Boolean posphone;

    private Boolean handphone;
}
