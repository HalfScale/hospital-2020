<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:system_page title="Registration">

	<jsp:attribute name="head_imports">
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath}/resources/pages/index/css/style.css" />
	</jsp:attribute>

	<jsp:attribute name="scripts">
		
	</jsp:attribute>

	<jsp:body>
		<div id="index-carousel" class="carousel slide" data-ride="carousel">
		  <div class="carousel-inner">
		    <div class="carousel-item active">
		      <img src="${pageContext.request.contextPath}/resources/pages/index/img/doctor1.png" class="d-block w-100" alt="...">
		    </div>
		    <div class="carousel-item">
		      <img src="${pageContext.request.contextPath}/resources/pages/index/img/doctor2.png" class="d-block w-100" alt="...">
		    </div>
		    <div class="carousel-item">
		      <img src="${pageContext.request.contextPath}/resources/pages/index/img/doctor3.jpg" class="d-block w-100" alt="...">
		    </div>
		  </div>
		  <a class="carousel-control-prev" href="#index-carousel" role="button" data-slide="prev">
		    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
		    <span class="sr-only">Previous</span>
		  </a>
		  <a class="carousel-control-next" href="#index-carousel" role="button" data-slide="next">
		    <span class="carousel-control-next-icon" aria-hidden="true"></span>
		    <span class="sr-only">Next</span>
		  </a>
		</div>
		
		<div class="container">
		  <div class="row">
		    <div class="col-sm">
		      <div class="card" style="width: 18rem;">
				  <img src="..." class="card-img-top" alt="...">
				  <div class="card-body">
				    <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
				  </div>
			 </div>
			</div>
		    <div class="col-sm">
		      <div class="card" style="width: 18rem;">
				  <img src="..." class="card-img-top" alt="...">
				  <div class="card-body">
				    <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
				  </div>
			 </div>
		    </div>
		    <div class="col-sm">
		      <div class="card" style="width: 18rem;">
				  <img src="..." class="card-img-top" alt="...">
				  <div class="card-body">
				    <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
				  </div>
			 </div>
		    </div>
		  </div>
		</div>
	</jsp:body>
</t:system_page>