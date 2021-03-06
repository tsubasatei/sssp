<%--
  Created by IntelliJ IDEA.
  User: ozora
  Date: 2019/2/21
  Time: 10:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <title>Insert</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.9.1.min.js"></script>
    <script>
        $(function () {
            $("#lastName").change(function () {
                var val = $(this).val();
                val = $.trim(val);
                $(this).val(val);

                // 若修改的 lastName 和之前的 lastName 一致，则不发送 Ajax 请求，直接 alert lastName 可用!
                var _oldLastName = $("#_oldLastName").val();
                _oldLastName = $.trim(_oldLastName);
                if(_oldLastName != null && _oldLastName != "" && _oldLastName == val) {
                    alert("lastName 可用!");
                    return;
                }

                var url = "${pageContext.request.contextPath }/ajaxValidateLastName";
                var args = {"lastName": val, "date": new Date()}; // date:禁用缓存

                $.post(url, args, function (data) {
                    if(data == "0") {
                        alert("lastName 可用!");
                    } else if(data == "1") {
                        alert("lastName 不可用!");
                    } else {
                        alert("网络或程序出错。")
                    }
                });

            });

        });
    </script>
</head>
<body>
    <c:set value="${pageContext.request.contextPath}/emp" var="url"/>
    <c:if test="${employee.id != null}">
        <c:set var="url" value="${pageContext.request.contextPath}/emp/${employee.id}"/>
    </c:if>
    <form:form action="${url}" method="POST" modelAttribute="employee">
        <c:if test="${employee.id != null}">
            <input type="hidden" id="_oldLastName" value="${employee.lastName}">
            <form:hidden path="id" />
            <input type="hidden" name="_method" value="PUT">
        </c:if>
        LastName: <form:input path="lastName" id="lastName"/><br>
        Email: <form:input path="email"/><br>
        Birth:<form:input path="birth"/><br>
        Deparment:
        <form:select path="department.id" items="${departments}"
                               itemLabel="departmentName" itemValue="id">

        </form:select><br>
        <input type="submit" value="Submit">
    </form:form>
</body>
</html>
