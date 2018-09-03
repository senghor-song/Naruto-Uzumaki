<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="contextMenuDialog" id="item1">
        <div class="card-body form-control p-0" style="border:none;">
            <div class="row pt-4 m-0">
                <div class="col-lg-4">
                    <div class="row">
                        <div class="col-lg-2 nametl">房源编号</div>
                        <div class="col-lg-4">${property.massProperty.propertyno }</div>
                        <div class="col-lg-6">${property.massProperty.estateT.cityT.city } ${property.massProperty.estateT.districtT.district } ${property.massProperty.estateT.areaT.area } ${property.massProperty.estateT.estate }
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-2 nametl">登记时间</div>
                        <div class="col-lg-4"><fmt:formatDate value="${property.massProperty.regdate }" pattern="yyyy-MM-dd"/></div>
                        <div class="col-lg-6">
                        ${property.massProperty.buildingvalue } 
                        ${property.massProperty.buildingtype == 1 ? '栋' :  '胡同'} 
                        ${property.massProperty.unitvalue } 
                        ${property.massProperty.unittype == 1 ? '栋'  :  '单元'} 
                        ${property.massProperty.roomno }
                        <%-- 
                        ${property.massProperty.buildingvalue } 
                        ${property.massProperty.buildingtype == 1 ? '栋' 
                        	: property.massProperty.buildingtype == 2 ? '弄'
                        	: property.massProperty.buildingtype == 3 ? '座'
                        	: property.massProperty.buildingtype == 4 ? '号'
                        	: property.massProperty.buildingtype == 5 ? '号楼'
                        	: property.massProperty.buildingtype == 6 ? '胡同'} 
                        ${property.massProperty.unitvalue } 
                        ${property.massProperty.unittype == 1 ? '栋' 
                        	: property.massProperty.unittype == 2 ? '幢' 
                        	: property.massProperty.unittype == 3 ? '号'
                        	: property.massProperty.unittype == 4 ? '号楼'
                        	: property.massProperty.unittype == 5 ? '单元'} 
                        ${property.massProperty.roomno } --%>
                        </div>
                    </div>
                </div>
                <div class="col-lg-1">
                    <div class="row">
                        <div class="col-lg-5 nametl">房厅卫</div>
                        <div class="col-lg-7">${property.massProperty.countf }/${property.massProperty.countt }/${property.massProperty.countw }</div>
                    </div>
                    <div class="row">
                        <div class="col-lg-4 nametl">面积</div>
                        <div class="col-lg-8">${property.massProperty.squares }</div>
                    </div>
                    <div class="row">
                        <div class="col-lg-4 nametl">楼层</div>
                        <div class="col-lg-8">${property.massProperty.floor }/${property.massProperty.floorall }</div>
                    </div>
                </div>
                <div class="col-lg-1">
                    <div class="row">
                        <div class="col-lg-4 nametl">朝向</div>
                        <div class="col-lg-8">${property.massProperty.direction }</div>
                    </div>
                    <div class="row">
                        <div class="col-lg-4 nametl">装修</div>
                        <div class="col-lg-8">${property.massProperty.decoration }</div>
                    </div>
                </div>
                <div class="col-lg-2">
                    <div class="row">
                        <div class="col-lg-3 nametl">商户</div>
                        <div class="col-lg-9">${property.massProperty.empStoreT.empstore }</div>
                    </div>
                    <div class="row">
                        <div class="col-lg-3 nametl">登记经纪</div>
                        <div class="col-lg-9">${property.massProperty.employeeT.emp }</div>
                    </div>
                </div>
                <div class="col-lg-1">
                    <img src="${property.massProperty.headimgpath }" class="w-100" alt="">
                </div>
                <div class="col-lg-2">
                    <div class="row">
                        <div class="col-lg-3 nametl">联系人</div>
                        <div class="col-lg-9">${property.massProperty.ower }</div>
                    </div>
                    <div class="row">
                        <div class="col-lg-3 nametl">联系方式</div>
                        <div class="col-lg-9">${property.massProperty.owertel }</div>
                    </div>
                </div>
            </div>
        </div>
    </div>