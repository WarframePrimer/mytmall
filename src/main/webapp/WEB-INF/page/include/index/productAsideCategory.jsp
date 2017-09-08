<%--
  Created by IntelliJ IDEA.
  User: warframe
  Date: 2017/9/7
  Time: 15:32
  To change this template use File | Settings | File Templates.
--%>

<!--竖状分类菜单右侧的推荐产品列表-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!--循环遍历map中的list-->
<c:forEach items="${productsByCategoryMap}" var="productsByCategory">
    <div class="productAsideCategory" cid="${productsByCategory.key.id}">
       <c:forEach items="${productsByCategory.value}" var="productByCategory">
           <div class="row ">
               <a href="#nowhere">
                   ${productByCategory.name}
               </a>
               <div class="seperator"></div>
           </div>
       </c:forEach>

    </div>

</c:forEach>



