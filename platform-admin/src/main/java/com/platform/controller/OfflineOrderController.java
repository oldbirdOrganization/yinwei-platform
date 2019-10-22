package com.platform.controller;

import com.platform.entity.OfflineOrderInfoPo;
import com.platform.entity.OrderImageEntity;
import com.platform.entity.OrderInfoEntity;
import com.platform.service.OfflineOrderService;
import com.platform.service.OrderImageService;
import com.platform.service.OrderInfoService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;
import com.platform.utils.StringUtils;
import com.platform.utils.excelutils.ExcelUtils;
import com.platform.vo.ImgVo;
import com.platform.vo.OfflineOrderInfoVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("offlineOrder")
public class OfflineOrderController {
    @Autowired
    private OfflineOrderService offlineOrderService;
    @Autowired
    private OrderInfoService orderInfoService;
    @Autowired
    private OrderImageService orderImageService;

    /**
     * 导入excel
     * @param file
     */
    @RequestMapping(value = "/readExcel", method = RequestMethod.POST)
    public R readExcel(@RequestParam(value="uploadFile", required = false) MultipartFile file){
        long t1 = System.currentTimeMillis();
        if (file == null) {
            return R.error(1001,"文件不能为空");
        }
        List<OfflineOrderInfoVo> list = ExcelUtils.readExcel("", OfflineOrderInfoVo.class, file);
        long t2 = System.currentTimeMillis();
        System.out.println(String.format("read over! cost:%sms", (t2 - t1)));
        for(OfflineOrderInfoVo str : list) {
            if(StringUtils.isNullOrEmpty(str.getBatchNo())){
                return R.error(1002,"批次号不能为空");
            }
        }
        //入库
        offlineOrderService.importOfflineOrders(list);
        return R.ok();
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
//    @RequiresPermissions("ordergoods:list")
    public R list(@RequestParam Map<String, Object> params){
        //查询列表数据
        Query query = new Query(params);

        int total = offlineOrderService.queryTotal(query);
        List<OfflineOrderInfoPo> offorderList = offlineOrderService.queryListPage(query);

        PageUtils pageUtil = new PageUtils(offorderList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 导出excel
     * @throws IOException
     */
    @RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
    public R exportExcel(@RequestParam Map<String, Object> params, HttpServletRequest request, HttpServletResponse response)  throws IOException {
        //查询列表数据
        Query query = new Query(params);
        query.put("offset", null);
        List<OfflineOrderInfoPo> resultList = offlineOrderService.queryList(query);
        List<OfflineOrderInfoVo> list = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(resultList)) {
            list = resultList.stream().map(a -> {
                OfflineOrderInfoVo vo = new OfflineOrderInfoVo();
                BeanUtils.copyProperties(a, vo);
                vo.setPaymentTime(a.getPaymentTime().toString());
                vo.setCouponPrice(a.getCouponPrice());
                vo.setDescriptionDescription(a.getProblemDescription());
                vo.setIsOuterOrder((int)a.getIsOuterOrder());
                vo.setCouponPrice(a.getCouponPrice());
                return vo;
            }).collect(Collectors.toList());
        }
        long t1 = System.currentTimeMillis();
        ExcelUtils.writeExcel(response, list, OfflineOrderInfoVo.class);
        long t2 = System.currentTimeMillis();
        System.out.println(String.format("write over! cost:%sms", (t2 - t1)));
        return R.ok();
    }

    /**
     * 详情
     * @param id
     * @return
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("order:info")
    public R info(@PathVariable("id") Integer id) {

        OfflineOrderInfoPo offlineOrder = offlineOrderService.queryDetailById(id);//线下已支付订单
        OrderInfoEntity orderInfoEntity =orderInfoService.queryObject(offlineOrder.getParentOrderId());//查询预约单信息

        if(!ObjectUtils.isEmpty(offlineOrder) && !ObjectUtils.isEmpty(orderInfoEntity)){
            BigDecimal onalreadyAmount=new BigDecimal(0);//线上已付金额
            BigDecimal offalreadyAmount=new BigDecimal(0);//线下已付金额
            // 查询线上支付订单信息
            Map<String, Object> premap = new HashMap<>();
            premap.put("parentOrderId",offlineOrder.getParentOrderId());
            List<OrderInfoEntity> preorderList=orderInfoService.queryList(premap);
            for (OrderInfoEntity preorderlist:preorderList){
                if(preorderlist.getPaymentStatus() == 2){//线上已付金额= 总金额 - 历次已支付金额 -历次优惠金额
                    onalreadyAmount=(onalreadyAmount.add(preorderlist.getOrderPrice())).subtract(preorderlist.getCouponPrice());
                }
            }
            // 查询线下已支付订单信息
            Map<String, Object> offlineMap=new HashMap<String, Object>();
            offlineMap.put("parentOrderId",offlineOrder.getParentOrderId());
            List<OfflineOrderInfoPo> offlineorderList = offlineOrderService.queryListCondtion(offlineMap);
            for(OfflineOrderInfoPo offlist:offlineorderList){
                if(offlist.getPaymentStatus() == 2){//线下已付金额= 总金额 - 历次已支付金额
                    offalreadyAmount=(offalreadyAmount.add(offlist.getOrderPrice()));
                }
            }
            orderInfoEntity.setAlreadyPayAmount(onalreadyAmount.add(offalreadyAmount));//此预约单已支付金额
            orderInfoEntity.setResiduesPayAmount(offlineOrder.getTotalAmount().subtract(onalreadyAmount.add(offalreadyAmount)));//预约单剩余尾款金额=总金额 -已付金额
        }
        Map<String, Object> imgmap = new HashMap<>();
        imgmap.put("orderId",offlineOrder.getParentOrderId());
        //查询预约订单相关图片信息
        List<ImgVo> imgVoList=new ArrayList<ImgVo>();
        List<OrderImageEntity> imglist=orderImageService.queryList(imgmap);
        for (OrderImageEntity ilist:imglist){
            ImgVo imgVo =new ImgVo();
            if(ilist.getSortType() !=2) {
                imgVo.setServiceFrontImgUrl(ilist.getUrl());
            }else {
                imgVo.setServiceLaterImgUrl(ilist.getUrl());
            }
            imgVoList.add(imgVo);
        }
        orderInfoEntity.setImgVoList(imgVoList);
        orderInfoEntity.setParentOrderNo(orderInfoEntity.getOrderNo());
        orderInfoEntity.setOrderNo(offlineOrder.getOrderNo());
        orderInfoEntity.setPaymentStatus(offlineOrder.getPaymentStatus());
        orderInfoEntity.setPaymentNo(offlineOrder.getPaymentNo());
        orderInfoEntity.setPaymentTime(offlineOrder.getPaymentTime());
        orderInfoEntity.setTotalAmount(offlineOrder.getTotalAmount().compareTo(BigDecimal.ZERO)==0? BigDecimal.ZERO:offlineOrder.getTotalAmount());
        orderInfoEntity.setOrderPrice(offlineOrder.getOrderPrice().compareTo(BigDecimal.ZERO)==0? BigDecimal.ZERO:offlineOrder.getOrderPrice());
        orderInfoEntity.setShroffAccountNumber(offlineOrder.getShroffAccountNumber());
        orderInfoEntity.setItem(offlineOrder.getItem());
        orderInfoEntity.setShroffAccountNumber(offlineOrder.getShroffAccountNumber());
        orderInfoEntity.setPayChannel(offlineOrder.getPayChannel());
        return R.ok().put("order", orderInfoEntity);
    }
}
