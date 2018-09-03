<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@include file="../common/tag.jsp" %>
        <!DOCTYPE html>
        <html>
            
            <head>
                    <title>经纪人应用-房产易推房软件官网</title>
                    <link href="<%=path%>/Static/Css/common.css" rel="stylesheet" type="text/css">
                    <link href="<%=path%>/Static/Css/bootstrap.css" rel="stylesheet" type="text/css">
                    <script src="<%=path%>/Static/Js/jquery-1.9.1.min.js"></script>
                    <script language="javascript" src="<%=path%>/Static/Js/respond.js"></script>
                    <script language="javascript" src="<%=path%>/Static/Js/bootstrap.js"></script>
                    <link href="<%=path%>/Static/Css/index.css" rel="stylesheet" type="text/css"></head>
                    <link href="<%=path%>/Static/Css/common.css" rel="stylesheet" type="text/css"></head>
            
            <body>
               
				<%@include file="indexTop.jsp" %>
                <div class="xhdp3">
                    <div class="body_1"></div>
                </div>
                <link href="<%=path%>/Static/Css/about.css" rel="stylesheet" type="text/css">
                <style type="text/css">#a2 .b1 { height: 150px; } #a2 .b1 .b { padding-top: 0px; } #a1 .b1 .b { padding-top: 20px } .body_2 { height: 200px; background: #F8F8F8; overflow: hidden; /*border:1px solid red;*/ }</style>
                <div class="iab" style="background: #F8F8F8" id="xx">
                    <div class="body_2">
                        <ul class="nav navbar-nav" id="myTab2">
                            <li class="active">
                                <a href="#xx">合作策略</a></li>
                            <li class="">
                                <a href="Cooperation.html#xx">市场合作</a></li>
                        </ul>
                        <!--{begin: 代理商查询}-->
                        <style>.agent-query-box { width: 820px; margin: 0 auto; padding: 10px; text-align: center; overflow: hidden; padding-top: 150px; border: 0px solid red; float: left; margin-left: 140px; } .agent-query-box label { font-size: 18px; float: left; } .agent-query-box input { width: 490px; height: 30px; float: left; } .agent-query-box button { width: 90px; height: 30px; background: #2E9F3F; border: 0; color: #fff; font-size: 16px; *height: 35px; float: left; } .loading-layer { border: 0px solid red; text-align: center; display: none; position: fixed; margin-left: 50px; top: 110px; width: 900px; height: 450px; } .loading-layer img { margin-top: 30px; }</style>
                        <ul>
                            <div class='agent-query-box'>
                                <label>代理商查询：</label>
                                <input name='agentName' placeholder="请输入代理商全称" />
                                <button type="button" id='btnAgentQuery' />查找</button></div>
                            <div class='loading-layer'>
                                <img src="<%=path%>/Static/Picture/loading.gif"></div>
                        </ul>
                        <!--{end: 代理商查询}--></div>
                    <div class="tab-content">
                        <div class="tab-pane active" id="a1">
                            <div class="b1">
                                <div class="b body_1">
                                    <p>据统计，我国房产经纪人已达500多万人。随着房产经纪人网络营销意识逐步提升，大量的房产门户网站得到了广泛应用，房产门户网站帮助房产经纪人带来订单。由于各个经纪人所购买的房产门户网站帐号数量较多，大大增加了房源发布、房源管理、房源刷新等工作量。随着市场竞争的加剧，房产经纪人订单获取越来越艰难，对能够帮助提升订单转化率的营销管理软件和服务需求也变得越来越迫切。房产易推房整合了营销推广、客户管理、协作办公的营销管理软件，成为了信息化领域的新蓝海。</p>
                                    <p>房产易推房是运用云端技术专门为房产经纪人量身设计、开发的一款功能全面且强大的专业房产软件，实现了第一时间个人房源采集、多站点一键房源群发、多站点一键房源刷新、房源克隆、房源共享、房源录入等理想功能的操作。房产易推房打破了经纪人需要到各个网站手动操作的常规方法，并且可以帮助经纪人更全面、更快捷、更方便的做好网络营销的软件。</p>
                                    <p>目前房产易推房已成为厦门，福州，杭州，南京，上海，北京等各地房产经纪人最得力的网络营销工具，其中包含麦田房产、我爱我家、链家地产、21世纪房产、德祐地产、丹厦房产等全国知名房产中介。</p>
                                    <p>现在应市场发展需求，合作伙伴战略是房产易推房的核心战略，房产易推房致力于引进更多合作伙伴，希望通过为更多的房产经纪人提供完整的营销管理软件和服务，促进合作伙伴的自身价值最大化、利益最大化，同时与合作伙伴一起通过营销管理软件和服务，推动房产中介公司的发展!</P>
                                </div>
                            </div>
                            <div class="b2">
                                <div class="b body_1" style="width: 1000px;">
                                    <h3 class="h">伙伴类型</h3>
                                    <div>
                                        <img src="<%=path%>/Static/Picture/iab1.jpg">
                                        <h3>行业企业</h3>
                                        <span>知名企业：当地大型房产网、分类信息、搜索引擎等的产品代理商，当地电脑维护公司等。</span></div>
                                    <div>
                                        <img src="<%=path%>/Static/Picture/iab2.jpg">
                                        <h3>房产网</h3>
                                        <span>房产网：拥有日流量100000PV以上房产网的公司或者站长。</span></div>
                                    <div>
                                        <img src="<%=path%>/Static/Picture/iab3.jpg">
                                        <h3>行业协会</h3>
                                        <span>行业协会：当地的房产中介行业协会相关负责人。</span></div>
                                    <div>
                                        <img src="<%=path%>/Static/Picture/iab4.jpg">
                                        <h3>独立个体</h3>
                                        <span>独立个体：从事房产网二手房的相关产品销售人员，从事房产中介相关工作人员，房产中介公司网经，当地电脑相关维护人员。</span></div>
                                </div>
                            </div>
                            <div class="b3">
                                <div class="b body_1" style="width: 1000px;">
                                    <h3 class="h">选择理由</h3>
                                    <div style="margin-right: 40px;">
                                        <img src="<%=path%>/Static/Picture/iab5.jpg">
                                        <div class="b31">
                                            <h3>产品技术</h3>
                                            <span>房产易推房创新利用云计算、云存储，分布式缓存和移动互联技术，量身设计、开发的一款专业房产软件，其软件功能全面且强大技术应用，为经纪人提供即取即用的营销管理和服务。房产易推房打破了经纪人需要到各个网站手动操作的常规，并且可以帮助经纪人更全面、更快捷、更方便的做好网络营销的软件，赢得更多订单！</span></div>
                                    </div>
                                    <div>
                                        <img src="<%=path%>/Static/Picture/iab6.jpg">
                                        <div class="b31">
                                            <h3>伙伴支持</h3>
                                            <span>房产易推房每年把营收的25%投入研发，确保能够为伙伴持续提供业内顶级的产品和技术，同时房产易推房将辅助伙伴不断提升自身销售、技术、服务和管理能力，并与伙伴共享各种成功的盈利模式，支持伙伴打造自我发展的“核心竞争力”，助力伙伴商业成功。</span></div>
                                    </div>
                                    <div style="margin-right: 40px;">
                                        <img src="<%=path%>/Static/Picture/iab7.jpg">
                                        <div class="b31">
                                            <h3>伙伴政策</h3>
                                            <span>“携手发展合作共赢”是房产易推房的核心业务策略，也是公司长期战略，房产易推房在此战略方针指导下制定了极具竞争力的合作伙伴政策，使得合作伙伴能够享有丰厚的利润，能够持续稳定的发展。</span></div>
                                    </div>
                                    <div>
                                        <img src="<%=path%>/Static/Picture/iab8.jpg">
                                        <div class="b31">
                                            <h3>资源整合</h3>
                                            <span>房产易推房全力搭建一个不断壮大的合作伙伴体系，积极促进合作伙伴成员间的互动与交流，促使不同伙伴之间进行资源整合，实现资源的价值最大化，为合作伙伴带来更多商业机会，促使合作伙伴得到更大的发展。</span></div>
                                    </div>
                                </div>
                            </div>
                            <div class="b4">
                                <div class="b body_1">
                                    <div class="b7">
                                        <a href="apply.html">
                                            <img src="<%=path%>/Static/Picture/bu3.jpg"></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="tab-pane " id="a2">
                            <div class="b1">
                                <div class="b body_1">
                                    <h3>市场伙伴</h3>
                                    <span>面向房产易推房的目标客户，拥有一定影响力的组织，或者与房产易推房目标客户相同的公司或者机构，能够在客户面前宣传推荐房产易推房的产品。</span></div>
                            </div>
                            <div class="b2">
                                <div class="b body_1">
                                    <h3>市场组成及合作模式</h3>
                                    <table style="margin-left: 16px;" border="2">
                                        <tr>
                                            <th rowspan="2" style="width: 210px;">伙伴类型</th>
                                            <th rowspan="2" style="width: 290px;">伙伴组合</th>
                                            <th colspan="2" style="width: 464px;">合作方式</th></tr>
                                        <tr>
                                            <th>项目协作销售</th>
                                            <th>产品代理销售</th></tr>
                                        <tr style="">
                                            <td rowspan="5" class="th1">市场伙伴</td>
                                            <td rowspan="">知名企业</td>
                                            <td class="ok1"></td>
                                            <td class="ok1"></td>
                                        </tr>
                                        <tr style="">
                                            <td rowspan="">房产网</td>
                                            <td class="ok1"></td>
                                            <td class="ok1"></td>
                                        </tr>
                                        <tr style="">
                                            <td rowspan="">行业协会</td>
                                            <td class="ok1"></td>
                                            <td class="ok1"></td>
                                        </tr>
                                        <tr style="">
                                            <td rowspan="">房产中介从业人员</td>
                                            <td class=""></td>
                                            <td class="ok1"></td>
                                        </tr>
                                        <tr style="">
                                            <td rowspan="">独立个体</td>
                                            <td></td>
                                            <td class="ok1"></td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                            <div class="b3">
                                <div class="b body_1">
                                    <h3>盈利模式</h3>
                                    <div>
                                        <img src="<%=path%>/Static/Picture/iab9.jpg">
                                        <div class="b31">
                                            <span>代理房产易推房产品带来的产品与服务销售收入</span></div>
                                    </div>
                                    <div>
                                        <img src="<%=path%>/Static/Picture/iab10.jpg">
                                        <div class="b31">
                                            <span>联合方案营销带来的产品与服务销售收入</span></div>
                                    </div>
                                    <div>
                                        <img src="<%=path%>/Static/Picture/iab11.jpg">
                                        <div class="b31">
                                            <span>共享房产易推房市场及客户资源带来的机会价值</span></div>
                                    </div>
                                </div>
                            </div>
                            <div class="b4">
                                <div class="b body_1">
                                    <h3>伙伴支持</h3>
                                    <div>
                                        <img src="<%=path%>/Static/Picture/iab12.jpg">
                                        <div class="b31">
                                            <span>提供稳定，易用，安全，智能化移动化的网络营销及管理软件</span></div>
                                    </div>
                                    <div>
                                        <img src="<%=path%>/Static/Picture/iab13.jpg">
                                        <div class="b31">
                                            <span>提供软件深度集成服务，接口联合开发与性能测试服务（量大）</span></div>
                                    </div>
                                    <div>
                                        <img src="<%=path%>/Static/Picture/iab14.jpg">
                                        <div class="b31">
                                            <span>在易推房现有的产品基础上为伙伴提供客制化的开发服务（量大）</span></div>
                                    </div>
                                    <div>
                                        <img src="<%=path%>/Static/Picture/iab15.jpg">
                                        <div class="b31">
                                            <span>联合方案与产品的销售工具（产品白皮书/DEMO/成功案例）等支持</span></div>
                                    </div>
                                    <div>
                                        <img src="<%=path%>/Static/Picture/iab16.jpg">
                                        <div class="b31">
                                            <span>联合方案与联合品牌的宣传推广</span></div>
                                    </div>
                                    <div>
                                        <img src="<%=path%>/Static/Picture/iab17.jpg">
                                        <div class="b31">
                                            <span>提供易推房产品标准化实施方法论培训 &nbsp; &nbsp;帮助伙伴建立起易推房产品的实施团队</span></div>
                                    </div>
                                </div>
                            </div>
                            <div class="b5">
                                <div class="b body_1">
                                    <h3>加盟流程</h3>
                                    <div>
                                        <img src="<%=path%>/Static/Picture/iab18.jpg">
                                        <div class="b31">
                                            <h3>合作申请</h3></div>
                                    </div>
                                    <div>
                                        <img src="<%=path%>/Static/Picture/iab19.jpg">
                                        <div class="b31">
                                            <h3>评估与认证</h3></div>
                                    </div>
                                    <div>
                                        <img src="<%=path%>/Static/Picture/iab20.jpg">
                                        <div class="b31">
                                            <h3>签署协议</h3></div>
                                    </div>
                                    <div>
                                        <img src="<%=path%>/Static/Picture/iab21.jpg">
                                        <div class="b31">
                                            <h3>合作推进</h3></div>
                                    </div>
                                    <div style="width: 102px;">
                                        <img src="<%=path%>/Static/Picture/iab22.jpg">
                                        <div class="b31" style="width: 180px; position: relative; left: -40px;">
                                            <h3>定期沟通与结果评估</h3></div>
                                    </div>
                                </div>
                            </div>
                            <div class="b6">
                                <div class="b body_1">
                                    <a href="apply.html" style="">
                                        <img src="<%=path%>/Static/Picture/bu3.jpg"></a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <script>$('#myTab2 a').click(function(e) {
                            //e.preventDefault();
                            $(this).tab('show');
                        });

                        $('#btnAgentQuery').click(function() {
                            var agentName = $('input[name=agentName]').val();
                            if (agentName == '') {
                                alert('请输入代理商全称');
                                return false;
                            }

                            $('.loading-layer').css('display', 'block');
                            $.get('/index/queryAgent', {
                                query: agentName
                            },
                            function(data) {
                                $('.loading-layer').css('display', 'none');
                                alert(data.msg);
                            },
                            'json');
                        })</script>
                </div>
                <div class="footw1">
                    <div class="body_1 ">
                        <div class="b_l">
                            <ul class="navr">
                                <li>
                                    <a href="">首页</a></li>
                                <li>
                                    <a href="page-1.html">关于我们</a></li>
                                <li>
                                    <a href="page-4.html">人才招聘</a></li>
                                <li>
                                    <a href="Customer.html">客服中心</a></li>
                                </li>
                                <li>
                                    <a href="Help-4.html">帮助中心</a></li>
                                <li>
                                    <a href="line.html">友情链接</a></li>
                            </ul>
                        </div>
                        <div class="b_r">版权所有 © 厦门市好景科技有限公司 闽ICP备09006319号
                            <script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://": " http://");
                                document.write(unescape("%3Cspan id='cnzz_stat_icon_1259598464'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s95.cnzz.com/z_stat.php%3Fid%3D1259598464%26show%3Dpic1' type='text/javascript'%3E%3C/script%3E"));</script>
                        </div>
                    </div>
                </div>
                <script src="<%=path%>/Static/Js/jquery.lazyload.min.js"></script>
                <script type="text/javascript">if ($('.js-xms-lazy').length > 0) {
                        $('.js-xms-lazy').lazyload();
                    }</script>
            </body>
        
        </html>