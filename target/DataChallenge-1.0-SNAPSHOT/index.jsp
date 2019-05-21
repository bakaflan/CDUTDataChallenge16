<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
    pageContext.setAttribute("APP_PATH", request.getContextPath());
%>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
    <title>主页</title>
    <!--引入jquery-->
    <script src="${APP_PATH}/static/js/jquery-3.2.1.min.js"></script>
    <!--引入样式-->
    <script src="${APP_PATH}/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <link href="${APP_PATH}/static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

  <div class="container">
      <!--标题-->
      <div class="row">
          <div class="col-md-12">
              <h1>大数据挑战赛医疗记录抽取</h1>
          </div>
      </div>


      <!--导入表格-->
      <p>1.导入表格数据</p>
      <div class="row">
          <div class="col-md-12">
              <div class="form-group">
                  <form action="/doRecord" method="post" enctype="multipart/form-data">
                      <input type="file" name="myfile" id="myfile2" value="" />
                      <input type="submit" value="导入Excel">
                  </form>
              </div>

          </div>
      </div>


      <p>2.分页查询</p>

      <div class="row">
          <div class="col-md-12">
              <%--<a href="/record">分页查询</a><br>--%>
          </div>
      </div>


      <!--数据显示-->
      <div class="row">
          <div class="col-md-12">
              <table class="table table-hover" id="record_table">
                  <thead>
                  <tr>
                      <th>id</th>
                      <th>specimen</th>
                      <th>pathology</th>
                      <th>differentLevel</th>
                      <th>iraits</th>
                      <th>uperCut</th>
                      <th>lowerCut</th>
                      <th>baseCut</th>
                  </tr>
                  </thead>
                  <tbody>
                  </tbody>
              </table>
          </div>
      </div>

      <!--显示分页信息-->
      <div class="row">
          <!--分页文字-->
          <div class="col-md-6" id="page_info_infromation">

          </div>
          <!--分页数字-->
          <div class="col-md-6" id="page_navarea">

          </div>
      </div>


      <p>3.条件查询医疗记录</p>
      <div class="row">
          <div class="col-md-12">
              <form action="/queryRecord" method="get">
                  <th>字段：</th>
                  <input id="filedName" name="filedName" type="text" /><br>
                  <th>关键字：</th>
                  <input id="keyWord" name="keyWord" type="text"/><br>
                  <input type="submit" value="查询"/>
              </form>
          </div>
      </div>


      <p>4.删除所有</p>
      <div class="row">
          <div class="col-md-12">
              <a href="/deleteRecordAll">测试删除所有</a>
          </div>
      </div>

  </div>


</body>

<script type="text/javascript">
    var totalRecord;
    $(function () {
        to_pagr(1);
    });

    function to_pagr(page) {
        $.ajax({
            url: "${APP_PATH}/record",
            data: "page=" + page,
            type: "GET",
            success: function (result) {
                console.log(result);
                build_emps_table(result);
                build_page_info(result);
                build_page_mav(result);
            }
        });
    }

    function build_emps_table(result) {
        $("#record_table tbody").empty();
        var records = result.extend.pageList.content;
        $.each(records, function (index, item) {

            var recordIdTd = $("<td></td>").append(item.id);
            var recordNameTd = $("<td></td>").append(item.specimen);
            var recordPathologyTd = $("<td></td>").append(item.pathology);
            var recordDifferentLevelTd = $("<td></td>").append(item.differentLevel);
            var recordIraitsTd = $("<td></td>").append(item.iraits);
            var recordUperCutTd = $("<td></td>").append(item.uperCut);
            var recordLowerCutTd = $("<td></td>").append(item.lowerCut);
            var recordBaseCutTd = $("<td></td>").append(item.baseCut);

            $("<tr></tr>").append(recordIdTd)
                .append(recordNameTd)
                .append(recordPathologyTd)
                .append(recordDifferentLevelTd)
                .append(recordIraitsTd)
                .append(recordUperCutTd)
                .append(recordLowerCutTd)
                .append(recordBaseCutTd)
                .appendTo("#record_table tbody");
        });
    }

    function build_page_info(result) {
        $("#page_info_infromation").empty();
        $("#page_info_infromation").append(" 当前第" + result.extend.pageList.number + "页,总共" + result.extend.pageList.totalPages + "页,总" + result.extend.pageList.totalElements + "记录");
        totalRecord = result.extend.pageList.totalElements;
    }

    //解析分页
    function build_page_mav(result) {

        $("#page_navarea").empty();
        var ul = $("<ul></ul>").addClass("pagination");

        var fistPageLi = $("<li></li>").append($("<a></a>").append("首页").attr("href", "#"));
        var prPageLi = $("<li></li>").append($("<a></a>").append("&laquo;"));
        if (result.extend.pageList.first == false) {
            fistPageLi.addClass("disabled");
            prPageLi.addClass("disabled");
        } else {
            fistPageLi.click(function () {
                to_pagr(1);
            });

            prPageLi.click(function () {
                to_pagr(result.extend.pageList.number - 1);
            });
        }


        var nextPageLi = $("<li></li>").append($("<a></a>").append("&raquo;"));
        var lastPageLi = $("<li></li>").append($("<a></a>").append("尾页").attr("href", "#"));

        if (result.extend.pageList.last == false) {
            nextPageLi.addClass("disabled");
            lastPageLi.addClass("disabled");
        } else {
            lastPageLi.click(function () {
                to_pagr(result.extend.pageList.totalPages);
            });

            nextPageLi.click(function () {
                to_pagr(result.extend.pageList.number + 1);
            });
        }

        ul.append(fistPageLi).append(prPageLi);
        $.each(result.extend.pageList.number, function (index, item) {

            var numLi = $("<li></li>").append($("<a></a>").append(item));
            if (result.extend.pageList.number == item) {
                numLi.addClass("active");
            }
            numLi.click(function () {
                to_pagr(item);
            });
            ul.append(numLi);
        });
        ul.append(nextPageLi).append(lastPageLi);
        var navEle = $("<nav></nav>").append(ul);
        navEle.appendTo("#page_navarea");
    }
</script>
</html>
