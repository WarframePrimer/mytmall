<%--
  Created by IntelliJ IDEA.
  User: warframe
  Date: 2017/6/22
  Time: 9:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <title>Spring处理ajax的请求</title>
    <script type="text/javascript" src="js/jquery/2.0.0/jquery.min.js"></script>
    <script type="text/javascript">
        <!--ajax的请求-->
        function ajaxTest() {
            $.ajax({
                url:"/tmall/ajax/ajaxTest.do",
                data:"name=" + $("#name").val(),
                type:"GET",
                dataType:"json",
                success:function (data) {
                    alert("success" + data.msg);
                    $("#result").html(data.msg);
                },
                error:function (data) {
                    alert("error" + data.msg);
                }
            });
        }
    </script>
</head>
<body>
    <input type="text" name="name" id="name" placeholder="请输入名称"/>
    <input type="submit" value="提交" onclick="ajaxTest()"/>

    <div id="result"></div>



</body>
</html>
