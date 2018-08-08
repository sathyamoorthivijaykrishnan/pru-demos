<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Rates</title>
</head>
<body>
	<c:if test="${not empty values}">
		<h2>Rates</h2>
		<table border="1">
			<tr>
				<th>effDate</th>
				<th>product</th>
				<th>indexName</th>
				<th>cdscOption</th>
				<th>band</th>
				<th>capRate</th>
				<th>mnCapRtCDSC</th>
				<th>mnCapRtPCDSC</th>
				<th>contractYr</th>
				<th>mvaInd</th>
			</tr>
			<c:forEach var="value" items="${values}">
				<tr>
					<td>${value.effDate}</td>
					<td>${value.product}</td>
					<td>${value.indexName}</td>
					<td>${value.cdscOption}</td>
					<td>${value.band}</td>
					<td>${value.capRate}</td>
					<td>${value.mnCapRtCDSC}</td>
					<td>${value.mnCapRtPCDSC}</td>
					<td>${value.contractYr}</td>
					<td>${value.mvaInd}</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>



	
	<a href="/rates">Back to Rates</a>

</body>
</html>