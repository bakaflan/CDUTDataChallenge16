<%--
  Created by IntelliJ IDEA.
  User: 26602
  Date: 2019/5/9
  Time: 9:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
    <title>主页</title>
</head>
<body>
  <%--<a href="/testMongo">测试mongdb</a><br>--%>
  <%--<a href="/insertmongodb">测试批量插入员工</a>--%>

  <%--<form action="/doemployee" method="post" enctype="multipart/form-data">--%>
      <%--<input type="file" name="myfile" id="myfile" value="" />--%>
      <%--<input type="submit" value="导入Excel">--%>
  <%--</form>--%>

<%--<a href="/deletefromdb">测试批量删除员工</a><br>--%>

  <h3 >大数据挑战赛医疗记录抽取</h3>

  <p>1.导入表格数据</p>
  <form action="/dorecord" method="post" enctype="multipart/form-data">
      <input type="file" name="myfile" id="myfile2" value="" />
      <input type="submit" value="导入Excel">
  </form>

  ========================================================
  <p>2.查询所有</p>
<a href="/queryrecord">查询所有医疗记录</a><br>
  ========================================================
  <p>3.条件查询医疗记录</p>
  <form action="/queryrecordtest" method="get">
      <input id="filedName" name="filedName" type="text"/><br>
      <input id="keyWord" name="keyWord" type="text"/><br>
      <input type="submit" value="查询"/>
  </form>
  ========================================================
  <p>4.删除所有</p>
<a href="/deleterecordAll">测试删除所有</a>
</body>
</html>
