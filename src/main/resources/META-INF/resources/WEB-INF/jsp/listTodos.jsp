<%@ include file="common/header.jspf"%>
<body>
	<%@ include file="common/navbar.jspf"%>
<div class="container">
	<h1>Your todo's are</h1>
	<table class="table table-hover">
		<thead>
			<tr>
				<th>Description</th>
				<th>Target Date</th>
				<th>Is Done?</th>
				<th>
				<th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${todos}" var="i">
				<tr>
					<td>${i.description}</td>
					<td>${i.targetDate}</td>
					<td>${i.done}</td>
					<td><a href="update-todo?id=${i.id}" class="btn btn-success">Update</a>
						<a href="delete-todo?id=${i.id}" class="btn btn-warning">Delete</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="add-todo" class="btn btn-success">Add Todo</a>
</div>
<%@ include file="common/footer.jspf"%>