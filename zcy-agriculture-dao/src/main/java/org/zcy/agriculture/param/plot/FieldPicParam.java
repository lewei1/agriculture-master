package org.zcy.agriculture.param.plot;

import org.zcy.agriculture.entity.TbPlotFieldPic;
import org.zcy.agriculture.vo.BaseVo;

import java.util.List;

public class FieldPicParam extends BaseVo {

    private Long plotId;

    private List<TbPlotFieldPic> fieldPicList;

    public Long getPlotId() {
        return plotId;
    }

    public void setPlotId(Long plotId) {
        this.plotId = plotId;
    }

    public List<TbPlotFieldPic> getFieldPicList() {
        return fieldPicList;
    }

    public void setFieldPicList(List<TbPlotFieldPic> fieldPicList) {
        this.fieldPicList = fieldPicList;
    }
}
