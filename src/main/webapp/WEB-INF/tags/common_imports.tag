<%@tag description="head common imports" pageEncoding="UTF-8"%>

<!--Required meta tags-->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!--CSS Links-->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/common/bootstrap-4.3.1-dist/css/bootstrap.min.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/common/datatables/css/datatables.min.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/common/css/util.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/common/css/system.css"/>

<!--JavaScript Source-->
<script src="${pageContext.request.contextPath}/resources/common/jQuery/jquery-3.4.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/common/bootstrap-4.3.1-dist/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/common/datatables/js/datatables.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/common/js/date.format.js"></script>
<script src="${pageContext.request.contextPath}/resources/common/js/util.js"></script>

<script>
(function ($g, $) {
	$g.file_path = '${file_path}';
}(window.$g = window.$g || {}), jQuery);
</script>