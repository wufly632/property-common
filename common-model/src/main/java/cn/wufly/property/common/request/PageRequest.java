package cn.wufly.property.common.request;

import lombok.Data;
@Data
public class PageRequest extends BaseRequest{
    private static final long serialVersionUID = 6883327671064882382L;

    private int pageSize = 10;

    private int pageIndex = 1;
}
