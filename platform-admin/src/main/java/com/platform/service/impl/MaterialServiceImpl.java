package com.platform.service.impl;

import com.platform.dao.MaterialMapper;
import com.platform.entity.MaterialEntity;
import com.platform.service.MaterialService;
import com.platform.utils.excelutils.ExcelUtil;
import com.platform.utils.excelutils.MultipleSheelPropety;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.alibaba.excel.metadata.Sheet;

@Service("materialService")
public class MaterialServiceImpl implements MaterialService {
    @Autowired
    private MaterialMapper materialMapper;

    @Override
    public int queryTotal(Map<String, Object> map){
        return materialMapper.queryTotal(map);
    }

    @Override
    public MaterialEntity queryObject(Long id) {
        return materialMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<MaterialEntity> queryList(Map<String, Object> map) {
        return materialMapper.queryList(map);
    }

    @Override
    public void save(MaterialEntity materialEntity) {
        materialMapper.insertSelective(materialEntity);
    }

    @Override
    public void update(MaterialEntity materialEntity) {
        materialMapper.updateByPrimaryKey(materialEntity);
    }

    @Override
    public void delete(Long id) {
        materialMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteBatch(Integer[] ids){
        materialMapper.deleteBatch(ids);
    }

    @Override
    public void downLoadMaterial(List dataList, ServletOutputStream outputStream) throws Exception {

        List<MultipleSheelPropety> multipleSheelPropetys = new ArrayList<MultipleSheelPropety>();


        Sheet index = new Sheet(0, 2);
        index.setSheetName("材料");
        index.setHead(createPaymentRateIndexHead());
        index.setAutoWidth(Boolean.TRUE);

        MultipleSheelPropety indexSheel = new MultipleSheelPropety();
        indexSheel.setSheet(index);
        indexSheel.setData(dataList);

        multipleSheelPropetys.add(indexSheel);
        ExcelUtil.writeDownloadWithMultipleSheel(outputStream, multipleSheelPropetys);
    }

    public static List<List<String>> createPaymentRateIndexHead(){
        List<List<String>> head = new ArrayList<List<String>>();
        List<String> headCoulumn1 = new ArrayList<String>();
        List<String> headCoulumn2 = new ArrayList<String>();
        List<String> headCoulumn3 = new ArrayList<String>();
        List<String> headCoulumn4 = new ArrayList<String>();
        List<String> headCoulumn5 = new ArrayList<String>();
        List<String> headCoulumn6 = new ArrayList<String>();

        headCoulumn1.add("材料id");
        headCoulumn2.add("材料名称");
        headCoulumn3.add("品牌");
        headCoulumn4.add("规格型号");
        headCoulumn5.add("单位");
        headCoulumn6.add("单价");

        head.add(headCoulumn1);
        head.add(headCoulumn2);
        head.add(headCoulumn3);
        head.add(headCoulumn4);
        head.add(headCoulumn5);
        head.add(headCoulumn6);
        return head;
    }

}
