<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>${title}</title>
</head>
<body>
<h1>${message}</h1>
<form action="t2.do" method="post">    
     参数名字:<input type="text" name="name">
     参数年龄:<input type="text" name="age">
     <input type="submit" value="submit">
</form>
</body>
</html>