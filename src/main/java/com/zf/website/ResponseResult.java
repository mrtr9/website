package com.zf.website;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author yc
 * @version 1.0
 * @project IdeaProjects
 * @date 2019-07-03 14:48
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseResult<T> implements Serializable {

    private Integer code;
    private  String message;
    private T data;

}
