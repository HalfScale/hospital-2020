<%@tag description="head common imports" pageEncoding="UTF-8"%>

<!--Required meta tags-->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!--CSS Links-->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/common/bootstrap-4.3.1-dist/css/bootstrap.min.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/common/datatables/css/datatables.min.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/common/css/util.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/common/css/system.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/common/jQuery-ui/jquery-ui.min.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/common/datetimepicker/jquery.datetimepicker.css"/>

<!--JavaScript Source-->
<script src="${pageContext.request.contextPath}/resources/common/jQuery/jquery-3.4.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/common/jQuery-ui/jquery-ui.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/common/bootstrap-4.3.1-dist/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/common/datatables/js/datatables.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/common/js/date.format.js"></script>
<script src="${pageContext.request.contextPath}/resources/common/datetimepicker/jquery.datetimepicker.full.min.js"></script>
<!--  <script src="${pageContext.request.contextPath}/resources/common/datejs/date.js"></script>
<script src="${pageContext.request.contextPath}/resources/common/datejs/core.js"></script>
<script src="${pageContext.request.contextPath}/resources/common/datejs/parser.js"></script>
<script src="${pageContext.request.contextPath}/resources/common/datejs/sugarpak.js"></script>
<script src="${pageContext.request.contextPath}/resources/common/datejs/time.js"></script>
<script src="${pageContext.request.contextPath}/resources/common/datejs/extras.js"></script> -->
<script src="${pageContext.request.contextPath}/resources/common/js/util.js"></script> 

<script>
(function ($g, $) {
	$g.file_path = '${file_path}';
	$g.root_path = '${pageContext.request.contextPath}';
}(window.$g = window.$g || {}), jQuery);
</script>