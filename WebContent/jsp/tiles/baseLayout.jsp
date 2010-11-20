<%@ include file="../include.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title><tiles:insertAttribute name="title" ignore="true" /></title>
    </head>
    <body>
        <table align="center" width="100%" height="100%" height="100%">
            <tr>
                <td colspan="2" >
                    <tiles:insertAttribute name="header" />
                </td>
            </tr>
            <tr>
                <td >
                    <tiles:insertAttribute name="body" />
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <tiles:insertAttribute name="footer" />
                </td>
            </tr>
        </table>
    </body>
</html>
