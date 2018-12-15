<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!-- Left navbar links -->
<ul class="navbar-nav">
    <li class="nav-item">
        <a class="nav-link" data-widget="pushmenu" href="#">
            <i class="fa fa-bars" style="top: 3px;position: relative;font-size: 20px;"></i>
        </a>
    </li>

    <!-- <li class="nav-item d-none d-sm-inline-block nav-item-img">
        <a href="index.html" class="nav-link" title="巡检，分辨率必须=1920*1080，全屏模式">
            <img src="/admin/static/image/5.png" alt=""/>
        </a>
    </li>
    <li class="nav-item d-none d-sm-inline-block nav-item-img">
        <a href="/admin/baiduMap/index" class="nav-link mymap" title="沙盘">
            <img src="/admin/static/image/6.png" alt=""/>
        </a>
    </li>
    <li class="nav-item d-none d-sm-inline-block nav-item-img">
        <a href="index.html" class="nav-link mymessage" title="OM">
            <img src="/admin/static/image/7.png" alt=""/>
        </a>
    </li>
    <li class="nav-item d-none d-sm-inline-block nav-item-img">
        <a href="index.html" class="nav-link mymessage" title="短信">
            <img src="/admin/static/image/8h.png" alt=""/>
        </a>
    </li> -->
</ul>


<ul class="navbar-nav ml-auto">
    <li class="nav-item dropdown">
        <a class="nav-link nav-item-img" data-toggle="dropdown" href="#" 
        onclick="window.location.href='/admin/venue/listview'" title="待审核教学场地（机构自行添加）">
            <img src="/admin/static/image/11h.png" alt=""/>
            <span class="badge badge-danger navbar-badge" id="venueCheckSum">0</span>
        </a>
    </li>
    <li class="nav-item dropdown">
        <a class="nav-link nav-item-img" data-toggle="dropdown" href="#"
        onclick="window.location.href='/admin/venue/listview'" title="场馆入驻">
            <img src="/admin/static/image/13h.png" alt=""/>
            <span class="badge badge-warning navbar-badge" id="venueEnterSum">0</span>
        </a>
    </li>
    <li class="nav-item dropdown">
        <form class="form-inline ml-3 search-margin-top">
            <div class="input-group input-group-sm">
                <input class="form-control form-control-navbar" type="search" placeholder="" aria-label="Search"/>
                <div class="input-group-append">
                    <button class="btn btn-navbar" type="button">
                        <i class="fa fa-search"></i>
                    </button>
                </div>
            </div>
        </form>
    </li>
    <li class="nav-item dropdown">
        <a class="nav-link nav-item-img" data-toggle="dropdown" href="#" 
        onclick="window.location.href='/admin/trainTeam/listview'" title="培训机构入驻">
            <img src="/admin/static/image/10h.png" alt=""/>
            <span class="badge badge-danger navbar-badge" id="trainEnterSum">0</span>
        </a>
    </li>
    <!-- <li class="nav-item dropdown">
        <a class="nav-link nav-item-img" data-toggle="dropdown" href="#" title="公盘">
            <img src="/admin/static/image/10h.png" alt=""/>
            <span class="badge badge-danger navbar-badge">0</span>
        </a>
    </li>
    <li class="nav-item dropdown">
        <a class="nav-link nav-item-img" data-toggle="dropdown" href="#" title="合作续期" 
        onclick="window.location.href='/admin/store/listview'">
            <img src="/admin/static/image/12h.png" alt=""/>
            <span class="badge badge-danger navbar-badge" id="empStoreVerifySum">0</span>
        </a>
    </li> -->
    <li class="nav-item dropdown">
        <a class="nav-link nav-item-img out-margin-left" href="javascript:;" data-href="login.html" id="logout" >
            <img src="/admin/static/image/u5427.png" alt="" title="注销"/>
        </a>
    </li>
</ul>

<div id="messageDiv" class="otherDialog hide">
    <div style="padding: 18px;">
        <div class="row m-0 p-0">
            <div class="col-lg-12">
                <div class="row lh-38">
                    <div class="col-lg-4">短信线路：</div>
                    <div class="col-lg-8">
                        <select name="" class="form-control" id="">
                            <option value="">阿里</option>
                        </select>
                    </div>
                </div>
                <div class="row lh-38">
                    <div class="col-lg-4">手机号码：</div>
                    <div class="col-lg-8">
                        <input type="text" class="form-control">
                    </div>

                </div>
                <div class="row lh-38">
                    <div class="col-lg-4">短信内容：</div>
                    <div class="col-lg-8">
                        <textarea name="" id="" cols="30" style="height: 160px;" class="form-control" placeholder="输入短信内容"></textarea>
                    </div>
                </div>
                <div class="row lh-38">
                    <div class="col-lg-4"></div>
                    <div class="col-lg-8">
                        <button class="form-control btn btn-primary w-100">发送</button>
                    </div>
                </div>
            </div>
        </div>
        <hr>
        <div class="row m-0 p-0">
            <div class="row p-0 m-0">
                <div class="w-100 clearfix">
                    <span class="float-left" style="margin-left: 5px; position: relative; margin-top: -3px; color: #999;">等待发送</span>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="mapDiv" class="otherDialog hide">
    <div style=" position: relative; top: 0px;">
    </div>

    <div style="position: absolute;
    top: 30px;
    left: 30px;
    width: 400px;
    text-align: center;
    height: 60px;
    border: 1px solid #ccc;
    padding: 8px;
    background: #fff;
    line-height: 22px;opacity: 0.7 !important;">
        <div class="row">
            <div class="col-lg-3" style="color: #FF0000;">
                <div>35</div>
                <div>商户(有效)</div>
            </div>
            <div class="col-lg-3" style="color: #800080;">
                <div>22</div>
                <div>商户(失效)</div>
            </div>
            <div class="col-lg-3" style="color: #0000FF;">
                <div>15</div>
                <div>新盘</div>
            </div>
            <div class="col-lg-3" style="color: #FF00FF;">
                <div>408</div>
                <div>小区</div>
            </div>
        </div>
        <div style="position: relative; top: 10px; left: 0px;">
            <div class="clearfix lh-38">
                <div class="float-left mr-1">深圳</div>
                <div class="float-left">
                    <input type="text" class="form-control input-sm" placeholder="小区/新盘/商户">
                </div>
            </div>
        </div>
    </div>
</div>
