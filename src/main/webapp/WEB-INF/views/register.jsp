<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>註冊頁面</title>
<!-- bootstrap -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- jQuery -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<!-- VueJS -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/vue/2.6.14/vue.min.js"></script>
</head>
<body>
    <div id="main" class="container-fluid d-flex flex-wrap justify-content-center">
        <div class="row my-3">
			Customer Title: <input type="text" id="customerTitle" value="IoTRestTest" placeholder="請輸入Title" class="my-2">
			User E-mail: <input type="text" id="userEmail" value="t104340502@ntut.org.tw" placeholder="請輸入信箱" class="mb-2">
            <button class="mb-2" @click="regist">註冊</button>
            
		<hr style="color: darkgreen;" size="5px">
		<a href="<c:url value='/login' />" class="text-center">回登入頁面</a>
		
        </div>
    </div>
</body>

<script>

	new Vue({
		el: '#main',
		methods: {
			regist: () => {
				let custTitle = $('#customerTitle').val();
				let userEmail = $('#userEmail').val();
				
				$.ajax({
					url: '/IoTRestTest/customers',
					type: 'POST',
					data: {
						custTitle: custTitle,
						userEmail: userEmail,
					},
					success: () => {
						alert("註冊成功");
					},
					
					error: () => {
						alert("註冊失敗");
					},
					
				});
			},
		},
	});
	
</script>
</html>