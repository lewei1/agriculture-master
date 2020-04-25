package org.zcy.agriculture.service.impl.plot;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zcy.agriculture.entity.TbPlotFieldPic;
import org.zcy.agriculture.mapper.plot.TbPlotFieldPicMapper;
import org.zcy.agriculture.page.Convert;
import org.zcy.agriculture.param.plot.FieldPicParam;
import org.zcy.agriculture.service.plot.ITbPlotFieldPicService;
import org.zcy.agriculture.util.ValidationUtil;

/**
 * 地块对应实地图片 服务层实现
 *
 * @author numberone
 * @date 2019-06-25
 */
@Service
public class TbPlotFieldPicServiceImpl implements ITbPlotFieldPicService {
    @Autowired
    private TbPlotFieldPicMapper tbPlotFieldPicMapper;

    /**
     * 查询地块对应实地图片信息
     *
     * @param fieldPicId 地块对应实地图片ID
     * @return 地块对应实地图片信息
     */
    @Override
    public TbPlotFieldPic selectTbPlotFieldPicById(Long fieldPicId) {
        return tbPlotFieldPicMapper.selectTbPlotFieldPicById(fieldPicId);
    }

    /**
     * 查询地块对应实地图片列表
     *
     * @param tbPlotFieldPic 地块对应实地图片信息
     * @return 地块对应实地图片集合
     */
    @Override
    public List<TbPlotFieldPic> selectTbPlotFieldPicList(TbPlotFieldPic tbPlotFieldPic) {
        return tbPlotFieldPicMapper.selectTbPlotFieldPicList(tbPlotFieldPic);
    }

    /**
     * 新增地块对应实地图片
     *
     * @param tbPlotFieldPic 地块对应实地图片信息
     * @return 结果
     */
    @Override
    public int insertTbPlotFieldPic(TbPlotFieldPic tbPlotFieldPic) {
        return tbPlotFieldPicMapper.insertTbPlotFieldPic(tbPlotFieldPic);
    }

    /**
     * 修改地块对应实地图片
     *
     * @param tbPlotFieldPic 地块对应实地图片信息
     * @return 结果
     */
    @Override
    public int updateTbPlotFieldPic(TbPlotFieldPic tbPlotFieldPic) {
        return tbPlotFieldPicMapper.updateTbPlotFieldPic(tbPlotFieldPic);
    }

    /**
     * 删除地块对应实地图片对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTbPlotFieldPicByIds(String ids) {
        return tbPlotFieldPicMapper.deleteTbPlotFieldPicByIds(Convert.toStrArray(ids));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int insertTbPlotFieldPicList(FieldPicParam fieldPicParam) {
        int result = -1;
        //删除所有
        if(!ValidationUtil.isEmpty(fieldPicParam.getPlotId())) {
            result = tbPlotFieldPicMapper.deleteTbPlotFieldPicByPlotId(fieldPicParam.getPlotId());
        }else {
            return result;
        }

        List<TbPlotFieldPic> fieldPicList = fieldPicParam.getFieldPicList();
        if(!ValidationUtil.isEmpty(fieldPicList)) {
            //添加plotId
            for(TbPlotFieldPic fieldPic : fieldPicParam.getFieldPicList()) {
                fieldPic.setPlotId(fieldPicParam.getPlotId());
            }
            result = tbPlotFieldPicMapper.insertTbPlotFieldPicList(fieldPicParam.getFieldPicList());
        }
        return result;
    }

    @Override
    public int deleteTbPlotFieldPicByPlotId(Long plotId) {
        return tbPlotFieldPicMapper.deleteTbPlotFieldPicByPlotId(plotId);
    }

}
