<%--
  Created by IntelliJ IDEA.
  User: warframe
  Date: 2017/6/7
  Time: 15:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>


<script>


</script>


<!--动态显示分页-->
<div class="paginationDiv">

    <ul class="pagination">
        <!--首页-->
        <!--如果分页中没有前一页就设置为disabled-->
        <li <c:if test="${!page.isHasPrevious()}">class="disabled"</c:if>><a href="?pageNum=1${page.param}"><span>&laquo;</span></a></li>
        <!--前一页(如果存在的话)-->
        <li <c:if test="${!page.isHasPrevious()}">class="disabled"</c:if>>
            <c:choose>
                <c:when test="${page.pageNum - 1 <= 0}">
                    <a href="?pageNum=1${page.param}">‹</a>
                </c:when>
                <c:otherwise>
                    <a href="?pageNum=${page.pageNum - 1}${page.param}">‹</a>
                </c:otherwise>
            </c:choose>
        </li>
        <c:forEach begin="1" end="${page.getTotalPages()}" varStatus="status">
            <li><a href="?pageNum=${status.count}${page.param}" <c:if test="${page.pageNum eq status.count}">style="font-weight: bold;" </c:if>>${status.count}</a></li>
        </c:forEach>

        <!--后一页(如果存在的话)-->
        <li <c:if test="${!page.isHasNext()}">class="disabled"</c:if>>
            <c:choose>
                <c:when test="${page.pageNum + 1 > page.getTotalPages() }">
                    <a href="?pageNum=${page.getTotalPages()}${page.param}">›</a>
                </c:when>
                <c:otherwise>
                    <a href="?pageNum=${page.pageNum + 1}${page.param}">›</a>
                </c:otherwise>
            </c:choose>
        </li>
        <!--尾页-->
        <li <c:if test="${!page.isHasNext()}">class="disabled"</c:if>><a href="?pageNum=${page.getTotalPages()}${page.param}">&raquo;</a></li>
    </ul>

</div>


