<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<style type="text/css">
#footer {
	margin-top: 7cm;
}

.header {
	background-color: #782669;
}
</style>
<title><tiles:insertAttribute name="title" ignore="true" /></title>
</head>
<body>

	<div class="col-md-12" style="padding: 10px; width: 100%;">
		<tiles:insertAttribute name="body" />
	</div>

</body>
</html>
