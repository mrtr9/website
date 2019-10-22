package com.zf.website.bean.dto;

import com.zf.website.bean.Link;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Model:
 * Author:Tr9(韩峰)
 * Description:
 * Time: 2019/9/26 17:28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LinkFormDTO {

    private List<Link> links;
}
