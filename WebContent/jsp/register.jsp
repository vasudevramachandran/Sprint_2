    <%@ page language="java" contentType="text/html; charset=ISO-8859-1"  
        pageEncoding="ISO-8859-1"%>  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">  
<title>Register.FairSplit &reg;</title>
</head>
<body>
  
<center>
<form action="registerServlet" method="post" onsubmit="window.location.href = 'http://stackoverflow.com';">
 <fieldset style="width: 300px;background-color:WHITE;">  
<legend style="background-color:#9EBA95;float:center;"> Register to FairSplit &reg; </legend>  
<table>  
                    <tr>  
                        <td>Name</td>  
                        <td><input type="text" name="rname" required="required" autofocus="autofocus"/></td>  
                    <tr>  
                        <td>Email address</td>  
                        <td><input type="text" name="remail" required="required" autofocus="autofocus"/></td>  
                    </tr>  
                    </tr>  
                    <tr>  
                        <td>Password</td>  
                        <td><input type="password" name="rpassword" required="required" /></td>  
                    </tr>  
                    <tr>  
                        <td><input type="submit" value="Register" /></td>  
                    </tr>  
                </table>
</fieldset>
</form>
</center>
</body>
</html>